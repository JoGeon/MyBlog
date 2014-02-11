package com.appengine.myblog.dao.basedao;

/**
 * <p>
 * Title: GenericDAO.java
 * </p>
 * <p>
 * Description: 定义泛型DAO接口规范
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013-7-15
 * </p>
 * <p>
 * Company: NO
 * </p>
 *
 * @author zhanglei
 * @version 1.0
 * @date 2013-7-15
 */
public interface IGenericDAO<T> {

    /**
     * 保存实体
     *
     * @param entity 实体id
     */
    public void save(T entity);

    /**
     * 更新实体
     * 更新实体,可以解决在同一个session不能保存对象的问题。使用merge
     *
     * @param entity 实体id
     */
    public void update(T entity);


    /**
     * 删除实体
     *
     * @param T      实体类
     * @param entity 实体id
     */

    public void delete(T entity);

    /**
     * 删除实体
     *
     * @param entityClass 实体类
     * @param entityids   实体id
     */

    public void delete(Class<T> entityClass, Object... entityids);

    /**
     * 获取实体
     *
     * @param
     * @param entityClass 实体类
     * @param entityId    实体id
     * @return
     */
    public T find(Class<T> entityClass, Long entityId);

    /**
     * 获取记录总数
     *
     * @param entityClass 实体类
     * @return
     */
    public int getCount(Class<T> entityClass);

    /**
     * 清除一级缓存的数据
     */
    public void clear();

    /**
     * 关闭
     */
    public void close();

}
