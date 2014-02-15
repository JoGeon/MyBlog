package com.appengine.myblog.action;

import com.appengine.myblog.domain.Author;
import com.appengine.myblog.service.AuthorService;
import com.appengine.myblog.util.BlogConstant;
import com.appengine.myblog.util.CookieUtils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

/**
 * <p>Description: 后台登陆</p>
 * <p>Copyright: Copyright (c) 14-2-15</p>
 * <p>Company: NO</p>
 * User: zhanglei
 * Date: 14-2-15
 * Time: 下午4:06
 */
@Controller
public class LoginAction {

    private Author author;

    private boolean authorCookie;

    @Resource
    AuthorService authorService;

    public String execute() {

        //处理Cookie
        if (CookieUtils.getAuthorCookie(ServletActionContext.getRequest(), authorService)) {
            return "success";
        } else
            return "input";

    }

    public String login() {

        if (author != null) {
            String authorname = author.getAuthorname();
            String authorpass = author.getPassword();
            if (authorname != null && !"".equals(authorname) && authorpass != null
                    && !"".equals(authorpass)) {
                author = authorService.findAuthor(authorname, authorpass);
                if(author != null) {
                    //判断是否添加到Cookie中
                    if(authorCookie) {
                        Cookie cookie = CookieUtils.addCookie(author);
                        ServletActionContext.getResponse().addCookie(cookie);
                    }
                    //将Author设置到Session中
                    ServletActionContext.getContext().getSession().put(BlogConstant.AUTHOR_SESSION, author);
                    return "success";
                }
            }
        }
        ServletActionContext.getPageContext().setAttribute(BlogConstant.Error, "用户名或密码错误！");
        return "login";
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public boolean isAuthorCookie() {
        return authorCookie;
    }

    public void setAuthorCookie(boolean authorCookie) {
        this.authorCookie = authorCookie;
    }

}
