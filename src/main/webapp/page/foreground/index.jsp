<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="/css/common.css">
<link type="text/css" rel="stylesheet" href="/css/foreground.css" />
<script type="text/javascript" src="/js/lib/jquery-1.10.2.js"></script>
<title>Welcome to SunnyZone</title>
</head>
<body>

	<div id="articlecontent">
        <%@include file="/page/foreground/common/header.jsp"%>

		<div class="wrap clearfix">

			<%@include file="/page/foreground/common/articleleft.jsp"%>
			
			<%@include file="/page/foreground/common/articleright.jsp"%>

            <%@include file="/page/foreground/common/page.jsp"%>

		</div>
	</div>
    <%@include file="/page/foreground/common/footer.jsp"%>
</body>
<script type="text/javascript" src="/js/admin/foreground.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
</html>