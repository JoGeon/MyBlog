package com.appengine.myblog.util;

import javax.servlet.ServletContext;

import com.appengine.myblog.domain.Visitor;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.appengine.myblog.action.BlogMainAction;

/**
 *  一些系统初始化数据的加载
* <p>Title: CacheContextBeanPostProcessor.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: NO</p>
* @author ThinkPadT420i
* @date 2013年12月2日
* @version 1.0
 */
public class CacheContextBeanPostProcessor implements BeanPostProcessor {
	
	static Logger logger = Logger.getLogger(CacheContextBeanPostProcessor.class.getName());
	
	@Autowired private ServletContext context = null;
	
	public CacheContextBeanPostProcessor() {
		
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
				try {
					if(bean instanceof BlogMainAction) {
						logger.info("begin execute BlogMainAction");
						BlogMainAction blogMainAction = ((BlogMainAction) bean);
						Visitor visitorInfo = blogMainAction.findMaxVisitor();
						long visitorCount = 0;
						if (visitorInfo != null) {
							visitorCount = visitorInfo.getOnlineCount();
						}
						context.setAttribute("visitorMaxCount", visitorCount);
						context.setAttribute("allVisitCount", 10);
						logger.info("end executeBlogMainAction and visitor:" + visitorCount);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}
	
}
