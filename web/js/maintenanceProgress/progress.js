$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/progress/progress_pager_Y");
});

function searchStatus_Y() {
    initTable("cusTable", "/progress/progress_pager_Y");
}

function searchStatus_N() {
    initTable("cusTable", "/progress/progress_pager_N");
}

function searchStatus_All() {
    initTable("cusTable", "/progress/progress_pager");
}

function recordOk() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "请选择一条数据", "error");
        return false;
    } else {
        var record = selectRow[0]
        if (record.speedStatus == "已登记" || record.speedStatus == "维修保养中") {
            $.get(  '/record/achieve_record?recordId=' + record.recordId, function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                    swal("成功提示", data.message, "success");
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, 'json');
        } else {
            swal('错误提示', "请不要重复确认!", "error");
            return false;
        }
    }

}

function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="showProgressWin btn btn-primary btn-sm">查看进度</button>'
    ].join('');

}
window.operateEvents = {
    'click .showProgressWin': function (e, value, row, index) {
        var progress = row;
        $("#stopAnimation_01").hide();
        $("#stopAnimation_02").hide();
        $("#animation_2").hide();
        $("#stopAnimation_03").hide();
        $("#animation_3").hide();
        $("#stopAnimation_04").hide();
        $("#animation_4").hide();
        $("#stopAnimation_05").hide();
        $("#animation_5").hide();
        $("#textColor_01").hide();
        $("#textColor_02").hide();
        $("#textColor_03").hide();
        $("#textColor_04").hide();
        $("#textColor_05").hide();
        $("#des_2").hide();
        $("#des_3").hide();
        $("#des_4").hide();
        $("#des_5").hide();
        $("#des_6").hide();
        if (progress.workInfo.userId !=null && progress.workInfo.userId != '' && progress.workInfo.userId != 'null' && progress.workInfo.userId != 'undefined'){
            $("#stopAnimation_01").show();
            $("#stopAnimation_02").show();
            $("#animation_1").hide();
            $("#animation_2").hide();
            $("#stopAnimation_2").hide();
            $("#stopAnimation_3").hide();
            $("#animation_3").show();
            $("#text_1").hide();
            $("#textColor_01").show();
            $("#text_2").hide();
            $("#textColor_02").show();
            $("#des_3").show();
            $("#des_1").hide();
        } else {
            $("#stopAnimation_01").hide();
            $("#stopAnimation_02").hide();
            $("#animation_1").show();
            $("#animation_2").show();
            $("#stopAnimation_2").show();
            $("#stopAnimation_3").show();
            $("#animation_3").hide();
            $("#text_1").show();
            $("#textColor_01").hide();
            $("#text_2").show();
            $("#textColor_02").hide();
            $("#des_3").hide();
            $("#des_1").show();
        }
        if(progress.pickupTime !=null && progress.pickupTime != '' && progress.pickupTime != 'null' && progress.pickupTime != 'undefined'){
            $("#stopAnimation_03").show();
            $("#animation_3").hide();
            $("#stopAnimation_4").hide();
            $("#animation_4").show();
            $("#text_3").hide();
            $("#textColor_03").show();
            $("#des_4").show();
            $("#des_3").hide();
        } else {
            $("#stopAnimation_03").hide();
            $("#animation_3").show();
            $("#stopAnimation_4").show();
            $("#animation_4").hide();
            $("#text_3").show();
            $("#textColor_03").hide();
            $("#des_4").hide();
            $("#des_3").show();
        }
        if(progress.chargeBill.recordId !=null && progress.chargeBill.recordId != '' && progress.chargeBill.recordId != 'null' && progress.chargeBill.recordId != 'undefined'){
            $("#stopAnimation_04").show();
            $("#animation_4").hide();
            $("#stopAnimation_5").hide();
            $("#animation_5").show();
            $("#text_4").hide();
            $("#textColor_04").show();
            $("#des_5").show();
            $("#des_4").hide();
        } else {
            $("#stopAnimation_04").hide();
            $("#animation_4").show();
            $("#stopAnimation_5").show();
            $("#animation_5").hide();
            $("#text_4").show();
            $("#textColor_04").hide();
            $("#des_5").hide();
            $("#des_4").show();
        }
        if(progress.actualEndTime !=null && progress.actualEndTime != '' && progress.actualEndTime != 'null' && progress.actualEndTime != 'undefined'){
            $("#stopAnimation_05").show();
            $("#animation_5").hide();
            $("#text_5").hide();
            $("#textColor_05").show();
            $("#des_5").hide();
            $("#des_6").show();
        } else {
            $("#stopAnimation_05").hide();
            $("#animation_5").show();
            $("#text_5").show();
            $("#textColor_05").hide();
            $("#des_6").hide();
            $("#des_5").show();
        }
        $("#searchDetailWin").modal('show');
    }
}
