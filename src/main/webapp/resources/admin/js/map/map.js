var map = new AMap.Map('mapDetail', {
    zoom: 13,
    center: [116.310484,37.452914]
});
console.log("_____________________");
console.log(mapMarkers);
for(var i = 0; i < mapMarkers.length; i++){
    var marker = new AMap.Marker({
        map: map,
        position: [mapMarkers[i].lon, mapMarkers[i].lat]
    })
}
// var marker1 = new AMap.Marker({
//     map: map,
//     position: [119.473188, 30.993253]
// });
// marker1.setLabel({
//     offset: new AMap.Pixel(20, 20),//修改label相对于maker的位置
//     content: "点击查看车辆详情"
// });
// var marker2 = new AMap.Marker({
//     map: map,
//     position: [118.473188, 35.993253]
// });
// marker2.setLabel({
//     offset: new AMap.Pixel(20, 20),//修改label相对于maker的位置
//     content: "点击查看车辆详情"
// });
// var marker3 = new AMap.Marker({
//     map: map,
//     position: [114.473188, 33.993253]
// });
// marker3.setLabel({
//     offset: new AMap.Pixel(20, 20),//修改label相对于maker的位置
//     content: "点击查看车辆详情"
// });
