<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
<link type="text/css" rel="stylesheet" href="${ctx}/css/common.css">
<link type="text/css" rel="stylesheet" href="${ctx}/css/backend.css" />
<script type="text/javascript" src="${ctx}/js/lib/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="${ctx}/js/lib/kindeditor/kindeditor-min.js"></script>
    <script type="text/javascript">
       var ctx = "${ctx}"
    </script>
</head>
<body>
	<div id="header">
		<div id="loading" style="display: none;">载入中.......</div>
		<div id="msg"></div>
	</div>
	
	<div id="allPanel">
		<div id="topPanel">
			<div class="top_left">欢迎登录blog后台管理</div>
			<div class="top_right">
				<a href="admin" title="admin">admin</a> 
				<a href="${ctx}/home" title="首页">首页</a>
				<a href="javascript:logout();" title="登出">登出</a>
			</div>
		</div>
		
		<div id="adminmenu" >
			<ul>
				<li>
					<div id="menu_main">
						<a href="#homepage" class="tab-current">后台首页 </a>
					</div>
				</li>
				<li>
					<div id="menu_article">
						<a href="#article">文章</a>
					</div>
					<ul class="menu_content">
						<li>
							<div id="menu_article-list">
								<a href="#article-list">所有文章</a>
							</div>
						</li>
						<li>
							<div id="menu_publish-article">
								<a href="#publish-article">发布文章</a>
							</div>
						</li>
						<li>
							<div id="menu_draft-list">
								<a href="#article-draft">草稿箱</a>
							</div>
						</li>
					</ul>
				</li>
				<li>
					<div id="menu_comment-list">
						<a href="#comment">评论</a>
					</div>
				</li>
				<li>
					<div id="menu_about">
						<a href="#about">关于</a>
					</div>
				</li>
			</ul>
		</div>

		<div id="Panel" >
			<div id="Panel_main" class="none">
				<div class="id">MyBlogPage1</div>
			</div>
			<div id="Panel_article-list" class="none"></div>
			<div id="Panel_publish-article" class="none"></div>
			<div id="Panel_draft-list" class="none">
				<div class="id">MyBlogPage4</div>
			</div>
			<div id="Panel_comment-list" class="none">
				<div class="id">MyBlogPage5</div>
			</div>
			<div id="Panel_about" class="none">
				<div class="id">MyBlogPage6</div>
			</div>
		</div>

		<div class="footer">
			<span>Prower by Sunny</span>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/admin/admin.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/articleList.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/publishArticle.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/editor.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
</html>