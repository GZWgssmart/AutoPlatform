var isAcc = false;

$(document).ready(function () {
    initDateTimePicker("form_datetime", "");
    initTable("saleTable", "/accessoriesSale/pager");

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

    destoryValidator("addWin", "addForm");
    destoryValidator("accTypeWin", "accForm");
    destoryValidator("editWin", "editForm");

});

function disableInput() {
    $("input[name='accessories.accName']").prop("disabled", true);
    $("input[name='accessories.accessoriesType.accTypeName']").prop("disabled", true);
    $("input[name='accUnit']").prop("disabled", true);
    $("input[name='accPrice']").prop("disabled", true);
    $("input[name='accessories.accName']").prop("disabled", true);
    $("input[name='accessories.accName']").prop("disabled", true);
    $("input[name='accessories.accName']").prop("disabled", true);
    $("input[name='accessories.accName']").prop("disabled", true);

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
        var accessoriesSale = selectRow[0];
        $("#editForm").fill(accessoriesSale);
        $("#SaleTime").val(formatterDate(accessoriesSale.accSaleTime));
        $("#editWin").modal('show');
    }
}

/**更新数据 */
function updateAccessoriesSaleInfo(formId) {
    $.post("/accessoriesSale/update",
        $("#editForm").serialize(),
        function (data) {
            if (data.result == "success") {
                $('#editWin').modal('hide');
                swal(data.message, "", "success");
                $('#cusTable').bootstrapTable('refresh');
                allSales();
                $(formId).data('bootstrapValidator').resetForm(true);
            } else if (data.result == "fail") {
                swal(data.message, "", "error");
            }
        }, "json");
}

function addAccessoriesSaleInfo(formId) {
    $.post("/accessoriesSale/addSale",
        $("#addForm").serialize(),
        function (data) {
            if (data.result == "success") {
                $('#addWin').modal('hide');
                swal(data.message, "", "success");
                $('#cusTable').bootstrapTable('refresh');
                $("input[type=reset]").trigger("click");
                $(formId).data('bootstrapValidator').resetForm(true);
            } else if (data.result == "fail") {
                swal(data.message, "", "error");
            }
        }, "json");
}


function fmtOperate(value, row, index) {
    return [
        '<button type="button" class="showEditWin btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>',
        '<button type="button" class="removeSale btn btn-danger  btn-sm" style="margin-right:15px;">删除</button>'
    ].join('');
}

window.operateEvents = {
    'click .removeSale': function (e, value, row, index) {
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
            $.get("/accessoriesSale/remove?id=" + row.accSaleId + "&status=" + row.accSaleCheck, function (data) {
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
        var accessoriesSale = row;
        $("#editForm").fill(accessoriesSale);
        $("#saleTime").val(formatterDate(accessoriesSale.accSaledTime));
        showAccEditWin();
    }
}
function showAccessories() {
    initTableNotTollbar("accTable", "/accessories/pager");
    validator("accForm");
    $("#accWin").modal("show");
}

function addAcc() {
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

function fmtSaleState(value) {
    if (value == 'Y') {
        return "已采购";
    } else {
        return "未采购";
    }
}

function delteleSale() {
    var selectRows = $("#cusTable").bootstrapTable('getSelections');
    var accSaleIdArr = [];
    for (var i = 0; i < selectRows.length; i++) {
        accSaleIdArr[i] = selectRows[i].accSaleId;
    }
    if (selectRows.length > 0) {
        $.each(selectRows, function (index, value, array) {
            if (value.accSaleCheck == "Y") {
                $.get("/accessoriesSale/batchDelete?accSaleArr=" + accSaleIdArr, function (data) {
                    if (data.result == "success") {
                        swal(data.result, "", "success");
                        $('#cusTable').bootstrapTable('refresh');
                    } else if (data.result == "fail") {
                        swal(data.result, "", "error");
                    }
                });
            } else if (value.accSaleCheck == "N") {
                swal('删除失败', "必须选择审核通过的数据", "error");
            }
        });

    } else {
        swal('删除失败', "请至少选择一条数据删除", "error");
    }
}

function onlyCheck() {
    initTable("cusTable", "/accessoriesSale/onlyCheck");
}

function onlySale() {
    initTable("cusTable", "/accessoriesSale/onlySale");
}

function allSales() {
    initTable("cusTable", "/accessoriesSale/pager");
}

function byAccNameSearch() {
    var accName = $("#sAccName").val();
    var SaleTimeStart = $("#SaleTimeStart").val();
    var SaleTimeEnd = $("#SaleTimeEnd").val();
    initTable("cusTable", "/accessoriesSale/byAccNameSearch?accName=" + accName + "&SaleTimeStart=" + SaleTimeStart + "&SaleTimeEnd=" + SaleTimeEnd);
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
                    stringLength: {
                        min: 0,
                        max: 8,
                        message: '字数不可以超过8个字符'
                    }
                },

            },
            accSaleCount: {
                validators: {
                    notEmpty: {
                        message: '不可以为空'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '只能是数字'
                    }
                }
            },
            accSalePrice: {
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
            accSaleDiscount: {
                validators: {
                    regexp: {
                        regexp: /^\d+(\.\d+)?$/,
                        message: '折扣只能是数字'
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
            accSaleTime: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            accSaleTotal: {
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
            accSaleMoney: {
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
                addAccessoriesSaleInfo("#addForm")
            } else if (formId == "editForm") {
                updateAccessoriesSaleInfo("#editForm");
            }
        })
}

function clearTempData() {
    $('#aAccName').html('').trigger("change");
    $('#aAccUnit').html('').trigger("change");
    $('#accSaleCount').html('').trigger("change");
    $('#accSalePrice').html('').trigger("change");
    $('#accSaleDiscount').html('').trigger("change");
    $('#accSaleTime').html('').trigger("change");
    $('#accSaleTotal').html('').trigger("change");
    $('#accSaleMoney').html('').trigger("change");
    $("input[type=reset]").trigger("click");
}

function showAccAddWin() {
    clearTempData();
    validator("addForm");
    $("#addWin").modal("show");
}

function showAccEditWin() {
    clearTempData();
    validator("editForm");
    $("#editWin").modal("show");
}

function showAccTypeWin() {
    initTableNotTollbar('accTypeTable', '/accessoriesType/queryByStatus_AccType?status=Y');
    $('#accTypeWin').modal('show');
}
