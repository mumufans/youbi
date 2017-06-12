$(document).ready(function () {
    $("#roadways").select2({
        data: roadways,
        multiple: true,
        allowClear: true
    });
    $('#roadways').val(records).trigger('change');

    $('#exist').bind('click', function(){
        $('#intersectionManageLink').click();
    });

    $('#submitBtn').bind('click', function(){
        var intersection = $('#intersectionForm').serializeArray();
        intersection.push({name: "roadwayIds", value: $('#roadways').val()});
        intersectionValidator(function(ifErr){
            if(ifErr){
                return;
            }else{
                $.ajax({
                    url: "/intersection/save",
                    type: "POST",
                    data: intersection,
                    dataType: "json",
                    success:function(data){
                        if(data.statusCode == 200){
                            $('#intersectionManageLink').click();
                        }else if(data.statusCode == 300){
                            alert("路口名称已存在");
                        }else{
                            alert(data.message);
                        }
                    }
                })
            }
        });
    })
});

function intersectionValidator(callback){
    var ifErr = false;
    if(!$('#intersectionName').val()){
        ifErr = true;
        $('#nameHint').remove();
        $('#intersectionName').parent().append('<span id="nameHint" class="text-danger">路口名称不能为空</span>');
        $('#intersectionName').addClass('danger');
    }
    if(!$('#intersectionType').val()){
        ifErr = true;
        $('#typeHint').remove();
        $('#intersectionType').parent().append('<span id="typeHint" class="text-danger">路口类型不能为空</span>');
        $('#intersectionType').addClass('danger');
    }
    if(!$('#intersectionLong').val()){
        ifErr = true;
        $('#longHint').remove();
        $('#intersectionLong').parent().append('<span id="longHint" class="text-danger">经度不能为空/</span>');
        $('#intersectionLong').addClass('danger');
    }
    if(!$('#intersectionLat').val()){
        ifErr = true;
        $('#latHint').remove();
        $('#intersectionLat').parent().append('<span id="latHint" class="text-danger">纬度不能为空</span>');
        $('#intersectionLat').addClass('danger');
    }
    if(!$('#intersectionLat').val()){
        ifErr = true;
        $('#unitHint').remove();
        $('#unit').parent().append('<span id="unitHint" class="text-danger">所属单位不能为空</span>');
        $('#unit').addClass('danger');
    }
    if(!$('#roadways').val()){
        ifErr = true;
        $('#roadHint').remove();
        $('#roadways').parent().append('<span id="roadHint" class="text-danger">经过道路不能为空</span>');
        $('#roadways').addClass('danger');
    }
    return callback(ifErr);
}
