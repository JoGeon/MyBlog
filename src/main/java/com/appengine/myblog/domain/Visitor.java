package com.appengine.myblog.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * <p>Title: VisitorInfo.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: NO</p>
 *
 * @author ThinkPadT420i
 * @version 1.0
 * @date 2013年12月20日
 */
@Entity(name = "blog_visitor")
public class Visitor {
    //访问ID
    private long visitID;

    /**
     * 第多少位访问者
     */
    private long onlineCount;

    /**
     * 访问时间
     */
    private Date onlineDate;

    /**
     * 访问IP
     */
    private String onlineIP;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getVisitID() {
        return visitID;
    }

    public void setVisitID(long visitID) {
        this.visitID = visitID;
    }

    public long getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(long onlineCount) {
        this.onlineCount = onlineCount;
    }

    public Date getOnlienDate() {
        return onlineDate;
    }

    public void setOnlienDate(Date onlienDate) {
        this.onlineDate = onlienDate;
    }

    public String getOnlineIP() {
        return onlineIP;
    }

    public void setOnlineIP(String onlineIP) {
        this.onlineIP = onlineIP;
    }

}
