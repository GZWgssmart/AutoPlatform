$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/supplyType/queryByPager?status=ALL");

});

/** 编辑数据 */
function showEditWin() {
    validator("editForm")
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var supplyType = selectRow[0];
        $("#editForm").fill(supplyType);
        $("#editWin").modal('show');
    }
}

function showAddWin() {
    validator("addForm");
    $("#addButton").removeAttr("disabled");
    $("#addWin").modal('show');
}


function operateFormatter(value, row, index) {
    if (row.supplyTypeStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-danger  btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showUpdateSupplyType1 btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    }else{
        return [
            '<button type="button" class="updateInactive btn btn-success  btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showUpdateSupplyType1 btn btn-primary  btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }

}
window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var status = 'N';
        $.get(contextPath + "/supplyType/updateStatus?id=" + row.supplyTypeId + "&status=" + status,
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
        $.get(contextPath + "/supplyType/updateStatus?id=" + row.supplyTypeId + "&status=" + status,
            function(data){
                if(data.result == "success"){
                    $('#cusTable').bootstrapTable('refresh');
                }else if(data.result == "fail"){
                    swal(data.message, "", "error");
                }
            },"json");
    },
    'click .showUpdateSupplyType1': function (e, value, row, index) {
        validator("editForm");
        var supplyType = row;
        $("#editForm").fill(supplyType);
        $("#addButton").removeAttr("disabled");
        $("#editWin").modal('show');
    }
}

/** 表单验证 */
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
            supplyTypeName: {
                message: '验证失败',
                validators: {
                    notEmpty: {
                        message: '供应商分类姓名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '供应商分类长度必须在2到20位之间'
                    }
                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/supplyType/add", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/supplyType/edit", formId, "editWin");
            }

        })

}

