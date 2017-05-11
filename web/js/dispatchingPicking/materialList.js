/**
 * Created by Xiao-Qiang on 2017/4/26.
 */
var contextPath = '';
$(document).ready(function () {
    //调用函数，初始化表格
    var speedStatus = "维修保养中";
    initTable("cusTable", contextPath + "/record/pager_speedStatus?speedStatus=" + speedStatus);
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
                    $('#cusTable1').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                } else if (data.result == "notLogin") {
                    swal({
                            title: "登入失败",
                            text: data.message,
                            type: "warning",
                            showCancelButton: false,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "确认",
                            closeOnConfirm: true
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                top.location.href = "/login/show_login";
                            }
                        });
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var status = 'Y';
        $.get(contextPath + "/materialList/update_status?id=" + row.materialId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable1').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                } else if (data.result == "notLogin") {
                    swal({
                            title: "登入失败",
                            text: data.message,
                            type: "warning",
                            showCancelButton: false,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "确认",
                            closeOnConfirm: true
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                top.location.href = "/login/show_login";
                            }
                        });
                }
            }, "json");
    }
}

function bySelectSearch() {
    var userName = $("#searchUserName").val();
    var startTime = $("#createTimeStart").val();
    var endTime = $("#createTimeEnd").val();
    initTableSetToolbar("cusTable1", contextPath + "/materialList/select_query?userName=" + userName + "&startTime=" + startTime + "&endTime=" + endTime, 'toolbar1');
}

function queryStatus(status) {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    var record = selectRow[0];
    var recordId = record.recordId;
    initTableSetToolbar('cusTable1', contextPath + '/materialList/queryByStatus_materialList?status=' + status + '&recordId=' + recordId, 'toolbar1');
}

function queryAll() {
    initTableSetToolbar('cusTable1', contextPath + '/materialList/query_pager', 'toolbar1');
}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#createTimeStart").val('');
    $("#createTimeEnd").val('');
    $("#searchUserName").val('');
    $("#searchDiv").hide();
    $("#showButton").show();
}

function showMaterialWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "只能选择一条数据查看维修保养明细", "error");
        return false;
    } else {
        var record = selectRow[0];
        var recordId = record.recordId;
            initTableSetToolbar("cusTable1", contextPath + "/materialList/query_pager?recordId=" + recordId, "toolbar1");
        $("#searchMaterialWin").modal('show');
    }
}

/** 显示是否回访 */
function formatterTrack(value, row, index) {
    if (value == "Y") {
        return "是";
    } else {
        return "<span style='color: red'>否</span>";
    }
}
