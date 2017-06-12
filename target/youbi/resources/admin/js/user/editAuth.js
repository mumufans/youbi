$(document).ready(function () {
    $('#submitBtn').click(function () {
        $('#hint').remove();
        var roleName = $('#role option:selected').attr('name');
        var adminUnit = $('#adminLevelOne').val();
        if (roleName=="CAPTAIN" && (adminUnit=="" || adminUnit=="undefined"|| adminUnit==""||adminUnit==undefined)) {
            $('#adminUnit').append('<span id="hint" class="text-danger">角色为部门管理员时，单位名称不能为空</span>');
            $('#adminLevelOne').addClass("alert-text");
            return;
        } else {
            var user = [];
            user.push({name: "userId", value: $('#userId').val()});
            user.push({name: "userName", value: $('#userName').val()});
            user.push({name: "duty", value: $('#duty').val()});
            user.push({name: "role.roleId", value: $('#role').val()});
            console.log( user.push({name: "adminunit.unitId", value:$('#adminLevelOne option:selected').val()}));
            if ($('#adminLevelOne option:selected').val() != "undefined" && $('#adminLevelOne option:selected').val()) {
                console.log("_________________");
                user.push({name: "adminunit.unitId", value:$('#adminLevelOne option:selected').val()});
            }
            $.ajax({
                type: 'POST',
                url: '/user/addUser',
                data: user,
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data.statusCode == 300) {
                        notifyFail('保存失败');
                    } else if (data.statusCode == 400) {
                        notifyFail("保存失败！");
                    } else {
                        notifySuccess("保存成功！");
                        $('#authManageLink').click();
                    }
                }
            })
        }
    });
    $('#adminLevelOne').bind('onfocus',function () {
        $('#hint').remove();
    });

    $('#role').bind('change',function(){
        var roleName = $('#role option:selected').attr('name');
        console.log(roleName);
        if(roleName=="CAPTAIN"){
            $.ajax({
                url: '/unit/getDropList',
                dataType: "json",
                type: "GET",
                success: function(data){
                    var levelOne = data.levelOne;
                    for (var i = 0; i < data.levelOne.length; i++) {
                        var valueOne = (levelOne[i].unitId == 'undefined') ? '' : levelOne[i].unitId;
                        var textOne = (levelOne[i].unitName == undefined) ? "" : levelOne[i].unitName;
                        $('#adminLevelOne').append("<option value=" + valueOne + ">" +
                            textOne + "</option>");
                    }
                }
            })
        }else{
            $('#adminLevelOne').empty();
        }
    })
});

function getNextLevelA() {
    var unitId = $('#adminLevelOne option:selected').val();
    $.ajax({
        url: '/unit/getNextLevel?unitId=' + unitId,
        dataType: "json",
        success: function (data) {
            var unitLevelTwoList = data.unitNextLevel;
            $('#adminLevelTwo').empty();
            $('#adminLevelTwo').append("<option value=''></option>");
            for (var i = 0; i < unitLevelTwoList.length; i++) {
                $('#adminLevelTwo').append("<option value='" + unitLevelTwoList[i].unitId +
                    "'>" + unitLevelTwoList[i].unitName +
                    "</option>")
            }
        }
    })
}

// function getAdmin() {
//     var roleName = $('#role option:selected').attr('name');
//     if (roleName == "CAPTAIN") {
//         $.ajax({
//             url: '/unit/getDropList',
//             dataType: "json",
//             type: "GET",
//             success: function (data) {
//                 $('#adminLevelOne').empty();
//                 $('#adminLevelTwo').empty();
//                 if (data.levelOne) {
//                     var levelOne = data.levelOne;
//                     var levelTwo = data.levelTwo;
//                     for (var i = 0; i < data.levelOne.length; i++) {
//                         var valueOne = (levelOne[i].unitId == 'undefined') ? '' : levelOne[i].unitId;
//                         var textOne = (levelOne[i].unitName == undefined) ? "" : levelOne[i].unitName;
//                         $('#adminLevelOne').append("<option value=" + valueOne + ">" +
//                             textOne + "</option>");
//                     }
//                 }
//                 if (data.levelTwo) {
//                     var levelTwo = data.levelTwo;
//                     for (var i = 0; i < levelTwo.length; i++) {
//                         var valueTwo = (levelTwo[i].unitId == 'undefined') ? '' : levelTwo[i].unitId;
//                         var textTwo = (levelTwo[i].unitName == undefined) ? "" : levelTwo[i].unitName;
//                         $('#adminLevelTwo').append("<option value=" + valueTwo + ">" +
//                             textTwo + "</option>");
//                     }
//
//                 }
//
//             }
//         })
//     } else {
//         $('#adminLevelOne').empty();
//         $('#adminLevelTwo').empty();
//     }
// }