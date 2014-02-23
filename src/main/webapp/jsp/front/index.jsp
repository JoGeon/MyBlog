<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/jsp/commons/static.jsp"%>
<title>Welcome to MyBlog</title>
</head>
<body>
	<div id="container">
        <%@include file="/jsp/front/common/header.jsp"%>

		<div class="wrap clearfix">

			<%@include file="/jsp/front/common/content.jsp"%>
			
			<%@include file="/jsp/front/common/right.jsp"%>

            <%@include file="/jsp/front/common/page.jsp"%>
		</div>

        <%@include file="/jsp/front/common/footer.jsp"%>
	</div>
</body>
</html>