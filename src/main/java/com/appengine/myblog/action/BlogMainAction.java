package com.appengine.myblog.action;

import java.util.*;
import javax.annotation.Resource;

import com.appengine.myblog.domain.*;
import com.appengine.myblog.service.AuthorService;
import com.appengine.myblog.service.VisitorInfoService;
import com.appengine.myblog.util.ServletUtil;
import org.springframework.stereotype.Controller;

import com.appengine.myblog.service.ArticleService;
import com.appengine.myblog.service.ArticleTypeServiec;
import com.appengine.myblog.util.Page;
import com.opensymphony.xwork2.ActionContext;

/**
 * <p>
 * Title: BackPageAction.java
 * </p>
 * <p>
 * Description: 后台管理
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013-7-18
 * </p>
 * <p>
 * Company: NO
 * </p>
 *
 * @author zhanglei
 * @version 1.0
 * @date 2013-7-18
 */
@Controller
public class BlogMainAction {

    @Resource
    ArticleService articleService;

    @Resource
    ArticleTypeServiec articleTypeService;

    @Resource
    VisitorInfoService visitInfoService;

    @Resource
    AuthorService authorService;

    private Author author;

    private Article article;

    private int articleID = 0;

    private int offset = 0; //当前访问的页面

    private String articleurl;  //访问网页的URL

    private List<Article> listArticles = new ArrayList<Article>();

    private List<ArticleType> listArticleTypes = new ArrayList<ArticleType>();

    public BlogMainAction() {

    }

    /**
     * 前台业务逻辑
     *
     * @return success
     */
    public String execute() {
        String hql = "from Article as a  where a.isPublish = 1 order by a.articlePublishTime DESC";
        List<Article> pageArticle = articleService.findArticleByPage(hql, offset - 1, Page.DEFAULT_PAGE_SIZE);

        String counthql = "select count(*) from Article a where a.isPublish = 1";
        int pageCount = articleService.getArticleCount(counthql);
        int totalPage = 0;

        Page page = new Page(offset, pageCount);
        if (pageCount != 0) {
            totalPage = page.getTotalPageCount();
        }

        //用于指定当前为home分页地址
        ActionContext.getContext().put("pageTag", Page.HOME_PAGE_TAG);

        ActionContext.getContext().put("page", page);

        ActionContext.getContext().put("totalPage", totalPage);
        ActionContext.getContext().put("pageCount", pageCount);

        ActionContext.getContext().put("articleCollection", pageArticle);
        //设置分页的初始值为0
        setOffset(0);
        return "success";
    }

    public String viewArticle() {
        String hql = " from Article as a where a.articleLink= ?";
        String params = articleurl;

        Article article = articleService.findArticleByLink(hql, params);

        if (article == null) {
            return "error";
        }

        ActionContext.getContext().put("article", article);
        return "success";
    }

    /**
     * 显示所有分类情况, 组织每个分类下面的文章。分类下的没有发布的文章不显示。
     *
     * @return 视图标识
     */
    public String showCategory() {
        List<ArticleType> articleTypes = articleTypeService.findAllArticleType();
        ActionContext.getContext().put("articleTypes", articleTypes);
        return "success";
    }

    /**
     * 查看文章分类下的文章
     *
     * @return success
     */
    public String viewCategory() {
        String hql = "from Article as a  where a.isPublish = 1 and a.articleType.articleTypeDesName = '" + articleurl + "' order by a.articlePublishTime DESC";
        List<Article> pageArticle = articleService.findArticleByPage(hql, offset - 1, Page.DEFAULT_PAGE_SIZE);

        String counthql = "select count(*) from Article as a  where a.isPublish = 1 and a.articleType.articleTypeDesName = ?";
        int pageCount = articleService.getArticleCount(counthql, articleurl);

        int totalPage = 0;
        Page page = new Page(offset, pageCount);
        if (pageCount != 0) {
            totalPage = page.getTotalPageCount();
        }

        //用于指定当前为category分页地址
        ActionContext.getContext().put("pageTag", Page.CATEGORY_PAGE_TAG + "/" + articleurl);

        //用于分页显示数据
        ActionContext.getContext().put("page", page);

        ActionContext.getContext().put("articleCollection", pageArticle);
        ActionContext.getContext().put("totalPage", totalPage);
        ActionContext.getContext().put("pageCount", pageCount);
        //设置分页的初始值为0
        setOffset(0);
        return "success";
    }


