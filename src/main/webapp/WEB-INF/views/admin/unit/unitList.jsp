<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel light-grey">
    <div class="panel-heading">
        <span><label>部门管理</label> / 部门列表</span>
    </div>
    <div class="panel-body">
        <p>
            <button class="btn blue" type="button" onclick="ajaxLink('/unit/unitPage?unitId=0')">添加部门</button>
        </p>
        <table class="table col-sm-9 col-md-10 main table-bordered table-hover table-striped" id="unitTable">
            <tr>
                <th style="cursor:pointer;" onclick="sortByUnitName()">
                    部门名称&nbsp;<span style="color: #265a88;"><i id="unitNamePoint()"></i></span>
                </th>
                <th>
                    部门编号
                </th>
                <th>
                    上级直属部门
                </th>
                <th>
                    所在地区
                </th>
                <th>
                    相关操作
                </th>
            </tr>
        </table>
        <nav style="margin-top: 400px;">
            <ul class="pagination" id="pages">
            </ul>
        </nav>
    </div>
</div>
<script type="text/javascript" src="/resources/admin/js/unit/unitList.js"></script>
