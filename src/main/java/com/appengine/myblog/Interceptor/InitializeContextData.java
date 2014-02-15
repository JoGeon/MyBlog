package com.appengine.myblog.Interceptor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.appengine.myblog.domain.Article;
import com.appengine.myblog.domain.Visitor;
import com.appengine.myblog.util.ServletUtil;
import org.apache.log4j.Logger;

import com.appengine.myblog.action.BlogMainAction;
import com.appengine.myblog.domain.ArticleType;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * 访问Action时，加载文章类型和文章数量
 * <p>Title: SearchArticleTypes.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: NO</p>
 *
 * @author ThinkPadT420i
 * @version 1.0
 * @date 2013年12月2日
 */
public class InitializeContextData extends AbstractInterceptor {

    static Logger logger = Logger.getLogger(InitializeContextData.class.getName());
    /**
     *
     */
    private static final long serialVersionUID = -1526463579567925077L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        BlogMainAction blogMainAction = null;
        if (invocation.getAction() instanceof BlogMainAction) {

            blogMainAction = (BlogMainAction) invocation.getAction();
            //加载文章分类
            //获取所有文章分类的内容
            if (ActionContext.getContext().getSession().get("articleTypeList") == null) {
                List<ArticleType> listArticleType = blogMainAction.findArticleTypeCount();
                ActionContext.getContext().getSession().put("articleTypeList", listArticleType);
            }

            //获取热门文章根据访问数量进行排序，并展示5个文章的标题
            if (ActionContext.getContext().getSession().get("articleList") == null) {
                List<Article> listArticles = blogMainAction.findHotArticles();
                ActionContext.getContext().getSession().put("articleList", listArticles);
            }
        }

        String result = invocation.invoke();

        //增加在线访问人数
        Map<String, Object> session = invocation.getInvocationContext().getSession();

        if (session.get("visitInfo") == null) {
            //设置当前访问者的相关信息并记录总访问量
            HttpServletRequest request = ServletActionContext.getRequest();
            String ip = ServletUtil.getIpAddr(request);

            //增加访问次数
//			int visitCount =(Integer)ActionContext.getContext().get("visitorMaxCount");
            long visitCount = (Long) ServletActionContext.getServletContext().getAttribute("visitorMaxCount");
            visitCount++;
            //设置新的环境变量
            ServletActionContext.getServletContext().setAttribute("visitorMaxCount", visitCount);
            //多少位访客
            Visitor visitor = new Visitor();
            visitor.setOnlineIP(ip);
            visitor.setOnlineCount(visitCount);
            visitor.setOnlienDate(new Date());
            session.put("visitInfo", visitor);
            if(blogMainAction !=null) {
                blogMainAction.saveVisitor(visitor);
            }
            logger.info("访客增加，当前第" + visitCount + "位访客！");
        }

        return result;
    }
}
