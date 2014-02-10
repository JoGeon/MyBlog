<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="pagination">
    <ul class="pageNav">
        <li>
            <a href="/<s:property value='#pageTag'/>/page/<s:property value='1'/>">首页</a>
        </li>
        <s:if test="#page.hasPreviousPage()">
            <li>
                <a href="/<s:property value='#pageTag'/>/page/<s:property value='#page.getPrevisouPage()'/>">上一页</a>
            </li>
        </s:if>

        <s:iterator value="#totalPage" status="state">
            <s:if test="#state.index+1 <= 5" >
                <li>
                    <a rel="next" href="/<s:property value='#pageTag'/>/page/<s:property value='#state.index+1'/>"><s:property value="#state.index+1"/> </a>
                </li>
            </s:if>
        </s:iterator>
        <s:if test="#page.hasNextPage()">
            <li>
                <a href="/<s:property value='#pageTag'/>/page/<s:property value='#page.getNextPage()'/>">下一页</a>
            </li>
        </s:if>
        <li>
            <a href="/<s:property value='#pageTag'/>/page/<s:property value='#totalPage'/>">末页</a>
        </li>
        <li>
            <span>共<s:property value="#pageCount"/>篇</span>
        </li>
    </ul>
</div>


<div class="push"><!-- not put anything here -->
</div>