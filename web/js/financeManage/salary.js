/**
 * Created by xiao-kang on 2017/4/17.
 */
var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/salary/query_pager");

    initDateTimePicker("datatimepicker","salaryTime");
    destoryValidator("editWin","editForm");
    destoryValidator("addWin","addForm");

});

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var salary = selectRow[0];
        validator("editForm");
        $("#editForm").fill(salary);
        $("#editTime").val(formatterDate(salary.salaryTime));
        $("#editWin").modal('show');
    }
}

function getDate() {
    $("#datatimepicker").val(new Date());
}

function showAddWin() {
    validator("addForm");
    $(".userId").val("");
    $(".userName").val("");
    $("#addWin").modal('show');
}

function showUserWin(){
    initTableNotTollbar("userTable", "/peopleManage/query_user");
    $("#userWin").modal('show');

}

function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="showUpdateIncomingType1 btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
    ].join('');
}
window.operateEvents = {
    'click .showUpdateIncomingType1': function (e, value, row, index) {
        var salary = row;
        validator("editForm");
        $("#editForm").fill(salary);
        $("#editTime").val(formatterDate(salary.salaryTime));
        $("#editWin").modal('show');
    }
}

function userFormatter(value, row, index) {
    return [
        '<button type="button" class="addUserName btn btn-primary  btn-sm" style="margin-right:15px;" >选择</button>'
    ].join('');
}

window.userEvents = {
    'click .addUserName': function (e, value, row, index) {
        var user = row;
        $(".userId").val(user.userId);
        $(".userName").val(user.userName);
        $("#userWin").modal('hide');
    }
}

function addUserName(){
    var selectRow = $("#userTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('选择失败', "只能选择一个员工", "error");
        return false;
    } else {
        var user = selectRow[0];
        $(".userId").val(user.userId);
        $(".userName").val(user.userName);
        $("#userWin").modal('hide');
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
            userId: {
                message: '验证失败',
                validators: {
                    notEmpty: {
                        message: '必须选择员工'
                    }
                }
            },
            prizeSalary: {
                validators: {
                    notEmpty: {
                        message: '奖金不能为空'
                    }
                }
            },
            minusSalary: {
                validators: {
                    notEmpty: {
                        message: '罚金不能为空'
                    }
                }
            },
            salaryTime: {
                validators: {
                    notEmpty:{
                        message: '发放时间不能为空'
                    }
                }

            },
            salaryDes:{
                validators:{
                    notEmpty:{
                        message: '工作描述不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 500,
                        message: '收入类型名称长度必须在2到500位之间'
                    }
                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/salary/add_salary", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/salary/update_salary", formId, "editWin");

            }


        })

}

