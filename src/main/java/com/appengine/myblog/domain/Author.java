package com.appengine.myblog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

/**
 * <p>Title: admin.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: NO</p>
 *
 * @author ThinkPadT420i
 * @version 1.0
 * @date 2013年11月6日
 */
@Entity
@Table(name = "blog_author")
public class Author {

    private Long authorID;

    private String authorname;

    private String password;

    private String authorDescription;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    @Column(name = "authorname", nullable = false, length = 30)
    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    @JSON(serialize = false)
    @Column(name = "authorpass", nullable = false, length = 225)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "authorDescription", nullable = false, length = 225)
    public String getAuthorDescription() {
        return authorDescription;
    }

    public void setAuthorDescription(String authorDescription) {
        this.authorDescription = authorDescription;
    }

}
