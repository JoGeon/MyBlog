package com.appengine.myblog.action.domainaction;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.appengine.myblog.domain.ArticleType;
import com.appengine.myblog.service.ArticleTypeServiec;

/**
 * 
* <p>Title: ArticleTypeAction.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: NO</p>
* @author ThinkPadT420i
* @date 2013年11月16日
* @version 1.0
 */
@Controller @Scope("prototype")
public class ArticleTypeAction {
	
	private ArticleType articleType;
	
	private String resultMessage;
	
	@Resource ArticleTypeServiec articleTypeService;
	
	public String saveArticleType() {
		articleTypeService.saveArticleType(articleType);
		resultMessage = "保存成功！";
		return "success";
	}

	public ArticleType getArticleType() {
		return articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
}
