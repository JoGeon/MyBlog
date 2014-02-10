package com.appengine.myblog.dao.hibernate;

import com.appengine.myblog.dao.hibernate.support.HibernateDaoSupport;
import com.appengine.myblog.domain.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 14-1-29</p>
 * <p>Company: NO</p>
 * User: zhanglei
 * Date: 14-1-29
 * Time: 下午5:33
 */
@Repository
public class AuthorDAO extends HibernateDaoSupport<Author> {

    public Author findAuthor(String hql, Object... params) {
        return findByParams(hql, params);
    }

}
