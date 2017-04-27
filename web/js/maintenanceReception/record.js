
var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/record/pager?status=ALL");

    initDateTimePickerNotValitor("datetimepicker");

    initSelect2("maintain_fix", "请选择维修保养项目", "/maintainFix/maintain_all", "540");

    destoryValidator("editWin", "editForm");
    destoryValidator("detailWin", "detailForm");
    destoryValidator("editDetailWin", "editDetailForm");

    initSelect2("company", "请选择汽修公司", "/company/company_all", "150");

    $("#maintainFixWin").on("hide.bs.modal", function () {
        $("#carPlate").html('&nbsp;');
        $("#userName").html('&nbsp;');
        $("#userPhone").html('&nbsp;');
        $("#maintainCarMileage").html('&nbsp;');
        $("#startTime").html('&nbsp;');
        $("#count").html('&nbsp;');

        for (var i = 0; i < 10; i++) {
            $("#maintainName" + i).html('&nbsp;');
            $("#maintainPrice" + i).html('&nbsp;');

            $("#fixName" + i).html('&nbsp;');
            $("#fixAcc" + i).html('&nbsp;');
            $("#fixCarMileage" + i).html('&nbsp;');
            $("#fixUser" + i).html('&nbsp;');
            $("#fixTime" + i).html('&nbsp;');
        }
    });

});

/** 格式化操作栏的按钮 */
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
/** 点击事件监听 */
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

var maintainMoney; // 用于验证输入的减价是否合理
/** 表单验证 */
function validator(formId) {
    $("#editButton").removeAttr("disabled");
    $("#detailButton").removeAttr("disabled");
    $("#editDetailButton").removeAttr("disabled");
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
                    regexp: {
                        regexp: /^[\.0-9]+$/,
                        message: '折扣或者减价不能小于等于0'
                    },
                    callback: {
                        message: "减价不能高于维修或保养的原价",
                        callback: function(value, validator) {
                            if (value <= 0) {
                                return false;
                            } else {
                                if (maintainMoney == null || maintainMoney == "" || maintainMoney == undefined || maintainMoney == 0) {
                                    return false;
                                } else {
                                    if (value <= maintainMoney) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }

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
            },
            maintainName: {
                validators: {
                    notEmpty: {
                        message: '请选择维修保养项目'
                    }

                }
            }
        }
    })

        .on('success.form.bv', function (e) {


            if (formId == "editForm") {
                formSubmit("/record/edit", formId, "editWin");
            } else if (formId == "detailForm") {
                formSubmit("/detail/add", formId, "detailWin");
            } else if (formId == "editDetailForm") {
                formSubmit("/detail/edit", formId, "editDetailWin");
                $('#detailTable').bootstrapTable('refresh');
                $("#searchDetailWin").modal('show');
            }

        })

}

/** 显示是否回访 */
function formatterTrack(value, row, index) {
    if (value == "Y") {
        return "是";
    } else {
        return "<span style='color: red'>否</span>";
    }
}

/** 显示生成维修保养明细的窗口 */
function showAddDetailWin() {
    validator("detailForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "只能选择一条数据生成维修保养明细", "error");
        return false;
    } else {
        maintainMoney = 0;
        var record = selectRow[0];
        $("input[type=reset]").trigger("click");
        $("#detailForm").fill(record);
        $("#detailWin").modal('show');
    }
}

/** 显示查看保养明细详情 */
function showDetailWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "只能选择一条数据查看维修保养明细", "error");
        return false;
    } else {
        var record = selectRow[0];
        var recordId = record.recordId;
        initTableSetToolbar("detailTable", "/detail/pager?recordId=" + recordId, "toolbar1");
        $("#searchDetailWin").modal('show');
    }
}

/** 显示修改保养明细详情的窗口 */
function showEditDetailWin() {
    validator("editDetailForm");
    var selectRow = $("#detailTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "只能选择一条数据修改", "error");
        return false;
    } else {
        var detail = selectRow[0];
        $("#editDetailForm").fill(detail);
        $('#editDetailMaintain').html('<option value="' + detail.maintain.maintainId + '">' + detail.maintain.maintainName + '</option>').trigger("change");
        $("#searchDetailWin").modal('hide');
        $("#editDetailWin").modal('show');
    }
}

