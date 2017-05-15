/**
 * Created by Xiao-Qiang on 2017/5/15.
 */

var contextPath = '';
$(document).ready(function () {
    initTable("cusTable", contextPath + "/record/pager_picking");
    initDateTimePickerNotValitor("form_datetime");
    $("#search").bind("click", initTable);
});

function showMUWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "只能选择一条数据查看维修保养明细", "error");
        return false;
    } else {
        var record = selectRow[0];
        var recordId = record.recordId;
        initTableSetToolbar("cusTable1", contextPath + "/materialUse/query_pager?recordId=" + recordId, "toolbar1");
        $("#searchMUWin").modal('show');
    }
}

function countPrice(value, row, index) {
    return "" + (row.accPrice * row.accCount);
}

function operateFormatter(value, row, index) {
    if (row.pickingStatus == '未审核') {
        return [
            '<button type="button" class="updatePS_1 btn btn-success  btn-sm">通过</button>',
            ' <button type="button" class="updatePS_2 btn btn-danger btn-sm" style="margin-right:15px;">回绝</button>'
        ].join('');
    } else {
        return ['已完成'].join('');
    }
}

window.operateEvents = {
    'click .updatePS_1': function (e, value, row, index) {
        var status = '通过';
        $.get(contextPath + "/materialUse/updatePickingStatusById?id=" + row.recordId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                    swal("成功提示", data.message, "success");
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
    'click .updatePS_2': function (e, value, row, index) {
        var status = '未通过';
            $.get(contextPath + "/materialUse/updatePickingStatusById?id=" + row.recordId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
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

function showReturnMaterial() {

}

