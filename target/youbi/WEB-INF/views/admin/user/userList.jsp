<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var unitList = (${unitLevelOne});
</script>
<div class="panel light-grey">
    <div class="panel-heading">
        <span style="color: #265a88"><label>用户管理</label> / 用户列表</span>
    </div>
    <div class="panel-body">
        <p>
            <button class="btn blue" onclick="ajaxLink('/user/addUserPage?userId=0')">添加用户</button>
        </p>
        <div style="height: 30px;">
            <div class="control-group">
                <label style="margin-left: 50px;">按姓名查找:&nbsp;&nbsp;</label>
                <input type="text" id="nameField"
                       style="height: 25px;"/>
                <label style="margin-left: 150px;">按部门查找:&nbsp;&nbsp;</label>
                <select id="unitFieldOne" class="my-input">
                </select>
                <select id="unitFieldTwo" class="my-input">
                    <option value=""></option>
                </select>
            </div>
        </div>
        <table class="table  main table-bordered table-hover table-striped" id="userTable">
            <tr>
                <th style=" cursor:pointer;" onclick="sortByUserName()">
                    姓 名&nbsp;<span style="color: #265a88;"><i id="userNamePoint()"></i></span>
                </th>
                <th>
                    登陆名
                </th>
                <th style="cursor:pointer;" onclick="sortByUnit()">
                    上级直属部门&nbsp;<span style="color: #265a88;"><i id="unitPoint()"></i></span>
                </th>
                <th>
                    警号
                </th>
                <th>
                    手机号
                </th>
                <th>
                    职务
                </th>
                <th>
                    相关操作
                </th>
            </tr>
        </table>
        <nav style="margin-top: 400px;">
            <ul class="pagination" id="userPages">
            </ul>
        </nav>
    </div>
</div>
<script type="text/javascript" src="/resources/admin/js/user/userList.js"></script>
