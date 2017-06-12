<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hubin1
  Date: 2017/4/12
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var roadways = (${roadways});
    var records = (${records});
</script>
<div class="panel light-grey">
    <div class="panel-heading">
        <span style="color: #265a88"><label>路口管理</label> / 编辑路口</span>
    </div>
    <div class="panel-body">
        <p class="left-block">
            <input type="button" class="btn green" value="返回列表" id="exist">
        </p>
        <div class="col-lg-6" style="margin-left: 3%;">
            <form:form id="intersectionForm" class="form" modelAttribute="intersection" onsubmit="return false;">
                <form:input path="intersectionId" name="intersectionId" hidden="hidden"/>
                <div class="control-group">
                    <label class="form-label">路口名称 </label>
                    <form:input path="intersectionName" name="intersectionName" class="form-input"
                                id="intersectionName"/>
                </div>
                <div class="control-group">
                    <label class="form-label">经过道路</label>
                    <select id="roadways" style="width:400px;">
                    </select>
                </div>
                <div class="control-group">
                    <label class="form-label">路口类型</label>
                    <form:select path="intersectionType.intersectionTypeId" id="intersectionType" class="form-input">
                        <form:option value=""></form:option>
                        <%--<c:forEach items="${intersectionTypes}" var="intersectionType">--%>
                        <form:options items="${intersectionTypes}" itemValue="intersectionTypeId"
                                      itemLabel="intersectionTypeName"></form:options>
                        <%--</c:forEach>--%>
                    </form:select>
                </div>
                <div class="control-group">
                    <label class="form-label" style="margin-left:30px;">坐标</label>
                    <form:input class="form-input" path="intersectionLong" cssStyle="width: 200px;"
                                id="intersectionLong" placeholder="请填写经度"/>
                    <form:input cssStyle="width: 200px;" path="intersectionLat" class="form-input"
                                id="intersectionLat" placeholder="请填写纬度"/>
                </div>
                <div class="control-group">
                    <label class="form-label">所属单位</label>
                        <form:select path="unit.unitId" class="form-input" id="unit">
                        <form:option value=""></form:option>
                        <form:options items="${units}" itemValue="unitId"
                        itemLabel="unitName"></form:options>
                        </form:select>
                    <%--<form:select path="unit.unitId" class="form-input" id="unit">--%>
                        <%--<option value=""></option>--%>
                        <%--<c:forEach items="${units}" var="unit">--%>
                            <%--<option value="${unit.unitId}">${unit.unitName}</option>--%>
                        <%--</c:forEach>--%>
                    <%--</form:select>--%>
                </div>
                <div class="btn-group">
                    <button id="submitBtn" class="btn blue">提交</button>
                    <button class="btn grey">重置</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<script src="/resources/admin/js/intersection/intersectionPage.js"></script>
