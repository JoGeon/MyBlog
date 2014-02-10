<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="blog_comments">
    <a id="comments"></a>
        <div class="article_comments">

            <div class="article_comment_title">文章评论</div>

            <div class="comment_list">
                <s:iterator  value="#article.comments" status="state">
                    <div class="comment_info">
                        <span id="commentLevel"><s:property value="commentLevel"/></span>楼
                        <span id="commentName"><s:property value="commentName"/></span>
                        <span>[<s:date name="commentTime" format="yyyy-MM-dd  HH:mm"/>]说：</span>
                        <a class="recomment" href="#">回复</a>
                        <a class="quotecomment" href="#">引用</a>
                    </div>

                    <div class="comment_content" id="com_content">
                        <s:property value="commnetContent"/>
                    </div>
                </s:iterator>


            </div>

                <div class="response_comment" id="respond_box">
                    <div id="respond" class="response_comment_box">
                        <span class="res_comment_title">给我留言</span>
                            <div id="comment-author-info">
                                <div class="response_comment_input">
                                    <label for="author">昵称*</label>
                                    <input name="author" id="author" type="text" class="comment_input" tabindex="1">
                                </div>
                                <div class="response_comment_input">
                                    <label for="email">邮箱*</label>
                                    <input type="text" name="email" id="email" tabindex="2" class="comment_input">
                                </div>
                                <div class="response_comment_input">
                                    <label for="url">网址</label>
                                    <input type="text" name="url" id="url" value="http://" class="comment_input" tabindex="3">
                                </div>
                            </div>

                            <div class="comment_textarea">
                                <label for="comment"></label><textarea name="w" id="comment" tabindex="4" class="comment_main"></textarea>
                                <div id="loading" style="display: none;"> 正在提交, 请稍候...</div>
                                <div id="error" style="display: none;">#</div>
                                <div id="article_ID" style="display: none;"><s:property value='#article.articleid'/></div>
                            </div>

                            <div class="comment_button">
                                <button id="submit">发表评论</button>
                            </div>
                   </div>
                </div>
        </div>
</div>