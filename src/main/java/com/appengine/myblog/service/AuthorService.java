package com.appengine.myblog.service;

import com.appengine.myblog.domain.Author;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 14-1-29</p>
 * <p>Company: NO</p>
 * User: zhanglei
 * Date: 14-1-29
 * Time: 下午5:28
 */
public interface AuthorService {

    public Author findAuthor(String authorname, String password);

    public Author findAuthorByParam(String hql, Object... params);


}
