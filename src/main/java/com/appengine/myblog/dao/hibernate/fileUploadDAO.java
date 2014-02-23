package com.appengine.myblog.dao.hibernate;

import com.appengine.myblog.dao.hibernate.support.HibernateDaoSupport;
import com.appengine.myblog.domain.FileUpload;
import org.springframework.stereotype.Repository;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 14-2-22</p>
 * <p>Company: NO</p>
 * User: zhanglei
 * Date: 14-2-22
 * Time: 下午2:27
 */
@Repository
public class FileUploadDAO extends HibernateDaoSupport<FileUpload> {

    public void saveFile(FileUpload fileUpload) {
        save(fileUpload);
    }

    public FileUpload findFileByName(String hql, Object... params) {
        return  (FileUpload)find(hql, params);
    }

}
