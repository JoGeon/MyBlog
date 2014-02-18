/**
 *
 * User: ThinkPadT420i
 * Date: 13-12-17
 * Time: 下午2:10
 *
 */
try{
    window.top.location.hostname;
    if (top.location.hostname != window.location.hostname)
    {
        top.location.href =window.location.href;
    }
}
catch(e){
    top.location.href =window.location.href;
}

$(document).ready(function() {

    if( typeof comment  != "undefined") {
        comment.FsubmitOnClick();
        comment.FinitOnClick();

    }

    if(typeof Util  != "undefined") {
        Util.changeArrowAction();
        Util.changeActive();
        Util.backToTop();
        Util.loadCounter();
    }


    $(document).ajaxStart(function() {
        $('#loading').show();
    }).ajaxStop(function() {
        $('#loading').hide();
    });

});
