package com.appengine.myblog.action.domainaction;

import com.appengine.myblog.domain.Article;
import com.appengine.myblog.domain.Comment;
import com.appengine.myblog.service.ArticleService;
import com.appengine.myblog.service.CommentService;
import com.appengine.myblog.util.BlogConstant;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: ThinkPadT420i
 * Date: 13-12-17
 * Time: 下午9:56
 */
@Controller
@Scope("prototype")
public class CommentAction {

    private Comment comment;

    private long articleID;

    private String resultMessage;

    @Resource
    CommentService commentService;
    @Resource
    ArticleService articleService;

    public String saveComment() {
        if (validationComment(comment)) {
            Article article = articleService.findArticleByID(articleID);
            comment.setCommentTime(new Date());
            comment.setArticle(article);
            //找到当前评论的最大评论层级
            String hql = "select max(c.commentLevel) from Comment c where c.article.articleid = '" + articleID + "'";
            int level = commentService.findCommentLevle(hql);
            comment.setCommentLevel((level + 1));
            commentService.saveComment(comment);
            resultMessage = BlogConstant.COMMENTSUCESS;
        }
        return BlogConstant.SUCCESS;
    }


    private boolean validationComment(Comment comment) {
        String commentName = comment.getCommentName();
        String commentEmail = comment.getCommentEmial();
        String commentURL = comment.getCommentURL();
        StringBuffer sb = new StringBuffer();

        //判断前台发过来的信息是否正确
        //评论名字只能由数字和字母组成下划线
        //邮箱和URL需要通过正则判断

        if (!commentName.matches("([a-zA-Z0-9_\\u4e00-\\u9fa5])*")) {
            sb.append("昵称必须是数字、字母或汉字组成！");
        } else if (!commentEmail.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
            sb.append("Email格式不匹配！");
        } else if (!commentURL.matches("[a-zA-z]+://[^\\s]*")) {
            sb.append("URL格式不匹配！");
        } else {
            return true;
        }
        setResultMessage(sb.toString());
        return false;
    }


    @JSON(serialize = false)
    public long getArticleID() {
        return articleID;
    }

    public void setArticleID(long articleID) {
        this.articleID = articleID;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
