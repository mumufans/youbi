<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>交通设施管理系统</title>
    <meta name="viewport" content="width=device=width, initial-scale=1">
    <meta name="discription" content="">
    <meta name="author" content="youbi">
    <link id="bs-css" href="/resources/css/bootstrap-cerulean.min.css" rel="stylesheet"/>
    <script src="/resources/js/jquery-2.1.4.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <%--<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>--%>
    <style>
        body {
            background-color: #eee;
            padding-top: 40px;
            padding-bottom: 40px;
        }
        .form-login {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }

        .formlogin-heading {
            color: #4b4b4b;
            margin-bottom: 10px;
        }

        .form-login .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
        }

        .form-login .form-control:focus {
            z-index: 2;
        }

        .form-login input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
</head>
<body>
<form action="../j_spring_security_check" method="post" class="form-login">
    <h1 class="formlogin-heading">登陆系统:</h1>
    <input id="j_username" name="j_username" type="text" class="form-control" value="${admin_user_login_name}"
           placeholder="用户名">
    <input id="j_password" name="j_password" type="password" class="form-control" placeholder="密码"/>
    <input type="submit" value="登陆" class="btn btn-primary btn-block btn-lg"/>
    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
        <div style="margin-top: 10px;font-size: 15px;" id="alertDiv">${SPRING_SECURITY_LAST_EXCEPTION.message}</div>
    </c:if>
</form>
</body>
<script>
    $(document).ready(function(){
        $('#j_username').focus(function(){
            $('#alertDiv').remove();
        });
        $('#j_password').focus(function(){
            $('#alertDiv').remove();
        })
    });
</script>
</html>
