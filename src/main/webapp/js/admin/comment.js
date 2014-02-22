/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 14-2-3</p>
 * <p>Company: NO</p>
 * User: zhanglei
 * Date: 14-2-3
 * Time: 下午10:39
 *
 */

var comment = function () {

    function submitComment() {

        if (Validation()) {

            var author = $("#author").val();

            var email = $("#email").val();

            var url = $("#url").val();

            var comment = $("#comment").val();

            var JSONObject = {
                "comment": {
                    "commentName": author,
                    "commentEmial": email,
                    "commentURL": url,
                    "commnetContent": comment
                },
                "articleID": $("#article_ID").text()
            };

            $.ajax({
                type: "POST",
                url: ctx +"/foreground/submitComment",
                cache: false,
                dataType: 'json',
                async: false,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(JSONObject)
            }).done(function (data) {
                    if(data.resultMessage == "success") {
                        $(".comment_list").prepend('<div class="comment_info">' +
                            '<a href="#'+data.comment.commentLevel +'"><span id="commentLevel">' + data.comment.commentLevel + '</span>楼</a>' +
                            '<span id="commentName">' + data.comment.commentName + '</span>' +
                            '<span> [' + data.comment.commentTime +']说：</span>' +
                            '<a class="recomment" href="#comment-text">回复</a></div>' +
                            '<div class="comment_content" id="com_content">' + data.comment.commnetContent +
                            '</div>');

                        $("#comment").val("");
                        alert("评论成功！");
                    } else {
                        alert(data.resultMessage);
                    }

                }).fail(function () {
                    console.log("save comment error");
                });
        }
    }

    function initOnClick() {
        $('.recomment').on( 'click' , function() {
            quoteComment($(this));
        });
    }

    function submitOnClick() {
        $('#submit').on('click', function() {
            submitComment();
        });
    }

    function quoteComment(jComment) {
        var commentName = jComment.parent().find("#commentName").text().trim();
//        var commentContent = jComment.parent().next().html().trim();
        var  commentLevel = jComment.parent().find("#commentLevel").text().trim();
        var quoteContent ="<blockquote>\r\n" +
            "<pre>回复  <a href='#"+ commentLevel + "'><span class='commentlevel'>" +commentLevel+"楼: </span></a>"+commentName + "：</pre>\r\n"
             + "</blockquote>\r\n" ;
        $("#comment").val(quoteContent);
    }

    /**
     * @return {boolean}
     */
    function  Validation() {
        var author = $("#author");
        var email = $("#email");
        var comment = $("#comment");
        if ("" === author.val().replace(/\s/g, "")) {
            alert("昵称不能为空！");
            author.focus().val("");
        } else if ("" === email.val().replace(/\s/g, "")) {
            alert("邮箱不能为空！");
            email.focus().val("");
        } else if ("" === comment.val().replace(/\s/g, "")) {
            alert("评论不能为空！");
            comment.focus().val("");
        } else {
            return true;
        }
        return false;
    }


    return {
        FinitOnClick : initOnClick,
        FsubmitOnClick : submitOnClick
    };
}();