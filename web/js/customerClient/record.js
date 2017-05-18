var contextPath = '';
var speedStatus;
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/record/record_queryPager?status=ALL");
});

/** 显示是否回访 */
function formatterTrack(value, row, index) {
    if (value == "Y") {
        return "是";
    } else {
        return "<span style='color: red'>否</span>";
    }
}

/** 显示查看保养明细详情 */
function showDetailWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "只能选择一条数据查看维修保养明细", "error");
        return false;
    } else {
        var record = selectRow[0];
        var recordId = record.recordId;
        initTableSetToolbar("detailTable", "/detail/pager?recordId=" + recordId, "toolbar1");
        $("#searchDetailWin").modal('show');
    }
}

/** 格式化原价和现价 */
function formatterMoney(value, row, index) {
    return "￥" + value;
}

/** 格式化打折或减价 */
function formatterDiscount(value, row, index) {
    if (parseFloat(value) < 1) {
        return value + "折";
    } else if (parseFloat(value) == 0) {
        return "无减价或折扣";
    } else {
        return "￥" + value;
    }
}
