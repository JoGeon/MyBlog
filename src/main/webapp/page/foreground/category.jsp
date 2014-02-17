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
    <title>Welcome to MyBlog</title>
</head>
<body>
<%@include file="/page/foreground/common/header.jsp"%>

<div id="articleType">

    <div class="wrap clearfix">

        <div class="articleCategory">
            <ul class="article_type_level1">
                <s:iterator value="#articleTypes">
                        <li class="type_name clear">
                            <div class="arrow right_arrow left"></div>
                            <a href='/category/<s:property value="articleTypeDesName"/>'>
                                  <s:property value="articleTypeName"/>
                            </a>
                            <span class="article_count"><s:property value="article.size()"/></span>
                        </li>
                        <!--循环分类下的文章 class="article_info"-->
                        <li class="none">
                            <ul class="article_type_level2">
                                <s:iterator value="article">
                                        <li class="type_content">
                                            <a href='/blog/<s:property value="articleLink"/>'><s:property value="articleTitle"/></a>
                                            <span class="views" title="浏览次数">（访问<s:property value="readPeople"/>次）</span>
                                        </li>
                                </s:iterator>
                            </ul>
                        </li>
                    </s:iterator>
            </ul>
        </div>

        <%@include file="/page/foreground/common/articleright.jsp"%>

    </div>
</div>

     <%@include file="/page/foreground/common/footer.jsp"%>
</body>
<script type="text/javascript" src="/js/admin/foreground.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
</html>