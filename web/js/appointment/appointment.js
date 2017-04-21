$(document).ready(function () {
    initTable("cusTable", "/appointment/query_pager");
    initSelect2("car_brand", "请选择品牌", "/carBrand/car_brand_all", "540");
    initSelect2("car_color", "请选择颜色", "/carColor/car_color_all", "540");
    initSelect2("car_model", "请选择车型", "/carModel/car_model_all", "540");
    initSelect2("car_plate", "请选择车牌", "/carPlate/car_plate_all", "540");
    initDateTimePicker("datetimepicker", "arriveTime");
});

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
/** 编辑数据 */
function showEditWin() {
    function showEditWin() {
        validator("editForm");
        var selectRow = $("#cusTable").bootstrapTable('getSelections');
        if (selectRow.length != 1) {
            swal('编辑失败', "只能选择一条数据进行编辑", "error");
            return false;
        } else {
            var checkin = selectRow[0];
            $("#editForm").fill(checkin);
            $('#editCarBrand').html('<option value="' + appointment.brand.brandId + '">' + appointment.brand.brandName + '</option>').trigger("change");
            $('#editCarColor').html('<option value="' + appointment.color.colorId + '">' + appointment.color.colorName + '</option>').trigger("change");
            $('#editCarModel').html('<option value="' + appointment.model.modelId + '">' + appointment.model.modelName + '</option>').trigger("change");
            $('#editCarPlate').html('<option value="' + appointment.plate.plateId + '">' + appointment.plate.plateName + '</option>').trigger("change");
            $('#editDatetimepicker').val(formatterDate(appointment.arriveTime));
            $("#editWin").modal('show');
        }
    }
}
/** 给datetimepicker添加默认值 */
function getDate(){
    $("#addDatetimepicker").val(new Date());
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
                formSubmit("/appointment/add", formId, "addWin");
            } else if (formId == "editForm") {
                formSubmit("/appointment/Update", formId, "editWin");
            }
        })
}
