/**
 * Created by xiao-kang on 2017/4/17.
 */
var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/incomingOutgoing/query_pager");
    destoryValidator("editWin","editForm");
});

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var incomingOutgoing = selectRow[0];
        validator("editForm");
        $("#editForm").fill(incomingOutgoing);
        $("#addButton1").removeAttr("disabled");
        $("#editWin").modal('show');
    }
}



function operateFormatter(value, row, index) {
    if (row.inOutStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-danger  btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    }else{
        return [
            '<button type="button" class="updateInactive btn btn-success  btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-primary  btn-sm" style="margin-right:15px;">编辑</button>'
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
              validator("editForm");
              $("#editForm").fill(incomingType);
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
            inOutMoney: {
                message: '收支金额失败',
                validators: {
                    notEmpty: {
                        message: '收支金额不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '收支金额只能是数字'
                    }
                }
            }


        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
            } else if (formId == "editForm") {
                formSubmit("/incomingOutgoing/update_inOut", formId, "editWin");

            }
        })

}
