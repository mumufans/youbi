<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel light-grey">
    <div class="panel-heading">
        <span style="color: #265a88"><label>信号灯管理</label> / 编辑信息</span>
    </div>
    <div class="panel-body">
        <p class="left-block">
            <input type="button" class="btn green" value="返回列表" id="exist">
        </p>
        <div class="col-lg-6" style="margin-left: 3%;">
            <form:form id="signalForm" class="form" modelAttribute="signal" onsubmit="return false;"
                       enctype="multipart/form-data">
                <form:input path="signalId" hidden="hidden"/>
                <form:input path="isUsed" hidden="hidden"/>
                <form:input path="isDamaged" hidden="hidden"/>
                <form:input path="isConfirmed" hidden="hidden"/>
                <div class="control-group">
                    <label style="margin-left: 60px;" class="form-label">信号灯类型&nbsp;</label>
                    <form:select type="text" id="signalType" path="signalType.signalTypeId" class="form-input">
                        <form:option value=""/>
                        <form:options items="${signalTypeList}" itemValue="signalTypeId" itemLabel="signalTypeName"/>
                    </form:select>
                </div>
                <div class="control-group">
                    <label class="form-label">所属道路&nbsp;</label>
                    <form:select path="roadway.roadId" id="roadway" class="form-input">
                        <form:option value=""/>
                        <form:options items="${roadwayList}" itemValue="roadId" itemLabel="roadName"/>
                    </form:select>
                </div>
                <c:if test="${currentUser.authorities eq '[ADMIN]'}">
                    <div class="control-group">
                        <label class="form-label">所属单位&nbsp;</label>
                        <form:select path="unit.unitId" id="unit" class="form-input">
                            <form:option value=""/>
                            <form:options items="${unitList}" itemValue="unitId" itemLabel="unitName"/>
                        </form:select>
                    </div>
                </c:if>
                <div class="control-group">
                    <label class="form-label">位置</label>
                    <form:input class="form-input" path="signalLong" cssStyle="width: 200px;"
                                id="signalLong" placeholder="请填写经度"/>
                    <form:input cssStyle="width: 200px;" path="signalLat" class="form-input"
                                id="signalLat" placeholder="请填写纬度"/>
                    <button class="btn blue" id="pointBtn">地图获取位置</button>
                </div>
                <div class="control-group">
                    <label class="form-label">备注</label>
                    <form:textarea path="description"/>
                </div>
                <div class="control-group">
                    <label class="form-label">上传图片</label>
                    <input id="signalImages" type="file" name="uploads[]" multiple class="form-control file-loading"
                           data-preview-file-type="any">
                </div>
                <div class="control-group">
                    <button type="submit" style="margin-left: 120px;" class="btn btn-primary" id="submitBtn">提交</button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<%--<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">--%>
    <%--<div class="modal-dialog getPoint">--%>
        <%--<div id="myPageTop" class="modal-title" style="position:absolute; margin-top : 100px;">--%>
            <%--<table>--%>
                <%--<tr>--%>
                    <%--<td>--%>
                        <%--<label>按关键字搜索：</label>--%>
                    <%--</td>--%>
                    <%--<td class="column2">--%>
                        <%--<label>左击获取经纬度：</label>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>--%>
                        <%--<input type="text" placeholder="请输入关键字进行搜索" id="tipinput">--%>
                    <%--</td>--%>
                    <%--<td class="column2">--%>
                        <%--<input type="text" readonly="true" id="lnglat">--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
        <%--</div>--%>
        <%--<div class="modal-content">--%>
           <%--<div class="modal-body" id="getPoint" style="position: relative;"></div>--%>
        <%--</div><!-- /.modal-content -->--%>
    <%--</div><!-- /.modal-dialog -->--%>
<%--</div>--%>

<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h5 class="modal-title">点击地图获取坐标</h5>
            </div>
            <div class="modal-body" id="getPoint" style="height: 600px;"></div>
            <div class="modal-footer">
                <table>
                <tr>
                <td>
                <label>按关键字搜索：</label>
                </td>
                <td class="column2">
                <label>左击获取经纬度：</label>
                </td>
                </tr>
                <tr>
                <td>
                <input type="text" placeholder="请输入关键字进行搜索" id="jiaoxinguang">
                </td>
                <td class="column2">
                <input type="text" readonly="true" id="lnglat">
                </td>
                </tr>
                </table>
                <button type="button" class="btn btn-primary">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<script type="text/javascript"
        <%--src="http://webapi.amap.com/maps?v=1.3&key=a92c81bf6f320fde00fbcf194992ddb4&plugin=AMap.Autocomplete"></script>--%>
<%--<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>--%>
<script src="/resources/admin/js/signal/signalPage.js"></script>
