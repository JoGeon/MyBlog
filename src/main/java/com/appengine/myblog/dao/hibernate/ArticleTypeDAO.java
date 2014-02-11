package com.appengine.myblog.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.appengine.myblog.dao.hibernate.support.HibernateDaoSupport;
import com.appengine.myblog.domain.ArticleType;

/**
 * <p>Title: ArticleTypeDAO.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: NO</p>
 *
 * @author ThinkPadT420i
 * @version 1.0
 * @date 2013年11月16日
 */
@Repository
public class ArticleTypeDAO extends HibernateDaoSupport<ArticleType> {

    /**
     * 保存新的文章类型
     *
     * @param articleType 文章类型
     */
    public void saveArticleType(ArticleType articleType) {
        save(articleType);
    }

    /**
     * 更新新的文章类型
     *
     * @param articleType 文章类型
     */
    public void updateArticleType(ArticleType articleType) {
        update(articleType);
    }

    /**
     * 删除文章类型
     *
     * @param articleType 文章类型
     * @param ID
     */
    public void deleteArticleType(Class<ArticleType> articleType, Object... ID) {
        delete(articleType, ID);
    }

    /**
     * 更新ID来查找文章的分类
     *
     * @param ID 文章分类
     * @return
     */
    public ArticleType findArticleTypeByID(Long ID) {
        return find(ArticleType.class, ID);
    }

    /**
     * 更具文章类别名来查找文章
     *
     * @param hql
     * @param ArticleTypeName
     * @return
     */
    public ArticleType findArticleTypeByName(String hql, String ArticleTypeName) {
        return findByParams(hql, ArticleTypeName);
    }

    /**
     * 查找所以文章类型
     *
     * @param hql 查找的hql语句
     * @return
     */
    public List<ArticleType> findAllArticleType(String hql) {
        return find(hql);
    }

    public List<ArticleType> findAllArticleType() {
        return loadAll(ArticleType.class);
    }

    /**
     * 查找所以文章类型
     *
     * @param hql 查找的hql语句
     * @return
     */
    @SuppressWarnings({"rawtypes"})
    public List<ArticleType> findArticleTypeCount(String hql) {
        return find(hql);
    }

}
