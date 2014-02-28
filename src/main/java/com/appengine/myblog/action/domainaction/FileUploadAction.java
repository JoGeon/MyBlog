package com.appengine.myblog.action.domainaction;

import com.appengine.myblog.domain.FileUpload;
import com.appengine.myblog.service.FileUploadService;
import com.appengine.myblog.util.BlogConstant;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 14-2-22</p>
 * <p>Company: NO</p>
 * User: zhanglei
 * Date: 14-2-22
 * Time: 下午2:42
 */
@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class FileUploadAction {

    private File imgFile;

    private String imgFileFileName;

    private String imgFileContentType;

    //图片，视频，Flash等路径
    private String dir;

    private String localUrl;

    private JSONObject msg = new JSONObject();

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    BlogConstant blogConstant;

    public String saveFileUpload() {

        String fileName = gainFileName();
        String savePath = gainSavePath();
        String ext = gainExt();
        ServletActionContext.getResponse().setHeader("cache-control", "no-cache");
        if (!validateExt(ext)) {
            getError("上传文件类型不允许");
            return BlogConstant.SUCCESS;
        }
        if (imgFile.length() >1000000 ) {
            getError("上传文件过大");
            return BlogConstant.SUCCESS;
        }

        File saveDir = new File(savePath);

        if (blogConstant.isSaveFlieTODB()) {
            //不支持本地系统写操作保存文件到数据库中
            saveFiletoDataBase(imgFile, fileName);

            savePath = ServletActionContext.getRequest().getContextPath() + "/fileupload/" +dir + "/";

            msg.put("error", 0);
            //上传成功返回文件url地址 。
            msg.put("url",  savePath + fileName);

            return BlogConstant.SUCCESS;
        }
        if (!saveDir.exists())  saveDir.mkdir();
        try {
        //支持本地系统直接保存到本地
        File target = new File(saveDir, fileName);
        FileUtils.copyFile(imgFile, target);

        } catch (Exception e) {
            getError("上传失败");
            return BlogConstant.SUCCESS;
        }
        savePath = ServletActionContext.getRequest().getContextPath() + "/upload/" +dir + "/";
        msg.put("error", 0);
        //上传成功返回文件url地址 。
        msg.put("url", savePath + fileName);

        return BlogConstant.SUCCESS;

    }

    private void saveFiletoDataBase(File imgFile, String fileName) {

        if(!imgFile.exists()) return;

        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(fileName);
        fileUpload.setFileDescription(getDir());
        fileUpload.setUploadTime(new Date());


        ByteArrayOutputStream bos = new ByteArrayOutputStream((int)imgFile.length());
        BufferedInputStream in = null;
        try{
            in = new BufferedInputStream(new FileInputStream(imgFile));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len;
            while(-1 != (len = in.read(buffer,0,buf_size))){
                bos.write(buffer,0,len);
            }
            fileUpload.setFileBlob(bos.toByteArray());
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                if(in != null) {
                    in.close();
                }
                bos.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileUploadService.saveUploadFile(fileUpload);
    }


    /**
     * 获取图片储存路径 根目录+ upload/articleImg
     *
     * @return path
     */
    private String gainSavePath() {

        return ServletActionContext.getServletContext().getRealPath("/")
                + "/upload/" + dir + "/"; // 服务器上面的真实路径

    }

    /**
     * 获取图片储存名称
     *
     * @return fileName
     */
    private String gainFileName() {
        String fileName = "";
        fileName += new SimpleDateFormat("yyyyMMdd").format(new Date()); // 以yyyyMMdd的方式生成时间
        fileName += new Random().nextInt(10000); // 时间文件名称生成随机数
        fileName += "." + gainExt(); // 连接上后缀名
        return fileName;
    }

    /**
     * 获取文件的后缀名 转小写
     *
     * @return ext
     */
    private String gainExt() {
        return imgFileFileName.substring(imgFileFileName.lastIndexOf(".") + 1)
                .toLowerCase();
    }

    private boolean validateExt(String fileExt) {

        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

        return Arrays.<String>asList(extMap.get(dir).split(",")).contains(fileExt);
    }

    /**
     * 返回Json
     *
     * @param message
     */
    //这里封装好json数据error表示错误，message 表示错误信息。
    private void getError(String message) {
        msg.put("error", 1);
        msg.put("message", message);
    }

    public String getImgFileContentType() {
        return imgFileContentType;
    }

    public void setImgFileContentType(String imgFileContentType) {
        this.imgFileContentType = imgFileContentType;
    }

    public File getImgFile() {
        return imgFile;
    }

    public void setImgFile(File imgFile) {
        this.imgFile = imgFile;
    }

    public String getImgFileFileName() {
        return imgFileFileName;
    }

    public void setImgFileFileName(String imgFileFileName) {
        this.imgFileFileName = imgFileFileName;
    }


    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public JSONObject getMsg() {
        return msg;
    }

    public void setMsg(JSONObject msg) {
        this.msg = msg;
    }
}
