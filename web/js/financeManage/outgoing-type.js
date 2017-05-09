/**
 * Created by xiao-kang on 2017/4/17.
 */
var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/outgoingType/query_pager");
    initSelect2("company", "请选择公司", "/company/company_all", "565");
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
        var outgoingType = selectRow[0];
        if(outgoingType.outTypeName != '工资支出' && outgoingType.company.companyId != null ){
            validator("editForm");
            $("#editForm").fill(outgoingType);
            $('#editCompany').html('<option value="' + outgoingType.company.companyId + '">' + outgoingType.company.companyName + '</option>').trigger("change");
            $("#editWin").modal('show');
        }else{
            swal('编辑失败', "你不能修改名称为工资支出的记录", "warning");
        }
    }
}

function showAddWin(){
    validator("addForm");
    $('#addCompany').html('').trigger("change");
    $("#addWin").modal('show');
}



function operateFormatter(value, row, index) {
    if (row.outTypeStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-danger  btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showUpdateoutgoingType1 btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    }else{
        return [
            '<button type="button" class="updateInactive btn btn-success  btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showUpdateoutgoingType1 btn btn-primary  btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }

}
window.operateEvents = {
         'click .updateActive': function (e, value, row, index) {
             var status = 'N';
             if(row.outTypeName != '工资支出' && row.company.companyId != null ) {
                 $.get(contextPath + "/outgoingType/update_status?id=" + row.outTypeId + "&status=" + status,
                     function (data) {
                         if (data.result == "success") {
                             $('#cusTable').bootstrapTable('refresh');
                         } else if (data.result == "fail") {
                             swal(data.message, "", "error");
                         }
                     }, "json");
             } else{
                 swal('编辑失败', "你不能冻结称为工资支出的记录", "warning");
             }
         },
          'click .updateInactive': function (e, value, row, index) {
              var status = 'Y';
              if(row.outTypeName != '工资支出' && row.company.companyId != null ) {
                  $.get(contextPath + "/outgoingType/update_status?id=" + row.outTypeId + "&status=" + status,
                      function (data) {
                          if (data.result == "success") {
                              $('#cusTable').bootstrapTable('refresh');
                          } else if (data.result == "fail") {
                              swal(data.message, "", "error");
                          }
                      }, "json");
              }else{
                  swal('编辑失败', "你不能激活名称为工资支出的记录", "warning");
              }
          },
          'click .showUpdateoutgoingType1': function (e, value, row, index) {
              var outgoingType = row;
              if(outgoingType.outTypeName != '工资支出' && outgoingType.company.companyId != null ) {
                  validator("editForm");
                  $("#editForm").fill(outgoingType);
                  $('#editCompany').html('<option value="' + outgoingType.company.companyId + '">' + outgoingType.company.companyName + '</option>').trigger("change");
                  $("#editWin").modal('show');
              }else{
                  swal('编辑失败', "你不能修改名称为工资支出的记录", "warning");
              }
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
            outTypeName: {
                message: '支出类型名称失败',
                validators: {
                    notEmpty: {
                        message: '支出类型名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '支出类型名称长度必须在2到20位之间'
                    }
                }
            },
            companyId: {
                validators: {
                    notEmpty: {
                        message: '所属公司不能为空'
                    }
                }
            }


        }
    })

        .on('success.form.bv', function (e) {
            var addName = $("#name").val();
            var editName = $("#name1").val();
            if (formId == "addForm") {
                if(addName != '工资支出'){
                    formSubmit("/outgoingType/add_outgoingType", formId, "addWin");
                }else{
                    swal('添加失败', "你不能添加名称为工资支出", "warning");
                    $('#addWin').modal('hide');
                    $('#' + formId).data('bootstrapValidator').resetForm(true);
                }

            } else if (formId == "editForm") {
                if(editName != '工资支出'){
                    formSubmit("/outgoingType/update_outgoingType", formId, "editWin");
                }else{
                    swal('编辑失败', "你不能修改名称为工资支出", "warning");
                    $('#editWin').modal('hide');
                    $('#' + formId).data('bootstrapValidator').resetForm(true);
                }
            }
        })

}
