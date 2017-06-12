var curPage = 1;
var userName = "";
var unitId = 0;
var sortName = 1;
var sortStatus = 1;
var url = "user/authPage";
var a = $('#userPages');
var data = {pageNumber: 1, userName: "", unitId : 0, sortName: 1, sortStatus: 1};

$(document).ready(function () {
    if (unitList.length < 2) {
        $('#unit').remove();
    } else if (unitList[1].unitLevel == 1) {
        $('#unitFieldOne').append("<option value=''></option>")
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
        x.insertCell(1).innerHTML = !list[i].unit ? "" : list[i].unit.unitName;
        x.insertCell(2).innerHTML = !list[i].duty ? "" : list[i].duty;
        x.insertCell(3).innerHTML = !list[i].role ? "" : list[i].role.roleZhname;
        x.insertCell(4).innerHTML = !list[i].adminUnit ? "" : list[i].adminUnit.unitName;
        x.insertCell(5).innerHTML = !list[i].duty ? "" : list[i].duty;
        x.insertCell(6).innerHTML = "<input type='button' class='btn blue' value='修改权限' onclick=ajaxLink('/user/editAuthPage?userId=" + list[i].userId + "')>";
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
