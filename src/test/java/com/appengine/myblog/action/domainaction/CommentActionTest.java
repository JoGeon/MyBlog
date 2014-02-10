package com.appengine.myblog.action.domainaction;

import com.appengine.myblog.domain.Comment;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsSpringTestCase;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 14-1-15</p>
 * <p>Company: NO</p>
 * User: zhanglei
 * Date: 14-1-15
 * Time: 下午3:23
 */
public class CommentActionTest extends StrutsSpringTestCase {


    /**
     * 测试保存comment，并测试私有方法验证提交表单内容是否正确。
     */
    public void testSaveComment() {

        Comment comment = new Comment();
        comment.setCommentName("1333");
        comment.setCommentLevel(1);
        comment.setCommentEmial("yinyuxinqing@126.com");
        comment.setCommentURL("http://www.baidu.com");

//        ActionProxy actionProxy = this.getActionProxy("/blog/comment/submitComment.action");

//        CommentAction commentAction = (CommentAction)actionProxy.getAction();
//        commentAction.setComment(comment);
//        commentAction.saveComment();
    }

}
