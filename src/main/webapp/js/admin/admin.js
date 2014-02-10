$(document).ready(function() {

    $('#adminmenu div').mousedown(function() {
        admin.FoperateDataAjax(this.id, false);
        return false;
    });
    
    Util.clearTipMessage();
    
    $(document).ajaxStart(function() {
        $('#loading').show();
    }).ajaxStop(function() {
        $('#loading').hide();
    });
});

var admin = function () {
    function operateDataAjax(div, loadflag, ID) {

        Util.operateTabClass(div);

        var existflag = Util.confimeEixtEditor();

        if (existflag) {
            Util.displayMainPanelItem(div);

            switch (div) {
                case 'menu_main':
                    FdisplayMainPanel();
                    break;
                case 'menu_article-list':
                    articleList.FdisplayArticleList();
                    break;
                case 'menu_publish-article':
                    publishArticle.FshowPublishArticle(loadflag, ID);
                    break;
            }
        }
    }

    return {
        FoperateDataAjax: operateDataAjax
    };
}();

