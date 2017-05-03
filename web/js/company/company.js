/**
 * Created by root on 2017/4/19.
 */

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/company/queryByPager");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});
function companyAll(){
    initTable("cusTable","/company/queryByPager");
}
/**查看不可用的公司*/
function statusUsableness(){
    var ids = 'Y';
    initTable("cusTable","/company/queryStatusPager?status="+ids);
}
/**查看可用的公司*/
function statusAvailable(){
    var ids = "N";
    initTable("cusTable","/company/queryStatusPager?status="+ids);
}

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var product = selectRow[0];
        $("#editForm").fill(product);
        validator("editForm");
        $("#editWin").modal('show');
    }
}

function operating(value, row, index) {
    if (row.companyStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm btn-danger">冻结</button>&nbsp;&nbsp;',
            '<button onclick="showEditWin();" type="button" class="btn btn-default  btn-sm btn-primary">编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm btn-success" >激活</button>&nbsp;&nbsp;',
            '<button onclick="showEditWin();" type="button" class="btn btn-default  btn-sm btn-primary" >编辑</button>'
        ].join('');
    }
}


function showAddWin(){
    validator("addForm");
    $("#addWin").modal('show');
}


window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var companyStatus = 'N';
        $.get("/company/companyStatusModify?id=" + row.companyId + "&status=" + row.companyStatus,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var companyStatus = 'Y';
        $.get("/company/companyStatusModify?id=" + row.companyId + "&status=" + row.companyStatus,
            function (data) {
                if (data.result == "success") {
                    // $('#addWin').modal('hide');
                    // swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
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
            companyName: {
                message: '公司名称失败',
                validators: {
                    notEmpty: {
                        message: '公司名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '公司名称长度必须在2到4位之间'
                    }
                }
            },
            companyDes: {
                message: '公司描述失败',
                validators: {
                    notEmpty: {
                        message: '公司描述不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: '公司描述长度必须在1到500位之间'
                    }
                }
            },
            companyAddress: {
                message: '公司地址失败',
                validators: {
                    notEmpty: {
                        message: '公司地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 200,
                        message: '公司地址长度必须在1到200位之间'
                    }
                }
            },
            companyTel: {
                message: '公司联系方式失败',
                validators: {
                    notEmpty: {
                        message: '公司联系方式不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 200,
                        message: '公司联系方式长度必须在1到11位之间'
                    }
                }
            },
            companyPricipal: {
                message: '公司负责人失败',
                validators: {
                    notEmpty: {
                        message: '公司负责人不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 200,
                        message: '公司负责人长度必须在1到10位之间'
                    }
                }
            },
            companyWebsite: {
                message: '公司官网URL失败',
                validators: {
                    notEmpty: {
                        message: '公司官网URL不能为空'
                    }
                }
            },
            companyOpenDate:{
                message: '公司创建时间',
                validators: {
                    notEmpty: {
                        message: '公司创建时间不能为空'
                    }
                }
            },
            companyOpenSize:{
                message: '公司规模失败',
                validators: {
                    notEmpty: {
                        message: '公司规模不能为空'
                    }
                }
            },
            companyLongitude:{
                message: '公司经度失败',
                validators: {
                    notEmpty: {
                        message: '公司经度不能为空'
                    }
                }
            },
            companyLatitude:{
                message:'公司纬度失败',
                validators:{
                    notEmpty:{
                        message:'纬度不能为空'
                    }
                }
            }

        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/company/InsertCompany", formId, "addWin");
            } else if (formId == "editForm") {
                formSubmit("/company/uploadCompany", formId, "editWin");

            }
        })

}
