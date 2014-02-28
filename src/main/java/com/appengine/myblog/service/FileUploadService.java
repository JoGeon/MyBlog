package com.appengine.myblog.service;

import com.appengine.myblog.domain.FileUpload;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 14-2-22</p>
 * <p>Company: NO</p>
 * User: zhanglei
 * Date: 14-2-22
 * Time: 下午2:35
 */
public interface FileUploadService {

    public void saveUploadFile(FileUpload fileUpload);

    public FileUpload findFileByName(String hql, Object... params);

}
