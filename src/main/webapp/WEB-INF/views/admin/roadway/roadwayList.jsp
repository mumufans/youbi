<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2017/4/13
  Time: 6:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var currentList1 = (${currentList1});
    var currentList2 = (${currentList2});
    var currentList3 = (${currentList3});
    var pageList = (${pageList});
</script>
<div class="panel light-grey">
    <div class="panel-heading">
        <span style="color: #265a88"><label>路口管理</label> / 编辑路口</span>
    </div>
    <div class="panel-body">
        <div class="btn-group group">
            <button class="btn blue" id="addRoad">添加道路</button>
        </div>
        <label style="margin-left: 50px;">按名称查找:&nbsp;&nbsp;</label>
        <input type="text" id="nameField"
               style="height: 25px;"/>
        <div>
            <table class="table col-sm-3 col-md-3 table-bordered table-hover table-striped"
                   style="width: 33%;float: left; border-right: solid 2px lightgray;" id="roadwayTable1">
                <tr>
                    <th>路口名称</th>
                    <th>所在区县</th>
                    <th width="20%">操作</th>
                </tr>
                <tbody id="roadwayBody1">
                </tbody>
            </table>
            <table class="table col-sm-3 col-md-3 table-bordered table-hover table-striped"
                   style="width:33%;float: left; border-right: solid 2px lightgray;" id="roadwayTable2">
                <tr>
                    <th>路口名称</th>
                    <th>所在区县</th>
                    <th>操作</th>
                </tr>
                <tbody id="roadwayBody2">
                </tbody>
            </table>
            <table class="table col-sm-3 col-md-3 main table-bordered table-hover table-striped"
                   style=" width:33%;float: left" id="roadwayTable3">
                <tr>
                    <th>路口名称</th>
                    <th>所在区县</th>
                    <th>操作</th>
                </tr>
                <tbody id="roadwayBody3">
                </tbody>
            </table>
        </div>
        <nav style="margin-top: 400px;">
            <ul class="pagination" id="roadwayPages">
            </ul>
        </nav>
    </div>
</div>
<script src="/resources/admin/js/roadway/roadwayList.js"></script>
