var curPage = 1;
var sort = 1;
var url = "unit/page";
var a = $('#pages');
var data = {sort: 1, pageNumber: 1};

$(document).ready(function () {
    showItems(url, data);
});

function displayItems(list) {
    $("#unitTable  tr:not(:first)").empty();
    if (list.length == 0) {
        $("#unitTable").append("<tr><td colspan='7'  style='vertical-align: middle; text-align: center'><label>没有找到符合条件的内容</label></td></tr>");
    }
    var table = document.getElementById('unitTable');
    for (var i = 0; i < list.length; i++) {
        console.log(list);
        var x = table.insertRow(i + 1);
        x.insertCell(0).innerHTML = !list[i].unitName ? "" : list[i].unitName;
        x.insertCell(1).innerHTML = !list[i].unitNumber ? "": list[i].unitNumber;
        x.insertCell(2).innerHTML = !list[i].parent ? "" : list[i].parent.unitName;
        x.insertCell(3).innerHTML = !list[i].county ? "" : list[i].county.name;
        x.insertCell(4).innerHTML = "<input type='button' class='btn blue group' value='修改信息' onclick=ajaxLink('/unit/unitPage?unitId=" + list[i].unitId +
            "')>" + "<input type='button' class='btn red group' value='删除' onclick=deleteUnit("+ list[i].unitId + ")>"
    }
}

function sortByUnitName(){
    sort = sort == 1 ? 2 : 1;
    data.sort = sort;
    showItems(url, data);
}

function deleteUnit(unitId){
    var deleteUnit = bootbox.dialog({
        message: "确认要删除该单位吗?",
        title: "删除确认",
        onEscape: true,
        buttons: {
            cancel: {
                label: "取消",
                className : "btn"
            },
            conform:{
                label: "确认",
                className: "btn red",
                callback:function(){
                    $.ajax({
                        url: "unit/delete",
                        type: "POST",
                        data: {unitId: unitId},
                        dataType: "json",
                        success:function(data){
                            if(data.statusCode == 200){
                                notifySuccess("删除成功!");
                                $('#unitManageLink').click();
                            }
                            if(data.statusCode == 400){
                                alert("删除失败\n" + data.message);
                            }
                        }
                    })
                }
            }
        }
    })
}

