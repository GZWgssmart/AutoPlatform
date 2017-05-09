/**
 * Created by Xiao-Qiang on 2017/4/26.
 */
var contextPath = '';
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", contextPath + "/materialList/query_pager");
    initDateTimePickerNotValitor("form_datetime");
    $("#search").bind("click", initTable);
});

function operateFormatter(value, row, index) {
    if (row.materialStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-success  btn-sm">冻结</button>',
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-danger  btn-sm">激活</button>',
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var status = 'N';
        $.get(contextPath + "/materialList/update_status?id=" + row.materialId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var status = 'Y';
        $.get(contextPath + "/materialList/update_status?id=" + row.materialId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    }
}

function bySelectSearch() {
    var userName = $("#searchUserName").val();
    var startTime = $("#createTimeStart").val();
    var endTime = $("#createTimeEnd").val();
    initTable("cusTable", "/materialList/select_query?userName=" + userName + "&startTime=" + startTime + "&endTime=" + endTime);
}

function queryStatus(status) {
    initTable('cusTable', contextPath + '/materialList/queryByStatus_materialList?status=' + status);
}

function queryAll() {
    initTable('cusTable', contextPath + '/materialList/query_pager');
}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#createTimeStart").val('');
    $("#createTimeEnd").val('');
    $("#searchUserName").val('');
    $("#searchDiv").hide();
    $("#showButton").show();
}
