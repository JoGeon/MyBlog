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
                    "commnetContent": comment,
                },
                "articleID": $("#article_ID").text()
            };

            $.ajax({
                type: "POST",
                url: "comment/submitComment",
                cache: false,
                dataType: 'json',
                async: false,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(JSONObject)
            }).done(function (data) {
                    if(data.resultMessage == "sucess") {
                        $(".comment_list").prepend('<div class="comment_info">' +
                            '<span id="commentLevel">' + data.comment.commentLevel + '</span>楼' +
                            '<span id="commentName">' + data.comment.commentName + '</span>' +
                            '<span> [' + data.comment.commentTime +']说：</span>' +
                            '<a class="recomment" href="#">回复</a> <a class="quotecomment" href="#">引用</a> </div>' +
                            '<div class="comment_content" id="com_content">' + data.comment.commnetContent +
                            '</div>');
                        alert("评论成功！");
                    } else {
                        alert(data.resultMessage);
                    }
                }).fail(function () {
                    console.log("save comment erroe");
                });
        }
    }

    function commentPage() {

    }

    function quoteComment() {
        var comments = $("#com_content").text();
        alert(comments);
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
        FsubmitComment : submitComment
    };
}();