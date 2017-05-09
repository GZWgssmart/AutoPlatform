/**
 * Created by root on 2017/4/24.
 */
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/maintainFix/queryByPager");
    initSelect2("company", "请选择汽修公司", "/company/company_all", "565");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});

function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var product = selectRow[0];
        validator("editForm");
        $('#companys').html('<option value="' + product.company.companyName + '">' + product.company.companyName + '</option>').trigger("change");
        $("#editForm").fill(product);
        $("#editWin").modal('show');
    }
}


function showAddWin(){
    validator("addForm");
    $("#addWin").modal('show');
    $("input[type=reset]").trigger("click");
}

function operating(value, row, index) {
    if (row.maintainStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm btn-danger" >冻结</button>&nbsp;&nbsp;',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default btn-sm btn-primary ">编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm btn-success" >激活</button>&nbsp;&nbsp;',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default btn-sm btn-primary ">编辑</button>'
        ].join('');
    }
}



window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var Status = 'N';
        $.get("/maintainFix/StatusModify?id=" + row.maintainId + "&status=" + Status,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var Status = 'Y';
        $.get("/maintainFix/StatusModify?id=" + row.maintainId + "&status=" + Status,
            function (data) {
                if (data.result == "success") {
                    // $('#addWin').modal('hide');
                    // swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json")
    },
    'click .showUpdateIncomingType1': function (e, value, row, index) {
        var incomingType = row;
        validator("editForm");
        $("#editForm").fill(incomingType);
        $("#editWin").modal('show');
    }
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
            maintainName: {
                message: '维修名称失败',
                validators: {
                    notEmpty: {
                        message: '维修名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '维修名称长度必须在2到20位之间'
                    }
                }
            },
            maintainHour: {
                message: '维修所需工时失败',
                validators: {
                    notEmpty: {
                        message: '维修所需工时不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '维修所需工时必须在1到10位之间'
                    }
                }
            },
            maintainMoney: {
                message: '维修基础费用失败',
                validators: {
                    notEmpty: {
                        message: '维修基础费用不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '维修基础费用必须在1元到100000元之间'
                    }
                }
            },
            maintainManHourFee: {
                message: '维修工时费用失败',
                validators: {
                    notEmpty: {
                        message: '维修工时费用不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '维修工时费用必须在1元到100000元之间'
                    }
                }
            },
            maintainDes: {
                message: '维修描述失败',
                validators: {
                    notEmpty: {
                        message: '维修描述用不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '维修描述用字符长度必须在1~500字之间'
                    }
                }
            },
            companyId: {
                message: '公司名称失败',
                validators: {
                    notEmpty: {
                        message: '公司名称不能为空'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/maintainFix/InsertMaintainItem", formId, "addWin");
            } else if (formId == "editForm") {
                formSubmit("/maintainFix/update", formId, "editWin");

            }
        })

}
