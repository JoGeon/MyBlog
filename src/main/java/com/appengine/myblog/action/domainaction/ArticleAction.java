package com.appengine.myblog.action.domainaction;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.appengine.myblog.domain.Article;
import com.appengine.myblog.domain.ArticleType;
import com.appengine.myblog.service.ArticleService;
import com.appengine.myblog.service.ArticleTypeServiec;


/**
 * <p>Title: ArticleAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013-7-16</p>
 * <p>Company: NO</p>
 * @author zhanglei
 * @date 2013-7-16
 * @version 1.0
 */
@Controller @Scope("prototype")
public class ArticleAction {
	
	private Article article;
	
	private int articleTypeID;
	
	private String resultMessage;
	
	private int  articleID = 0;
	
	@Resource ArticleService articleService;
	@Resource ArticleTypeServiec articleTypeServiec;
	
	public String managerArticle() {
		return "success";
	}

	/**
	 * 创建文章，并进行保存,保存在草稿箱内
	 * @return 
	 */
	public String createArticle() {
		article.setArticleCreateTime(new Date());
		article.setArticleUpdateTime(new Date());
		
		ArticleType articleType = articleTypeServiec.findArticleTypeByID((long) articleTypeID);
		//设置ArticleType
		
		article.setArticleType(articleType);
		
		articleService.saveArticle(article);
		
		//保存后返回该文章的ID编号
		articleID = article.getArticleid().intValue();
		resultMessage = "保存成功！";
		
		//增加文章类型的数量
//		int articleTypeID = article.getArticleTypeID();
		
		return "success";
	}
	
	/**
	 * 发布文章
	 * @return 
	 */
	public String publishArticle() {
		
		//先设置发布文章
		article.setIsPublish(1);
		Article publishArticle = saveArticleData();
		
		//保存后返回该文章的ID编号
		articleID = publishArticle.getArticleid().intValue();
		resultMessage = "发布成功！";
		return "success";
	}
	
	/**
	 *  考虑更换文章类型的处理逻辑。
	 * @return 返回视图
	 */
	public String updateArticle() {
		
		Article updateArticle = saveArticleData();
		
		//保存后返回该文章的ID编号
		articleID = updateArticle.getArticleid().intValue();
		resultMessage = "更新成功！";
		return "success";
	}
	
	public Article saveArticleData() {
		
		articleID = article.getArticleid().intValue();
		Article createdArticle = articleService.findArticleByID((long) articleID);
		
		//如果更改类别
		if(createdArticle.getArticleType().getArticleTypeId() != articleTypeID) {
			ArticleType articleType = articleTypeServiec.findArticleTypeByID((long) articleTypeID);
			createdArticle.setArticleType(articleType);
		}
		
		//设置更新的内容
		createdArticle.setArticleContent(article.getArticleContent());
		createdArticle.setArticleLink(article.getArticleLink());
		createdArticle.setArticleTitle(article.getArticleTitle());
		createdArticle.setArticleTags(article.getArticleTags());
		
		//设置文章发布
		if(createdArticle.getIsPublish() == 1 || article.getIsPublish() == 1) {
			createdArticle.setIsPublish(1); //1文章已发布
		}
		
		createdArticle.setArticleCreateTime(createdArticle.getArticleCreateTime());
		createdArticle.setArticlePublishTime(new Date());
		createdArticle.setArticleUpdateTime(new Date());
		
		
		articleService.updateArticle(createdArticle);
		
		return createdArticle;
	}
	
	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}


	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public int getArticleID() {
		return articleID;
	}

	public void setArticleID(int articleID) {
		this.articleID = articleID;
	}

	@JSON(serialize = false)
	public int getArticleTypeID() {
		return articleTypeID;
	}

	public void setArticleTypeID(int articleTypeID) {
		this.articleTypeID = articleTypeID;
	}

}

