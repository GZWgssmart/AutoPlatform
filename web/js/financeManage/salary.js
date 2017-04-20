/**
 * Created by xiao-kang on 2017/4/17.
 */
var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/salary/query_pager");
    initSelect2("car_model", "请选择员工", "/carBrand/car_brand_all");
    initDateTimePicker("datatimepicker");

});

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var incomingType = selectRow[0];

        $("#updateForm").fill(incomingType);
        $("#addButton1").removeAttr("disabled");
        $("#editWin").modal('show');
    }
}

function getDate() {
    $("#addDatetimepicker").val(new Date());
}

function showAddWin() {
    validator("addForm");
    $("#addButton").removeAttr("disabled");
    $("#addWin").modal('show');
}


function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="showUpdateIncomingType1 btn btn-default  btn-sm" style="margin-right:15px;" >编辑</button>'
    ].join('');
}
window.operateEvents = {
    'click .showUpdateIncomingType1': function (e, value, row, index) {
        var incomingType = row;
        $("#updateForm").fill(incomingType);
        $("#addButton1").removeAttr("disabled");
        $("#editWin").modal('show');
    }
}

function validator(formId) {

    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            prizeSalary: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 18,
                        message: '用户名长度必须在6到18位之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '用户名只能包含大写、小写、数字和下划线'
                    }
                }
            },
            minusSalary: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress: {
                        message: '邮箱地址格式有误'
                    }
                }
            },
            brandId: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },

                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/salary/add_salary", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/incomingType/update_incomingType", formId, "editWin");

            }


        })

}