    /**
     * 显示文章归档，按日期显示
     *
     * @return resutl
     */
    public String showArchive() {
        String hql = "select new List(a.articleTitle, a.articleLink, a.articlePublishTime) from Article as a  where a.isPublish = 1";
        List pageArticle = articleService.findAllArticle(hql);
        //处理归档内容
        Map<String, List<List>> mapArchive = new HashMap<>();
        List<List> articleList;
        for (Iterator it = pageArticle.iterator(); it.hasNext(); ) {
            //存放归档信息的List
            articleList = new ArrayList<>();
            //数据库查询出来的Article信息
            List article = (List) it.next();
            //格式化日期作为Map的主键
            String date = ServletUtil.formatDate(String.valueOf(article.get(2)));
            //查找该日期KEY确定集合里面是否有该日期记录
            List<List> list = mapArchive.get(date);
            if (list != null && list.size() > 0) {
                //存在则直接添加List
                list.add(article);
                mapArchive.put(date, list);
            } else {
                //如果不存在，则添加到新的key-value对中
                articleList.add(article);
                mapArchive.put(date, articleList);
            }
        }

        ActionContext.getContext().put("archive", mapArchive);
        return "success";
    }

    /**
     * 显示关于信息。
     *
     * @return success
     */
    public String showAbout() {

        return "success";
    }


    /**
     * 完成用户名的验证，并转到后台管理页面。
     *
     * @return success
     */
    public String login() {
        if (author == null) return "input";
        String authorname = author.getAuthorname();
        String authorpass = author.getPassword();
        String hql = "from Author a  where a.authorname = ? and a.password = ?";
        author = authorService.findAuthorByParam(hql, authorname, authorpass);

        if (author == null) return "input";

        return "success";
    }


    /**
     * 查找热门文章，根据访问数量进行排序，并展示5个文章的标题
     *
     * @return articles
     */
    public List<Article> findHotArticles() {
        String hql = "select new List(a.articleLink, a.articleTitle) from Article as a where a.isPublish = 1 order by a.readPeople DESC";
        return articleService.findhotArticle(hql, 5);
    }

    //后台业务逻辑

    /**
     * Article相关业务逻辑
     *
     * @return success
     */
    public String findAllArticle() {
        String hql = "from Article";
        listArticles = articleService.findArticleByPage(hql, offset, Page.DEFAULT_PAGE_SIZE);
        return "success";
    }

    public String findArticle() {
        article = articleService.findArticleByID((long) articleID);
        return "success";
    }

    /**
     * ArticleType相关业务逻辑
     *
     * @return success
     */
    public String findAllArticleType() {
        listArticleTypes = articleTypeService.findAllArticleType();
        return "success";
    }

    /**
     * 查找文章分类和文章数量
     */
    public List<ArticleType> findArticleTypeCount() {
        String hql = " select new List(b.articleTypeDesName, b.articleTypeName as articleTypeName,  count(a.articleType.articleTypeId)) from Article as a  right join a.articleType as b group by b.articleTypeId";
        return articleTypeService.findArticleTypeCount(hql);
    }


    /**
     * 查找最大访客
     *
     * @return visitor
     */
    public Visitor findMaxVisitor() {
        String hql = "from blog_visitor as a where a.onlineCount = (select max(a.onlineCount) from a)";
        return visitInfoService.findMaxVisitor(hql);
    }

    public void saveVisitor(Visitor visitInfo) {
        if (visitInfo == null) return;
        visitInfoService.save(visitInfo);
    }

    /**
     * setter和getter方法
     *
     * @return url
     */
    public String getArticleurl() {
        return articleurl;
    }

    public void setArticleurl(String articleurl) {
        this.articleurl = articleurl;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Article> getListArticles() {
        return listArticles;
    }

    public void setListArticles(List<Article> listArticles) {
        this.listArticles = listArticles;
    }

    public List<ArticleType> getListArticleTypes() {
        return listArticleTypes;
    }

    public void setListArticleTypes(List<ArticleType> listArticleTypes) {
        this.listArticleTypes = listArticleTypes;
    }

}
