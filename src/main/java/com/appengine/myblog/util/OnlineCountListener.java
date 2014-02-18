package com.appengine.myblog.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * <p>Title: OnlineCountListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: NO</p>
 *
 * @author ThinkPadT420i
 * @version 1.0
 * @date 2013年12月20日
 */
public class OnlineCountListener implements HttpSessionListener, ServletContextListener {

    static Logger logger = Logger.getLogger(OnlineCountListener.class.getName());

    private ServletContext context;

    /**
     * 保存访问者的基本信息，并统计访问累计数，当前在线数。
     * 访问数量累计增加，在线数量增加和减少。
     *
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("在线用户增加！");
        long count = 0;
        if (context.getAttribute("onlineCount") != null) {
            count = (Long) context.getAttribute("onlineCount");
        }
        OnlineCountStatistics.setCount(count);
        OnlineCountStatistics.increaseCount();
        logger.info("当前在线用户数量" + OnlineCountStatistics.getCount());
        //保存当前在线信息
        context.setAttribute("onlineCount", OnlineCountStatistics.getCount());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("在线用户减少！");
        OnlineCountStatistics.decreaseCount();
        logger.info("当前在线用户数量" + OnlineCountStatistics.getCount());
        context.setAttribute("onlineCount", OnlineCountStatistics.getCount());
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        context = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
