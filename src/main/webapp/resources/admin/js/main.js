$(document).ready(function(){
    $('.accordion > a').click(function (e) {
        e.preventDefault();
        var $ul = $(this).siblings('ul');
        var $li = $(this).parent();
        if ($ul.is(':visible'))
            $li.removeClass('active'); else $li.addClass('active');
        $li.siblings('li').removeClass('active');
        $li.siblings('li').children('ul').slideUp(50);
        $ul.slideToggle(50);
    });

    $('.accordion li.active:first').parents('ul').slideDown();

    $('a.ajax-link').click(function(e){
        e.preventDefault();
        $('#loading').remove();
        $('#content').fadeOut().parent().append('<div id="loading" class="center">加载中...<div class="center"></div></div>');
        var $clink = $(this);
        //History.pushState(null, null, $clink.attr('href'));
        $clink.parent('li').siblings('li').removeClass('active');
        $clink.parent().parent().parent().siblings('li').children('ul').children().removeClass('active');
        $clink.parent('li').addClass('active');
        $.ajax({
            url: $clink.attr('href'),
            success: function (msg) {
                //alert(msg);
                $('#content').html(msg);
                $('#loading').remove();
                $('#content').fadeIn();
                //docReady();
            }
        });
    });
    // $('.date-format').datetimepicker({
    //     language: 'cn',//显示中文
    //     format: 'yyyy-mm-dd',//显示格式
    //     minView: "month",//设置只显示到月份
    //     initialDate: new Date(),//初始化当前日期
    //     autoclose: true,//选中自动关闭
    //     todayBtn: true,//显示今日按钮
    //     bootcssVer: 3
    // });
    // $('.date-format').bind('change',function(){
    //     alert("1111111");
    // })
});

function showItems(url, data) {
    $.ajax({
        url: url,
        dataType: "json",
        data: data,
        success: function (success) {
            displayItems(success.currentList);
            var nextStr = (curPage == success.currentPages.end) ? "return;" : "nextPage(" + "'" + url + "'" +")";
            a.empty();
            a.append('<li id="pages"> <a onclick="lastPage('+ "url" +')">上一页</a> </li>');
            for (var i = success.currentPages.begin; i <= success.currentPages.end; i++) {
                a.append( '<li><a onclick="goToPage( '+ i + ','+ "url" +')"><span>' + i + '</span></a></li>');
            }
            a.append('<li> <a onclick=' + nextStr +
                '>下一页</a> </li>');
            a.children().eq(curPage - success.currentPages.begin + 1).children().css({backgroundColor:'#157ab5', color:'white'});
        }
    })
}

function lastPage(url) {
    if(curPage <= 1 ){
        curPage = 1;
        return;
    }else{
        curPage -= 1;
        data.pageNumber = curPage;
        showItems(url, data);
    }
}

function nextPage(url) {
    curPage += 1;
    data.pageNumber = curPage;
    showItems(url, data);
}

function goToPage(pageNumber, url) {
    curPage = pageNumber;
    data.pageNumber = curPage;
    showItems(url, data);
    // $.ajax({
    //     url: url,
    //     dataType: "json",
    //     data: data,
    //     success: function (data) {
    //         if (data.currentPages.begin == 0) {
    //             return ;
    //         }
    //         curPage = pageNumber;
    //         displayItems(data.currentList);
    //         displayPages(a, curPage, data.currentPages, url);
    //     }
    // })
}


function ajaxLink(url){
    $('.sidebar-nav').removeClass('active');
    $('.navbar-toggle').removeClass('active');
    $('#loading').remove();
    $('#content').fadeOut().parent().append('<div id="loading" class="center">加载中...<div class="center"></div></div>');

    $.ajax({
        url: url,
        success: function(msg){
            $('#content').html(msg);
            $('#loading').remove();
            $('#content').fadeIn();
        }
    })
}

function notifySuccess(message){
    $('nav.navbar-fixed-top').append("<div id='actionSuccess' style='position: relative;width: 1300px;height: 60px;margin:0px auto;top: 40px;text-align: center;' class='alert-success'><label><h4>"
        + message +"</h4></label></div>");
    setTimeout(a, 1500);
    function a (){
        $('#actionSuccess').remove();
    }
}

function notifyFail(message){
    $('nav.navbar-fixed-top').append("<div id='actionFail' style='position: relative;width: 1000px;height: 60px;margin:0px auto;top: 40px;text-align: center;' class='alert-danger'><label><h4>"
        + message +"</h4></label></div>");
    setTimeout(a, 1500);
    function a (){
        $('#actionFail').remove();
    }
}