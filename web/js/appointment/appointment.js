var customer;
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/appointment/query_pager?status=ALL");

    initSelect2("car_brand", "请选择品牌", "/carBrand/car_brand_all", "565");
    initSelect2("car_color", "请选择颜色", "/carColor/car_color_all", "565");
    initSelect2("car_plate", "请选择车牌", "/carPlate/car_plate_all", "565");
    initSelect2("company", "请选择汽修公司", "/company/company_all", "150");

    destoryValidator("addWin","addForm");
    destoryValidator("editWin","editForm");

    $("#isApp").bootstrapSwitch({
        onText: '是',
        offText: '否',
        onColor: 'success',
        offColor: 'danger',
        size: 'normal',

        onSwitchChange: function (event, state) {
            if (state == true) {
                //调用函数，初始化表格
                initTableNotTollbar("appTable", "/customer/customerInfo_pager");
                $("#appWin").modal('show');
            } else if (state == false) {
            }
        }
    });

    $("#appWin").on("hide.bs.modal", function () {

        $('#isApp').bootstrapSwitch('state', false);

    });

});

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

/** 监听switch的监听事件 */
function isAppChoice() {
    if ($('#isApp').bootstrapSwitch('state')) {
        if (customer != null && customer != "" && customer != undefined) {
            setData(customer,"customer");
        }
    } else {
        if (customer != null && customer != "" && customer != undefined) {
            clearAddForm();
        }
    }
}

/** 选择车主*/
function checkApp() {
    var selectRow = $("#appTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('选择失败', "只能选择一条数据", "error");
        return false;
    } else {
        customer = selectRow[0];
        setData(customer,"customer");
        $("#appWin").on("hide.bs.modal", function () {
            $('#isApp').bootstrapSwitch('state', true);
        });
        $("#appWin").modal('hide');
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

/** 显示添加数据的窗口 */
function showAddWin() {
    customer = "";
    validator("addForm");
    initDateTimePicker("datetimepicker", "arriveTime","addForm");
    $("#addWin").modal('show');
}

/** 关闭选择 */
function closeAppWin() {
    $("#appWin").modal('hide');
    $("#addWin").modal('show')
}

/** 给添加的form表单设置值 */
function setData(customer) {
    $("#appDiv").hide();
    $("#addUserName").val(customer.userName);
    $("#addUserPhone").val(customer.userPhone);
    $("#addUserId").val(customer.userId);

}

/** 清除添加的form表单信息 */
function clearAddForm() {
    $("#appDiv").show();
    $('#addUserName').html('').trigger("change");
    $('#addUserPhone').html('').trigger("change");
    $('#addUserId').html('').trigger("change");
    $("input[type=reset]").trigger("click");
}

/** 编辑数据 */
function showEditWin() {
    validator("editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');

    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var appointment = selectRow[0];
        $("#editForm").fill(appointment);
        $('#editCarBrand').html('<option value="' + appointment.brand.brandId + '">' + appointment.brand.brandName + '</option>').trigger("change");
        $('#editCarColor').html('<option value="' + appointment.color.colorId + '">' + appointment.color.colorName + '</option>').trigger("change");
        $('#editCarModel').html('<option value="' + appointment.model.modelId + '">' + appointment.model.modelName + '</option>').trigger("change");
        $('#editCarPlate').html('<option value="' + appointment.plate.plateId + '">' + appointment.plate.plateName + '</option>').trigger("change");
        $('#editDatetimepicker').val(formatterDate(appointment.arriveTime));
        $("#editWin").modal('show');
    }
}

/** 返回按钮 */
function operateFormatter(value, row, index) {
    if (row.appoitmentStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-danger btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-primary btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-success btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-primary btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }

}

/** 更改状态 */
window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        $.get("/appointment/update_status?appointmentId=" + row.appointmentId + "&status=" + row.appoitmentStatus,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }else if (data.result == "notLogin") {
                    swal({
                            title: "登入失败",
                            text: data.message,
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "确认",
                            cancelButtonText: "取消",
                            closeOnConfirm: true,
                            closeOnCancel: true
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                top.location.href = "/login/show_login";
                            } else {
                            }
                        });
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        $.get("/appointment/update_status?appointmentId=" + row.appointmentId + "&status=" + row.appoitmentStatus,
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
    'click .showUpdateIncomingType1': function (e, value, row, index) {
        var appointment = row;
        $("#editForm").fill(appointment);
        $('#editCarBrand').html('<option value="' + appointment.brand.brandId + '">' + appointment.brand.brandName + '</option>').trigger("change");
        $('#editCarColor').html('<option value="' + appointment.color.colorId + '">' + appointment.color.colorName + '</option>').trigger("change");
        $('#editCarModel').html('<option value="' + appointment.model.modelId + '">' + appointment.model.modelName + '</option>').trigger("change");
        $('#editCarPlate').html('<option value="' + appointment.plate.plateId + '">' + appointment.plate.plateName + '</option>').trigger("change");
        $('#editDatetimepicker').val(formatterDate(appointment.arriveTime));
        validator("editForm");
        $("#editWin").modal('show');
    }
}

/**  条件查询*/
function searchCheckin() {
    var userName = $("#searchUserName").val();
    var userPhone = $("#searchUserPhone").val();
    var carPlate = $("#searchCarPlate").val();
    var maintainOrFix = $("#searchMaintainOrFix").val();
    var companyId = $("#searchCompanyId").val();
    initTable("cusTable", "/appointment/appointment_pager?userName=" + userName + "&userPhone=" + userPhone + "&carPlate=" + carPlate + "&maintainOrFix=" + maintainOrFix + "&companyId=" + companyId);
}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchUserName").val('');
    $("#searchUserPhone").val('');
    $("#searchCarPlate").val('');
    $("#searchMaintainOrFix").val('all');
    $('#searchCompanyId').html('').trigger("change");
    $("#searchDiv").hide();
    $("#showButton").show();
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
                $.post("/appointment/add",
                    $("#" + formId).serialize(),
                    function (data) {
                        if (data.result == "success") {
                            $('#addWin').modal('hide');
                            $('#cusTable').bootstrapTable('refresh');
                        } else if (data.result == "fail") {
                            swal("错误提示", data.message, "error");
                        } else if (data.result == "notLogin") {
                            swal({
                                    title: "登入失败",
                                    text: data.message,
                                    type: "warning",
                                    showCancelButton: true,
                                    confirmButtonColor: "#DD6B55",
                                    confirmButtonText: "确认",
                                    cancelButtonText: "取消",
                                    closeOnConfirm: true,
                                    closeOnCancel: true

                                },
                                function (isConfirm) {
                                    if (isConfirm) {
                                        top.location.href = "/login/show_login";
                                    } else {
                                    }
                                });
                        }
                    }, "json");
                clearAddForm();
            } else if (formId == "editForm") {
                formSubmit("/appointment/edit", formId, "editWin");
            }
        })
}


/** 子窗口调用父窗口的js方法 */
function showMaintain() {
    parent.showMaintainPage();
}

