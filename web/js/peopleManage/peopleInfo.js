$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/peopleManage/peopleInfo_pager");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);

    initSelect2("user_company", "请选择公司", "/company/company_all", "565");
});




function gender(value, row, index) {
    if (row.userGender == 'M') {
        return '男'
    }else if (row.userGender == 'F'){
        return '女'
    }else if (row.userGender == 'N'){
        return '未知'
    }
}

function operateFormatter(value, row, index) {
    if (row.userStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm">冻结</button>',
            '<button type="button" class="showUpdateInfo btn btn-default  btn-sm">编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm">激活</button>',
            '<button type="button" class="showUpdateInfo btn btn-default  btn-sm">编辑</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var userStatus = 'N';
        $.get("/peopleManage/peopleInfo_status?id=" + row.userId + "&status=" + userStatus,
            function (data) {
                if (data.result == "success") {

                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var userStatus = 'Y';
        $.get("/peopleManage/peopleInfo_status?id=" + row.userId + "&status=" + userStatus,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .showUpdateInfo': function (e, value, row, index) {
        var checkin = row;
        $("#editForm").fill(checkin);
        $('#editCarBrand').html('<option value="' + checkin.company.companyId + '">' + checkin.company.companyName + '</option>').trigger("change");
        validator("editForm");
        $("#editWin").modal('show');
    }
}

function statusFormatter(value, row, index) {
    if (row.userStatus == 'Y') {
        return [
            '可用'
        ].join('');
    }else if(row.userStatus == 'N'){
        return [
            '不可用'
        ].join('');
    }

}


/**编辑数据 */
function showEditWin() {
    validator("editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var checkin = selectRow[0];
        $("#editForm").fill(checkin);
        $('#editCompany').html('<option value="' + checkin.company.companyId + '">' + checkin.company.companyName + '</option>').trigger("change");
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
                message: '车主验证失败',
                validators: {
                    notEmpty: {
                        message: '车主姓名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 4,
                        message: '车主长度必须在2到4位之间'
                    }
                }
            },
            userPhone: {
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '手机号只能是11位'
                    }
                }
            },
            brandId: {
                validators: {
                    notEmpty: {
                        message: '品牌不能为空'
                    }

                }
            },
            colorId: {
                validators: {
                    notEmpty: {
                        message: '颜色不能为空'
                    }

                }
            },
            modelId: {
                validators: {
                    notEmpty: {
                        message: '车型不能为空'
                    }

                }
            },
            plateId: {
                validators: {
                    notEmpty: {
                        message: '车牌不能为空'
                    }

                }
            },
            carPlate: {
                validators: {
                    notEmpty: {
                        message: '车牌号码不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 5,
                        message: '车牌号码只能是5位'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '车牌不能是中文'
                    }
                }
            },
            oilCount: {
                validators: {
                    notEmpty: {
                        message: '汽车油量不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '油量只能是数字'
                    }

                }
            },
            carMileage: {
                validators: {
                    notEmpty: {
                        message: '汽车行驶里程不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '行驶里程只能是数字'
                    }

                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/peopleManage/peopleInfo_insert", formId, "addWin");
                $.post(url,
                    $("#" + formId).serialize(),
                    function (data) {
                        if (data.result == "success") {
                            $('#' + winId).modal('hide');
                            swal(data.message, "默认密码为123456", "success");
                            $('#cusTable').bootstrapTable('refresh');
                            $('#' + formId).data('bootstrapValidator').resetForm(true);
                        } else if (data.result == "fail") {
                            swal(data.message, "", "error");
                        }
                    }, "json");

            } else if (formId == "editForm") {
                formSubmit("/peopleManage/peopleInfo_update", formId, "editWin");
            }


        })

}