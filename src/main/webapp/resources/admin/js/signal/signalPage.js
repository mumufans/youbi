$(document).ready(function () {
});

var files = [];

$('#submitBtn').bind('click', function () {
    signalValidator(function (isError) {
        $('#submitBtn').attr('disabled', 'disabled');
        if (isError) {
            return;
        } else {
            var signal = $('#signalForm').serializeArray();
            // var data1 = document.getElementById("signalForm");
            // var formData = new FormData(data1);
            // console.log(formData);
            $.ajax({
                url: "/management/signal/save",
                data: signal,
                cache: false,
                contentType: false,
                processData: false,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    $('#signalManageLink').click();
                }
            })
        }
    })
});

$('#signalImages').bind('change', function () {
    var input = document.getElementById("signalImages");
    files.push.apply(files, input.files);
    input.files = files;
    console.log(files.length);
    console.log(input.files.length);
});

$('#pointBtn').bind('click', function () {
    var map = new AMap.Map('getPoint', {
        zoom: 13,
        center: [116.310484,37.452914],
        resizeEnable: true
    });
    var clickEventListener = map.on('click', function(e) {
        document.getElementById("lnglat").value = e.lnglat.getLng() + ',' + e.lnglat.getLat()
    });
    var auto = new AMap.Autocomplete({
        input: "jiaoxinguang"
    });
    AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
    function select(e) {
        if (e.poi && e.poi.location) {
            map.setZoom(15);
            map.setCenter(e.poi.location);
        }
    }
    $('#myModal').modal({
        keyboard: true
    })
});

$('#signalForm').find(".form-input").bind("focus", function () {
    $('#submitBtn').removeAttr('disabled');
});

function signalValidator(callback) {
    var isError = false;
    if (!$('#signalType').val()) {
        isError = true;
        $('#typeHint').remove();
        $('#signalType').parent().append('<span id="typeHint" class="text-danger">信号灯类型不能为空</span>');
        $('#signalType').addClass('danger');
    }
    if (!$('#roadway').val()) {
        isError = true;
        $('#roadHint').remove();
        $('#roadway').parent().append('<span id="roadHint" class="text-danger">所属道路不能为空</span>');
        $('#roadway').addClass('danger');
    }
    var a = document.getElementById('unit');
    if (a) {
        if (!$('#unit').val()) {
            isError = true;
            $('#unitHint').remove();
            $('#unit').parent().append('<span id="unitHint" class="text-danger">所属部门不能为空</span>');
            $('#unit').addClass('danger');
        }
    }
    if (!$('#signalLong').val()) {
        isError = true;
        $('#longHint').remove();
        $('#signalLong').parent().append('<span id="longHint" class="text-danger">经度不能为空</span>');
        $('#signalLong').addClass('danger');
    }
    if (!$('#signalLat').val()) {
        isError = true;
        $('#latHint').remove();
        $('#signalLat').parent().append('<span id="latHint" class="text-danger">/纬度不能为空</span>');
        $('#signalLat').addClass('danger');
    }
    return callback(isError);
}
