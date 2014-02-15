package com.appengine.myblog.util;

import com.appengine.myblog.action.LoginAction;
import com.appengine.myblog.domain.Author;
import com.appengine.myblog.service.AuthorService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 14-2-15</p>
 * <p>Company: NO</p>
 * User: zhanglei
 * Date: 14-2-15
 * Time: 下午4:56
 */
public class CookieUtils {

    /**
     * cookie的增加、删除、查询
     */
    public static final String USER_COOKIE = "user.cookie";

    // 添加一个cookie
    public static Cookie addCookie(Author author) {
        Cookie cookie = new Cookie(USER_COOKIE, author.getAuthorname() + ","
                + author.getPassword());
        System.out.println("添加cookie");
        cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周
        return cookie;
    }

    // 得到cookie
    public static boolean getAuthorCookie(HttpServletRequest request, AuthorService authorService) {
        Cookie[] cookies = request.getCookies();
        System.out.println("cookies: " + cookies);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("cookie: " + cookie.getName());
                if (CookieUtils.USER_COOKIE.equals(cookie.getName())) {
                    String value = cookie.getValue();
                    if (StringUtils.isNotBlank(value)) {
                        String[] split = value.split(",");
                        String username = split[0];
                        String password = split[1];
                        Author author = authorService.findAuthor(username, password);
                        if (author != null) {
                            HttpSession session = request.getSession();
                            session.setAttribute(BlogConstant.AUTHOR_SESSION, author);// 添加用户到session中
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // 删除cookie
    public static Cookie delCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (USER_COOKIE.equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    return cookie;
                }
            }
        }
        return null;
    }

}
