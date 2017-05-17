
var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/progress/progress_pager?status=ALL");

    destoryValidator("editWin", "editForm");
    destoryValidator("detailWin", "detailForm");
    destoryValidator("editDetailWin", "editDetailForm");

});

function operateFormatter(value, row, index) {
    if (row.recordStatus == 'Y') {
        return [
            '<button style="margin-left: 10px" type="button"  class="showProgressImg btn btn-primary">查看进度</button>'
        ].join('');
    } else {
        return [
            '<button style="margin-left: 10px" type="button"  class="showProgressImg btn btn-primary">查看进度</button>'
        ].join('');
    }

}
window.operateEvents = {
    'click .showProgressImg': function (e, value, row, index) {
        var user = row;
        $("#searchDetailWin").modal('show');
    }
}
/** 显示编辑数据 */
function showEditWin() {
    validator("editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var record = selectRow[0];
        $("#editForm").fill(record);
        $('#editStartTime').val(formatterDate(record.startTime));
        $('#editEndTime').val(formatterDate(record.endTime));
        $("#editWin").modal('show');
    }
}

var maintainMoney;
/** 表单验证 */
function validator(formId) {
    $("#editButton").removeAttr("disabled");
    $("#detailButton").removeAttr("disabled");
    $("#editDetailButton").removeAttr("disabled");
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            recordDes: {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 500,
                        message: '描述不能超过500个字'
                    }

                }
            },
            maintainDiscount: {
                validators: {
                    notEmpty: {
                        message: '折扣或者减价不能为空'
                    },
                    numeric: {
                        message: '折扣或者减价只能是数字'
                    },
                    regexp: {
                        regexp: /^[\.0-9]+$/,
                        message: '折扣或者减价不能小于等于0'
                    },
                    callback: {
                        message: "减价不能高于维修或保养的原价",
                        callback: function(value, validator) {
                            if (value <= 0) {
                                return false;
                            } else {
                                if (maintainMoney == null || maintainMoney == "" || maintainMoney == undefined || maintainMoney == 0) {
                                    return false;
                                } else {
                                    if (value <= maintainMoney) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }

                            }

                        }
                    }

                }
            },
            maintainId: {
                validators: {
                    notEmpty: {
                        message: '维修保养项目不能为空'
                    }

                }
            },
            maintainName: {
                validators: {
                    notEmpty: {
                        message: '请选择维修保养项目'
                    }

                }
            }
        }
    })

        .on('success.form.bv', function (e) {


            if (formId == "editForm") {
                formSubmit("/record/edit", formId, "editWin");
            } else if (formId == "detailForm") {
                formSubmit("/detail/add", formId, "detailWin");
            } else if (formId == "editDetailForm") {
                formSubmit("/progress/progress_byInfo", formId, "searchDetailWin");
                $("#searchDetailWin").modal('show');
            }

        })

}






