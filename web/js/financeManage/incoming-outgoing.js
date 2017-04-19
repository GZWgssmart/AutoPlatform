/**
 * Created by xiao-kang on 2017/4/17.
 */
var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/incomingOutgoing/query_pager");
});

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var incomingOutgoing = selectRow[0];
        $("#updateForm").fill(incomingOutgoing);
        $("#addButton1").removeAttr("disabled");
        $("#editWin").modal('show');
    }
}

/**提交编辑数据 */
function update() {
    var name = $("#name1").val();
    var error = document.getElementById("error1");
    if(name != ''){
        $.post(contextPath + "/incomingOutgoing/update_inOut",
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
        $("#addButton1").attr('disabled','disabled');
    }else{
        error.innerHTML = "请输入正确的数据";
        $("#addButton1").removeAttr("disabled");
    }


}



function operateFormatter(value, row, index) {
    if (row.inOutStatus == 'Y') {
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
         'click .updateActive': function (e, value, row, index) {
             var status = 'N';
             $.get(contextPath + "/incomingOutgoing/update_status?id=" + row.inOutId + "&status=" + status,
                 function(data){
                     if(data.result == "success"){
                         $('#cusTable').bootstrapTable('refresh');
                     }else if(data.result == "fail"){
                         swal(data.message, "", "error");
                     }
                 },"json");
         },
          'click .updateInactive': function (e, value, row, index) {
              var status = 'Y';
              $.get(contextPath + "/incomingOutgoing/update_status?id=" + row.inOutId + "&status=" + status,
                  function(data){
                      if(data.result == "success"){
                          $('#cusTable').bootstrapTable('refresh');
                      }else if(data.result == "fail"){
                          swal(data.message, "", "error");
                      }
                  },"json");
          },
          'click .showUpdateIncomingType1': function (e, value, row, index) {
              var incomingType = row;
              $("#updateForm").fill(incomingType);
              $("#addButton1").removeAttr("disabled");
              $("#editWin").modal('show');
         }
}

function queryByInOutType(type){
    if(type == 1){
        initTable("cusTable", "/incomingOutgoing/query_inOutType?type=2");
        $('#cusTable').bootstrapTable('hideColumn', 'incomingType.inTypeName');
    }else if(type == 2){
        initTable("cusTable", "/incomingOutgoing/query_inOutType?type=1");
        $('#cusTable').bootstrapTable('hideColumn', 'outgoingType.outTypeName');
    }else if(type == 3){
        initTable("cusTable", "/incomingOutgoing/query_pager");
    }
}

function statusFormatter(value, row, index) {
    if (row.inOutStatus == 'Y') {
        return [
            '可用'
        ].join('');
    }else if(row.inOutStatus == 'N'){
        return [
            '不可用'
        ].join('');
    }

}


