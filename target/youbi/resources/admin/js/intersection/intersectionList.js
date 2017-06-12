var a = $('#intersectionPages');
var curPage = 1;
var intersectionName="";
var unitId = 0;
var sortStatus = 1;
var url = "/intersection/page?nameField=" + intersectionName + "&unitId=" + unitId + "&sortStatus=" + sortStatus
    + "&pageNumber=";
for(var i = 0; i< currentList.length; i ++){
    console.log(currentList[i].isDamaged);
}
$(document).ready(function(){
    displayItems(currentList);
    displayPages(a, curPage, pageList, url + 1);
    $("#addIntersection").bind("click", function(){
        ajaxLink("/intersection/intersectionPage?intersectionId=0");
    });
    $("#nameField").bind("input propertychange", function(){
        getAjax($('#nameField').val(), !$('#unitField').val() ? 0 : $('#unitField').val() , 1, 1);
    });
    $("#unitField").bind("input propertychange", function(){
        getAjax($('#nameField').val(), !$('#unitField').val() ? 0 : $('#unitField').val() , 1, 1);
    });
    $('#mapDetail').bind("click", function(){
        ajaxLink("/intersection/mapDetail");
    })
});

function getAjax(nameField, unitField, sortStatus, pageNumber){
    url = "/intersection/page?nameField=" + nameField + "&unitId=" + unitField + "&sortStatus=" + sortStatus
        + "&pageNumber=";
    $.ajax({
        url: url + pageNumber,
        dataType: "json",
        type: "get",
        success:function(data){
            displayItems(data.currentList);
            displayPages(a, curPage, data.currentPages, url);
        }
    })
}

function displayItems(list){
    console.log(list);
    $("#intersectionTable  tr:not(:first)").empty();
    var table = document.getElementById('intersectionTable');
    if (list.length == 0) {
        $("#intersectionTable").append("<tr><td colspan='7'  style='vertical-align: middle; text-align: center'><label>没有找到符合条件的内容</label></td></tr>");
    }
    for(var i = 0; i < list.length; i++){
        var x = table.insertRow(i + 1);
        x.insertCell(0).innerHTML = "<a onclick='return null;'>" + (!list[i].intersectionName ? '': list[i].intersectionName).toString() + "</a>";
        x.insertCell(1).innerHTML = !list[i].unit ? "": list[i].unit.unitName;
        x.insertCell(2).innerHTML = !list[i].intersectionLong ? '':list[i].intersectionLong + " / "
            + (!list[i].intersectionLat ? '':list[i].intersectionLat).toString();
        x.insertCell(3).innerHTML = !list[i].intersectionType ? '': list[i].intersectionType.intersectionTypeName;
        x.insertCell(4).innerHTML = list[i].isDamaged == 0 ? "正常" : "损坏";
        x.insertCell(5).innerHTML = "<button class='btn group blue' onclick=ajaxLink('/intersection/intersectionPage?intersectionId=" +
            list[i].intersectionId +"')>"+ "修改信息" +"</button>"
    }
}

