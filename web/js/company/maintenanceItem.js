/**
 * Created by root on 2017/4/24.
 */
$(document).ready(function () {
    //调用函数，初始化表格
    // initTable("cusTables","/accessories/queryByIdAcc");
    initTable("cusTable","/maintainFix/queryByMaintenanceItemPager");
    initSelect2("company", "请选择汽修公司", "/company/company_all", "565");
    initSelect2("acc_accessoriesType", "请选择配件类别", "/accessoriesType/accessoriesType_All", "550");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
    destoryValidator('addWin', 'addForm');
    destoryValidator('editWin', 'editForm');
});

function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var product = selectRow[0];
        $('.company').html('<option value="' + product.company.companyName + '">' + product.company.companyName + '</option>').trigger("change");
        validator("editForm");
        $("#editForm").fill(product);
        $("#editWin").modal('show');
    }
}

function showAddWin(){
    validator("addForm");
    $("#addWin").modal('show');
    $("input[type=reset]").trigger("click");
}

/**
 * 显示 配件窗口
 * */
var maintenanceltemId
function showAddacc(){
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('选择保养项目失败', "只能选择一条保养项目进行配件分配", "error");
        return false;
    } else {
        var product = selectRow[0];
        maintenanceltemId = product.maintainId;
        $("#maintenanceWin").modal('show');
    }
}


function queryByTypeId(obj){
    initTableNotTollbar("cusTable2", "/accessories/queryByIdAcc?id=" + obj.value);
}

function operating(value, row, index) {
    if (row.maintainStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm btn-danger" >冻结</button>&nbsp;&nbsp;',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default btn-sm btn-primary ">编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm btn-success" >激活</button>&nbsp;&nbsp;',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default btn-sm btn-primary ">编辑</button>'
        ].join('');
    }
}

var AccessoriesId
function operationWin(value,row,index){
    alert(row.accId);
    if(row.accId != null){
        return[
            '<button type="button" class="btn btn-default  btn-sm btn-success" onclick="showAccWin();">选择配件</button>'
        ]
    }
}

function showAccWin(){
   $("#accWin").modal('show');
    var Row = $("#cusTable2").bootstrapTable('getSelections');
    var acc = Row[0]
    var accId = null;
}

function Addacc() {
   var count = $("#count").val();
   if(Id != null || maintenanceltemId != null||count != null){
       alert(count);
       alert(AccessoriesId);
       alert(maintenanceltemId);
   }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var Status = 'N';
        $.get("/maintainFix/StatusModify?id=" + row.maintainId + "&status=" + Status,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                } else if (data.result == "notLogin") {
                    swal({
                            title: "登入失败",
                            text: data.message,
                            type: "warning",
                            showCancelButton: false,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "确认",
                            closeOnConfirm: true
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                top.location.href = "/login/show_login";
                            }
                        });
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var Status = 'Y';
        $.get("/maintainFix/StatusModify?id=" + row.maintainId + "&status=" + Status,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                } else if (data.result == "notLogin") {
                    swal({
                            title: "登入失败",
                            text: data.message,
                            type: "warning",
                            showCancelButton: false,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "确认",
                            closeOnConfirm: true
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                top.location.href = "/login/show_login";
                            }
                        });
                }
            }, "json")
    },
    'click .showUpdateIncomingType1': function (e, value, row, index) {
        var incomingType = row;
        validator("editForm");
        $("#editForm").fill(incomingType);
        $("#editWin").modal('show');
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
            maintainName: {
                message: '保养名称失败',
                validators: {
                    notEmpty: {
                        message: '保养名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '保养名称长度必须在2到20位之间'
                    }
                }
            },
            maintainHour: {
                message: '保养所需工时失败',
                validators: {
                    notEmpty: {
                        message: '保养所需工时不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '保养所需工时必须在1到10位之间'
                    }
                }
            },
            maintainMoney: {
                message: '保养基础费用失败',
                validators: {
                    notEmpty: {
                        message: '保养基础费用不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '保养基础费用必须在1元到100000元之间'
                    }
                }
            },
            maintainManHourFee: {
                message: '保养工时费用失败',
                validators: {
                    notEmpty: {
                        message: '保养工时费用不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '保养工时费用必须在1元到100000元之间'
                    }
                }
            },
            maintainDes: {
                message: '保养描述失败',
                validators: {
                    notEmpty: {
                        message: '保养描述用不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '保养描述用字符长度必须在1~500字之间'
                    }
                }
            },
            companyId: {
                message: '公司名称失败',
                validators: {
                    notEmpty: {
                        message: '公司名称不能为空'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/maintainFix/InsertMaintain", formId, "addWin");
            } else if (formId == "editForm") {
                formSubmit("/maintainFix/update", formId, "editWin");
            }
        })

}
