package com.appengine.myblog.service.impl;

import com.appengine.myblog.dao.hibernate.VisitorDAO;
import com.appengine.myblog.domain.Visitor;
import com.appengine.myblog.service.VisitorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
* <p>Title: VisitorInfoServiceImpl.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: NO</p>
* @author ThinkPadT420i
* @date 2013年12月20日
* @version 1.0
 */
@Service
public class VisitorInfoServiceImpl implements VisitorInfoService {

	@Autowired VisitorDAO visitorDAO;

	@Override
	public void save(Visitor visitInfo) {
		visitorDAO.saveVisitor(visitInfo);
	}

	@Override
	public void update(Visitor visitInfo) {
		visitorDAO.updateVisitor(visitInfo);
	}

	@Override
	public Visitor findMaxVisitor(String hql) {
		Visitor visitorInfo = visitorDAO.findMaxVisitor(hql);
		return visitorInfo;
	}
}
