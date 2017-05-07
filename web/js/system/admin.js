/**
 * Created by xiao-kang on 2017/4/17.
 */
var contextPath = '';
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", contextPath + "/admin/query_pager");
    /*initSelect2("adminCAndSO", "选择管理员类型", contextPath + "/role/query_cAdminAndSOAdmin", "540");*/
    /*initSelect2("admin_company", "请选择公司", contextPath + "/company/company_all", "540");*/
    $("#search").bind("click", initTable);
});

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length == 0) {
        swal('编辑失败', "必须选择一条数据进行编辑", "error");
        return false;
    } else if (selectRow.length > 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
    } else {
        var admin = selectRow[0];
        $("#updateForm").fill(admin);
        $("#editWin").modal('show');
    }
}

/**提交编辑数据 */
function updateAdmin() {
    $.post(contextPath + "/admin/update_admin",
        $("#updateForm").serialize(),
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

function showAddWin() {
    validator("addForm");
    /*$('#adminTypeSelect').html('').trigger("change");
    $('#addCompany').html('').trigger("change");*/
    $("input[type=reset]").trigger("click");
    $("#addWin").modal('show');
}

/**
 * 批量删除数据
 */
function deleteAdmin() {
    var rows = $("#cusTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        swal('删除失败', "请选择一条或多条数据进行删除", "error");
    } else {
        var ids = "";
        for (var i = 0, len = rows.length; i < len; i++) {
            if (ids == "") {
                ids = rows[i].id;
            } else {
                ids += "," + rows[i].id
            }
            if (ids != "") {
                swal({
                        title: "确定要删除所选数据?",
                        text: "删除后将无法恢复，请谨慎操作！",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是的，我要删除!",
                        cancelButtonText: "让我在考虑一下....",
                        closeOnConfirm: false
                    },
                    function () {
                        $.get(contextPath + "/admin/deleteById/" + rows[0].ids,
                            function (data) {
                                swal(data.message, "您已经永久删除了这条信息。", "success");
                                $('#cusTable').bootstrapTable('refresh');
                            }, "json");

                    });
            }
        }

    }
}

function thisStatus(value, row, index) {
    if (value == 'Y') {
        return "可用";
    } else {
        return "不可用";
    }
}

function operateFormatter(value, row, index) {
    if (row.userStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-success  btn-sm">冻结</button>',
            ' <button type="button" class="showEditModule btn btn-default  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-danger  btn-sm">激活</button>',
            ' <button type="button" class="showEditModule btn btn-default  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var status = 'N';
        $.get(contextPath + "/admin/update_status?id=" + row.userId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var status = 'Y';
        $.get(contextPath + "/admin/update_status?id=" + row.userId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    }
}

function queryAll() {
    initTable("cusTable", contextPath + "/admin/query_pager");
}

function queryCompany() {
    initTable("cusTable", contextPath + "/admin/company_pager");
}

function querySystem() {
    initTable("cusTable", contextPath + "/admin/system_pager");
}

/*function adminSelect(selectId) {
    var roleId = $("#" + selectId).val();
    var roleName = $("#" + selectId).find("option:selected").text();
    if (roleName == "董事长") {
        $(".admin_company").prop("disabled", false);
    } else {
        $(".admin_company").prop("disabled", true);
        $("#addCompany").empty();
    }
}*/

/**生成默认密码*/
function defaultPwd() {
    var password = "123456";
    $("#pwd").val(password);
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
           userName: {
                message: '名称验证失败',
                validators: {
                    notEmpty: {
                        message: '名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 10,
                        message: '名称长度必须在2到10位之间'
                    }
                }
            },userPwd: {
                message: '密码验证失败',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 16,
                        message: '密码长度必须在6到16位之间'
                    }
                }
            },userEmail: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    regexp: {
                        regexp: /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/,
                        message: '邮箱格式错误'
                    }
                }
            },userPhone: {
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    regexp: {
                        regexp: /^1(3|4|5|7|8)\d{9}$/,
                        message: '手机号格式错误'
                    }
                }
            },userIdentity: {
                validators: {
                    notEmpty: {
                        message: '身份证不能为空'
                    },
                    regexp: {
                        regexp: /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/,
                        message: '身份证格式错误'
                    }
                }
            },userAddress: {
                message: '地址验证失败',
                validators: {
                    notEmpty: {
                        message: '地址不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 15,
                        message: '地址长度必须在2到15位之间'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit(contextPath + "/admin/add_admin", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit(contextPath + "/permission/update_permission", formId, "editWin");
            }
        })

}

function getBirthday(obj) {
    var identity = obj.value;
    if (identity.length == 18) {
        var year = identity.substring(6, 10);
        var month = identity.substring(10, 12);
        var date = identity.substring(12, 14);
        var birthday = year + "-" + month + "-" + date;
        $("#birthday").val(birthday);
    }
}


