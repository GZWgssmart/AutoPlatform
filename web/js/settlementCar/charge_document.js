$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/bill/pager?status=ALL");

    initDateTimePicker("datetimepicker", "chargeTime", "editForm");

    destoryValidator("editWin", "editForm");

});

/** 格式化操作栏的按钮 */
function operateFormatter(value, row, index) {
    if (row.chargeBillStatus == 'Y') {
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
        $.get("/bill/update_status?id=" + row.chargeBillId + "&status=" + row.chargeBillStatus,
            function(data){
                if(data.result == "success"){
                    $('#cusTable').bootstrapTable('refresh');
                }else if(data.result == "fail"){
                    swal(data.message, "", "error");
                }
            },"json");
    },
    'click .updateInactive': function (e, value, row, index) {
        $.get("/bill/update_status?id=" + row.chargeBillId + "&status=" + row.chargeBillStatus,
            function(data){
                if(data.result == "success"){
                    $('#cusTable').bootstrapTable('refresh');
                }else if(data.result == "fail"){
                    swal(data.message, "", "error");
                }
            },"json");
    },
    'click .showEditWin': function (e, value, row, index) {

        var chargeBill = row;
        $("#editForm").fill(chargeBill);
        $("#editWin").modal('show');
    }
}

/** 根据条件搜索 */
function searchCondition() {
    var userName = $("#searchUserName").val();
    var userPhone = $("#searchUserPhone").val();
    var paymentMethod = $("#searchPaymentMethod").val();
    initTable("cusTable", "/bill/condition_pager?userName=" + userName + "&userPhone=" + userPhone + "&paymentMethod=" + paymentMethod);

}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchUserName").val('');
    $("#searchUserPhone").val('');
    $("#searchPaymentMethod").val('all');
    $("#searchDiv").hide();
    $("#showButton").show();
}

/** 显示修改收费单据的信息 */
function showEditWin() {
    validator("editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('修改失败', "只能选择一条收费单据", "error");
        return false;
    } else {
        var chargeBill = selectRow[0];
        $("#editForm").fill(chargeBill);
        $("#editChargeTime").val(formatterDate(chargeBill.chargeTime))
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
            chargeBillMoney: {
                validators: {
                    notEmpty: {
                        message: '总金额不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 5,
                        message: '总金额的长度不能超过5位'
                    }
                }
            },
            actualPayment: {
                validators: {
                    notEmpty: {
                        message: '车主实际付款不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 5,
                        message: '实际付款金额不能超过5位'
                    }
                }
            },
            chargeTime: {
                validators: {
                    notEmpty: {
                        message: '付款时间不能为空'
                    }

                }
            },
            chargeBillDes: {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 500,
                        message: '描述不能超过500个字'
                    }

                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "editForm") {
                formSubmit("/bill/edit", formId, "editWin");

            }


        })
}

/** 显示打印收费单据的win */
function showChargeBillWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('打印失败', "只能选择一条收费单据进行打印", "error");
        return false;
    } else {
        var chargeBill = selectRow[0];
        $("#chargeTime").html(formatterDate2(chargeBill.chargeTime));
        $("#companyName").html(chargeBill.record.company.companyName);
        $("#paymentMethod").html(chargeBill.paymentMethod);
        $("#actualPaymentMax").html(upDigit(chargeBill.actualPayment));
        $("#actualPayment").html(chargeBill.actualPayment);
        $("#chargeBillDes").html(chargeBill.chargeBillDes);
        $("#chargeBillWin").modal("show");
    }

}

/** 打印收费单据 */
function printChargeBill() {
    var newWin=window.open('about:blank', '', '');
    var titleHTML = document.getElementById('printDiv').innerHTML;// 拿打印div所有元素
    newWin.document.write("<html><head><title></title><link rel='stylesheet' type ='text/css' href='/css/bootstrap.min.css'></head><body>" + titleHTML + "</body></html>");
    newWin.document.location.reload();
    newWin.print();
    newWin.close();
}

