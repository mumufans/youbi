<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hubin1
  Date: 2017/3/16
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--------------------编辑用户界面------------------------------>
<script type="text/javascript" src="/resources/admin/js/unit/unitPage.js"></script>
<div style="height: 35px;">
    <ul class="breadcrumb" style="height: 35px;">
        <li>
            <span style="color: #265a88"><label>部门管理</label> / 添加部门</span>
        </li>
    </ul>
</div>
<div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-content" style="height: auto; min-height: 600px;">
                <p class="left-block">
                    <button class="btn green" style="margin-top: 10px;margin-left: 15px;" id="exist">返回列表</button>
                </p>
                <form id="unitForm">
                    <input id="unitId" value="${unit.unitId}" hidden="hidden">
                    <div class="control-group">
                        <label>部门名称&nbsp;&nbsp;</label>
                        <input type="text" id="unitName" class="my-input"
                               placeholder="请填写单位名称" required value="${unit.unitName}">
                    </div>
                    <div class="control-group">
                        <label>部门编号&nbsp;&nbsp;</label>
                        <input type="text" id="unitNumber" class="my-input"
                               placeholder="请输入单位编号" value="${unit.unitNumber}">
                    </div>
                    <div class="control-group">
                        <label>所属区县&nbsp;&nbsp;</label>
                        <select id="county" class="my-input">
                            <option value=""></option>
                            <c:forEach items="${countyList}" var="county">
                                <option value="${county.id}">${county.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="control-group">
                        <label>上级部门&nbsp;&nbsp;</label>
                        <select id="parent" class="my-input">
                            <c:if test="${currentUser.authorities eq '[ADMIN]'}">
                                <option value=""></option>
                            </c:if>
                            <c:forEach items="${LevelOneList}" var="unitLevelOne">
                                <c:if test="${unitLevelOne.unitId == unit.parent.unitId}">
                                    <option value="${unitLevelOne.unitId}"
                                            selected="selected">${unitLevelOne.unitName}</option>
                                </c:if>
                                <c:if test="${unitLevelOne.unitId != unit.parent.unitId}">
                                    <option value="${unitLevelOne.unitId}">${unitLevelOne.unitName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <input class="btn blue" id="submitBtn" style="width: 60px;margin-left: 80px;" value="保存">
                </form>
            </div>
        </div>
    </div>
</div>