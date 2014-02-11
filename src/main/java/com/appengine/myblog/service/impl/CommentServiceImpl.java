package com.appengine.myblog.service.impl;

import com.appengine.myblog.domain.Comment;
import com.appengine.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appengine.myblog.dao.hibernate.CommentDAO;

/**
 * <p>Title: CommentServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: NO</p>
 *
 * @author ThinkPadT420i
 * @version 1.0
 * @date 2013年12月20日
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDAO commentDAO;

    @Override
    public void saveComment(Comment comment) {
        commentDAO.saveComment(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentDAO.deleteComment(comment);
    }

    @Override
    public int findCommentLevle(String hql) {
        return commentDAO.findCommentLevel(hql);
    }
}
