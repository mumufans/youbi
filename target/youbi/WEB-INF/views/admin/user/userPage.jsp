<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: hubin1
  Date: 2017/3/22
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var x = ${inject};
</script>
<script type="text/javascript" src="/resources/admin/js/user/userPage.js"></script>
<div class="panel light-grey">
    <div class="panel-heading">
        <span style="color: #265a88"><label>用户管理</label> / 编辑用户</span>
    </div>
    <div class="panel-body">
        <p class="left-block">
            <input type="button" class="btn green" value="返回列表" id="exist">
        </p>
        <form:form id="addUserForm" class="form" modelAttribute="user" onsubmit="return false;" action="#">
            <form:input path="userId" name="userId" hidden="hidden"/>
            <div class="control-group">
                <label for="userName" style="margin-left: 15px;" class="form-label">登录名&nbsp;</label>
                <form:input type="text" name="userName" id="userName" path="userName" class="form-input"/>
            </div>
            <div class="control-group">
                <label style="margin-left: 30px;" class="form-label">姓名&nbsp;</label>
                <form:input type="text" name="zhname" path="zhname" class="form-input" id="zhname"/>
            </div>
            <div class="control-group">
                <label style="margin-left: 15px;" class="form-label">手机号&nbsp;</label>
                <form:input type="text" path="mobile" name="mobile" class="form-input" id="mobile"/>
            </div>
            <div class="control-group">
                <label style="margin-left: 30px;" class="form-label">警号&nbsp;</label>
                <form:input type="text" name="badeNumber" path="badeNumber" class="form-input"/>
            </div>
            <div class="control-group">
                <label class="form-label">直属部门&nbsp;</label>
                <select name="unitLevelOne" id="unitLevelOne" class="form-input"
                        onchange="getNextLevel()" required="required">
                    <c:forEach items="${unitLevelOne}" var="unitOne">
                        <option value="${unitOne.unitId}">${unitOne.unitName}</option>
                    </c:forEach>
                </select>
                <select id="unitLevelTwo" class="form-input">
                    <c:forEach items="${unitLevelTwo}" var="unitTwo">
                        <option value="${unitTwo.unitId}">${unitTwo.unitName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="control-group">
                <label style="margin-left: 30px;" class="form-label">职务&nbsp;</label>
                <form:input type="text" path="duty" name="duty" class="form-input"
                            ng-model="user.duty"/>
            </div>
            <p style="margin-left: 30px; font-size: 13px;">
                注：新用户默认密码为手机号，<br>若未填写初始密码为12345678
            </p>
            <div class="control-group">
                <button id="submitBtn" style="margin-left: 50px;" class="btn blue">提交</button>
                <button class="btn" style="margin-left: 30px;" type="reset">重置</button>
            </div>
        </form:form>
    </div>
</div>