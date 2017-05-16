var isAcc = false;
var count = 0;
$(document).ready(function () {
    initDateTimePicker("form_datetime", "");
    initTable("cusTable", "/accessoriesBuy/pager");

    initSelect2("supply", "请选择供应商", "/supply/queryAll", 560);
    initSelect2("accTypeA", "请选择配件类别", "/accessoriesType/accessoriesType_All", 560);

    initBsSwitchBuy("isAcc", switchChange);

    disableSwitch("accWin", "isAcc");


    destoryValidator("addWin", "addForm");
    destoryValidator("accWin", "accForm");
    destoryValidator("editWin", "editForm");
});

function initBsSwitchBuy(id, onSwitchChange) {
    initBsSwitch.call(this, id, onSwitchChange);
}

function switchChange(event, state) {
    onSwitchChange.call(this, event, state);
}

override :switchChange = function (event, state) {
    if (state == true) {
        disableInput();
        isAcc = true;
        showAccessories();
    } else if (state == false) {
        enableInput();
        isAcc = false;
    }
}

function closwSwitchCleanData(switchId) {

}

function disableSwitch(modalId, switchId) {
    $("#" + modalId).on("hide.bs.modal", function () {
        $("#" + switchId).bootstrapSwitch("state", false);
    });
}

function enableSwitch(modalId, switchId) {
    $("#" + modalId).on("hide.bs.modal", function () {
        $("#" + switchId).bootstrapSwitch("state", true);
    });
}

function disableInput() {
    $("input[name='accessories.accName']").prop("disabled", true);
    $("input[name='accUnit']").prop("disabled", true);
    $("input[name='accessories.accessoriesType.accTypeName']").prop("disabled", true);
    $("input[name='accessories.accCommodityCode']").prop("disabled", true);
}

function enableInput() {
    $("input[name='accessories.accName']").prop("disabled", false);
    $("input[name='accUnit']").prop("disabled", false);
    $("input[name='accessories.accessoriesType.accTypeName']").prop("disabled", false);
    $("input[name='accessories.accCommodityCode']").prop("disabled", false);
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
function updateAccessoriesBuyInfo(formId) {
    $.post("/accessoriesBuy/update",
        $("#" + formId).serialize(),
        function (data) {
            if (data.result == "success") {
                $('#editWin').modal('hide');
                swal(data.message, "", "success");
                $('#cusTable').bootstrapTable('refresh');
                allBuys();
                $("#" + formId).data('bootstrapValidator').resetForm(true);
            } else if (data.result == "fail") {
                swal(data.message, "", "error");
            }
        }, "json");
}

/**添加采购信息 */
function addAccessoriesBuyInfo(formId) {
    $.post("/accessoriesBuy/isAccAdd?state=" + isAcc,
        $("#" + formId).serialize(),
        function (data) {
            if (data.result == "success") {
                $('#addWin').modal('hide');
                swal(data.message, "", "success");
                $('#cusTable').bootstrapTable('refresh');
                $("input[type=reset]").trigger("click");
                $(formId).data('bootstrapValidator').resetForm(true);
            } else if (data.result == "fail") {
                destoryValidator(formId, "addForm");
                clearTempData();
                validator(formId);
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
        console.log(accessoriesBuy);
        $("#editForm").fill(accessoriesBuy);
        $('#supplyType').html('<option value="' + accessoriesBuy.accessories.supply.supplyId + '">' + accessoriesBuy.accessories.supply.supplyName + '</option>').trigger("change");
        $('#eAccType').html('<option value="' + accessoriesBuy.accessories.accessoriesType.accTypeId + '">' + accessoriesBuy.accessories.accessoriesType.accTypeName + '</option>').trigger("change");
        $("#buyTime").val(formatterDate(accessoriesBuy.accBuyTime));
        showAccEditWin();
        autoCalculation1("eAccBuyCount", "eAccBuyPrice", "eAccBuyDiscount");
    }
}

function fmtAccOperate(value, row, index) {
    return [
        '<button type="button" class="slData btn btn-primary  btn-sm" style="margin-right:15px;" >选择</button>'
    ].join('');
}

window.operateAccEvents = {
    'click .slData': function (e, value, row, index) {
        var acc = row;
        $("#accBuyTime").val(formatterDate(acc.accUsedTime));
        // autoCalculation1(formId, count, price, discount, names);
        $("#addForm").fill(acc);
        enableSwitch("accWin", "isAcc");
        $("#accWin").modal("hide");
    }
}

function showAccessories() {
    initTableNotTollbar("accTable", "/accessories/pager");
    $("#accWin").modal("show");
}

function addAccBuy() {
    var selectRow = $("#accTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        disableSwitch("accWin", "isAcc");
        swal('添加失败', "请至少选择一条数据后关闭本窗口", "error");
    } else {
        var acc = selectRow[0];
        $("#accBuyTime").val(formatterDate(acc.accUsedTime));
        $("#addForm").fill(acc);
        enableSwitch("accWin", "isAcc");

        $("#accWin").modal("hide");
    }
    disableSwitch();
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

function byAccNameSearch() {
    var accName = $("#sAccName").val();
    var buyTimeStart = $("#buyTimeStart").val();
    var buyTimeEnd = $("#buyTimeEnd").val();
    initTable("cusTable", "/accessoriesBuy/byAccNameSearch?accName=" + accName + "&buyTimeStart=" + buyTimeStart + "&buyTimeEnd=" + buyTimeEnd);
}

function validator(formId) {
    $("#addButton").removeAttr("disabled");
    $("#editButton").removeAttr("disabled");
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },

        fields: {
            'accessories.accName': {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 15,
                        message: '不能超过15个字符'
                    }
                }
            },

            'accessories.accTypeId': {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                }
            },

            'accessories.accCommodityCode': {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 13,
                        message: '不可以超过13个数字'
                    }
                }
            },

            'accessories.supplyId': {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },

            accDes: {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 500,
                        message: '字数不可以超过500个字符'
                    }
                }
            },
            accUnit: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 3,
                        message: '字数不可以超过3个字符'
                    },
                }
            },
            'accessories.accessoriesType.accTypeName': {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                },

            },
            accBuyCount: {
                validators: {
                    notEmpty: {
                        message: '不可以为空',
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '只能是数字'
                    }
                }
            },
            accBuyPrice: {
                validators: {
                    notEmpty: {
                        message: '不可以为空'
                    },
                    regexp: {
                        regexp: /^([1-9][0-9]*)+(.[0-9]{1,2})?$/,
                        message: '只接受小数点后两位'
                    }
                }
            },
            accBuyDiscount: {
                validators: {
                    regexp: {
                        regexp: /^(\d(\.\d)?|10)$/,
                        message: '请输入正确的折扣'
                    }
                }
            },
            'accessories.company.companyName': {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                },
                stringLength: {
                    min: 0,
                    max: 8,
                    message: '字数不可以超过8个字符'
                }
            },
            accBuyTime: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            accBuyTotal: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }

                },
                regexp: {
                    regexp: /^([1-9][0-9]*)+(.[0-9]{1,2})?$/,
                    message: '只接受小数点后两位'
                }
            },
            accBuyMoney: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }

                },
                regexp: {
                    regexp: /^\d+(\.\d+)?$/,
                    message: '只能是数字'
                }
            },
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                addAccessoriesBuyInfo(formId);
            } else if (formId == "editForm") {
                updateAccessoriesBuyInfo(formId);
            }
        })
}

