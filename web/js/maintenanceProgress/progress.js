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
        }
        if(progress.pickupTime !=null && progress.pickupTime != '' && progress.pickupTime != 'null' && progress.pickupTime != 'undefined'){
            $("#stopAnimation_03").show();
            $("#animation_3").hide();
            $("#stopAnimation_4").hide();
            $("#animation_4").show();
            $("#text_3").hide();
            $("#textColor_03").show();
        } else {
            $("#stopAnimation_03").hide();
            $("#animation_3").show();
            $("#stopAnimation_4").show();
            $("#animation_4").hide();
            $("#text_3").show();
            $("#textColor_03").hide();
        }
        if(progress.chargeBill.recordId !=null && progress.chargeBill.recordId != '' && progress.chargeBill.recordId != 'null' && progress.chargeBill.recordId != 'undefined'){
            $("#stopAnimation_04").show();
            $("#animation_4").hide();
            $("#stopAnimation_5").hide();
            $("#animation_5").show();
            $("#text_4").hide();
            $("#textColor_04").show();
        } else {
            $("#stopAnimation_04").hide();
            $("#animation_4").show();
            $("#stopAnimation_5").show();
            $("#animation_5").hide();
            $("#text_4").show();
            $("#textColor_04").hide();
        }
        if(progress.actualEndTime !=null && progress.actualEndTime != '' && progress.actualEndTime != 'null' && progress.actualEndTime != 'undefined'){
            $("#stopAnimation_05").show();
            $("#animation_5").hide();
            $("#text_5").hide();
            $("#textColor_05").show();
        } else {
            $("#stopAnimation_05").hide();
            $("#animation_5").show();
            $("#text_5").show();
            $("#textColor_05").hide();
        }
        $("#searchDetailWin").modal('show');
    }
}
