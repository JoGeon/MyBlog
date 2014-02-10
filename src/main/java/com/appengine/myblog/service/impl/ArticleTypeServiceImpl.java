package com.appengine.myblog.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appengine.myblog.dao.hibernate.ArticleTypeDAO;
import com.appengine.myblog.domain.ArticleType;
import com.appengine.myblog.service.ArticleTypeServiec;
/**
 * 
* <p>Title: ArticleTypeServiceImpl.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: NO</p>
* @author ThinkPadT420i
* @date 2013年11月16日
* @version 1.0
 */
@Service
public class ArticleTypeServiceImpl implements ArticleTypeServiec {

	@Autowired ArticleTypeDAO articleTypeDAO;
	
	@Override
	public void saveArticleType(ArticleType articleType) {
		articleTypeDAO.saveArticleType(articleType);
	}

	@Override
	public void updateArticleType(ArticleType articleType) {
		articleTypeDAO.update(articleType);
	}

	@Override
	public void deleteArticleType(Class<ArticleType> articleType, Object... ID) {
		articleTypeDAO.deleteArticleType(articleType, ID);
	}

	@Override
	public ArticleType findArticleTypeByID(Long ArticleTypeID) {
		return articleTypeDAO.findArticleTypeByID(ArticleTypeID);
	}

	@Override
	public List<ArticleType> findAllArticleType(String hql) {
		return articleTypeDAO.findAllArticleType(hql);
	}

	@Override
	public ArticleType findArticleTypeByName(String hql, String ArticleTypeName) {
		return articleTypeDAO.findArticleTypeByName(hql, ArticleTypeName);
	}

	@Override
	public List<ArticleType> findAllArticleType() {
		return articleTypeDAO.findAllArticleType();
	}

	@Override
	public List<ArticleType> findArticleTypeCount(String hql) {
		List  listarticleTypes =  articleTypeDAO.findArticleTypeCount(hql);
        List<ArticleType> articleTypes = new ArrayList<ArticleType>();
        ArticleType articleType = null;
        for(Iterator it = listarticleTypes.iterator(); it.hasNext(); ) {
            List typeAndCount = (List) it.next();
            articleType = new ArticleType();
            articleType.setArticleTypeDesName((String)typeAndCount.get(0));
            articleType.setArticleTypeName((String)typeAndCount.get(1));
            articleType.setArticleTypeCount(((Long)typeAndCount.get(2)).intValue());
            articleTypes.add(articleType);
        }
		return articleTypes;
	}

}
