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
<%@include file="/page/foreground/common/header.jsp"%>

<div id="articleType">

    <div class="wrap clearfix">

        <div class="articleCategory">
            <ul class="article_type_level1">
                <s:iterator value="#archive.keySet()" id="key">
                    <li class="type_name clear">
                        <div class="arrow right_arrow left"></div>
                        <a href='/archive/<s:property value='#key.substring(0,7).replaceAll("[\u4e00-\u9fa5]", "-")'/>'>
                            <s:property value="#key"/>
                        </a>
                        <span class="article_count"><s:property value="#archive.get(#key).size()"/></span>
                    </li>
                    <!--循环分类下的文章 class="article_info"-->
                    <li class="none">
                        <ul class="article_type_level2">
                            <s:iterator var="article" value="#archive.get(#key)">
                                <li class="type_content">
                                    <span><s:date name="#article.get(2)" format="dd日"/>：</span>
                                    <a href='/blog/<s:property value="#article.get(1)"/>'><s:property value="#article.get(0)"/></a>
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

<s:debug/>
</body>
<script type="text/javascript" src="/js/admin/foreground.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
</html>