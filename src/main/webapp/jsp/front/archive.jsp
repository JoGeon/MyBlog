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
                <!--年-->
                <s:iterator value="#archive.keySet()" id="keyyear">
                    <li class="type_name clear">
                        <div class="arrow right_arrow left"></div>
                        <a href='${ctx}/archive/<s:property value='#keyyear'/>'>
                            <s:property value="#keyyear"/>年
                        </a>
                    </li>
                    <!--月-->
                    <li class="none">
                        <ul class="article_type_level2">
                            <s:iterator  value ="#archive.get(#keyyear).keySet()" id="keymonth">
                                <li class="type_name clear">
                                    <div class="arrow right_arrow left"></div>
                                    <a href='${ctx}/archive/<s:property value='#keyyear'/>/<s:property value='#keymonth'/>'>
                                        <s:property value="#keymonth"/>月
                                    </a>
                                </li>
                                <li class="none">
                                    <ul class="article_type_level3">
                                        <s:iterator var="mapday" value ="#archive.get(#keyyear).get(#keymonth).keySet()" id="keyday">

                                            <li class="type_name clear">
                                                <div class="arrow right_arrow left"></div>
                                                <a href='${ctx}/archive/<s:property value='#keyyear'/>/<s:property value='#keymonth'/>/<s:property value='#keyday'/>'>
                                                    <s:property value="#keyday"/>日
                                                </a>
                                                    <span class="article_count"><s:property value="#archive.get(#keyyear).get(#keymonth).get(#keyday).size()"/></span>
                                            </li>
                                            <!--循环分类下的文章 class="article_info"-->
                                            <li class="none">
                                                <ul class="article_type_level4">
                                                    <s:iterator var="article" value="#archive.get(#keyyear).get(#keymonth).get(#keyday)">
                                                        <li class="type_content">
                                                            <a href='${ctx}/blog/<s:property value="#article.get(1)"/>'><s:property value="#article.get(0)"/></a>
                                                        </li>
                                                    </s:iterator>
                                                 </ul>
                                             </li>
                                        </s:iterator>
                                    </ul>
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