/** 关闭修改维修保养明细的窗口 */
function closeEditDetailWin() {
    $("#editDetailWin").modal('hide');
    $("#searchDetailWin").modal('show');
}

/** 格式化原价 */
function formatterMoney(value, row, index) {
    return "$" + value;
}

/** 格式化打折或减价 */
function formatterDiscount(value, row, index) {
    if (parseFloat(value) < 1) {
        return value + "折";
    } else {
        return "$" + value;
    }
}

/** 选择维修保养项目 */
function choiseMaintain() {
    var maintainOrFix = $("#maintainOrFix").val();
    if (maintainOrFix == "维修") {
        initTableNotTollbar("fixTable", "/maintainFix/queryByMaintenanceItemPager");
        $("#fixWin").modal('show');
    } else if (maintainOrFix == "保养") {
        initTableNotTollbar("maintainTable", "/maintainFix/queryByPager");
        $("#maintainWin").modal('show');
    }
}

/** 确定选择的保养项目 */
function determineMaintain() {
    determineMaintainOrFix("maintainTable", "maintainWin", "只能选择一条保养项目");
}

/** 确定选择的维修项目 */
function determineFix() {
    determineMaintainOrFix("fixTable", "fixWin", "只能选择一条保养项目");
}

/** 选择维修保养项目的公共方法 */
function determineMaintainOrFix(tableId, winId, message) {
    var selectRow = $("#" + tableId).bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', message, "error");
        return false;
    } else {
        var maintain = selectRow[0];
        $("#detailMaintainId").val(maintain.maintainId);
        $("#detailMaintainName").val(maintain.maintainName);
        maintainMoney = maintain.maintainMoney;
        $("#" + winId).modal('hide');
    }
}

/** 根据条件搜索 */
function searchCondition() {
    var userName = $("#searchUserName").val();
    var carPlate = $("#searchCarPlate").val();
    var maintainOrFix = $("#searchMaintainOrFix").val();
    var companyId = $("#searchCompanyId").val();
    if (companyId != null && companyId != "") {
        initTable("cusTable", "/record/condition_pager?userName=" + userName + "&carPlate=" + carPlate + "&maintainOrFix=" + maintainOrFix + "&companyId=" + companyId);
    } else {
        swal("错误提示", "请选择一家汽修公司", "error");
    }

}

/** 生成明细清单 */
function generateDetail() {
    var selectRow = $("#detailTable").bootstrapTable('getSelections');
    if (selectRow.length < 1) {
        swal('错误提示', "最少选择一条数据", "error");
        return false;
    } else {
        var len = selectRow.length;
        var detail1 = selectRow[0];
        var maintainOrFix = detail1.record.checkin.maintainOrFix;
        var count = 0;
        if (maintainOrFix == "保养") {
            for (var i = 0; i < len; i++) {
                var detail = selectRow[i];
                $("#maintainName" + i).html(detail.maintain.maintainName);
                $("#maintainPrice" + i).html(detail.price);
                count += detail.price;
            }
            $("#count").html(count);
        } else if (maintainOrFix == "维修") {
            for (var i = 0; i < len; i++) {
                var detail = selectRow[i];
                $("#fixName" + i).html(detail.maintain.maintainName);
                $("#fixAcc" + i).html("配件名");
                $("#fixCarMileage" + i).html(detail.record.checkin.carMileage);
                $("#fixUser" + i).html("张师傅");
                $("#fixTime" + i).html(formatterDate1(detail.record.startTime));
            }
        }



        var carPlate = detail1.record.checkin.plate.plateName + "-" + detail1.record.checkin.carPlate;
        var userName = detail1.record.checkin.userName;
        var userPhone = detail1.record.checkin.userPhone;
        var maintainCarMileage = detail1.record.checkin.carMileage;
        var startTime = detail1.record.startTime;
        $("#carPlate").html(carPlate);
        $("#userName").html(userName);
        $("#userPhone").html(userPhone);
        $("#maintainCarMileage").html(maintainCarMileage);
        $("#startTime").html(formatterDate1(startTime));

        $("#maintainFixWin").modal("show");
    }
}





