package com.appengine.myblog.dao.hibernate.support;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.appengine.myblog.dao.basedao.IGenericDAO;

/**
 * <p>
 * Title: GenericDAOImple.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013-7-15
 * </p>
 * <p>
 * Company: NO
 * </p>
 * 
 * @author zhanglei
 * @date 2013-7-15
 * @version 1.0
 */
@Transactional
public class HibernateDaoSupport<T> implements IGenericDAO<T> {

	@Autowired
	private HibernateTemplate hibernateTemplate;
    private String hql;
    private String[] params;

    /**
	 * 通过反射获取子类确定的泛型类
	 */
	public HibernateDaoSupport() {
//		Type genType = getClass().getGenericSuperclass();
//		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
//		entityClass = (Class<T>) params[0];
	}

	/**
	 * 根据ID加载PO实例
	 * 
	 * @return 返回相应的持久化PO实例
	 */
	@Override
	public T find(Class<T> entityClass, Long entityId) {
		return getHibernateTemplate().load(entityClass,
				 entityId);
	}


    /**
	 * 根据ID获取PO实例
	 * 
	 * @return 返回相应的持久化PO实例
	 */
	public T get(Class<T> entityClass, Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 获取PO的所有对象
	 * 
	 * @return list
	 */
	public List<T> loadAll(Class<T> entityClass) {
		return getHibernateTemplate().loadAll( entityClass);
	}

	/**
	 * 保存PO
	 * 
	 */
	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	/**
	 * 删除一个或多个实体对象
	 * 
	 */

	@Override
	public void delete(Class<T> entityClass, Object... entityids) {
		for (Object id : entityids) {
			getHibernateTemplate().delete(
					getHibernateTemplate().get(entityClass, (Serializable) id));
		}

	}

    /**
     *
     * @param entity
     */
	@Override
	public void update(T entity) {
		getHibernateTemplate().merge(entity);
	}

    /**
     *
     * @param entity
     */
    @Override
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }


    /**
	 * 执行HQL查询
	 * 
	 * @param hql
	 * @return 查询结果
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql) {
		return getHibernateTemplate().find(hql);
	}

	/**
	 * 执行带参的HQL查询
	 * 
	 * @param hql
	 * @param params
	 * @return 查询结果
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object... params) {
		return this.getHibernateTemplate().find(hql, params);
	}
	
	/**
	 * 执行带参的HQL查询,查询一条记录
	 * 
	 * @param hql
	 * @param params
	 * @return 查询结果
	 */
	@SuppressWarnings("unchecked")
	public T findByParams(String hql, Object... params) {
		List<T> list = (List<T>)getHibernateTemplate().find(hql, params);
		return list.size()>0 ? list.get(0) : null;
	}
	

	/**
	 * 对延迟加载的实体PO执行初始化
	 * 
	 * @param entity
	 */
	public void initialize(Object entity) {
		this.getHibernateTemplate().initialize(entity);
	}

	/**
	 * 创建Query对象.
	 * 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 * 留意可以连续设置,如下：
	 * 
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 
	 * 调用方式如下：
	 * 
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 * 
	 * @param values
	 *            可变参数.
	 */
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}
	
	/**
	 * 使用hql语句进行分页查询
	 *
     * @param hql 需要查询的hql语句
     * @param offset 第一条记录的索引
     * @param pageSize 每页需要显示的记录数
     * @return 当前页面所有的记录数
	 */
    @SuppressWarnings("unchecked")
	public List<T> findByPage(final String hql, final int offset, final int pageSize) {
        return getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {

            @Override
            public List<T> doInHibernate(Session session) throws HibernateException,
                    SQLException {
                return session.createQuery(hql)
                        .setFirstResult(offset)
                        .setMaxResults(pageSize)
                        .list();
            }

        });
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Session getSession() {
		return SessionFactoryUtils.getSession(
				hibernateTemplate.getSessionFactory(), true);
	}

    @Override
    public int getCount(Class<T> entityClass) {
        return 0;
    }

	public int getCount(String hql, String... params) {
        List count = getHibernateTemplate().find(hql, (Object[])params);
		return count.size() > 0 ? ((Long)count.get(0)).intValue() : 0;
	}

	@Override
	public void clear() {
		
	}

	@Override
	public void close() {
		
	}

}
