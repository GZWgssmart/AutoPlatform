/**
 * Created by Administrator on 2017-04-17.
 */


$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/checkin/checkin_pager");

    initSelect2("car_brand", "请选择品牌", "/carBrand/car_brand_all", "540");
    initSelect2("car_color", "请选择颜色", "/carColor/car_color_all", "540");
    initSelect2("car_model", "请选择车型", "/carModel/car_model_all", "540");
    initSelect2("car_plate", "请选择车牌", "/carPlate/car_plate_all", "540");
    initDateTimePicker("datetimepicker", "arriveTime");


});

/** 是否需要洗车 */
function carWash(value, row, index) {
    if (value == "Y") {
        return "是";
    } else {
        return "否";
    }
}

/** 添加数据 */
function showAddWin() {
    validator("addForm");
    $('#addCarBrand').html('').trigger("change");
    $('#addCarColor').html('').trigger("change");
    $('#addCarModel').html('').trigger("change");
    $('#addCarPlate').html('').trigger("change");
    $("input[type=reset]").trigger("click");
    $("#addWin").modal('show');
}

/** 给datetimepicker添加默认值 */
function getDate() {
    if ($("#app").val() == "N") {
        $("#addDatetimepicker").val(new Date());
    }
}

/** 判断是否选中 */
function checkAppointment(combox) {
    var val = combox.value;
    if (val == "Y") {
        //调用函数，初始化表格
        initTableNotTollbar("appTable", "/appointment/query_pager");

        //当点击查询按钮的时候执行
        $("#search").bind("click", initTable);
        $("#addWin").modal('hide');
        $("#appWin").modal('show');
    } else {
        $("#appWin").modal('hide');
        $("#addWin").modal('show');
        $("input[type=reset]").trigger("click");
    }
}

/** 关闭预约 */
function closeAppWin() {
    $("#appWin").modal('hide');
    $("#addWin").modal('show')
    $("#app").val("N");
}

/** 选择预约记录 */
function checkApp() {
    var selectRow = $("#appTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('选择失败', "只能选择一条数据", "error");
        return false;
    } else {
        $("#appWin").modal('hide');
        var appointment = selectRow[0];
        $("#addUserName").val(appointment.userName);
        $("#addUserPhone").val(appointment.userPhone);
        $("#addDatetimepicker").val(formatterDate(appointment.arriveTime));
        $('#addCarBrand').html('<option value="' + appointment.brand.brandId + '">' + appointment.brand.brandName + '</option>').trigger("change");
        $('#addCarColor').html('<option value="' + appointment.color.colorId + '">' + appointment.color.colorName + '</option>').trigger("change");
        $('#addCarModel').html('<option value="' + appointment.model.modelId + '">' + appointment.model.modelName + '</option>').trigger("change");
        $('#addCarPlate').html('<option value="' + appointment.plate.plateId + '">' + appointment.plate.plateName + '</option>').trigger("change");
        $("#addMaintainOrFix").val(appointment.maintainOrFix);
        $("#addWin").modal('show');
    }
}

/** 编辑数据 */
function showEditWin() {
    validator("editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var checkin = selectRow[0];
        $("#editForm").fill(checkin);
        $('#editCarBrand').html('<option value="' + checkin.brand.brandId + '">' + checkin.brand.brandName + '</option>').trigger("change");
        $('#editCarColor').html('<option value="' + checkin.color.colorId + '">' + checkin.color.colorName + '</option>').trigger("change");
        $('#editCarModel').html('<option value="' + checkin.model.modelId + '">' + checkin.model.modelName + '</option>').trigger("change");
        $('#editCarPlate').html('<option value="' + checkin.plate.plateId + '">' + checkin.plate.plateName + '</option>').trigger("change");
        $('#editDatetimepicker').val(formatterDate(checkin.arriveTime));
        $("#editWin").modal('show');
    }
}

/** 返回按钮 */
function operateFormatter(value, row, index) {
    if (row.checkinStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default  btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }

}
/** 更改状态 */
window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        $.get("/checkin/update_status?checkinId=" + row.checkinId + "&status=" + row.checkinStatus,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        $.get("/checkin/update_status?checkinId=" + row.checkinId + "&status=" + row.checkinStatus,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .showUpdateIncomingType1': function (e, value, row, index) {
        var checkin = row;
        $("#editForm").fill(checkin);
        $('#editCarBrand').html('<option value="' + checkin.brand.brandId + '">' + checkin.brand.brandName + '</option>').trigger("change");
        $('#editCarColor').html('<option value="' + checkin.color.colorId + '">' + checkin.color.colorName + '</option>').trigger("change");
        $('#editCarModel').html('<option value="' + checkin.model.modelId + '">' + checkin.model.modelName + '</option>').trigger("change");
        $('#editCarPlate').html('<option value="' + checkin.plate.plateId + '">' + checkin.plate.plateName + '</option>').trigger("change");
        $('#editDatetimepicker').val(formatterDate(checkin.arriveTime));
        validator("editForm");
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
            userName: {
                message: '车主验证失败',
                validators: {
                    notEmpty: {
                        message: '车主姓名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 4,
                        message: '车主长度必须在2到4位之间'
                    }
                }
            },
            userPhone: {
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '手机号只能是11位'
                    }
                }
            },
            brandId: {
                validators: {
                    notEmpty: {
                        message: '品牌不能为空'
                    }

                }
            },
            colorId: {
                validators: {
                    notEmpty: {
                        message: '颜色不能为空'
                    }

                }
            },
            modelId: {
                validators: {
                    notEmpty: {
                        message: '车型不能为空'
                    }

                }
            },
            plateId: {
                validators: {
                    notEmpty: {
                        message: '车牌不能为空'
                    }

                }
            },
            carPlate: {
                validators: {
                    notEmpty: {
                        message: '车牌号码不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 5,
                        message: '车牌号码只能是5位'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '车牌不能是中文'
                    }
                }
            },
            oilCount: {
                validators: {
                    notEmpty: {
                        message: '汽车油量不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '油量只能是数字'
                    }

                }
            },
            carMileage: {
                validators: {
                    notEmpty: {
                        message: '汽车行驶里程不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '行驶里程只能是数字'
                    }

                }
            },
            arriveTime: {
                validators: {
                    notEmpty: {
                        message: '到店时间不能为空'
                    }

                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/checkin/add", formId, "addWin");


            } else if (formId == "editForm") {
                formSubmit("/checkin/edit", formId, "editWin");
            }


        })

}




