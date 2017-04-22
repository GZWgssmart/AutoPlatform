


var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/MessageSend/query_pager");

    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});




/** 添加数据 */
function showAddWin() {
    $("#addWin").modal('show');

}

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    var ids ='';
    if(selectRow.length > 0){
    for(var i =0; i < selectRow.length; i++){
        if(ids == ''){
            ids = selectRow[i];
        }else{
            ids = ','+selectRow[i];
        }
            validator("editForm");
            $.get("/ids="+ids,
                function(data){
                    if(data == 'success'){
                        $("#editWin").modal('show');
                        swal(data.message, "", "success");
                        $('#cusTable').bootstrapTable('refresh');
                    }
            })
    }
    }else{
        swal('编辑失败', "至少选择一行数据", "error");
    }

}

/**提交编辑数据 */
function updateIncomingType() {
    $("#addButton1").attr('disabled','disabled');
    var name = $("#name1").val();
    var error = document.getElementById("error1");
    if(name != ''){
        $.post(contextPath + "/MessageSend/update_messageSend",
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
        $.post(contextPath + "/complaint/add_complaint",
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


function operateFormatter(value, row, index) {
    if (row.inTypeStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    }else{
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default  btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }

}
window.operateEvents = {
        'click .showUpdateIncomingType1': function (e, value, row, index) {
        var message = row;
        validator("editForm");
        $("#editForm").fill(message);
        $("#editWin").modal('show');
    }
}

function statusFormatter(value, row, index) {
    if (row.inTypeStatus == 'Y') {
        return [
            '可用'
        ].join('');
    }else if(row.inTypeStatus == 'N'){
        return [
            '不可用'
        ].join('');
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
        sendMsg:{
                validators:{
                    notEmpty:{
                        message: '短信内容不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: '短信内容不能超过500个字符'
                    }
                }
            }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/salary/add_salary", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/MessageSend/update_MessageSend", formId, "editWin");

            }


        })

}
