$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/progress/progress_pager");

});

function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="showProgressWin btn btn-primary btn-sm">查看进度</button>'
    ].join('');

}
window.operateEvents = {
    'click .showProgressWin': function (e, value, row, index) {
        var progress = row;
        if (progress.workInfo.userId !=null && progress.workInfo.userId != '' && progress.workInfo.userId != 'null' && progress.workInfo.userId != 'undefined'){
            $("#stopAnimation_1").addClass("#animation");
        }
        $("#searchDetailWin").modal('show');
    }
}
