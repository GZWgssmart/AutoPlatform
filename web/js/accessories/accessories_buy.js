var isAcc = false;

$(document).ready(function () {
    initDateTimePicker("form_datetime");
    initTable("cusTable", "pager");

    $("#accSelect").fadeToggle(225);

    $("#isAcc").bootstrapSwitch({
        onText: '是',
        offText: '否',
        onColor: 'success',
        offColor: 'danger',
        size: 'normal',

        onSwitchChange: function (event, state) {
            if (state == true) {
                isAcc = true;
                $("#accSelect").fadeIn(225);
            } else if (state == false) {
                isAcc = false;
                $("#accSelect").fadeToggle(225);
            }
        }
    });
});

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var accessoriesBuy = selectRow[0];
        $("#editForm").fill(accessoriesBuy);
        $("#buyTime").val(formatterDate(accessoriesBuy.accBuyTime));
        $("#editWin").modal('show');
    }
}

/**更新数据 */
function updateAccessoriesBuyInfo() {
    $.post(contextPath + "/accessoriesBuy/update",
        $("#editForm").serialize(),
        function (data) {
            if (data.result == "success") {
                $('#editWin').modal('hide');
                swal(data.message, "", "success");
                $('#cusTable').bootstrapTable('refresh');
            } else if (data.result == "fail") {
                swal(data.message, "", "error");
            }
        }, "json");
}

/**添加采购信息 */
function addAccessoriesBuyInfo() {
    $.post(contextPath + "/accessoriesBuy/isAccAdd?state=" + isAcc,
        $("#addForm").serialize(),
        function (data) {
            if (data.result == "success") {
                $('#addWin').modal('hide');
                swal(data.message, "", "success");
                $('#cusTable').bootstrapTable('refresh');
                $("input[type=reset]").trigger("click");
            } else if (data.result == "fail") {
                swal(data.message, "", "error");
            }
        }, "json");
}


function operateFormatter(value, row, index) {
    if (row.accBuyStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-danger  btn-sm" style="margin-right:15px;">冻结</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-success  btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-primary  btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }

}
window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var status = 'N';
        $.get(contextPath + "/accessoriesBuy/updateStatus?id=" + row.accBuyId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    // swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    // swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var status = 'Y';
        $.get(contextPath + "/accessoriesBuy/updateStatus?id=" + row.accBuyId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    // swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    // swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .showUpdateIncomingType1': function (e, value, row, index) {
        var accessoriesBuy = row;
        $("#editForm").fill(accessoriesBuy);
        $("#buyTime").val(formatterDate(accessoriesBuy.accBuyTime));
        $("#editWin").modal('show');
    }
}
function showAccessories() {
    initTableNotTollbar("accTable", "/accessories/pager");
    $("#accWin").modal("show");
}

function addAccBuy() {
    var selectRow = $("#accTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('添加失败', "请至少选择一条数据后关闭本窗口", "error");
    } else {
        var acc = selectRow[0];
        $("#addForm").fill(acc);
        $("#accWin").modal("hide");
    }
    // $("#accUnit").val(sr.accUnit);
    // $("#accBuyCount").val(sr.accBuyCount);
}

