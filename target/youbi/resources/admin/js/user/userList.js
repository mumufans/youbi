var curPage = 1;
var userName = "";
var unitId = 0;
var sortName = 1;
var sortStatus = 1;
var url = "user/page";
var data = {pageNumber: 1, userName: "", unitId : 0, sortName: 1, sortStatus: 1};
var a = $('#userPages');

$(document).ready(function () {
    if (unitList.length < 2) {
        $('#unit').remove();
    } else if (unitList[1].unitLevel == 1) {
        $('#unitFieldOne').append("<option value=''></option>");
        for (var i = 0; i < unitList.length; i++) {
            $('#unitFieldOne').append("<option value=" + unitList[i].unitId + ">" + unitList[i].unitName + "</option>");
        }
    } else if (unitList[1].unitLevel == 2) {
        $('#unitFieldOne').remove();
        for (var i = 0; i < unitList.length; i++) {
            $('#unitFieldTwo').append("<option value=" + unitList[i].unitId + ">" + unitList[i].unitName + "</option>");
        }
    } else {
        $('#unit').remove();
    }
    showItems(url, data);
});

function displayItems(list) {
    $("#userTable  tr:not(:first)").empty();
    var table = document.getElementById('userTable');
    if (list.length == 0) {
        $("#userTable").append("<tr><td colspan='7'  style='vertical-align: middle; text-align: center'><label>没有找到符合条件的内容</label></td></tr>");
    }
    for (var i = 0; i < list.length; i++) {
        var x = table.insertRow(i + 1);
        x.insertCell(0).innerHTML = !list[i].zhname ? "" : list[i].zhname;
        x.insertCell(1).innerHTML = !list[i].userName ? "" : list[i].userName;
        x.insertCell(2).innerHTML = !list[i].unit ? "" : list[i].unit.unitName;
        x.insertCell(3).innerHTML = !list[i].badeNumber ? "" : list[i].badeNumber;
        x.insertCell(4).innerHTML = !list[i].mobile ? "" : list[i].mobile;
        x.insertCell(5).innerHTML = !list[i].duty ? "" : list[i].duty;
        x.insertCell(6).innerHTML = "<input type='button' class='btn group blue' value='修改信息' onclick=ajaxLink('/user/addUserPage?userId=" + list[i].userId + "')>" +
            "<input type='button' class='btn group purple' value='重置密码' onclick=changePSW('" + list[i].userName +"'," + list[i].userId +")>" +
            "<input type='button' class='btn group red' value='删除' onclick='deleteUser(" + list[i].userId + ")'>";
    }
}

$('#nameField').bind('input propertychange', function () {
    var nameField = $('#nameField').val();
    var unitField = (!$('#unitFieldTwo').val()) ? $('#unitFieldOne').val() : $('#unitFieldTwo').val();
    if (!unitField) {
        unitField = 0;
    }
    data = {pageNumber: 1, userName: nameField, unitId : unitField, sortName: sortName, sortStatus: sortStatus};
    showItems(url, data);
});

$('#unitFieldOne').bind('input propertychange', function () {
    var nameField = $('#nameField').val();
    var unitField = $('#unitFieldOne').val();
    if (!unitField) {
        unitField = 0;
    }
    data = {pageNumber: 1, userName: nameField, unitId : unitField, sortName: sortName, sortStatus: sortStatus};
    showItems(url, data);
    $.ajax({
        url: "unit/getNextLevel?unitId=" + unitField,
        dataType: "json",
        success: function (data) {
            var unitNextLevel = data.unitNextLevel;
            var x = $('#unitFieldTwo');
            x.empty();
            x.append("<option value=''></option>")
            for (var i = 0; i < unitNextLevel.length; i++) {
                x.append("<option value='" + unitNextLevel[i].unitId + "'>"
                    + unitNextLevel[i].unitName + "</option>")
            }
        }
    })
});

$('#unitFieldTwo').bind('input propertychange', function () {
    var nameField = $('#nameField').val();
    var unitField = $('#unitFieldTwo').val();
    var unitFieldOne = $('#unitFieldOne').val();
    if (!unitField && unitFieldOne != "" && unitFieldOne != undefined && unitFieldOne != null) {
        unitField = unitFieldOne;
    }
    if (!unitField && !unitFieldOne) {
        unitField = 0;
    }
    data = {pageNumber: 1, userName: nameField, unitId : unitField, sortName: sortName, sortStatus: sortStatus};
    showItems(url, data);
});

function sortByUserName() {
    data.sortName = 1;
    data.sortStatus = sortStatus == 1 ? 2 : 1;
    showItems(url, data);
}

function sortByUnit() {
    data.sortName = 2;
    data.sortStatus = sortStatus == 1 ? 2 : 1;
    showItems(url, data);
}

function deleteUser(userId) {
    var changePSWDialog = bootbox.dialog({
        message: "您确认要删除该用户吗?",
        title: "删除用户",
        onEscape: true,
        buttons: {
            cancel: {
                label: "取消",
                className: "btn-default"
            },
            confirm: {
                label: "确认",
                className: "btn-primary",
                callback: function () {
                    $.ajax({
                        url: ctx + 'user/delete',
                        type: 'POST',
                        data: {userId: userId},
                        dataType: "json",
                        success: function (data) {
                           if(data.statusCode == 200){
                               var nameField = $('#nameField').val();
                               var unitField = $('#unitFieldOne').val();
                               if (!unitField) {
                                   unitField = 0;
                               }
                               get(nameField, unitField, sortName, sortStatus, curPage);
                               notifySuccess("删除成功！");
                           }
                            if(data.statusCode == 400){
                                notifyFail("删除失败！");
                            }
                        },
                        error: function () {
                            notifyFail("删除失败！");
                        }
                    })
                }
            }
        }
    })
}

function changePSW(name, id){
    var changePSWBootBox = bootbox.dialog({
        title: "修改密码",
        onEscape: true,
        message: "<div id='changePSWDiv'>" +
        "<form id='changePSWForm' class='form-horizontal'>" +
        "<div class='form-group'><label style='float: left;'>用户名:</label><input id='userName'value=" + name + " style='float: left;width: 200px;' readonly></div>" +
        "<div><label  style='float: left;'>密   码:</label> <input  style='float: left;width: 200px;' id='userPSW'></div>" +
        "</form></div>",
        buttons:{
            save: {
                label:"保存",
                className : "btn-success",
                callback: function(){
                    var password=$('#userPSW').val();
                    console.log(password);
                    if(!password){
                    }else{
                        $.ajax({
                            type:"POST",
                            url: "user/changePSW",
                            data: {id: id, password: password},
                            dataType:"json",
                            success:function(data){
                                console.log("______success___________");
                            },
                            error:function(){
                                console.log("__________error____________");
                            }
                        })
                    }
                }
            }
        }
    })
}

