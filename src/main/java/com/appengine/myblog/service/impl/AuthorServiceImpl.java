package com.appengine.myblog.service.impl;

import com.appengine.myblog.dao.hibernate.AuthorDAO;
import com.appengine.myblog.domain.Author;
import com.appengine.myblog.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 14-1-29</p>
 * <p>Company: NO</p>
 * User: zhanglei
 * Date: 14-1-29
 * Time: 下午5:31
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired AuthorDAO authorDAO;

    @Override
    public Author findAuthorByParam(String hql, Object... params) {
        return authorDAO.findAuthor(hql, params);
    }
}
