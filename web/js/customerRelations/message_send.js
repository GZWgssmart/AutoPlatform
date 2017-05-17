


var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/MessageSend/query_pager");

});


/**添加车主*/
function showCustomer(){
    initTableNotTollbar("customerTable", "/record/pager_message");
    $("#customerWin").modal('show');
}

function addCustomer(){
    var selectRow = $("#customerTable").bootstrapTable('getSelections');
    if(selectRow.length < 1){
        swal('操作错误', "至少选择一个车主", "error");
    }else{
        addMessageId();
        $("#customerWin").modal('hide');
    }
}

var userId = new Array();
/**插入Id*/
function addMessageId(){
    var selectRow = $("#customerTable").bootstrapTable('getSelections');
    if(selectRow.length>0){
        for(var i = 0; i<selectRow.length;i++){
            userId[i] =selectRow[i].checkin.userId;
        }
        $.get("/MessageSend/addMessageId?userId="+userId,
            function(data){
                swal({
                        title: "是否修改短信发送内容",
                        text: data.message,
                        type: "warning",
                        showCancelButton: false,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "确认",
                        cancelButtonText: "取消",
                    closeOnConfirm: true,
                    closeOnCancel: true
                    },function (isConfirm) {
                    if (isConfirm) {
                        showEditWin(1,'all');
                    } else {
                        $('#cusTable').bootstrapTable('refresh');
                    }
                });

            })
    }else {
        swal('添加失败', "至少选择一行数据", "error");
    }
}

var idList = new Array();
var sendMsg = '';

/** 编辑数据 */
function showEditWin(str,type) {
    if(type == 'select'){
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    }else if(type == 'all'){
        $('#cusTable').bootstrapTable('refresh');
        var selectRow = $("#cusTable").bootstrapTable('getData');
    }
    if(selectRow.length > 0 || str==2){
        if(str==1){
        idList = new Array();
        for(var i =0; i < selectRow.length; i++){
            idList[i] = selectRow[i].messageId;
        }
        }else {
            idList = queryAllId();
        }
        $("#editWin").modal('show');
        validator("editForm");
    }else{
        swal('编辑失败', "至少选择一行数据", "error");
    }

}

function  queryAllId() {
    var queryList = new Array();
    $.get("/MessageSend/queryAllId",
        function(data){
            $.each(data, function (index, item) {
                queryList[index] = data[index].messageId;
            })
        })
    return queryList;
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
                sendMsg = $("#sendMsg").val();
                $.get("/MessageSend/update_messageSend?idList="+idList+"&sendMsg="+sendMsg,
                    function(data){
                        if(data.result == 'success'){
                            $('#editWin').modal('hide');
                            swal(data.message, "", "success");
                            $('#cusTable').bootstrapTable('refresh');
                        }
                    })
            }


        })

}
