package com.appengine.myblog.service;

import java.util.List;

import com.appengine.myblog.domain.ArticleType;

/**
 * 
* <p>Title: ArticleTypeServiece.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: NO</p>
* @author ThinkPadT420i
* @date 2013年11月16日
* @version 1.0
 */
public interface ArticleTypeServiec {
	
	public void saveArticleType(ArticleType articleType);
	
	public void updateArticleType(ArticleType articleType);
	
	public void deleteArticleType(Class<ArticleType> ArticleType, Object... ID);
	
	public ArticleType findArticleTypeByID(Long ArticleTypeID);
	
	public ArticleType findArticleTypeByName(String hql, String ArticleTypeName);
	
	public List<ArticleType> findAllArticleType(String hql);
	
	public List<ArticleType> findAllArticleType();
	
	/**
	 *  返回文章类型和数量
	 * @param hql 查询语句
	 * @return
	 */
	public List<ArticleType> findArticleTypeCount(String hql);
}
