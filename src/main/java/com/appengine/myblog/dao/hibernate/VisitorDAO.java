package com.appengine.myblog.dao.hibernate;

import com.appengine.myblog.dao.hibernate.support.HibernateDaoSupport;
import com.appengine.myblog.domain.Visitor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Title: VisitorDAO.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: NO</p>
 *
 * @author ThinkPadT420i
 * @version 1.0
 * @date 2013年12月20日
 */
@Repository
public class VisitorDAO extends HibernateDaoSupport<Visitor> {

    public void saveVisitor(Visitor visitInfo) {
        save(visitInfo);
    }

    public void updateVisitor(Visitor visitInfo) {
        update(visitInfo);
    }

    public Visitor findMaxVisitor(String hql) {
        List<Visitor> visitInfo = find(hql);
        return visitInfo.size() == 0 ? null : visitInfo.get(0);
    }
}