function clearTempData() {
    enableInput();
    $('#aAccName').html('').trigger("change");
    $('#aAccUnit').html('').trigger("change");
    $('#addCarModel').html('').trigger("change");
    $('#accBuyCount').html('').trigger("change");
    $('#accBuyPrice').html('').trigger("change");
    $('#accBuyDiscount').html('').trigger("change");
    $('#aCompanyName').html('').trigger("change");
    $('#accBuyTime').html('').trigger("change");
    $('#accBuyTotal').html('').trigger("change");
    $('#accBuyMoney').html('').trigger("change");
    $("input[type=reset]").trigger("click");
}

function showAccAddWin() {
    // clearTempData();
    validator("addForm");
    $("#addWin").modal('show');
}

function showAccEditWin() {
    // clearTempData();
    validator("editForm");
    $("#editWin").modal('show');
}

/**
 * 自动计算价格
 * @param buyCount 购买数量
 * @param buyPrice 购买价格
 * @param buyDiscount 购买折扣
 */
function autoCalculation1(buyCount, buyPrice, buyDiscount) {
    var bCount = "";
    var bPrice = ""
    var bDiscount = "";
    var rs = "";
    var urs = "";
    $("#" + buyCount + ",#" + buyPrice + ",#" + buyDiscount).bind("input onfocus", function () {
        bCount = $("#" + buyCount).val();
        bPrice = $("#" + buyPrice).val();
        bDiscount = $("#" + buyDiscount).val();
        if (bCount != null && bCount != "" && bPrice != null && bPrice != "" && bDiscount != null && bDiscount != "") {
            rs = (bCount * bPrice) * bDiscount;
            urs = bCount * bPrice;
            $("#eAccBuyTotal").val(urs);
            $("#eAccBuyMoney").val(rs);
            if (bDiscount == null && bDiscount == "") {
                var rs = bCount * bPrice;
                $("#eAccBuyTotal").val(rs);
            }
        } else {
            $("#eAccBuyTotal").val("0");
            $("#eAccBuyMoney").val("0");
        }
    })
}

function autoCalculation(iId) {
    var id = iId.id;
    count = $("#accBuyCount").val();
    var price = $("#accBuyPrice").val();
    var discount = $("#accBuyDiscount").val();
    var result = 0;
    var money = 0;
    if (count != null && count != "" && price != null && price != "") {
        if (id == "accBuyTotal") {
            result = count * price;
            $("#" + id).val(result);
        } else if (id == "accBuyMoney") {
            if (discount != null && discount != "") {
                var rs = $("#accBuyTotal").val();
                if (rs != null && rs != "") {
                    money = rs * discount;
                    $("#" + id).val(money);
                }
            } else if (discount == "0" || discount == "") {
                var rs = $("#accBuyTotal").val();
                $("#" + id).val(rs);
            }
        }
    }
}
