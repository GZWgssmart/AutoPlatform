var tempData = {
    chart: {
        type: 'column'
    },
    title: {
        text: '收入与支出本月统计'
    },
    yAxis: {
        min: 0,
        title: {
            text: '金额 ($)'
        }
    },
    tooltip: {
        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
        '<td style="padding:0"><b>{point.y:.1f} $</b></td></tr>',
        footerFormat: '</table>',
        shared: true,
        useHTML: true
    },
    plotOptions: {
        column: {
            pointPadding: 0.2,
            borderWidth: 0
        }
    },
    credits: {
        enabled: false
    },
    series: []
};



$(function () {
    getColumnarChart("columnar", "/incomingOutgoing/query_default", tempData,"default");

    initDateTime("datatimepicker")
});

function showSearchForm(count) {
    if(count == 1){
        $("#searchDiv1").show();
        $("#searchDiv2").hide();
        $("#searchDiv3").hide();
        $("#searchDiv4").hide();
        $("#searchDiv5").hide();
        $("#showButton1").attr("class"," btn btn-success");
        $("#showButton2").attr("class"," btn btn-primary");
        $("#showButton3").attr("class"," btn btn-primary");
        $("#showButton4").attr("class"," btn btn-primary");
        $("#showButton5").attr("class"," btn btn-primary");
        $("#start1").val("");
        $("#end1").val("");
    }else if(count == 2){
        $("#searchDiv2").show();
        $("#searchDiv1").hide();
        $("#searchDiv3").hide();
        $("#searchDiv4").hide();
        $("#searchDiv5").hide();
        $("#showButton2").attr("class"," btn btn-success");
        $("#showButton1").attr("class"," btn btn-primary");
        $("#showButton3").attr("class"," btn btn-primary");
        $("#showButton4").attr("class"," btn btn-primary");
        $("#showButton5").attr("class"," btn btn-primary");
        $("#start2").val("");
        $("#end2").val("");
    }else if(count == 3){
        $("#searchDiv3").show();
        $("#searchDiv2").hide();
        $("#searchDiv1").hide();
        $("#searchDiv4").hide();
        $("#searchDiv5").hide();
        $("#showButton3").attr("class"," btn btn-success");
        $("#showButton2").attr("class"," btn btn-primary");
        $("#showButton1").attr("class"," btn btn-primary");
        $("#showButton4").attr("class"," btn btn-primary");
        $("#showButton5").attr("class"," btn btn-primary");
        $("#start3").val("");
        $("#end3").val("");
    }else if(count == 4){
        $("#searchDiv4").show();
        $("#searchDiv2").hide();
        $("#searchDiv3").hide();
        $("#searchDiv1").hide();
        $("#searchDiv5").hide();
        $("#showButton4").attr("class"," btn btn-success");
        $("#showButton2").attr("class"," btn btn-primary");
        $("#showButton3").attr("class"," btn btn-primary");
        $("#showButton1").attr("class"," btn btn-primary");
        $("#showButton5").attr("class"," btn btn-primary");
        $("#start4").val("");
        $("#end4").val("");
    }else if(count == 5){
        $("#searchDiv5").show();
        $("#searchDiv2").hide();
        $("#searchDiv3").hide();
        $("#searchDiv4").hide();
        $("#searchDiv1").hide();
        $("#showButton5").attr("class"," btn btn-success");
        $("#showButton2").attr("class"," btn btn-primary");
        $("#showButton3").attr("class"," btn btn-primary");
        $("#showButton4").attr("class"," btn btn-primary");
        $("#showButton1").attr("class"," btn btn-primary");
        $("#start5").val("");
        $("#end5").val("");
    }

}

/** 关闭搜索的form */
function closeSearchForm(count) {
    if(count == 1){
        $("#searchDiv1").hide();
        $("#start1").val("");
        $("#end1").val("");
        $("#showButton1").attr("class"," btn btn-primary");
    }else if(count == 2){
        $("#searchDiv2").hide();
        $("#start2").val("");
        $("#end2").val("");
        $("#showButton2").attr("class"," btn btn-primary");
    }else if(count == 3){
        $("#searchDiv3").hide();
        $("#start3").val("");
        $("#end3").val("");
        $("#showButton3").attr("class"," btn btn-primary");
    }else if(count == 4){
        $("#searchDiv4").hide();
        $("#start4").val("");
        $("#end4").val("");
        $("#showButton4").attr("class"," btn btn-primary");
    }else if(count == 5){
        $("#searchDiv5").hide();
        $("#start5").val("");
        $("#end5").val("");
        $("#showButton5").attr("class"," btn btn-primary");
    }

}

function searchCheckin(count){
    var type = '';
    if(count == 1){
        type = 'year'
        var start = $("#start1").val();
        var end = $("#end1").val();
        validator(start,end,type);
    }else if(count == 2){
        type = 'quarter'
        var start = $("#start2").val();
        var end = $("#end2").val();
        validator(start,end,type);
    }else if(count == 3){
        type = 'month'
        var start = $("#start3").val();
        var end = $("#end3").val();
        validator(start,end,type);
    }else if(count == 4){
        type = 'week'
        var start = $("#start4").val();
        var end = $("#end4").val();
        validator(start,end,type);
    }else if(count == 5){
        type = 'day'
        var start = $("#start5").val();
        var end = $("#end5").val();
        validator(start,end,type);
    }
}

function initDateTime(clazz) {
    $('.' + clazz).datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        initialDate: new Date(),
        autoclose: true,
        todayHighlight: true,
        minView: "month",//选择日期后，不会再跳转去选择时分秒
        todayBtn: true//显示今日按钮
    })
}

function validator( start, end, type){
    if(start != '' && end != ''){
        getColumnarChart("columnar", "/incomingOutgoing/query_condition?start=" + start +"&end=" + end + "&type=" + type, tempData,type);
    }else{
        getColumnarChart("columnar", "/incomingOutgoing/query_default", tempData,"default");
    }
}
