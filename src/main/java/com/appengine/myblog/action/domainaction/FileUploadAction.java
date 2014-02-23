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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    private String dir;

    private String localUrl;

    private JSONObject msg = new JSONObject();

    @Autowired
    FileUploadService fileUploadService;

    public String saveFileUpload() {
        FileUpload fileUpload = new FileUpload();

        String fileName = gainFileName();
        String savePath = gainSavePath();
        String ext = gainExt();
        ServletActionContext.getResponse().setContentType("text/html");
        ServletActionContext.getResponse().setHeader("cache-control", "no-cache");
        if (!validateExt(ext)) {
            getError("上传文件类型不允许");
            return BlogConstant.SUCCESS;
        }
        if (imgFile.length() >1024 * 1024 * 1024 ) {
            getError("上传文件过大");
            return BlogConstant.SUCCESS;
        }
        File saveDir = new File(savePath);

        if (!saveDir.exists()) {
            getError("上传路径不存在");
            return BlogConstant.SUCCESS;
        } else {
            if (!saveDir.canWrite()) {
                getError("上传路径没有写权限");
                return BlogConstant.SUCCESS;
            }
        }
        try {
            File target = new File(saveDir, fileName);
            FileUtils.copyFile(imgFile, target);

//            fileUploadService.saveImage(fileUpload);
        } catch (Exception e) {
            getError("上传失败");
            return BlogConstant.SUCCESS;
        }
        savePath = ServletActionContext.getRequest().getContextPath() + "/upload/articleimage/";
        msg.put("error", 0);
        //上传成功返回文件url地址 。
        msg.put("url", savePath + fileName);

        return BlogConstant.SUCCESS;

    }


    /**
     * 获取图片储存路径 根目录+ upload/articleImg
     *
     * @return
     */
    private String gainSavePath() {

        return ServletActionContext.getServletContext().getRealPath("/")
                + "\\upload\\articleimage\\"; // 服务器上面的真实路径

    }

    /**
     * 获取图片储存名称
     *
     * @return
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
     * @return
     */
    private String gainExt() {
        return imgFileFileName.substring(imgFileFileName.lastIndexOf(".") + 1)
                .toLowerCase();
    }

    private boolean validateExt(String ext) {
        // 定义可上传文件的 类型
        List<String> fileTypes = new ArrayList<String>();
        // 指定上传类型
        fileTypes.add("jpg");
        fileTypes.add("jpeg");
        fileTypes.add("bmp");
        fileTypes.add("gif");
        fileTypes.add("png");
        return fileTypes.contains(ext) ? true : false;
    }

    /**
     * 返回Json
     *
     * @param message
     * @return
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
