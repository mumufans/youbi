var curPage = 1;
var data = {signalTypeId: 0, countyId: 0, unitId: 0, roadId: 0,beginTime: null, endTime: null, isDamaged: 0, isUsed: 1,
    isConfirmed : 0, orderStatus: false, pageNumber: 1};
var url = "/management/signal/list";
var a = $('#signalPages');
$(document).ready(function(){
    showItems(url, data);
});
$('#addSignalBtn').bind('click', function(){
    ajaxLink('/management/signal/editPage?id=0');
});
function displayItems(list){
    console.log(list);
    $("#signalTable  tr:not(:first)").empty();
    var table = document.getElementById('signalTable');
    if (list.length == 0) {
        $("#signalTable").append("<tr><td colspan='7'  style='vertical-align: middle; text-align: center'><label>没有找到符合条件的内容</label></td></tr>");
    } else {
        for(var i = 0; i < list.length; i++){
            var x = table.insertRow(i + 1);
            x.insertCell(0).innerHTML = list[i].signalLong.toString() + ", " + list[i].signalLat.toString();
            x.insertCell(1).innerHTML = list[i].signalType.signalTypeName;
            x.insertCell(2).innerHTML = list[i].county.name;
            x.insertCell(3).innerHTML = list[i].unit.unitName;
            x.insertCell(4).innerHTML = list[i].roadway.roadName;
            x.insertCell(5).innerHTML = list[i].isDamaged == true ? "故障" : "正常";
            x.insertCell(6).innerHTML = list[i].isConfirmed == true ? "通过" : "未审核";
            x.insertCell(7).innerHTML = list[i].user.zhname;
            x.insertCell(8).innerHTML = !list[i].description ? "" : list[i].description;
            x.insertCell(9).innerHTML = list[i].createTime;
            x.insertCell(10).innerHTML = "<button class='btn blue group' onclick=updateSignal(" + list[i].signalId +
                ")>修改信息</button>" +  "<button class='btn blue group' onclick=deleteSignal(" + list[i].signalId +
                ")>删除</button>";
        }
    }
}

function updateSignal(signalId){
    ajaxLink("/management/signal/editPage?id=" + signalId);
}

function deleteUser(signalId) {
    var dialog = bootbox.dialog({
        message: "您确认要删除此信号灯吗?",
        title: "删除",
        onEscape: true,
        buttons: {
            confirm: {
                label: "确认",
                className: "btn-primary",
                callback: function () {
                    $.ajax({
                        url: ctx + 'management/signal/delete/' + signalId,
                        type: 'DELETE',
                        dataType: "json",
                        success: function (data) {
                            if(data.statusCode == 200){
                                // var nameField = $('#nameField').val();
                                // var unitField = $('#unitFieldOne').val();
                                // if (!unitField) {
                                //     unitField = 0;
                                // }
                                // get(nameField, unitField, sortName, sortStatus, curPage);
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
            },
            cancel: {
                label: "取消",
                className: "btn-default"
            }
        }
    })
}
