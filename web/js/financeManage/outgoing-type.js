/**
 * Created by xiao-kang on 2017/4/17.
 */
var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/outgoingType/query_pager");

    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var outgoingType = selectRow[0];
        $("#updateForm").fill(outgoingType);
        $("#editWin").modal('show');
    }
}

/**提交编辑数据 */
function updateOutgoingType() {
    $("#addButton1").attr('disabled','disabled');
    var name = $("#name1").val();
    var error = document.getElementById("error1");
    if(name != '') {
        $.post(contextPath + "/outgoingType/update_outgoingType",
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
    }else{
        error.innerHTML = "请输入正确的数据";
        $("#addButton1").removeAttr("disabled");
    }

}

/**提交添加数据 */
function addOutgoingType() {
    $("#addButton").attr('disabled','disabled');
    var name = $("#name").val();
    var error = document.getElementById("error");
    if (name != "") {
        $.post(contextPath + "/outgoingType/add_outgoingType",
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
    if (row.outTypeStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showUpdateoutgoingType1 btn btn-default  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    }else{
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showUpdateoutgoingType1 btn btn-default  btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }

}
window.operateEvents = {
         'click .updateActive': function (e, value, row, index) {
             var status = 'N';
             $.get(contextPath + "/outgoingType/update_status?id=" + row.outTypeId + "&status=" + status,
                 function(data){
                     if(data.result == "success"){
                         $('#addWin').modal('hide');
                         swal(data.message, "", "success");
                         $('#cusTable').bootstrapTable('refresh');
                     }else if(data.result == "fail"){
                         swal(data.message, "", "error");
                     }
                 },"json");
         },
          'click .updateInactive': function (e, value, row, index) {
              var status = 'Y';
              $.get(contextPath + "/outgoingType/update_status?id=" + row.outTypeId + "&status=" + status,
                  function(data){
                      if(data.result == "success"){
                          $('#addWin').modal('hide');
                          swal(data.message, "", "success");
                          $('#cusTable').bootstrapTable('refresh');
                      }else if(data.result == "fail"){
                          swal(data.message, "", "error");
                      }
                  },"json");
          },
          'click .showUpdateoutgoingType1': function (e, value, row, index) {
              var outgoingType = row;
              $("#updateForm").fill(outgoingType);
              $("#editWin").modal('show');
         }
}

function statusFormatter(value, row, index) {
    if (row.outTypeStatus == 'Y') {
        return [
            '可用'
        ].join('');
    }else if(row.outTypeStatus == 'N'){
        return [
            '不可用'
        ].join('');
    }

}

