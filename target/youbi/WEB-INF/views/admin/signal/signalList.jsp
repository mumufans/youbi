<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2017/4/15
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel light-grey">
    <div class="panel-heading">
        <span style="color: #265a88"><label>信号灯管理</label> / 信号灯列表</span>
    </div>
    <div class="panel-body">
        <button class="btn blue" id="addSignalBtn">添加信号灯</button>
        <table class="table  main table-bordered table-hover table-striped" id="signalTable">
            <tr>
                <th>
                    经度/纬度
                </th>
                <th>
                    信号灯类型
                </th>
                <th>
                    所属区县
                </th>
                <th>
                    所属部门
                </th>
                <th>
                    所在道路
                </th>
                <th>
                    是否损坏
                </th>
                <th>
                    是否审核通过
                </th>
                <th>
                    创建人
                </th>
                <th>
                    备注
                </th>
                <th style="cursor:pointer;" onclick="sortByDate()">
                    创建日期
                </th>
                <th>
                    操作
                </th>
            </tr>
        </table>
        <nav style="margin-top: 400px;">
            <ul class="pagination" id="signalPages">
            </ul>
        </nav>
    </div>
</div>
<script src="/resources/admin/js/signal/signalList.js"/>
