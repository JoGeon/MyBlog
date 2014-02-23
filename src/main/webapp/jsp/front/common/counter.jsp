<%@ page language="java" contentType="text/html; charset=UTF-8"
               pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id ="counter">
    <div class="onlineCount">
        在线：<span><s:property value="#application.onlineCount"/></span>
    </div>
    <div class="visitorCount">
        访问：<span><s:property value="#application.visitorMaxCount" /></span>
    </div>
</div>