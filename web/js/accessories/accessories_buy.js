var isAcc = false;
var count = 0;
$(document).ready(function () {

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

function showSearchFormSale() {
    initDateTimePickerNotValitor("form_datetime");
    showSHForm.call(this);
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
    var bDiscount = $("#accBuyDiscount").val();
    if (bDiscount == '' || bDiscount == null) {
        $("#accBuyDiscount").val(1);
        $.post("/accessoriesBuy/isAccAdd?state=" + isAcc,
            $("#" + formId).serialize(),
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                    $("input[type=reset]").trigger("click");
                } else if (data.result == "fail") {
                    destoryValidator(formId, "addForm");
                    clearTempData();
                    validator(formId);
                    swal(data.message, "", "error");
                }
            }, "json");
    }

}


function fmtOperate(value, row, index) {
    if (row.accBuyStatus == 'Y') {
        return ['<button type="button" class="removeBuy btn btn-danger  btn-sm" style="margin-right:15px;">冻结</button>',
            '<button type="button" class="showEditWin btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('')
    } else {
        return ['<button type="button" class="enableBuy btn btn-success  btn-sm" style="margin-right:15px;">激活</button>',
            '<button type="button" class="showEditWin btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('')
    }
}

window.operateEvents = {
    'click .removeBuy': function (e, value, row, index) {
        $.get("/accessoriesBuy/remove?id=" + row.accBuyId, function (data) {
            if (data.result == "success") {
                $('#addWin').modal('hide');
                $('#cusTable').bootstrapTable('refresh');
            } else if (data.result == "fail") {
                swal(data.message, "", "error");
            }
        });
    },
    'click .showEditWin': function (e, value, row, index) {
        var accessoriesBuy = row;
        $("#editForm").fill(accessoriesBuy);
        $("#eAccDes").val(accessoriesBuy.accessories.accDes);
        $('#supplyType').html('<option value="' + accessoriesBuy.accessories.supply.supplyId + '">' + accessoriesBuy.accessories.supply.supplyName + '</option>').trigger("change");
        $('#eAccType').html('<option value="' + accessoriesBuy.accessories.accessoriesType.accTypeId + '">' + accessoriesBuy.accessories.accessoriesType.accTypeName + '</option>').trigger("change");
        $("#buyTime").val(formatterDate(accessoriesBuy.accBuyTime));
        showAccEditWin();
        autoCalculation1("eAccBuyCount", "eAccBuyPrice", "eAccBuyDiscount");
    },
    'click .enableBuy': function (e, value, row, index) {
        $.get("/accessoriesBuy/enable?id=" + row.accBuyId,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            });
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

function fmtDiscount(value) {
    if (value == 1) {
        return "无折扣"
    } else {
        return value;
    }
}

function fmtBuyState(value) {
    if (value == 'Y') {
        return "可用";
    } else {

        return "不可用";
    }
}
/**
 * 批量冻结状态
 */
function delteleBuy() {
    var selectRows = $("#cusTable").bootstrapTable('getSelections');
    var accBuyIdArr = [];
    for (var i = 0; i < selectRows.length; i++) {
        accBuyIdArr[i] = selectRows[i].accBuyId;
    }
    if (selectRows.length > 0) {
        $.each(selectRows, function (index, value, array) {
            $.get("/accessoriesBuy/batchDelete?accBuyArr=" + accBuyIdArr,
                function (data) {
                    if (data.result == "success") {
                        swal(data.result, "", "success");
                        $('#cusTable').bootstrapTable('refresh');
                    } else if (data.result == "fail") {
                        swal(data.result, "", "error");
                    }
                });
        });
    } else {
        swal('操作失败', "请至少选择一条数据冻结", "error");
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
    initDateTimePicker("form_datetime", "buyTimeStart", "formSearch");
    initDateTimePicker("form_datetime", "buyTimeEnd", "formSearch");
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
                        regexp: /^([0-9](\.[0-9]+)?|10)$/,
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
    initDateTimePicker("form_datetime", "accBuyTime", "addForm");
    // clearTempData();
    validator("addForm");
    $("#addWin").modal('show');
    autoCalculation1("accBuyCount", "accBuyPrice", "accBuyDiscount", "accBuyTotal", "accBuyMoney");
}

function showAccEditWin() {
    initDateTimePicker("form_datetime", "accBuyTime", "editForm");
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

/**
 *
 * @param buyCount 购买数量
 * @param buyPrice 购买单价
 * @param buyDiscount 购买折扣
 * @param buyTotal 购买总价
 * @param buyMoney 购买最终价
 */
function autoCalculation1(buyCount, buyPrice, buyDiscount, buyTotal, buyMoney) {
    var bCount = "";
    var bPrice = "";
    var bDiscount = "";
    var bTotal = "";
    var bMoney = "";
    var udrs = ""; //未打折扣总价
    var rs = ""; // 打完折扣总价
    $("#" + buyCount + ",#" + buyPrice + ",#" + buyDiscount).bind("input", function () {
        bCount = $("#" + buyCount).val();
        bPrice = $("#" + buyPrice).val();
        bDiscount = $("#" + buyDiscount).val();
        if (bCount != null && bCount != "") {
            if (bPrice != null && bPrice != "" && bDiscount != null && bDiscount != "") {
                rs = (bCount * bPrice) * bDiscount;
                var urs = bCount * bPrice;
                $("#" + buyTotal).val(urs);
                $("#" + buyMoney).val(rs);
            } else {
                udrs = bCount * bPrice;
                $("#" + buyTotal).val(udrs);
                $("#" + buyMoney).val(udrs);
            }
        } else {
            $("#" + buyTotal).val(0);
            $("#" + buyMoney).val(0);
        }
    })
}
