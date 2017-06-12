var a = $('#roadwayPages');
var curPage = 1;
var nameField = "";
var url = "/management/roadway/page?nameField=" + nameField + "&pageNumber=";
$(document).ready(function () {
    displayItems(document.getElementById("roadwayBody1"), currentList1);
    if (currentList2 == "") {
        $('#roadwayTable2').attr("hidden", "hidden");
    } else {
        $('#roadwayTable2').removeAttr("hidden");
        displayItems(document.getElementById("roadwayBody2"), currentList2);
    }
    if (currentList3 == "") {
        $('#roadwayTable3').attr("hidden", "hidden");
    } else {
        $('#roadwayTable3').removeAttr("hidden");
        displayItems(document.getElementById("roadwayBody3"), currentList3);
    }
    displayPages3(a, curPage, pageList, url);
    $("#addRoad").bind("click", function () {
        editName(0);
    });
});

$('#nameField').bind('input propertychange', function () {
    nameField = $("#nameField").val()
    getList(nameField);
});

function getList(nameField) {
    var url = "/management/roadway/page?nameField=" + nameField + "&pageNumber=";
    $.ajax({
        url: url + 1,
        type: "GET",
        dataType: "json",
        success: function (data) {
            if (data.currentPages.begin == 0) {
                return;
            }
            curPage = 1;
            displayItems(document.getElementById("roadwayBody1"), data.currentList1);
            if (data.currentList2 == "") {
                $('#roadwayTable2').attr("hidden", "hidden");
            } else {
                $('#roadwayTable2').removeAttr("hidden");
                displayItems(document.getElementById("roadwayBody2"), data.currentList2);
            }
            if (data.currentList3 == "") {
                $('#roadwayTable3').attr("hidden", "hidden");
            } else {
                $('#roadwayTable3').removeAttr("hidden");
                displayItems(document.getElementById("roadwayBody3"), data.currentList3);
            }
            displayPages3(a, curPage, data.currentPages, url);
        }
    })
}

function displayItems(e, list) {
    e.innerHTML = "";
    for (var i = 0; i < list.length; i++) {
        var tr = document.createElement("tr");
        var td1 = document.createElement("td");
        td1.innerHTML = list[i].roadName;
        var td2 = document.createElement("td");
        td2.innerHTML = list[i].county.name;
        var td3 = document.createElement("td");
        td3.innerHTML = "<button class='btn blue' onclick=editName(" + list[i].roadId + ")>修改名称</button>";
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        e.appendChild(tr);
    }
}

function editName(id) {
    console.log(id);
    $.ajax({
        url: "management/roadway/editPage?id=" + id,
        type: "GET",
        success: function(data){
            var changePSWBootBox = bootbox.dialog({
                onEscape: true,
                message: data,
                buttons:{
                    cancel: {
                        label: "取消",
                        className: "btn primary"
                    },
                    confirm:{
                        label: "保存",
                        className: "btn blue",
                        callback: function(){
                            var roadway1 = $('#roadwayForm').serializeArray();
                            console.log(roadway1);
                            $.ajax({
                                url: "/management/roadway/save",
                                type: "POST",
                                dataType: "json",
                                data: roadway1,
                                success: function (data) {
                                    if(data.statusCode == 200){
                                        $('#roadwayManageLink').click();
                                    }else if(data.statusCode == 300){
                                        alert("道路名称已存在");
                                    }
                                }
                            })
                        }
                    }
                }
            })
        }
    });
}

function displayPages3(a, curPage, pages, url) {
    a.empty();
    a.append('<li id="pages"> <a onclick="lastPage3(' + "url" + "," + "a" + ')">上一页</a> </li>');
    for (var i = pages.begin; i <= pages.end; i++) {
        a.append('<li><a onclick="goToPage3( ' + i + ',' + "url" + "," + "a" + ')"><span>' + i + '</span></a></li>');
    }
    a.append('<li> <a onclick="nextPage3(' + "url" + "," + "a" + ')">下一页</a> </li>');
    a.children().eq(curPage - pages.begin + 1).children().css({backgroundColor: '#157ab5', color: 'white'});
}

function lastPage3(url, a) {
    nameField = $('#nameField').val();
    url = "/management/roadway/page?nameField=" + nameField + "&pageNumber=";
    $.ajax({
        url: url + (curPage - 1),
        dataType: "json",
        success: function (data) {
            if (data.currentPages.begin == 0) {
                return;
            }
            curPage -= 1;
            displayItems(document.getElementById("roadwayBody1"), data.currentList1);
            if (currentList2 == "") {
                $('#roadwayTable2').attr("hidden", "hidden");
            } else {
                $('#roadwayTable2').removeAttr("hidden");
                displayItems(document.getElementById("roadwayBody2"), data.currentList2);
            }
            if (currentList3 == "") {
                $('#roadwayTable3').attr("hidden", "hidden");
            } else {
                $('#roadwayTable3').removeAttr("hidden");
                displayItems(document.getElementById("roadwayBody3"), data.currentList3);
            }
            displayPages3(a, curPage, data.currentPages, url);
        },
        error: function () {
            return;
        }
    })
}

function nextPage3(url, a) {
    nameField = $('#nameField').val();
    url = "/management/roadway/page?nameField=" + nameField + "&pageNumber=";
    $.ajax({
        type: 'GET',
        url: url + (curPage + 1),
        dataType: "json",
        success: function (data) {
            if (data.currentPages.begin == 0) {
                return;
            }
            curPage += 1;
            console.log(data.currentList1);
            displayItems(document.getElementById("roadwayBody1"), data.currentList1);
            if (data.currentList2 == "") {
                $('#roadwayTable2').attr("hidden", "hidden");
            } else {
                $('#roadwayTable2').removeAttr("hidden");
                displayItems(document.getElementById("roadwayBody2"), data.currentList2);
            }
            if (data.currentList3 == "") {
                $('#roadwayTable3').attr("hidden", "hidden");
            } else {
                $('#roadwayTable3').removeAttr("hidden");
                displayItems(document.getElementById("roadwayBody3"), data.currentList3);
            }
            displayPages3(a, curPage, data.currentPages, url);
        },
        error: function () {
            return;
        }
    })
}

function goToPage3(pageNumber, url, a) {
    nameField = $('#nameField').val();
    url = "/management/roadway/page?nameField=" + nameField + "&pageNumber=";
    $.ajax({
        url: url + pageNumber,
        dataType: "json",
        success: function (data) {
            if (data.currentPages.begin == 0) {
                return;
            }
            curPage = pageNumber;
            displayItems(document.getElementById("roadwayBody1"), data.currentList1);
            if (data.currentList2 == "") {
                $('#roadwayTable2').attr("hidden", "hidden");
            } else {
                $('#roadwayTable2').removeAttr("hidden");
                displayItems(document.getElementById("roadwayBody2"), data.currentList2);
            }
            if (data.currentList3 == "") {
                $('#roadwayTable3').attr("hidden", "hidden");
            } else {
                $('#roadwayTable3').removeAttr("hidden");
                displayItems(document.getElementById("roadwayBody3"), data.currentList3);
            }
            displayPages3(a, curPage, data.currentPages, url);
        }
    })
}
