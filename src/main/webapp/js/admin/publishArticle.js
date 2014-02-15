var publishArticle = function() {

    var autoSaveDraftTimer = "";

    var AUTOSAVETIME = 300 * 1000;

    function initpublishArticle() {

        $('#saveArticle').on('click', function() {
            var flag = ArticleFlag("isSave");
            saveArticle(flag);
        });

        $('#publishArticle').on('click', function() {
            var flag = ArticleFlag("isPublish");
            saveArticle(flag);
        });

        $('#addcategory').on('click', function() {
            addArticleCategory();
        });


        //定时保存文章内容
        clearDraftTimer();
        autoSaveDraftTimer = setInterval(function() {
            autoSaveToDraft();
        }, AUTOSAVETIME);
    }

    function showPublishArticle(loadflag, ID) {
        $('#Panel_publish-article').load('articledata/publish-articleservlet', function(response, status, xhr) {
            if (status == "success") {
                editor.FCreatEditor();
                //获取所有分类数据
                getAllArticleType(loadflag, ID);
                initpublishArticle();
            } else {
                var msg = "Sorry but there was an error: ";
                $("#msg").html(msg + xhr.status + " " + xhr.statusText);
            }
        });
    }

    /**
     *
     * @param {Object} isPublish保存文章
     */
    function saveArticle(isPublish) {
        if (validate()) {

            var url = "";

            switch(isPublish) {
                case 'isSave':
                    url = "createArticle";  //创建文章
                    break;
                case 'isPublish':
                    url = "publishArticle";  //发布文章
                    break;
                case 'isUpdate':
                    url = "updateArticle"; //更新文章
                    break;
            }

            var articleContent = editor.FgetEditorText();

            var JSONObject = {
                "article" : {
                    "articleid" : $("#IDNum").val(),
                    "articleTitle" : $("#articleTitle").val(),
                    "articleLink" : $("#articleLink").val(),
                    "articleContent" : articleContent,
                    "articleTags" : $("#articletags").val()
                },
                "articleTypeID" : $("#category").val()
            };

            $.ajax({
                type : "POST",
                url : url,
                cache : false,
                dataType : 'json',
                async : false,
                contentType : 'application/json; charset=utf-8',
                data : JSON.stringify(JSONObject)
            }).done(function(data) {
                console.log("save article success" + data.resultMessage  + data.articleID);
                $("#msg").text(data.resultMessage);
                if(data.articleID != null) $("#IDNum").val(data.articleID);
            }).fail(function() {
                console.log("save article erroe");
            });
        }
    }

     /**
     *
     * @param ID
     */
    function setArticleDate(ID) {

        $.ajax({
            type : "GET",
            url : "getArticleByID?articleID=" + ID
        }).done(function(data) {
            //在编辑器中添加内容
            $("#articleTitle").val(data.article.articleTitle);
            $("#articleLink").val(data.article.articleLink);
            editor.FsetEditorText(data.article.articleContent);
            $("#articletags").val(data.article.articleTags);
            $("#category").val(data.article.articleType.articleTypeId);
            $("#IDNum").val(data.article.articleid);
        }).fail(function() {
            console.log("save article erroe");
        });
}

    /**
     *自动保存文章内容，防止文章内容丢失。
     * 第一次保存后，获得文章的ID号，然后根据文章ID号进行内容更新。每5分钟自动保存一次。
     */
    function autoSaveToDraft() {
            console.log("begin autosave article");
            if($("#articleTitle").val().replace(/\s/g, "") === "" || $("#articleLink").val().replace(/\s/g, "") === "" ||
            $("#articletags").val().replace(/\s/g, "") === "" || editor.FgetEditorText().replace(/\s/g, "") === "") return;
            var saveflag = ArticleFlag("isSave");
            saveArticle(saveflag);
            console.log("end autosave article");
    }

    /**
     * @description 清除定时器
     */
    function clearDraftTimer() {
        if (autoSaveDraftTimer !== "") {
            window.clearInterval(autoSaveDraftTimer);
            autoSaveDraftTimer = "";
        }
    };

    function ArticleFlag(isflag) {
        var articleID = $("#IDNum").val();
        if (typeof(articleID) == "undefined") return;
        var flag = "";
        if (articleID == -1) {
            flag = isflag;
        } else if ("isPublish" == isflag) {
            flag = "isPublish";
        } else {
            flag = "isUpdate";
        }
        return flag;
    }

    function addArticleCategory() {
        if(validateType()) {
            var JSONObject = {
                "articleType" : {
                    "articleTypeName" : $('#articleTypeName').val(),
                    "articleTypeDesName" : $('#articleTypeDesName').val(),
                    "articleTypeDescription" : $('#articleTypeDescription').val(),
                    "articleTypeCount" : '0'
                }
            };

            $.ajax({
                type : "POST",
                url : "saveArticleType",
                cache : false,
                dataType : 'json',
                async : false,
                contentType : 'application/json; charset=utf-8',
                data : JSON.stringify(JSONObject)
            }).done(function(data) {
                console.log("save article success" + data.resultMessage  + data.articleTypeName);
                if(data.articleType != null) {
                    $('#category').append('<option value="'+data.articleType.articleTypeId+'">' + data.articleType.articleTypeName + '</option>');
                    $('#articleTypeName').val("");
                    $('#articleTypeDescription').val("");
                }
                $("#msg").text(data.resultMessage);
            }).fail(function() {
                console.log("save article erroe");
            });
        }

    }

    function getAllArticleType(loadflag, ID) {

        $.ajax({
            type : "GET",
            url : "getAllArticleType"
        }).done(function(data) {
            //设置文章类别
            setArticleType(data.listArticleTypes);
            //设置文章内容
            if(loadflag)setArticleDate(ID);
        }).fail(function() {
            console.log("save article erroe");
        });
    }

    function setArticleType(articleTypes) {
        if(articleTypes != null) {
          $.each(articleTypes, function(i, articleType) {
               $('#category').append('<option value="'+articleType.articleTypeId+'">' + articleType.articleTypeName + '</option>');
         });
        }
    }


    function validate() {
        var articleContent = editor.FgetEditorText();
        if (articleContent == null)
            return;
        var msg = $("#msg");
        if ($("#articleTitle").val().replace(/\s/g, "") === "") {
            msg.text("文章标题不能为空！");
            $("#articleTitle").focus().val("");
        } else if ($("#articleLink").val().replace(/\s/g, "") === "") {
            msg.text("文章链接不能为空！");
            $("#articleLink").focus().val("");
        } else if (articleContent.replace(/\s/g, "") === "") {
            $("#msg").text("文章内容不能为空！");
        } else if ($("#articletags").val().replace(/\s/g, "") === "") {
            msg.text("文章标签不能为空！");
            $("#articletags").focus().val("");
        } else {
            return true;
        }
        return false;
    };

    function validateType() {
        var articleTypeName = $('#articleTypeName').val().toLowerCase();
        if(articleTypeName === "") return false;
        var articleTypes = $('#category').find('option');
        $.each(articleTypes, function(i, articleType) {
            console.log("articleType articleTypeName=" + articleTypeName + "articleType.articleTypeName= " + articleType.text);
            if(articleTypeName == articleType.text.toLowerCase()) {
                $("#msg").text("文章类别已存在！");
                $('#articleTypeName').focus().val("");
                $('#articleTypeDescription').val("");
                return false;
            }
        });
        return true;
    }

    return {
        FshowPublishArticle : showPublishArticle,
        FgetAllArticleType : getAllArticleType,
        FsetArticleDate : setArticleDate,
        FclearDraftTimer : clearDraftTimer
    };
}();

