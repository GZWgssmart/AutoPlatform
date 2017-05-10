/**
 * Created by root on 2017/4/18.
 */

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/carBrand/queryByPager");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchBrandName").val('');
    $("#searchDiv").hide();
    $("#showButton").show();
}

function searchBrand(){
    var brandName = $("#searchBrandName").val();
    initTable("cusTable","/carBrand/search?brandName="+brandName);
}
function showAddWin(){
    validator("addForm");
    $("#addWin").modal('show');
}
function brandAll(){
    initTable("cusTable","/carBrand/queryByPager");
}

/**查询可用*/
function statusUsableness(){
    var status = 'Y';
    initTable("cusTable","/carBrand/queryByStatusPager?status="+status);
}

/**查询不可用*/
function statusAvailable(){
    var status = 'N';
    initTable("cusTable","/carBrand/queryByStatusPager?status="+status);
}

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var product = selectRow[0];
        validator("editForm");
        $("#editForm").fill(product);
        $("#editWin").modal('show');
    }
}

function operating(value, row, index) {
    if (row.brandStatus == 'Y') {
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

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var Status = 'N';
        $.get("/carBrand/brandStatusModify?id=" + row.brandId + "&status=" + Status,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var Status = 'Y';
        $.get("/carBrand/brandStatusModify?id=" + row.brandId + "&status=" + Status,
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
            brandName: {
                message: '品牌名称失败',
                validators: {
                    notEmpty: {
                        message: '品牌名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '品牌名称长度必须在2到20位之间'
                    }
                }
            },
            brandDes: {
                message: '品牌描述失败',
                validators: {
                    notEmpty: {
                        message: '品牌描述不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: '品牌描述长度必须在1到500位之间'
                    }
                }
            }

        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/carBrand/insertCarBrand", formId, "addWin");
            } else if (formId == "editForm") {
                formSubmit("/carBrand/uploadCarBrand", formId, "editWin");
            }
        })

}