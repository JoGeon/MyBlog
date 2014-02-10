package com.appengine.myblog.dao.jpa.support;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
public class JPADaoSupport<T> implements IGenericDAO<T> {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(T entity) {
		em.persist(entity);
	}

	@Override
	public void update(T entity) {
		em.merge(entity);
	}

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

    @Override
	public void delete(Class<T> entityClass, Object... entityids) {
		for (Object id : entityids) {
			em.remove(em.getReference(entityClass, id));
		}
	}

	@Override
	public T find(Class<T> entityClass, Long entityId) {
		em.find(entityClass, entityId);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public List<T> getScrollData(Class<T> entityClass, int firstindex,
			int maxresult, String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		
		List<T> list = new ArrayList<T>();
		String entityname = getEntityName(entityClass);
		
		Query query = em.createQuery("select o from " + entityname + " o "
				+ (wherejpql == null ? "" : "where " + wherejpql)
				+ buildOrderby(orderby));
		
		setQueryParams(query, queryParams);
		
		if (firstindex != -1 && maxresult != -1)
			query.setFirstResult(firstindex).setMaxResults(maxresult);
		
		list.addAll(query.getResultList());
		
		return list;
	}

	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public List<T> getScrollData(Class<T> entityClass, int firstindex,
			int maxresult, String wherejpql, Object[] queryParams) {
		
		return getScrollData(entityClass, firstindex, maxresult, wherejpql, queryParams, null);
	}

	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public List<T> getScrollData(Class<T> entityClass, int firstindex,
			int maxresult, LinkedHashMap<String, String> orderby) {
		
		return getScrollData(entityClass, firstindex, maxresult, null, null, null);
	}

	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public List<T> getScrollData(Class<T> entityClass, int firstindex,
			int maxresult) {
		return getScrollData(entityClass, firstindex,  maxresult);
	}

	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public List<T> getScrollData(Class<T> entityClass) {
		return getScrollData(entityClass, -1,  -1);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public int getCount(Class<T> entityClass) {
		return (Integer)em.createQuery("select count(*) from "+ getEntityName(entityClass)).getSingleResult();
	}

	@Override
	public void clear() {
		em.clear();
	}

	@Override
	public void close() {
		em.close();
	}
	
	/**
	 * 获取实体的名称
	 * 
	 * @param entityClass
	 *            实体类
	 * @return
	 */
	protected  String getEntityName(Class<T> entityClass) {
		String entityname = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		if (entity.name() != null && !"".equals(entity.name())) {
			entityname = entity.name();
		}
		return entityname;
	}
	
	protected void setQueryParams(Query query, Object[] queryParams) {
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i + 1, queryParams[i]);
			}
		}
	}

	/**
	 * 组装order by语句
	 * 
	 * @param orderby
	 * @return
	 */
	protected String buildOrderby(LinkedHashMap<String, String> orderby) {
		StringBuffer orderbyql = new StringBuffer("");
		if (orderby != null && orderby.size() > 0) {
			orderbyql.append(" order by ");
			for (String key : orderby.keySet()) {
				orderbyql.append("o.").append(key).append(" ")
						.append(orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length() - 1);
		}
		return orderbyql.toString();
	}

}
