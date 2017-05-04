
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

function showAddWin(){
    validator("addForm");
    $("#addWin").modal('show');
    $("input[type=reset]").trigger("click");
}

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/carModel/queryByPager");
    initSelect2("car_brand", "请选择汽车品牌", "/carBrand/car_brand_all", "550");
    destoryValidator("addWin","addForm");
    destoryValidator("editWin","editForm");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});

function modelAll(){
    initTable("cusTable","/carModel/queryByPager");
}

/**查询可用*/
function statusUsableness(){
    var status = 'Y';
    initTable("cusTable","/carModel/queryByModelPager?status="+status);
}

/**查询不可用*/
function statusAvailable(){
    var status = 'N';
    initTable("cusTable","/carModel/queryByModelPager?status="+status);
}

function operating(value, row, index) {
    if (row.modelStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm btn-danger" >冻结</button>&nbsp;&nbsp;',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default  btn-sm btn-primary" >编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm btn-success" >激活</button>&nbsp;&nbsp;',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default  btn-sm btn-primary" >编辑</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var modelStatus = 'N';
        $.get("/carModel/modelStatusModify?id=" + row.modelId + "&status=" + row.modelStatus,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var modelStatus = 'Y';
        $.get("/carModel/modelStatusModify?id=" + row.modelId + "&status=" + row.modelStatus,
            function (data) {
                if (data.result == "success") {
                    // $('#addWin').modal('hide');
                    // swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
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
            brandId: {
                message: '品牌失败',
                validators: {
                    notEmpty: {
                        message: '汽车品牌不能为空'
                    }
                }
            },
            modelName: {
                message: '车型名称失败',
                validators: {
                    notEmpty: {
                        message: '车型名称不能为空'
                    }
                }
            },
            modelDes: {
                message: '车型描述失败',
                validators: {
                    notEmpty: {
                        message: '车型描述不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '车型描述用字符长度必须在1~500字之间'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/carModel/insertCarModel", formId, "addWin");
            } else if (formId == "editForm") {
                formSubmit("/carModel/uploadCarModel", formId, "editWin");

            }
        })

}

