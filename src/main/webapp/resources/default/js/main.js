$(function () {
    $.ajaxSetup({
        contentType:"application/x-www-form-urlencoded;charset=utf-8",
        complete:function(XMLHttpRequest,textStatus){
            // 通过XMLHttpRequest取得响应头，sessionstatus，
            var sessionstatus = XMLHttpRequest.getResponseHeader("sessionStatus");
            if(sessionstatus == '911'){
                window.location.replace(ctx + "/signin");
            }
        }
    });

    $('.navbar-toggle').click(function (e) {
        e.preventDefault();
        $('.nav-sm').html($('.navbar-collapse').html());
        $('.sidebar-nav').toggleClass('active');
        $(this).toggleClass('active');
    });

    bootbox.setDefaults({
        locale: "zh_CN",
        show: true,
        backdrop: true,
        closeButton: true,
        animate: true,
        onEscape: true
    });

});

function newWindow(url, winName) {
    window.open (url, winName, 'height=850, width=650, top=0,left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}
