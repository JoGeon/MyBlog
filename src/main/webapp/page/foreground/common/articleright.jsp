<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="articleright">
  
	<h3>文章分类</h3>
	<div class="category">
		<s:iterator value="#session.articleTypeList">
		  <a href='/category/<s:property value="articleTypeDesName"/>'><s:property value="articleTypeName"/>
		  	<span><s:property value="articleTypeCount"/></span>
		  </a>
	    </s:iterator>
	</div>

	<h3>热门文章</h3>
    <div class="hotarticle">
        <s:iterator value="#session.articleList">
            <div class="article_hot">
                <a href='/blog/<s:property value="articleLink"/>'>
                    <s:property value="articleTitle"/>
                </a>
            </div>
        </s:iterator>
    </div>
</div>