package com.appengine.myblog.service;

import java.util.List;

import com.appengine.myblog.domain.Article;

/**
 * <p>Title: ArticleService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013-7-15</p>
 * <p>Company: NO</p>
 *
 * @author zhanglei
 * @version 1.0
 * @date 2013-7-15
 */
public interface ArticleService {

    public void saveArticle(Article article);

    public void updateArticle(Article article);

    /**
     * 通过分页来查找数据
     *
     * @param hql
     * @param offset
     * @param pageSize
     * @return
     */
    public List<Article> findArticleByPage(String hql, int offset, int pageSize);

    public Article findArticleByLink(String hql, String articleLink);

    public Article findArticleByID(Long id);

    public List<Article> findhotArticle(String hql, int pageSize);

    public List<Article> findAllArticle(String hql);


    public int getArticleCount(String hql, String... params);

}

