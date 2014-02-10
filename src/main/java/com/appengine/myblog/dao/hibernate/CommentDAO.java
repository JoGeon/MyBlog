package com.appengine.myblog.dao.hibernate;

import com.appengine.myblog.dao.hibernate.support.HibernateDaoSupport;
import com.appengine.myblog.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
* <p>Title: CommentDAO.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: NO</p>
* @author ThinkPadT420i
* @date 2013年12月20日
* @version 1.0
 */
@Repository
public class CommentDAO extends HibernateDaoSupport<Comment> {

    public void saveComment(Comment comment) {
        save(comment);
    }

    public void deleteComment(Comment comment) {
        delete(comment);
    }

    /**
     * 找到当前评论的最大层级
     *
     * @param hql
     * @return
     */
    public int findCommentLevel(String hql) {
        List commentLevel = find(hql);
        return commentLevel.size()>0 ?  ((Integer)commentLevel.get(0)) : 0 ;
    }

}
