package com.appengine.myblog.domain;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Date;
/**
 * 
* <p>Title: Comment.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: NO</p>
* @author ThinkPadT420i
* @date 2013年12月20日
* @version 1.0
 */
@Entity
@Table(name="blog_comment")
public class Comment {

    /**评论ID*/
    private Long commentID;

    /**评论名称*/
    private String commentName;

    /**评论邮箱*/
    private String commentEmial;

    /**评论网址*/
    private String commentURL;

    /**评论内容*/
    private String commnetContent;

    /**评论级数*/
    private int commentLevel;

    /**评论时间*/
    private Date commentTime;

    /**评论所属的文章，为多个评论对一个文章*/
    private Article article;


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    @JSON(serialize = false)
    public String getCommentEmial() {
        return commentEmial;
    }

    public void setCommentEmial(String commentEmial) {
        this.commentEmial = commentEmial;
    }

    @JSON(serialize = false)
    public String getCommentURL() {
        return commentURL;
    }

    public void setCommentURL(String commentURL) {
        this.commentURL = commentURL;
    }

    public String getCommnetContent() {
        return commnetContent;
    }

    public void setCommnetContent(String commnetContent) {
        this.commnetContent = commnetContent;
    }

    public int getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(int commentLevel) {
        this.commentLevel = commentLevel;
    }

    @JSON(format="yyyy-MM-dd HH:mm:ss")
    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    @JSON(serialize = false)
    @ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER,optional=false)
    @JoinColumn(name="articleID")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
