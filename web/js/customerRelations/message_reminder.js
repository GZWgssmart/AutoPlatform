


var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/MessageReminder/query_pager");
    initSelect2("car_model", "请选择一条数据", "/MessageReminder/query_user","570");
    initDateTimePicker("nowDatrtime","remindTime");
    initDateTimePicker("nowDatrtime","remindCreatedTime");
});

function getDate() {
    $("#editRemindCreatedTime").val(new Date());
}

function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="showUpdate btn btn-default  btn-sm" style="margin-right:15px;" >编辑</button>'
    ].join('');
}
window.operateEvents = {
    'click .showUpdate': function (e, value, row, index) {
        var reminder = row;
        $("#editForm").fill(reminder);
        $('#editLastMaintainTime').val(formatterDate(reminder.lastMaintainTime));
        $('#editRemindTime').val(formatterDate(reminder.remindTime));
        $('#editRemindCreatedTime').val(formatterDate(reminder.remindCreatedTime));
        $("#editWin").modal('show');
    }
}




/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var reminder = selectRow[0];
        $("#editForm").fill(reminder);
        validator("editForm");
        $('#editLastMaintainTime').val(formatterDate(reminder.lastMaintainTime));
        $('#editRemindTime').val(formatterDate(reminder.remindTime));
        $('#editRemindCreatedTime').val(formatterDate(reminder.remindCreatedTime));
        $("#editWin").modal('show');
    }
}

/**提交编辑数据 */
function updateIncomingType() {
    $("#editButton").attr('disabled','disabled');
    var name = $("#name1").val();
    var error = document.getElementById("error1");
    if(name != ''){
        $.post(contextPath + "/MessageReminder/edit",
            $("#updateForm").serialize(),
            function(data){
                if(data.result == "success"){
                    $('#editWin').modal('hide');
                    swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                }else if(data.result == "fail"){
                    swal(data.message, "", "error");
                }
            },"json");
    }else{
        error.innerHTML = "请输入正确的数据";
        $("#addButton1").removeAttr("disabled");
    }


}

/**提交添加数据 */
function addCompaint() {
    $("#addButton").attr('disabled','disabled');
    var name = $("#complaintReply").val();
    var error = document.getElementById("error");
    if (name != "") {
        $.post(contextPath + "/MessageReminder/add_complaint",
            $("#addForm").serialize(),
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                    $("input[type=reset]").trigger("click");
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    }else{
        error.innerHTML = "请输入正确的数据";
        $("#addButton").removeAttr("disabled");
    }

}


/** 表单验证 */
function validator(formId) {
    $("#editButton").removeAttr("disabled");
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            remindMsg: {
                validators: {
                    notEmpty: {
                        message: '保养提醒内容不能为空'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            formSubmit("/MessageReminder/edit", formId, "editWin");
        })

}





