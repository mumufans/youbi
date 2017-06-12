<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/includes.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn">
<head>
    <meta charset="utf-8">
    <title>交通设施管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">

    <!-- The styles -->
    <%--<link href="<c:url value='/resources/css/jquery.dataTables.css'/>" rel='stylesheet'>--%>
    <link href="<c:url value='/resources/css/bootstrap-cerulean.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/charisma-app.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/jquery.iphone.toggle.css'/>" rel='stylesheet'>
    <link href="<c:url value='/resources/css/bootstrapValidator.min.css'/>" rel="stylesheet">
    <link rel="icon" href="/favicon.ico" type="" mce-href="image/x-icon"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" mce-href="image/x-icon"/>
    <link href="<c:url value='/resources/css/main.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/Font-Awesome-master/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css'/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/select2.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>

    <!-- jQuery -->
    <script src="<c:url value='/resources/js/jquery-2.1.4.min.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrapValidator.min.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap-datetimepicker.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap-datetimepicker.fr.js'/>"></script>
    <%--<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=a92c81bf6f320fde00fbcf194992ddb4"></script>--%>
    <%--<script src="//webapi.amap.com/ui/1.0/main.js"></script>--%>
    <script type="text/javascript"
            src="http://webapi.amap.com/maps?v=1.3&key=a92c81bf6f320fde00fbcf194992ddb4&plugin=AMap.Autocomplete"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
    <%--<script src="<c:url value='/resources/js/summernote/summernote.min.js'/>"></script>--%>
    <%--<script src="<c:url value='/resources/js/summernote/lang/summernote-zh-CN.js'/>"></script>--%>
    <%--<script src="<c:url value='/resources/js/fileInput/fileinput.min.js'/>"></script>--%>
    <%--<script src="<c:url value='/resources/js/fileInput/zh.js'/>"></script>--%>
    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <!--<script src="/resources/default/js/html5shiv.js"></script>-->
    <!--<script src="/resources/default/js/respond.min.js"></script>-->
    <![endif]-->
    <!-- The fav icon -->
    <script>
        var ctx = '${ctx}';
    </script>
</head>
<body>
<nav class="navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#" style="margin-top: 5px;"><span
                    style="font-family: 仿宋; font-size: 130%; color: #e2e2e2">交通设施管理系统</span> </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <div><a>您好： 管理员</a></div>
                </li>
                <!-- <li><a href="">个人设置</a></li> -->
                <li class="dropdown">
                    <%--<a href id="personal-setting-dropdown" uib-dropdown-toggle>个人设置</a>--%>
                    <div><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        个人设置 <b class="caret"></b>
                    </a></div>
                    <%--<ul>--%>
                    <%--<li>--%>
                    <%--<div><a href="#">个人信息</a></div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                    <%--<a href="#">修改密码</a>--%>
                    <%--</li>--%>
                    <%--</ul>--%>
                </li>
                <li><a href="/j_spring_security_logout">登出系统</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- topbar ends -->
<div style="margin-top: 60px;">
    <!-- left menu starts -->
    <div class=" page-sidebar">
        <ul class="page-sidebar-menu">
            <li>
                <a href="<c:url value='/'/>"><i class="glyphicon glyphicon-home"></i>
                    <span class="title"> 首页</span>
                </a>
            </li>
            <li class="accordion">
                <a href="javascript:void(0)">
                    <i class="glyphicon glyphicon-cog"></i>
                    <span class="title">系统</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a class="ajax-link" id="userManageLink" href="<c:url value='/user/list'/>">用户管理</a>
                    </li>
                    <c:if test="${currentUser.authorities eq '[ADMIN]'}">
                        <li>
                            <a class="ajax-link" id="unitManageLink" href="<c:url value="/unit/list"/>">部门管理</a>
                        </li>
                    </c:if>
                    <li>
                        <a class="ajax-link" id="authManageLink" href="<c:url value="/user/authList"/>">权限管理</a>
                    </li>
                </ul>
            </li>
            <li class="accordion">
                <a href="javascript:void(0)">
                    <i class="glyphicon glyphicon-cog"></i>
                    <span class="title">设施管理</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a class="ajax-link" id="intersectionManageLink"
                           href="<c:url value='/intersection/list'/>">路口管理</a>
                    </li>
                    <li>
                        <a class="ajax-link" id="roadwayManageLink" href="<c:url value="/management/roadway/list"/>">道路管理</a>
                    </li>
                    <li>
                        <a class="ajax-link" id="signalManageLink"
                           href="<c:url value="/management/signal/page"/>">信号灯管理</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="page-content">
        <div id="content" class="span6">
            <div>
                <ul class="breadcrumb">
                    <li>
                        <a href="<c:url value=''/>">首页</a>
                    </li>
                </ul>
            </div>
            <div class=" row">
            </div>
            <!-- content ends -->
        </div><!--/#content.col-md-0-->
    </div>
    <hr>
    <%--<%@ include file="dialog/changepwd.jsp"%>--%>
    <footer class="row">
        <p class="col-md-12 col-sm-12 col-xs-12 copyright text-center">Copyright &copy; <a href="javascript:void(0)"
                                                                                           target="_blank">Shinetech</a>
            2016</p>
    </footer>
</div><!--/.fluid-container-->
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/resources/js/jquery.cookie.js'/>"></script>
<script src="<c:url value='/resources/js/jquery.history.js'/>"></script>
<!-- data table plugin -->
<script src="<c:url value='/resources/js/jquery.dataTables.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootbox.min.js'/>"></script>
<!-- notification plugin -->
<script src="<c:url value='/resources/js/jquery.noty.js'/>"></script>
<!-- select or dropdown enhancer -->
<script src="<c:url value='/resources/js/chosen.jquery.min.js'/>"></script>
<script src="<c:url value='/resources/admin/js/main.js'/>"></script>
<script src="/resources/js/select2.full.min.js"></script>
<script type="text/html">
    <div style='background-color: #5AAD34; margin-top: 300px;position: absolute;width: 600px;height: 200px;'>

    </div>
</script>
</body>
</html>


