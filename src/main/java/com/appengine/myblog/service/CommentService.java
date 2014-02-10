package com.appengine.myblog.service;

import com.appengine.myblog.domain.Comment;

/**
 * 
* <p>Title: CommentService.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: NO</p>
* @author ThinkPadT420i
* @date 2013年12月20日
* @version 1.0
 */
public interface CommentService {

    public void saveComment(Comment comment);

    public void deleteComment(Comment comment);

    public int findCommentLevle(String hql);

}
