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
    getColumnarChart("columnar", "/incomingOutgoing/query_default", tempData,"default","收入与支出本月统计");
    initDateTime("datatimepicker")

    $("#myTab a").click(function(e){
        $(this).tab("show");
        $(".datatimepicker").val("");
    });
});

function search(count){
    var type = '';
    if(count == 1){
        type = 'year'
        var start = $("#start1").val();
        var end = $("#end1").val();
        validator(start,end,type,"收入与支出年统计");
    }else if(count == 2){
        type = 'quarter'
        var start = $("#start2").val();
        var end = $("#end2").val();
        validator(start,end,type,'收入与支出季度统计');
    }else if(count == 3){
        type = 'month'
        var start = $("#start3").val();
        var end = $("#end3").val();
        validator(start,end,type,'收入与支出月统计');
    }else if(count == 4){
        type = 'week'
        var start = $("#start4").val();
        var end = $("#end4").val();
        validator(start,end,type,'收入与支出周统计');
    }else if(count == 5){
        type = 'day'
        var start = $("#start5").val();
        var end = $("#end5").val();
        validator(start,end,type,'收入与支出日统计');
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

function validator( start, end, type,text){
    if(start != '' && end != ''){
        getColumnarChart("columnar", "/incomingOutgoing/query_condition?start=" + start +"&end=" + end + "&type=" + type, tempData,type,text);
    }else{
        getColumnarChart("columnar", "/incomingOutgoing/query_default", tempData,"default","收入与支出本月统计");
    }
}
