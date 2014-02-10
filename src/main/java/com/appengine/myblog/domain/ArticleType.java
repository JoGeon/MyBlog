package com.appengine.myblog.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Where;

/**
 * <p>Title: ArticleType.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013-7-15</p>
 * <p>Company: NO</p>
 * @author zhanglei
 * @date 2013-7-15
 * @version 1.0
 */
@Entity
@Table(name="blog_articleType")
public class ArticleType {
	
	private Long articleTypeId;
	
	private String articleTypeName;

    private String articleTypeDesName;

    private String articleTypeDescription;
	
	private Set<Article> article = new HashSet<Article>();
	
	@Transient
	private int articleTypeCount;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="articleTypeId", nullable=false)
	public Long getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(Long articleTypeId) {
		this.articleTypeId = articleTypeId;
	}

	public String getArticleTypeName() {
		return articleTypeName;
	}

	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}

	public String getArticleTypeDescription() {
		return articleTypeDescription;
	}

	public void setArticleTypeDescription(String articleTypeDescription) {
		this.articleTypeDescription = articleTypeDescription;
	}

    public String getArticleTypeDesName() {
        return articleTypeDesName;
    }

    public void setArticleTypeDesName(String articleTypeDesName) {
        this.articleTypeDesName = articleTypeDesName;
    }

	@JSON(serialize = false)
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.MERGE, CascadeType.REMOVE },
                     fetch = FetchType.LAZY,
                      mappedBy = "articleType")
    @Where(clause = "isPublish = 1")
	public Set<Article> getArticle() {
		return article;
	}

	public void setArticle(Set<Article> article) {
		this.article = article;
	}

	public int getArticleTypeCount() {
		return articleTypeCount;
	}

	public void setArticleTypeCount(int articleTypeCount) {
		this.articleTypeCount = articleTypeCount;
	}
	
}

