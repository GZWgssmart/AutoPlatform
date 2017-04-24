/**
 * Created by GOD on 2017/4/17.
 */

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/accessoriesType/pager");

    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
    initSelect2("accType_company", "请选择公司", "/company/company_all", "565");
});

/**编辑数据 */
function showEditWin() {
    validator("editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var accessoriesType = selectRow[0];
        $("#editForm").fill(accessoriesType);
        $('#editCompany').html('<option value="' + accessoriesType.company.companyId + '">' + accessoriesType.company.companyName + '</option>').trigger("change");
        $("#editWin").modal('show');
    }
}

/**提交添加数据 */
function showAddWin() {
    validator("addForm");
    $('#addCompany').html('').trigger("change");
    $("input[type=reset]").trigger("click");
    $("#addWin").modal('show');
}

function thisStatus(value, row, index) {
    if (value == 'Y') {
        return "可用";
    } else {
        return "不可用";
    }
}

function operateFormatter(value, row, index) {
    if (row.accTypeStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showUpdateInfo btn btn-default  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showUpdateInfo btn btn-default  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var status = 'N';
        $.get(contextPath + "/accessoriesType/update_status?id=" + row.accTypeId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    // swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var status = 'Y';
        $.get(contextPath + "/accessoriesType/update_status?id=" + row.accTypeId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    // swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .showUpdateInfo': function (e, value, row, index) {
        var accessoriesType = row;
        $("#editForm").fill(accessoriesType);
        $('#editCompany').html('<option value="' + accessoriesType.company.companyId + '">' + accessoriesType.company.companyName + '</option>').trigger("change");
        validator("editForm");
        $("#editWin").modal('show');
    }
}

function queryAll() {
    initTable(contextPath + "cusTable", "/accessoriesType/pager");
}

function queryStatus(status) {
    initTable('cusTable', contextPath + '/accessoriesType/queryByStatus_AccType?status=' + status);
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
            accTypeName: {
                message: '配件分类验证失败',
                validators: {
                    notEmpty: {
                        message: '配件分类名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 8,
                        message: '配件分类名称长度必须在2到8位之间'
                    }
                }
            },
            accTypeDes: {
                validators: {
                    notEmpty: {
                        message: '配件分类描述不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 15,
                        message: '配件分类描述长度必须在2到15位之间'
                    }
                }
            },
            companyId: {
                validators: {
                    notEmpty: {
                        message: '公司不能为空'
                    }

                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/accessoriesType/add", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/accessoriesType/update", formId, "editWin");
            }


        })

}