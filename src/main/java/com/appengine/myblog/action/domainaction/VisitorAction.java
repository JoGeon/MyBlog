package com.appengine.myblog.action.domainaction;

import com.appengine.myblog.domain.Visitor;
import com.appengine.myblog.service.VisitorInfoService;

import javax.annotation.Resource;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 14-2-15</p>
 * <p>Company: NO</p>
 * User: zhanglei
 * Date: 14-2-15
 * Time: 下午5:49
 */
public class VisitorAction {

    @Resource
    VisitorInfoService visitInfoService;


    public void saveVisitor(Visitor visitInfo) {
        if (visitInfo == null) return;
        visitInfoService.save(visitInfo);
    }

}
