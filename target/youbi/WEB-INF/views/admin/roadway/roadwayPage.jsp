<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hubin1
  Date: 2017/4/14
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <label><h3>添加/编辑道路信息</h3></label>
    <form:form modelAttribute="roadway" cssStyle="margin-top: 30px;" id="roadwayForm" class="form"  onsubmit="return false;">
        <form:input path="roadId" name="roadId" hidden="hideen"/>
        <div class="control-group">
            <label class="form-label">道路名称</label>
            <form:input path="roadName" name="roadName" cssClass="form-input"/>
        </div>
        <div class="control-group">
            <label class="form-label">所属区县</label>
            <form:select path="county.id" name="county" cssClass="form-input">
                <form:option value=""></form:option>
                <form:options items="${countyList}" itemValue="id" itemLabel="name"/>
            </form:select>
        </div>
        <div class="btn-group">
            <button id="submitBtn" class="btn blue">保存</button>
            <button id="cancel" class="btn primary">取消</button>
        </div>
    </form:form>
</div>