/**
 * Created by Administrator on 2017-04-17.
 */


$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/checkin/checkin_pager");

    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);

    initSelect2("car_brand", "请选择品牌", "/carBrand/car_brand_all");
    initSelect2("car_color", "请选择颜色", "/carColor/car_color_all");
    initSelect2("car_model", "请选择车型", "/carModel/car_model_all");
    initSelect2("car_plate", "请选择车牌", "/carPlate/car_plate_all");
    initDateTimePicker("datetimepicker");

});

/** 添加数据 */
function showAddWin() {
    $("input[type=reset]").trigger("click");
    $("#addWin").modal('show');
}

/**提交添加数据 */
function addCheckin() {
    var appFlag = $("#app").val();
    $.ajax({
        cache: true,
        type: "POST",
        url:"/checkin/add",
        data:$('#addForm').serialize(),// 你的formid
        async: false,
        error: function(request) {
            swal("请求错误", "", "error");
        },
        success: function(data) {
            if(data.result == "success"){
                $('#addWin').modal('hide');
                swal(data.message, "", "success");
                $('#cusTable').bootstrapTable('refresh');
            }else if(data.result == "fail"){
                swal(data.message, "", "error");
            }
        }
    });

}

/** 给datetimepicker添加默认值 */
function getDate(){
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

/**提交编辑数据 */
function editCheckin() {
    $.post("/checkin/edit",
        $("#editForm").serialize(),
        function(data){
            if(data.result == "success"){
                $('#editWin').modal('hide');
                swal(data.message, "", "success");
                $('#cusTable').bootstrapTable('refresh');
            }else if(data.result == "fail"){
                swal(data.message, "", "error");
            }
        },"json");

}
/** 返回按钮 */
function operateFormatter(value, row, index) {
    if (row.checkinStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    }else{
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
            function(data){
                if(data.result == "success"){
                    $('#cusTable').bootstrapTable('refresh');
                }else if(data.result == "fail"){
                    swal(data.message, "", "error");
                }
            },"json");
    },
    'click .updateInactive': function (e, value, row, index) {
        $.get("/checkin/update_status?checkinId=" + row.checkinId + "&status=" + row.checkinStatus,
            function(data){
                if(data.result == "success"){
                    $('#cusTable').bootstrapTable('refresh');
                }else if(data.result == "fail"){
                    swal(data.message, "", "error");
                }
            },"json");
    },
    'click .showUpdateIncomingType1': function (e, value, row, index) {
        var checkin = row;
        $("#editForm").fill(checkin);
        $('#editCarBrand').html('<option value="' + checkin.brand.brandId + '">' + checkin.brand.brandName + '</option>').trigger("change");
        $('#editCarColor').html('<option value="' + checkin.color.colorId + '">' + checkin.color.colorName + '</option>').trigger("change");
        $('#editCarModel').html('<option value="' + checkin.model.modelId + '">' + checkin.model.modelName + '</option>').trigger("change");
        $('#editCarPlate').html('<option value="' + checkin.plate.plateId + '">' + checkin.plate.plateName + '</option>').trigger("change");
        $('#editDatetimepicker').val(formatterDate(checkin.arriveTime));
        $("#editWin").modal('show');
    }
}