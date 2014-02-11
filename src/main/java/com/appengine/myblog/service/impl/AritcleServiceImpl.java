package com.appengine.myblog.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appengine.myblog.dao.hibernate.ArticleDAO;
import com.appengine.myblog.domain.Article;
import com.appengine.myblog.service.ArticleService;

/**
 * <p>Title: AritcleServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013-7-15</p>
 * <p>Company: NO</p>
 *
 * @author zhanglei
 * @version 1.0
 * @date 2013-7-15
 */
@Service
public class AritcleServiceImpl implements ArticleService {

    @Autowired
    ArticleDAO articleDAO;

    @Override
    public void saveArticle(Article article) {
        articleDAO.saveArticle(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleDAO.updateArticle(article);
    }

    @Override
    public List<Article> findArticleByPage(String hql, int offset, int pageSize) {
        return articleDAO.findArticleByPage(hql, offset, pageSize);
    }

    @Override
    public Article findArticleByLink(String hql, String articleLink) {
        return articleDAO.findArticleByLink(hql, articleLink);
    }

    @Override
    public Article findArticleByID(Long id) {
        return articleDAO.findArticleByID(id);
    }

    /**
     * 查找最热门文章
     *
     * @param hql
     * @param pageSize
     * @return
     */
    @Override
    public List<Article> findhotArticle(String hql, int pageSize) {
        List pageArticle = articleDAO.findArticleByPage(hql, 0, pageSize);
        Article article = null;
        List<Article> articles = new ArrayList<Article>();
        for (Iterator it = pageArticle.iterator(); it.hasNext(); ) {
            List hotArticle = (List) it.next();
            article = new Article();
            article.setArticleLink((String) hotArticle.get(0));
            article.setArticleTitle((String) hotArticle.get(1));
            articles.add(article);
        }
        return articles;
    }

    @Override
    public List<Article> findAllArticle(String hql) {
        return articleDAO.findAllArticle(hql);
    }

    @Override
    public int getArticleCount(String hql, String... params) {
        return articleDAO.getArticleCount(hql, params);
    }

}

