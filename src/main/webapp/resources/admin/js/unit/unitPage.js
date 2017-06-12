$(document).ready(function () {
    $('#unitName').focus(function(){
        $('#hint').remove();
        $('#unitName').removeClass('alert-danger');
    });
    $('#submitBtn').click(function (){
        if(!$('#unitName').val()){
            $('#hint').remove();
            $('#unitName').parent().append('<p id="hint" class="text-danger">单位名称不能为空</p>');
            $('#unitName').addClass('text-danger');
            return ;
        }
        var unit = {unitId: $('#unitId').val(),unitName: $('#unitName').val(), unitNumber: $('#unitNumber').val(),
            parent:$('#parent').val(), county: !$('#county').val() ? null : $('#county').val()};
        $.ajax({
            type: 'POST',
            url: ctx + '/unit/add',
            data: unit,
            dataType: "json",
            success: function (data) {
                if (data.statusCode == 200) {
                    $('#unitManageLink').click();
                } else if (data.statusCode == 300) {
                    $('#hint').remove();
                    $('#unitName').parent().append('<p id="hint" class="text-danger">单位名称已存在</p>');
                    $('#unitName').addClass('alert-danger');
                } else if (data.statusCode == 400) {
                    window.location.href = '/error';
                }
            }
        })
    })
    $('#exist').bind('click', function(){
        $('#unitManageLink').click();
    })
});