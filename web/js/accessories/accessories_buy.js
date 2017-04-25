var isAcc = false;

$(document).ready(function () {
    initDateTimePicker("form_datetime", "");
    initTable("cusTable", "pager");

    $("#isAcc").bootstrapSwitch({
        onText: '是',
        offText: '否',
        onColor: 'success',
        offColor: 'danger',
        size: 'normal',
        onSwitchChange: function (event, state) {
            if (state == true) {
                isAcc = true;
                showAccessories();
            } else if (state == false) {
                isAcc = false;
            }
        }
    });

    $("#accWin").on("hide.bs.modal", function () {
        $("#isAcc").bootstrapSwitch("state", false);
    });
});

function disableInput() {
    $("#accName").prop("disabled", true);

}

function onState() {
    $("#accWin").on("hide.bs.modal", function () {
        disableInput();
        $("#isAcc").bootstrapSwitch("state", true);
    });
}

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('ions');
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
    $.post("/accessoriesBuy/update",
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
    $.post("/accessoriesBuy/isAccAdd?state=" + isAcc,
        $("#addForm").serialize(),
        function (data) {
            alert(data.result);
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


function fmtOperate(value, row, index) {
    return [
        '<button type="button" class="showEditWin btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>',
        '<button type="button" class="removeBuy btn btn-danger  btn-sm" style="margin-right:15px;">删除</button>'
    ].join('');
}

window.operateEvents = {
    'click .removeBuy': function (e, value, row, index) {
        swal({
            title: "您确定要删除吗？",
            text: "",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            cancelButtonText: "取消",
            confirmButtonText: "是的，我要删除",
            confirmButtonColor: "#ec6c62"
        }, function () {
            $.get("/accessoriesBuy/remove?id=" + row.accBuyId + "&status=" + row.accBuyCheck, function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            });
        });

    },
    'click .showEditWin': function (e, value, row, index) {
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
        onState();
        $("#accWin").modal("hide");
    }
}

function fmtCheckState(value) {
    if (value == 'Y') {
        return "已审核";
    } else {
        return "审核中";
    }
}

function fmtBuyState(value) {
    if (value == 'Y') {
        return "已采购";
    } else {
        return "未采购";
    }
}

function delteleBuy() {
    var selectRows = $("#cusTable").bootstrapTable('getSelections');
    var accBuyIdArr = [];
    for (var i = 0; i < selectRows.length; i++) {
        accBuyIdArr[i] = selectRows[i].accBuyId;
    }
    if (selectRows.length > 0) {
        $.each(selectRows, function (index, value, array) {
            if (value.accBuyCheck == "Y") {
                $.get("/accessoriesBuy/batchDelete?accBuyArr=" + accBuyIdArr, function (data) {
                    if (data.result == "success") {
                        swal(data.result, "", "success");
                        $('#cusTable').bootstrapTable('refresh');
                    } else if (data.result == "fail") {
                        swal(data.result, "", "error");
                    }
                });
            } else if (value.accBuyCheck == "N") {
                swal('删除失败', "必须选择审核通过的数据", "error");
            }
        });

    } else {
        swal('删除失败', "请至少选择一条数据删除", "error");
    }
}

function onlyCheck() {
    initTable("cusTable", "/accessoriesBuy/onlyCheck");
}

function onlyBuy() {
    initTable("cusTable", "/accessoriesBuy/onlyBuy");
}

function allBuys() {
    initTable("cusTable", "/accessoriesBuy/pager");
}

function byAccNameSearch(accName) {
    var accName = $("#sAccName").val();
    if (accName != "") {
        $.post("/accessoriesBuy/byAccNameSearch?accName=" + accName, function (data) {
            if (data.result == "success") {
                initTable("cusTable", "/accessoriesBuy/byAccNameSearch");
                $("#cusTable").bootstrapTable("refresh");
            }
        });
    } else {
        swal("请输入查询字段", "", "warning");
    }
}

