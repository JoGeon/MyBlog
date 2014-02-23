<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/commons/taglibs.jsp" %>
<div id="header">
    <div class="titlebanner">
        <div class="waper clearfix">
            <div class="welcomepic">
                <p class="right">Everything will be ok!</p>
            </div>
        </div>
    </div>
    <div class="navbar">
        <div class="waper clearfix">
            <div class="search right">
                <form action="#" id="cse-search-box" method="get">
                    <input id="search-box left" name="q" type="text" placeholder="Search" value="">
                    <button id="search-button">搜索</button>
                </form>
            </div>
            <ul id="nav">
                <li id="home"><a href="${ctx}/home"> 首页</a></li>
                <li id="category"><a href="${ctx}/category">分类</a></li>
                <li id="archive"><a href="${ctx}/archive">归档</a></li>
                <li id="about"><a href="${ctx}/about">关于</a></li>
            </ul>
        </div>
    </div>
</div>