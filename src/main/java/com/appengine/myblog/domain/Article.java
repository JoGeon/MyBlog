package com.appengine.myblog.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.apache.struts2.json.annotations.JSON;

/**
 * <p>Title: Article.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013-7-15</p>
 * <p>Company: NO</p>
 *
 * @author zhanglei
 * @version 1.0
 * @date 2013-7-15
 */
@Entity
@Table(name = "blog_article")
public class Article {

    private Long articleid;

    private String articleTitle;

    private String articleLink;

    private String articleAuthor;

    private String articleContent;

    private String articleTags;

    private int isPublish;

    private int readPeople;

    private Date articleCreateTime;

    private Date articlePublishTime;

    private Date articleUpdateTime;

    private ArticleType articleType;

    private Set<Comment> comments = new HashSet<Comment>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getArticleid() {
        return articleid;
    }

    public void setArticleid(Long articleid) {
        this.articleid = articleid;
    }

    @Column(name = "articletitle", nullable = false, length = 225)
    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Column(name = "articleLink", nullable = false, length = 225)
    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }

    @Column(name = "articleAuthor", nullable = true, length = 30)
    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    //@Lob表示大的文本字段
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "articleContent", nullable = false)
    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Column(name = "articleTags", nullable = true)
    public String getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(String articleTags) {
        this.articleTags = articleTags;
    }

    @Column(name = "isPublish", length = 1)
    public int getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(int isPublish) {
        this.isPublish = isPublish;
    }

    @Column(name = "readPeople")
    public int getReadPeople() {
        return readPeople;
    }

    public void setReadPeople(int readPeople) {
        this.readPeople = readPeople;
    }

    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "articleCreateTime", nullable = true)
    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "articlePublishTime", nullable = true)
    public Date getArticlePublishTime() {
        return articlePublishTime;
    }

    public void setArticlePublishTime(Date articlePublishTime) {
        this.articlePublishTime = articlePublishTime;
    }

    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "articleUpdateTime", nullable = true)
    public Date getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(Date articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "articleTypeID")
    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    @JSON(serialize = false)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.LAZY,
            mappedBy = "article")
    @OrderBy("commentID DESC")
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}

