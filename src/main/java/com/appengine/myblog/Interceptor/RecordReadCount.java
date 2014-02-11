package com.appengine.myblog.Interceptor;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.appengine.myblog.domain.Article;
import com.appengine.myblog.service.ArticleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 记录文章访问次数
 * <p>Title: RecordReadCount.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: NO</p>
 *
 * @author ThinkPadT420i
 * @version 1.0
 * @date 2013年12月2日
 */
public class RecordReadCount extends AbstractInterceptor {

    /**
     *
     */
    private static final long serialVersionUID = 1884003414632877196L;
    static Logger logger = Logger.getLogger(RecordReadCount.class.getName());

    @Resource
    ArticleService articleService;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String result = invocation.invoke();
        if (invocation.getProxy().getMethod().equals("viewArticle")) {
            StringBuilder message = new StringBuilder(100);
            long start = System.currentTimeMillis();
            Article article = (Article) ActionContext.getContext().get("article");
            if (article == null) return "error";
            article.setReadPeople(article.getReadPeople() + 1);
            articleService.updateArticle(article);
            long executetime = System.currentTimeMillis() - start;
            message.append("Save article time:").append(executetime).append("ms");
            logger.info(message);
        }
        return result;
    }

}
