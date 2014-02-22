var articleList = function() {
    
    function displayArticleList() {
        $('#Panel_article-list').load('articledata/article-listservlet', function(response, status, xhr) {
            if (status == "success") {
                $.ajax({
                    url : "getArticleList"
                }).done(function(data) {
                    $.each(data.listArticles, function(i, article) {
                        if(article.articlePublishTime == null) article.articlePublishTime = "未发布";
                        console.log(article.articleType.articleTypeName);
                        //添加表格数据
                        $('.articleinfo table tbody').append(
                            '<tr> <td class="tdtitle">'+ article.articleTitle + 
                                '<div class="row-action">' +
                                   '<span class="view"><a href="'+ctx +'/blog/' + article.articleLink +'" title="' + article.articleTitle + '" target="_blank">查看</a></span>' +
                                   '<span class="edit"><a href="'+ctx +'/background/admin#publish-article">编辑</a><span>' +article.articleid+'</span></span>'+
                                 '</div>' +"</td>"+
                            '<td>'+ article.articleType.articleTypeName + '</td>'+
                            '<td>'+ article.articleTags + '</td>'+
                            '<td>'+ article.articleTitle + "</td>"+
                            '<td>'+ article.articleCreateTime + '</td>'+
                            '<td>'+ article.articlePublishTime  + '</td>'+
                            '<td>'+ article.articleUpdateTime + '</td>'+
                            '<td>'+ article.readPeople+ '</td>'+
                            '</tr>' );                   
                    });
                    initArticleTable();
                }).fail(function() {
                    alert("error");
                });
            } else if (status == "error") {
                var msg = "Sorry but there was an error: ";
                $("#error").html(msg + xhr.status + " " + xhr.statusText);
            } else {
                alert("error");
            }
        });
      }
    
    function initArticleTable() {

        $('.edit').on('click', function() {
             //显示文章编辑界面
            var articleID = $(this).find('span').text()
            console.log("当前文章ID=" + articleID);
             admin.FoperateDataAjax('menu_publish-article', true, articleID);
        }); 
    }
    
    
    return {
        FdisplayArticleList : displayArticleList
    };
}();

