package com.appengine.myblog.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.appengine.myblog.dao.hibernate.support.HibernateDaoSupport;
import com.appengine.myblog.domain.Article;


/**
 * <p>Title: ArticleDAO.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013-7-15</p>
 * <p>Company: NO</p>
 * @author zhanglei
 * @date 2013-7-15
 * @version 1.0
 */
@Repository
public class ArticleDAO extends HibernateDaoSupport<Article> {

	/**
	 * 保存新的文章
	 * @param article 文章
	 */
	public void saveArticle(Article article) {
		save(article);
	}
	
	/**
	 * 更新文章，不设置文章删除。
	 * @param article 文章
	 */
	public void updateArticle(Article article) {
		update(article);
	}
	
	/**
	 * 查找文章
	 * @param id 文章编号
	 * @return 文章
	 */
	public Article findArticleByID(Long id) {
        return find(Article.class,id);
	}
	
	
	/**
	 * 
	 * @param hql
	 * @param articleLink 文章链接
	 * @return 返回一个Article实体
	 */
	public Article findArticleByLink(String hql, String articleLink) {
        return findByParams(hql, articleLink);
	}
	
	/**
	 * 分页查找文章
	 *
     * @param hql
     * @param offset 页数
     * @param pageSize 文章数
     * @return
	 */
	public List findArticleByPage(String hql, int offset, int pageSize) {
        return findByPage(hql, offset, pageSize);
	}


    public List<Article> findAllArticle(String hql) {
        return find(hql);
    }

    public int getArticleCount(String hql, String... params) {
        return getCount(hql, params);
    }


}

