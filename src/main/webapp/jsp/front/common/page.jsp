<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/commons/taglibs.jsp"%>
<div class="pagination">
    <ul class="pageNav">
        <li>
            <a href="${ctx}/<s:property value='#pageTag'/>/page/<s:property value='1'/>">首页</a>
        </li>
        <s:if test="#page.hasPreviousPage()">
            <li>
                <a href="${ctx}/<s:property value='#pageTag'/>/page/<s:property value='#page.getPrevisouPage()'/>">上一页</a>
            </li>
        </s:if>

        <s:iterator value="#totalPage" status="state">
            <s:if test="#state.index+1 <= 5" >
                <li>
                    <a rel="next" href="${ctx}/<s:property value='#pageTag'/>/page/<s:property value='#state.index+1'/>"><s:property value="#state.index+1"/> </a>
                </li>
            </s:if>
        </s:iterator>
        <s:if test="#page.hasNextPage()">
            <li>
                <a href="${ctx}/<s:property value='#pageTag'/>/page/<s:property value='#page.getNextPage()'/>">下一页</a>
            </li>
        </s:if>
        <li>
            <a href="${ctx}/<s:property value='#pageTag'/>/page/<s:property value='#totalPage'/>">末页</a>
        </li>
        <li>
            <span>共<s:property value="#pageCount"/>篇</span>
        </li>
    </ul>
</div>
