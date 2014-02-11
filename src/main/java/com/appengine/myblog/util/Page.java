package com.appengine.myblog.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * <p>Title: Page.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: NO</p>
 *
 * @author ThinkPadT420i
 * @version 1.0
 * @date 2013年12月20日
 */
public class Page {

    private static final long serialVersionUID = -5624189033006412710L;


    //用于分页的pageTag
    public static final String HOME_PAGE_TAG = "home";
    public static final String CATEGORY_PAGE_TAG = "category";

    public static int DEFAULT_PAGE_SIZE = 10;
    private int pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数
    private int start; // 当前页第一条数据在List中的位置,从0开始
    private int totalCount = 0; // 总记录数

    /**
     * 构造方法，只构造空页.
     */
    public Page() {

    }

    public Page(int start, int totalCount) {
        this.start = start;
        this.totalCount = totalCount;
    }

    public Page(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 取总记录数.
     */
    public int getTotalCount() {
        return this.totalCount;
    }

    /**
     * 取总页数.
     */
    public int getTotalPageCount() {
        if (totalCount % pageSize == 0)
            return totalCount / pageSize;
        else
            return totalCount / pageSize + 1;
    }

    /**
     * 取每页数据容量.
     */
    public int getPageSize() {
        return pageSize;
    }


    /**
     * 取该页当前页码,页码从1开始.
     */
    public int getCurrentPageNo() {
        return start;
    }

    /**
     * 取下一页,start从0页开始
     *
     * @return
     */
    public int getNextPage() {
        return (start + 1);
    }


    /**
     * 取上一页，页面以控制代码显示否，此处就不对start进行判断。
     *
     * @return
     */
    public int getPrevisouPage() {
        return (start - 1);
    }

    /**
     * 该页是否有下一页.
     */
    public boolean hasNextPage() {
        return this.getCurrentPageNo() < this.getTotalPageCount() - 1;
    }

    /**
     * 该页是否有上一页.
     */
    public boolean hasPreviousPage() {
        return this.getCurrentPageNo() > 1;
    }

    /**
     * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
     */
    protected static int getStartOfPage(int pageNo) {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    /**
     * 获取任一页第一条数据在数据集的位置.
     *
     * @param pageNo   从1开始的页号
     * @param pageSize 每页记录条数
     * @return 该页第一条数据
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }
}