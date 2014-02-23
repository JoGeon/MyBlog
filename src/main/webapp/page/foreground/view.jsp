<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/page/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/common.css">
<link type="text/css" rel="stylesheet" href="${ctx}/css/foreground.css" />
<script type="text/javascript" src="${ctx}/js/lib/jquery-1.10.2.js"></script>
    <script type="text/javascript">
        var ctx = "${ctx}"
    </script>
<title><s:property value="articleurl"/></title>
</head>
<body>
<div id="container">

        <%@include file="/page/foreground/common/header.jsp"%>

		<div class="wrap clearfix">
			<div class="articlecon">
				<div class="articleleft">
				<!-- begin content -->
					<div class="content">
						<div class="tit clearfix">
							 <h1><a href='${ctx}/blog/<s:property value="#article.articleLink"/>'><s:property value="#article.articleTitle"/></a></h1>
							 <a class="comment right" title="评论"href="${ctx}/blog/<s:property value="#article.articleLink"/>#comments">共<s:property value="#article.comments.size()"/>条评论</a>
						</div>
						<div class="text">
							<div class="infor_tag">
								<a class="tag" rel="tag" href='${ctx}/tag/<s:property value="#article.articleTags"/>'>
									<span><s:property value="#article.articleTags"/></span>
								</a>
							</div>
								<s:property value='#article.articleContent' escapeHtml="false"/>
						</div>
						<div class="info clearfix">
							<div class="right">
								<span class="author"><s:property value="#article.articleAuthor"/></span> 
								<span class="time"><s:date name="#article.articleCreateTime" format="yyyy-MM-dd  HH:mm"/>发表</span>
								<span class="edit"><s:date name="#article.articleUpdateTime" format="yyyy-MM-dd HH:mm"/>更新</span> 
								<span class="views" title="浏览次数"><s:property value="#article.readPeople + 1"/>次浏览</span> 
							</div>
						</div>
					</div>
				<!-- end content -->
				</div>
			</div>
			<%@include file="/page/foreground/common/right.jsp"%>

            <!-- comment -->
            <%@include file="/page/foreground/comment.jsp"%>

			</div>

            <%@include file="/page/foreground/common/footer.jsp"%>
		</div>
</body>
<script type="text/javascript" src="${ctx}/js/admin/foreground.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/comment.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
</html>