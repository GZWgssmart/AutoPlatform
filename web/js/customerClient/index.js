var userInfo;

var editPhone;
$(document).ready(function () {
    //调用函数，初始化表格


    initSelect2("car_brand", "请选择品牌", "/carBrand/car_brand_all", "565");
    initSelect2("car_color", "请选择颜色", "/carColor/car_color_all", "565");
    initSelect2("car_plate", "请选择车牌", "/carPlate/car_plate_all", "565");
    initSelect2("company", "请选择汽修公司", "/company/company_all", "150");

    destoryValidator("addWin","addForm");



});

/** 监听是否从系统中选择监听事件 */
function isUserChoice() {
    if ($('#choiceUser').bootstrapSwitch('state')) {
        if (userInfo != null && userInfo != "" && userInfo != undefined) {
            setData(userInfo, "userInfo");
        }
    } else {
        if (userInfo != null && userInfo != "" && userInfo != undefined) {
            clearAddForm();
        }
    }
}



/** 添加选择品牌 */
function checkBrand(combo) {
    $('#addCarModel').html('').trigger("change");
    var brandId = combo.value;
    if (brandId != null && brandId != undefined && brandId != "") {
        $("#carModelDiv").show();
        initSelect2("car_model", "请选择车型", "/carModel/car_model_all?brandId=" + brandId, "565");
    } else {
        $("#carModelDiv").hide();
    }
}

/** 修改窗口选择车型 */
function editCheckBrand(combo) {
    $('#editCarModel').html('').trigger("change");
    var brandId = combo.value;
    if (brandId != null && brandId != undefined && brandId != "") {

        initSelect2("car_model", "请选择车型", "/carModel/car_model_all?brandId=" + brandId, "565");
    } else {

    }
}


/** 清除添加的form表单信息 */
function clearAddForm() {
    $("#userDiv").show();
    $('#addCarBrand').html('').trigger("change");
    $('#addCarColor').html('').trigger("change");
    $('#addCarModel').html('').trigger("change");
    $('#addCarPlate').html('').trigger("change");
    $("input[type=reset]").trigger("click");
}



/** 显示添加数据的窗口 */
function showAddWin(companyId) {
    userInfo = "";
    validator("addForm");

    initDateTimePicker("datetimepicker", "arriveTime","addForm");
    $("#addWin").modal('show');
    $("#companyId").val(companyId);
}

/** 关闭选择 */
function closeAppWin() {

    $("#appWin").modal('hide');
}

/** 给添加的form表单设置值 */
function setData(customer) {
    $("#appDiv").hide();
    $("#addUserName").val(customer.userName);
    $("#addUserPhone").val(customer.userPhone);
    $("#addUserId").val(customer.userId);

}

/** 表单验证 */
function validator(formId) {
    $("#addButton").removeAttr("disabled");
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
                    },
                    threshold: 11,
                    remote: {
                        url: '/peopleManage/peoplePhone_verification?editPhone='+editPhone,
                        message: '该手机号已存在',
                        delay :  2000,
                        type: 'GET'
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
                formSubmit("/appointment/addCustomer", formId, "addWin");
                clearAddForm();
            }
        })
}


/** 子窗口调用父窗口的js方法 */
function showMaintain() {
    parent.showMaintainPage();
}

