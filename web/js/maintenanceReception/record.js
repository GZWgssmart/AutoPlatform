
var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/record/pager");

    initDateTimePicker("datetimepicker", "");

    initSelect2("maintain_fix", "请选择维修保养项目", "/maintainFix/maintain_all", "540");

});

function operateFormatter(value, row, index) {
    if (row.recordStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-danger btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showEditWin btn btn-primary btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-success btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showEditWin btn btn-primary btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }

}
window.operateEvents = {
         'click .updateActive': function (e, value, row, index) {
             $.get(contextPath + "/record/update_status?id=" + row.recordId + "&status=" + row.recordStatus,
                 function(data){
                     if(data.result == "success"){
                         $('#cusTable').bootstrapTable('refresh');
                     }else if(data.result == "fail"){
                         swal(data.message, "", "error");
                     }
                 },"json");
         },
          'click .updateInactive': function (e, value, row, index) {
              $.get(contextPath + "/record/update_status?id=" + row.recordId + "&status=" + row.recordStatus,
                  function(data){
                      if(data.result == "success"){
                          $('#cusTable').bootstrapTable('refresh');
                      }else if(data.result == "fail"){
                          swal(data.message, "", "error");
                      }
                  },"json");
          },
          'click .showEditWin': function (e, value, row, index) {

              var record = row;
              $("#editForm").fill(record);
              $('#editStartTime').val(formatterDate(record.startTime));
              $('#editEndTime').val(formatterDate(record.endTime));
              $("#editWin").modal('show');
         }
}

/** 显示编辑数据 */
function showEditWin() {
    validator("editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var record = selectRow[0];
        $("#editForm").fill(record);
        $('#editStartTime').val(formatterDate(record.startTime));
        $('#editEndTime').val(formatterDate(record.endTime));
        $("#editWin").modal('show');
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
            recordDes: {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 500,
                        message: '描述不能超过500个字'
                    }

                }
            },
            maintainDiscount: {
                validators: {
                    notEmpty: {
                        message: '折扣或者减价不能为空'
                    },
                    numeric: {
                        message: '折扣或者减价只能是数字'
                    },
                    callback: {
                        message: '折扣或者减价不能小于等于0',
                        callback: function(value, validator) {
                            if (value <= 0) {
                                return false;
                            } else {
                                return true;
                            }

                        }
                    }

                }
            },
            maintainId: {
                validators: {
                    notEmpty: {
                        message: '维修保养项目不能为空'
                    }

                }
            }
        }
    })

        .on('success.form.bv', function (e) {


            if (formId == "editForm") {
                formSubmit("/record/edit", formId, "editWin");
            } else if (formId == "detailForm") {
                alert("aa")
            }

        })

}

function formatterTarck(value, row, index) {
    if (value == "Y") {
        return "是";
    } else {
        return "<span style='color: red'>否</span>";
    }
}

/** 显示生成维修保养明细的窗口 */
function showDetailWin() {
    validator("detailForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "只能选择一条数据生成维修保养明细", "error");
        return false;
    } else {
        var record = selectRow[0];
        $("#detailForm").fill(record);
        $("#detailWin").modal('show');
    }
}







