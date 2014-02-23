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

        <div class="articleCategory">
            <ul class="article_type_level1">
                <s:iterator value="#articleTypes">
                        <li class="type_name clear">
                            <div class="arrow right_arrow left"></div>
                            <a href='${ctx}/category/<s:property value="articleTypeDesName"/>'>
                                  <s:property value="articleTypeName"/>
                            </a>
                            <span class="article_count"><s:property value="article.size()"/></span>
                        </li>
                        <!--循环分类下的文章 class="article_info"-->
                        <li class="none">
                            <ul class="article_type_level2">
                                <s:iterator value="article">
                                        <li class="type_content">
                                            <a href='${ctx}/blog/<s:property value="articleLink"/>'><s:property value="articleTitle"/></a>
                                            <span class="views" title="浏览次数">（访问<s:property value="readPeople"/>次）</span>
                                        </li>
                                </s:iterator>
                            </ul>
                        </li>
                    </s:iterator>
            </ul>
        </div>

        <%@include file="/jsp/front/common/right.jsp"%>

    </div>

    <%@include file="/jsp/front/common/footer.jsp"%>
</div>
</body>
</html>