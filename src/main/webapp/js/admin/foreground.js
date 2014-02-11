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

    $('.arrow').on('click', function() {
        if($(this).is(".right_arrow")) {
            $(this).removeClass("right_arrow").addClass("bottom_arrow");
            $(this).parent().next().slideDown();
        } else {
            $(this).removeClass("bottom_arrow").addClass("right_arrow");
            $(this).parent().next().slideUp();
        }
    });

    if(typeof Util  != "undefined") {
        Util.ChangeActive();
    }

    $(document).ajaxStart(function() {
        $('#loading').show();
    }).ajaxStop(function() {
        $('#loading').hide();
    });

});
