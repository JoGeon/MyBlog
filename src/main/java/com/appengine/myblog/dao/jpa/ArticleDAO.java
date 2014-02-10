package com.appengine.myblog.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.appengine.myblog.dao.jpa.support.JPADaoSupport;
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
public class ArticleDAO extends JPADaoSupport<Article> {

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
	public Article findArticleById( Long id) {
		Article article = find(Article.class, id);
		return article;
	}
	
	/**
	 *  查找所有文章
	 * @return 所以文章
	 */
	public List<Article> findAllArticle() {
		 List<Article> list = getScrollData(Article.class);
		return list;
	}
}

