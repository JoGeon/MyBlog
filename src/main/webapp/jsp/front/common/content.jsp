<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/commons/taglibs.jsp"%>
<div class="articleleft">
<!-- begin content -->
    <s:iterator value="#articleCollection">
        <div class="content">
            <div class="tit clearfix">
                 <a href='${ctx}/blog/<s:property value="articleLink"/>'><s:property value="articleTitle"/></a>
            </div>
            <div class="text">
                <div class="infor_tag">
                    <a class="tag" rel="tag" href='${ctx}/tag/<s:property value="articleTags"/>'>
                        <span><s:property value="articleTags"/></span>
                    </a>
                </div>
                <span>
                    <s:if test='articleContent.replaceAll("<[^>]+>", "").length()>300'>
                        <s:property value='articleContent.replaceAll("<[^>]+>", "").substring(0,300)' escapeHtml="false"/>.....
                    </s:if>
                    <s:else>
                        <s:property value='articleContent.replaceAll("<[^>]+>", "")' escapeHtml="false"/>......
                    </s:else>
                </span>
            </div>
            <div class="info clearfix">
                <div class="right">
                    <span class="author"><s:property value="articleAuthor"/></span>
                    <span class="time"><s:date name="articleCreateTime" format="yyyy-MM-dd  HH:mm"/>发表</span>
                    <span class="edit"><s:date name="articleUpdateTime" format="yyyy-MM-dd HH:mm"/>更新</span>
                    <span class="views" title="浏览次数"><s:property value="readPeople"/>次浏览</span>
                    <a class="comment" title="评论"href='${ctx}/blog/<s:property value="articleLink"/>#comments'><s:property value="comments.size()"/></a>
                </div>
            </div>
        </div>
    </s:iterator>
<!-- end content -->
</div>
