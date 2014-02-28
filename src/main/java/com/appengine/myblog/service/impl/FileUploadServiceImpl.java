package com.appengine.myblog.service.impl;

import com.appengine.myblog.dao.hibernate.FileUploadDAO;
import com.appengine.myblog.domain.FileUpload;
import com.appengine.myblog.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 14-2-22</p>
 * <p>Company: NO</p>
 * User: zhanglei
 * Date: 14-2-22
 * Time: 下午2:38
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    FileUploadDAO fileUploadDAO;

    @Override
    public void saveUploadFile(FileUpload fileUpload) {
        fileUploadDAO.saveFile(fileUpload);
    }

    @Override
    public FileUpload findFileByName(String hql, Object... params) {
        return fileUploadDAO.findFileByName(hql, params);
    }
}
