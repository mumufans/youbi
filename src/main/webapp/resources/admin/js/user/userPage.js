$(document).ready(function () {
    if (x.unitOne) {
        $('#unitLevelOne').val(x.unitOne);
    }
    if (x.unitTwo) {
        $('#unitLevelTwo').val(x.unitTwo);
    }

    $('#submitBtn').bind('click', function () {
        $('#submitBtn').attr('disabled', 'disabled');
        if (!$('#userName').val()) {
            $('#nameHint').remove();
            $('#userName').parent().append('<span id="nameHint" class="text-danger">登陆名不能为空</span>');
            $('#userName').addClass('danger');
            return;
        }
        if (!/^[A-Za-z0-9]+$/.test($('#userName').val())) {
            $('#nameHint').remove();
            $('#userName').parent().append('<span id="nameHint" class="text-danger">登录名只能为数字和字母</span>');
            $('#userName').addClass('danger');
            return;
        }
        if (!$('#zhname').val()) {
            $('#zhnameHint').remove();
            $('#zhname').parent().append('<span id="zhnameHint" class="text-danger">姓名不能为空</span>');
            $('#zhname').addClass('danger');
            return;
        }
        if (($('#mobile').val() !== null && $('#mobile').val() !== undefined && $('#mobile').val() !== "") && !/^1\d{10}$/.test($('#mobile').val())) {
            $('#mobileHint').remove();
            $('#mobile').parent().append('<span id="mobileHint" class="text-danger">手机号码格式错误</span>');
            return;
        }
        if (!$('#unitLevelOne').val()) {
            $('#unitHint').remove();
            $('#unitLevelOne').parent().append('<span id="unitHint" class="text-danger">上属部门不能为空</span>');
            $('#unitLevelOne').addClass('danger');
            return;
        }
        var user = $('#addUserForm').serializeArray();
        if (!$('#unitLevelTwo option:selected').val()) {
            user.push({name: "unit.unitId", value: $('#unitLevelOne option:selected').val()})
        } else {
            user.push({name: "unit.unitId", value: $('#unitLevelTwo option:selected').val()})
        }
        $.ajax({
            type: 'POST',
            url: '/user/addUser',
            data: user,
            dataType: "json",
            success: function (data) {
                if (data.statusCode == 300) {
                    alert("用户名已存在");
                } else if (data.statusCode == 400) {
                    notifyFail("保存失败！");
                } else {
                    notifySuccess("保存成功！");
                    $('#userManageLink').click();
                }
            }
        })
    });

    $('#userName').bind('change', function(){
       $('#submitBtn').removeAttr("disabled");
    });
    $('#zhname').bind('change', function(){
        $('#submitBtn').removeAttr("disabled");
    });
    $('#mobile').bind('change',function(){
        $('#submitBtn').removeAttr("disabled");
    });
    $('#unitLevelOne').bind('change',function(){
        $('#submitBtn').removeAttr("disabled");
    })

    $('#exist').bind('click', function () {
        $('#userManageLink').click();
    })
});

function getNextLevel() {
    var unitId = !$('#unitLevelOne option:selected').val() ? 0 : $('#unitLevelOne option:selected').val();
    $.ajax({
        url: '/unit/getNextLevel?unitId=' + unitId,
        dataType: "json",
        success: function (data) {
            var unitLevelTwoList = data.unitNextLevel;
            $('#unitLevelTwo').empty();
            $('#unitLevelTwo').append("<option value=''></option>");
            for (var i = 0; i < unitLevelTwoList.length; i++) {
                $('#unitLevelTwo').append("<option value='" + unitLevelTwoList[i].unitId +
                    "'>" + unitLevelTwoList[i].unitName +
                    "</option>")
            }
        }
    })
}
