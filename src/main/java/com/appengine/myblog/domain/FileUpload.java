package com.appengine.myblog.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Description:图片，视频，Flash，附件等文件 </p>
 * <p>Copyright: Copyright (c) 14-2-21</p>
 * <p>Company: NO</p>
 * User: zhanglei
 * Date: 14-2-21
 * Time: 下午9:51
 */
@Entity
@Table(name="blog_fileupload")
public class FileUpload {

    private Long fileid;

    private String fileName;

    private byte[] fileBlob;

    private Date uploadTime;

    private String fileDescription;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Lob
    public byte[] getFileBlob() {
        return fileBlob;
    }

    public void setFileBlob(byte[] fileBlob) {
        this.fileBlob = fileBlob;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

}
