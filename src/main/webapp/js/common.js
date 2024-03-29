var Util = {

    /**
     *@description 操作tab的css
     */
    operateTabClass: function(div) {
        var divtitle = $('#' + div);
        this.addTabClass(divtitle, 'a', 'tab-current');
    },
    
    /**
     *@description 移除特定CSS 
     */
    addTabClass: function (divid, tag, className) {
        $('#adminmenu div').children().removeClass(className);
        divid.children(tag).addClass(className);
    },
    
    /**
     * @description 清除提示信息
     */
    clearTipMessage: function() {
        setInterval(function() {
            if ($("#msg").text() !== "") {
                setTimeout(function() {
                    $("#msg").text("");
                }, 1000);
            }
        }, 3000);
    },
    
     /**
     * @description 当离开编辑界面后，提示用户内容已经修改，如果离开则清空文章内容，并清空定时器
     */
    confimeEixtEditor: function() {
        var content = editor.FgetEditorText();
        if(content != null && content.length > 0) {
             if(confirm("页面值已经修改，是否要离开？")){
                editor.FsetEditorText("");
                publishArticle.FclearDraftTimer();
                return true;
            } else {
                return false;
            }
        }
        return true;
    },
    
     /**
     * 根据鼠标点击的Menu，动态显示MainPane的Item项。
     * @param {Object} PanelDivID
     */
    displayMainPanelItem: function (PanelDivID) {
        var mainPaneItemId = '#Panel' + PanelDivID.substr(4);

        $('#Panel').children().each(function(index) {
            /*console.log( index + ": " + $(this).text() );*/
            if ($(this).is(mainPaneItemId)) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    },

    loadCounter: function() {
        setInterval(function() {
            $.get(  ctx +"/foreground/getWebCounter", function(data, textStatus) {
                if(textStatus == "success") {
                    console.log(data.onlieCount + data.visitCount);
                    $(".onlineCount span").text(data.onlieCount);
                    $(".visitorCount span").text(data.visitCount);
                } else {
                    $(".onlineCount span").text(0);
                    $(".visitorCount span").text(0);
                }
            }, "json")
        }, 60000);
    },
    
    /**
     * @description 检测页面错误
     */
    error: function () {
        $("#tipMsg").text("Error: " + arguments[0] +
            " File: " + arguments[1] + "\nLine: " + arguments[2] +
            " please report this issue on https://github.com/b3log/b3log-solo/issues/new");
        $("#loadMsg").text("");
    },
        
    /**
     * @description 非 IE6/7 的浏览器，跳转到 kill-browser 页面
     */
    killIE: function () {
        if ($.browser.msie) {
            // kill IE6 and IE7
            if ($.browser.version === "6.0" || $.browser.version === "7.0") {
                window.location = latkeConfig.servePath + "/kill-browser.html";
                return;
            }
            
            // 后台页面 kill 360 
            if (window.external && window.external.twGetRunPath) {
                var path = external.twGetRunPath();
                if(path && path.toLowerCase().indexOf("360se") > -1 && 
                    window.location.href.indexOf("admin-index") > -1) {
                    window.location = latkeConfig.servePath + "/kill-browser.html";
                    return; 
                }
            }
        }
    },
    
    /**
     * @description 替换[emXX] 为图片
     * @param {String} str 替换字符串
     * @returns {String} 替换后的字符
     */
    replaceEmString: function (str) {
        var commentSplited = str.split("[em");
        if (commentSplited.length === 1) {
            return str;
        }
        
        str = commentSplited[0];
        for (var j = 1; j < commentSplited.length; j++) {
            var key = commentSplited[j].substr(0, 2);
            str += "<img src='" + latkeConfig.staticServePath + "/skins/" +
            Label.skinDirName + "/images/emotions/em" + key + ".png' alt='" +
            Label["em" + key + "Label"] + "' title='" +
            Label["em" + key + "Label"] + "'/>" + commentSplited[j].substr(3);
        }
        return str;
    },
    
    /**
     * @description URL 没有协议头，则自动加上 http://
     * @param {String} url URL 地址
     * @returns {String} 添加后的URL
     */
    proessURL: function (url) {
        if (!/^\w+:\/\//.test(url)) {
            url = "http://" + url;
        }
        return url;
    },
    
    /**
     * @description 切换到手机版
     * @param {String} skin 切换前的皮肤名称
     */
    switchMobile: function (skin) {
        Cookie.createCookie("btouch_switch_toggle", skin, 365);
        setTimeout(function () {
            location.reload();
        }, 1250); 
    },
    
    /**
     * @description topbar 相关事件
     */
    setTopBar: function () {
        var $top = $("#top");
        if ($top.length === 1) {
            var $showTop = $("#showTop");
            
            $showTop.click(function () {
                $top.slideDown();
                $showTop.hide();
            });
            
            $("#hideTop").click(function () {
                $top.slideUp();
                $showTop.show();
            });
        }
    },
    
    /**
     * @description 回到顶部
     */
    goTop: function () {
        var acceleration = acceleration || 0.1;

        var y = $(window).scrollTop();
        var speed = 1 + acceleration;
        window.scrollTo(0, Math.floor(y / speed));

        if (y > 0) {
            var invokeFunction = "Util.goTop(" + acceleration + ")";
            window.setTimeout(invokeFunction, 16);
        }
    },

    backToTop: function () {
        // hide #back-top first
        $("#back-top").hide();

        // fade in #back-top
        $(window).scroll(function () {
            if ($(this).scrollTop() > 100) {
                $('#back-top').fadeIn();
            } else {
                $('#back-top').fadeOut();
            }
        });

        // scroll body to 0px on click
        $('#back-top a').click(function () {
            $('body,html').animate({
                scrollTop: 0
            }, 800);
            return false;
        });
    },

    /**
     * @description 回到底部
     */
    goBottom: function (bottom) {
        if (!bottom) {
            bottom = 0;
        }
        window.scrollTo(0, $("body").height() - $(window).height() - bottom);
    },
    
    /**
     * @description 页面初始化执行的函数 
     */
    init: function () {
        //window.onerror = Util.error;
        Util.killIE();
        Util.setTopBar();
    },

    /**
     * @description 替换侧边栏表情为图片
     * @param {Dom} comments 评论内容元素
     */
    replaceSideEm: function (comments) {
        for (var i = 0; i < comments.length; i++) {
            var $comment = $(comments[i]);
            $comment.html(Util.replaceEmString($comment.html()));
        }
    },
    
    /**
     * @description 根据 tags，穿件云效果
     * @param {String} [id] tags 根元素 id，默认为 tags
     */
    buildTags: function (id) {
        id = id || "tags";
        
        // 根据引用次数添加样式，产生云效果
        var classes = ["tags1", "tags2", "tags3", "tags4", "tags5"],
        bList = $("#" + id + " b").get();
        var max = parseInt($("#" + id + " b").last().text());
        var distance = Math.ceil(max / classes.length);

        for (var i = 0; i < bList.length; i++) {
            var num = parseInt(bList[i].innerHTML);
            // 算出当前 tag 数目所在的区间，加上 class
            for (var j = 0; j < classes.length; j++) {
                if (num > j * distance && num <= (j + 1) * distance) {
                    bList[i].parentNode.className = classes[j];
                    break;
                }
            }
        }
        
        // 按字母或者中文拼音进行排序
        $("#" + id).html($("#" + id + " li").get().sort(function(a, b) {
            var valA = $(a).find("span").text().toLowerCase();
            var valB = $(b).find("span").text().toLowerCase();
            // 对中英文排序的处理
            return valA.localeCompare(valB);
        }));

    },

    /**
     * 改变arrow方向
     */
    changeArrowAction: function() {
        $('.arrow').on('click', function() {
            if($(this).is(".right_arrow")) {
                $(this).removeClass("right_arrow").addClass("bottom_arrow");
                $(this).parent().next().slideDown();
            } else {
                $(this).removeClass("bottom_arrow").addClass("right_arrow");
                $(this).parent().next().slideUp();
            }
        });
    },
    
    /**
     * @description 时间戳转化为时间格式
     * @param {String} time 时间
     * @param {String} format 格式化后日期格式
     * @returns {String} 格式化后的时间
     */
    toDate: function (time, format) {
        var dateTime = new Date(time);
        var o = { 
            "M+" : dateTime.getMonth()+1, //month 
            "d+" : dateTime.getDate(), //day 
            "H+" : dateTime.getHours(), //hour 
            "m+" : dateTime.getMinutes(), //minute 
            "s+" : dateTime.getSeconds(), //second 
            "q+" : Math.floor((dateTime.getMonth()+3)/3), //quarter 
            "S" : dateTime.getMilliseconds() //millisecond 
        };

        if(/(y+)/.test(format)) { 
            format = format.replace(RegExp.$1, (dateTime.getFullYear()+"").substr(4 - RegExp.$1.length)); 
        } 

        for(var k in o) { 
            if(new RegExp("("+ k +")").test(format)) { 
                format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
            } 
        } 
        return format; 
    },

    changeActive: function() {
        var urlpath = window.location.pathname;

        //改变当前所选页面CSS
        var seltarget = urlpath.split("/");
        $("#nav").children("li").each(function( index ) {
//            console.log( index + ": " + this.id + ":" + urlpath.indexOf( this.id) );
            if(urlpath.indexOf( this.id) > 0 ) {
                $(this).find("a").addClass("navbaractive");
            } else {
                $(this).find("a").removeClass("navbaractive");
            }
        });

        //改变当前所选指定页面CSS
        if(urlpath.indexOf("page") >= 0) {
            var seltarget = urlpath.split("/");
            var selPage = seltarget[seltarget.length-1];
            $(".pageNav").children("li").each(function(index) {
                console.log( index + ": " + $(this).text() );
                var num = $(this).text().trim();
//                console.log(num == selPage);
                if( num == selPage) {
//                    console.log("add class");
                    $(this).find("a").addClass("pageactive");
                }else {
                    $(this).find("a").removeClass("pageactive");
                }
            });
        }
    }
};

if (!Cookie) {
    /**
     * @description Cookie 相关操作
     * @static
     */
    var Cookie = {
        /**
         * @description 读取 cookie
         * @param {String} name cookie key
         * @returns {String} 对应 key 的值，如 key 不存在则返回 ""
         */
        readCookie: function (name) {
            var nameEQ = name + "=";
            var ca = document.cookie.split(';');
            for(var i=0;i < ca.length;i++) {
                var c = ca[i];
                while (c.charAt(0)==' ') c = c.substring(1,c.length);
                if (c.indexOf(nameEQ) == 0) return decodeURIComponent(c.substring(nameEQ.length,c.length));
            }
            return "";
        },
        
        /**
         * @description 清除 Cookie
         * @param {String} name 清除 key 为 name 的该条 Cookie
         */
        eraseCookie: function (name) {
            this.createCookie(name,"",-1);
        },

        /**
         * @description 创建 Cookie
         * @param {String} name 每条 Cookie 唯一的 key
         * @param {String} value 每条 Cookie 对应的值，将被 UTF-8 编码
         * @param {Int} days Cookie 保存时间
         */
        createCookie: function (name, value, days) {
            var expires = "";
            if (days) {
                var date = new Date();
                date.setTime(date.getTime()+(days*24*60*60*1000));
                expires = "; expires="+date.toGMTString();
            }
            document.cookie = name+"="+encodeURIComponent(value)+expires+"; path=/";
        },
        loadStyles: function (url) {
            var link = document.createElement("link");
            link.rel = "stylesheet";
            link.type = "text/css";
            link.href = url;
            var head = document.getElementsByTagName("head")[0];
            head.appendChild(link);
            }
    };
}