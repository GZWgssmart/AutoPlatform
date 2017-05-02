$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/supply/queryByPager?status=ALL");
    initSelect2("supplyType", "请选择供应商分类", "/supplyType/queryAll", "150");
    initSelect2("company", "请选择汽修公司", "/company/company_all", "150");
    destoryValidator("addWin", "addForm");
    destoryValidator("editWin", "editForm");

    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});

/** 编辑数据 */
function showEditWin() {
    validator("editForm")
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var product = selectRow[0];
        $("#editForm").fill(product);
        $("#editWin").modal('show');
    }
}

function showAddWin() {
    validator("addForm");
    $("input[type=reset]").trigger("click");
    $("#addWin").modal('show');
}


function operateFormatter(value, row, index) {
    if (row.supplyStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-danger  btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showUpdateSupplyType1 btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    }else{
        return [
            '<button type="button" class="updateActive btn btn-success  btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showUpdateSupplyType1 btn btn-primary  btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }

}
window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        $.get("/supply/updateStatus?id=" + row.supplyId + "&status=Y",
            function(data){
                if(data.result == "success"){
                    $('#cusTable').bootstrapTable('refresh');
                }else if(data.result == "fail"){
                    swal(data.message, "", "error");
                }
            },"json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var status = 'Y';
        $.get("/supply/updateStatus?id=" + row.supplyId + "&status=N",
            function(data){
                if(data.result == "success"){
                    $('#cusTable').bootstrapTable('refresh');
                }else if(data.result == "fail"){
                    swal(data.message, "", "error");
                }
            },"json");
    },
    'click .showUpdateSupplyType1': function (e, value, row, index) {
        validator("editForm");
        var supply = row;
        $("#editForm").fill(supply);
        $("#addButton").removeAttr("disabled");
        $("#editWin").modal('show');
    }
}

/** 表单验证 */
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
            supplyName: {
                message: '供应商验证失败',
                validators: {
                    notEmpty: {
                        message: '供应商名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 4,
                        message: '供应商名称长度必须在2到30位之间'
                    }
                }
            },
            supplyTel: {
                validators: {
                    notEmpty: {
                        message: '联系电话不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '手机号只能是11位'
                    }
                }
            },
            supplyPricipal: {
                validators: {
                    notEmpty: {
                        message: '负责人不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 4,
                        message: '负责人姓名在2到4位之间'
                    }
                }
            },
            supplyAddress: {
                validators: {
                    notEmpty: {
                        message: '地址不能为空'
                    }

                }
            },
            supplyBank: {
                validators: {
                    notEmpty: {
                        message: '开户银行不能为空'
                    }
                }
            },
            supplyBankAccount: {
                validators: {
                    notEmpty: {
                        message: '开户人不能为空'
                    }

                }
            },
            supplyBankNo: {
                validators: {
                    notEmpty: {
                        message: '开户卡号不能为空'
                    },
                    stringLength: {
                        min: 16,
                        max: 19,
                        message: '卡号在16到19位之间'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '卡号不能是中文'
                    }
                }
            },
            supplyAlipay: {
                validators: {
                    notEmpty: {
                        message: '支付宝不能为空'
                    },
                    regexp: {
                        regexp: /^[A-Za-z0-9]+$/,
                        message: '支付宝不能是中文'
                    }
                }
            },
            supplyWechat: {
                validators: {
                    notEmpty: {
                        message: '微信不能为空'
                    },
                    regexp: {
                        regexp: /^[A-Za-z0-9-]+$/,
                        message: '微信不能是中文，微信是由数字字母中划线组成'
                    }

                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/supply/add", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/supply/edit", formId, "editWin");
            }

        })

}

/** 根据条件搜索 */
function searchSupply() {
    var supplyName = $("#searchSupplyName").val();
    var supplyPricipal = $("#searchSupplyPricipal").val();
    var supplyTypeId = $("#searchSupplyTypeId").val();
    var companyId = $("#searchCompanyId").val();
    if (companyId != null && companyId != "") {
        initTable("cusTable", "/supply/conditionPager?supplyName=" + supplyName  + "&supplyPricipal="+ supplyPricipal + "%supplyTypeId=" + supplyTypeId + "&companyId=" + companyId);
    } else {
        swal("错误提示", "请选择一家汽修公司", "error");
    }

}
