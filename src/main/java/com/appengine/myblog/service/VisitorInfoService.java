package com.appengine.myblog.service;

import com.appengine.myblog.domain.Visitor;

/**
 * 
* <p>Title: VisitorInfoService.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: NO</p>
* @author ThinkPadT420i
* @date 2013年12月20日
* @version 1.0
 */
public interface VisitorInfoService {

	/**
	 *  保存访问者信息
	 * @param visitInfo
	 */
	public void save(Visitor visitInfo);

	/**
	 * 更新访问者信息
	 * @param visitInfo
	 */
	public void update(Visitor visitInfo);

	/**
	 * 查找最大访客号
	 * @param hql
	 * @return
	 */
	public Visitor findMaxVisitor(String hql);
}
