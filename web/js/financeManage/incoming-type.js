/**
 * Created by xiao-kang on 2017/4/17.
 */
var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/incomingType/query_pager");
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
        var incomingType = selectRow[0];
        validator("editForm");
        $("#editForm").fill(incomingType);
        $("#editWin").modal('show');
    }
}


function showAddWin(){
   validator("addForm");
    $("#addWin").modal('show');
}

function operateFormatter(value, row, index) {
    if (row.inTypeStatus == 'Y') {
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
             $.get(contextPath + "/incomingType/update_status?id=" + row.inTypeId + "&status=" + status,
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
              $.get(contextPath + "/incomingType/update_status?id=" + row.inTypeId + "&status=" + status,
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
              $("#editWin").modal('show');
         }
}

function searchStatus(status){
    if(status == 'Y'){
        var status = 'Y';
        initTable("cusTable", "/incomingType/query_status?status="+status);
    }else if(status == 'N'){
        var status = 'N';
        initTable("cusTable", "/incomingType/query_status?status="+status);
    }else if(status == 'ALL'){
        initTable("cusTable", "/incomingType/query_pager");
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
            inTypeName: {
                message: '收入类型名称失败',
                validators: {
                    notEmpty: {
                        message: '收入类型名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '收入类型名称长度必须在2到20位之间'
                    }
                }
            }


        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/incomingType/add_incomingType", formId, "addWin");
            } else if (formId == "editForm") {
                formSubmit("/incomingType/update_incomingType", formId, "editWin");

            }
        })

}
