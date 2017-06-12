<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var currentList = (${currentList});
    var pageList = (${pageList});
</script>
<div class="panel light-grey">
    <div class="panel-heading">
        <span><label>设施管理</label>/路口管理</span>
    </div>
    <div class="panel-body">
        <div class="btn-group group">
            <button class="btn blue" id="addIntersection">添加路口</button>
            <button class="btn purple" id="mapDetail" style="margin-left: 20px;">查看地图</button>
        </div>
        <div style="height: 30px;">
            <div class="control-group">
                <label style="margin-left: 50px;">按名称查找:&nbsp;&nbsp;</label>
                <input type="text" id="nameField"
                       style="height: 25px;"/>
                <c:if test="${currentUser.authorities eq '[ADMIN]'}">
                    <label style="margin-left: 150px;">按部门查找:&nbsp;&nbsp;</label>
                    <select id="unitField" class="my-input">
                        <c:forEach items="${unitList}" var="unit">
                            <option value="${unit.unitId}">${unit.unitName}</option>
                        </c:forEach>
                    </select>
                </c:if>
            </div>
        </div>
        <table class="table col-sm-9 col-md-10 main table-bordered table-hover table-striped" id="intersectionTable">
            <tr>
                <th>路口名称</th>
                <th>所属单位</th>
                <th>坐标 经/纬</th>
                <th>路口类型</th>
                <th>是否损坏</th>
                <th>操作</th>
            </tr>
            <tr>
            </tr>
        </table>
        <nav style="margin-top: 400px;">
            <ul class="pagination" id="intersectionPages">
            </ul>
        </nav>
    </div>
</div>
<script src="/resources/admin/js/intersection/intersectionList.js"></script>

<%--<script>--%>
<%--$('.date-format').datetimepicker({--%>
<%--language: 'cn',//显示中文--%>
<%--format: 'yyyy-mm-dd',//显示格式--%>
<%--minView: "month",//设置只显示到月份--%>
<%--initialDate: new Date(),//初始化当前日期--%>
<%--autoclose: true,//选中自动关闭--%>
<%--todayBtn: true,//显示今日按钮--%>
<%--bootcssVer: 3,--%>
<%--clearBtn: true--%>
<%--});--%>
<%--</script>--%>
