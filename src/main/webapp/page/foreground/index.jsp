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
<title>Welcome to MyBlog</title>
</head>
<body>
	<div id="container">
        <%@include file="/page/foreground/common/header.jsp"%>

		<div class="wrap clearfix">

			<%@include file="/page/foreground/common/content.jsp"%>
			
			<%@include file="/page/foreground/common/right.jsp"%>

            <%@include file="/page/foreground/common/page.jsp"%>

		</div>

        <%@include file="/page/foreground/common/footer.jsp"%>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/admin/foreground.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
</html>