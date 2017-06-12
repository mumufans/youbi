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
<script type="text/javascript" src="/resources/admin/js/user/editAuth.js"></script>
<div class="panel light-grey">
    <div class="panel-heading">
        <span style="color: #265a88"><label>用户管理</label> / 修改用户权限</span>
    </div>
    <div class="panel-body">
        <p class="left-block">
            <input type="button" class="btn green" value="返回列表" id="exist">
        </p>
        <div class="col-lg-6" style="margin-left: 3%;">
            <form:form id="editAuthForm" class="form" modelAttribute="user" onsubmit="return false;">
                <form:input path="userId" name="userId" id="userId" hidden="hidden"/>
                <form:input path="userName" name="userName" id="userName" hidden="hidden"/>
                <div class="control-group">
                    <label style="margin-left: 60px;" class="form-label">姓名&nbsp;</label>
                    <form:input type="text" name="zhname" path="zhname" class="form-input disabled" disabled="true"/>
                </div>
                <div class="control-group">
                    <label class="form-label">上级直属单位&nbsp;</label>
                    <form:input path="unit.unitName" disabled="true" class="form-input disabled"/>
                </div>
                <div class="control-group">
                    <label style="margin-left: 60px;" class="form-label">职务&nbsp;</label>
                    <form:input type="text" name="duty" id="duty" path="duty" class="form-input"/>
                </div>
                <div class="control-group">
                    <label style="margin-left: 60px;" class="form-label">角色&nbsp;</label>
                    <select id="role" class="form-input">
                        <c:forEach items="${roleList}" var="role">
                            <c:if test="${user.role.roleId eq role.roleId}">
                                <option value="${role.roleId}" selected="selected"
                                        name="${role.roleName}">${role.roleZhname}</option>
                            </c:if>
                        </c:forEach>
                        <c:forEach items="${roleList}" var="role">
                            <c:if test="${user.role.roleId ne role.roleId}">
                                <option value="${role.roleId}" id="${role.roleName}"
                                        name="${role.roleName}">${role.roleZhname}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="control-group" id="adminUnit">
                    <label style="margin-left: 15px;" class="form-label">所管理部门&nbsp;</label>
                    <select name="adminLevelOne" id="adminLevelOne" class="form-input" onchange="getNextLevelA()">
                        <option value=""></option>
                        <c:forEach items="${unitList}" var="unitOne">
                            <c:if test="${user.adminunit.unitId eq unitOne.unitId}">
                                <option value="${unitOne.unitId}"
                                        selected="selected">${unitOne.unitName}</option>
                            </c:if>
                        </c:forEach>
                        <c:forEach items="${unitList}" var="unitOne">
                            <c:if test="${user.adminunit.unitId ne unitOne.unitId}">
                                <option value="${unitOne.unitId}">${unitOne.unitName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="control-group">
                    <button type="submit" style="margin-left: 120px;" class="btn btn-primary" id="submitBtn">提交</button>
                </div>
            </form:form>
        </div>
    </div>
</div>