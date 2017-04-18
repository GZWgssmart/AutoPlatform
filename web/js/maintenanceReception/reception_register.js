/**
 * Created by Administrator on 2017-04-17.
 */


$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/checkin/checkin_pager");

    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);



});

/** 添加数据 */
function showAddWin() {
    $(".car_brand").select2({
        // enable tagging
        tags: true,
        language: 'zh-CN',
        placeholder: "请选择品牌",
        minimumResultsForSearch: -1,
        // loading remote data
        // see https://select2.github.io/options.html#ajax
        ajax: {
            url: "/carBrand/car_brand_all",
            processResults: function (data, page) {
                console.log(data);
                var parsed = data;
                var arr = [];
                for(var x in parsed){
                    arr.push(parsed[x]); //这个应该是个json对象
                }
                console.log(arr);
                return {
                    results: arr
                };
            }
        },

    });
    $(".car_color").select2({
        // enable tagging
        tags: true,
        language: 'zh-CN',
        placeholder: "请选择颜色",
        minimumResultsForSearch: -1,
        // loading remote data
        // see https://select2.github.io/options.html#ajax
        ajax: {
            url: "/carColor/car_color_all",
            processResults: function (data, page) {
                console.log(data);
                var parsed = data;
                var arr = [];
                for(var x in parsed){
                    arr.push(parsed[x]); //这个应该是个json对象
                }
                console.log(arr);
                return {
                    results: arr
                };
            }
        }
    });
    $(".car_model").select2({
        // enable tagging
        tags: true,
        language: 'zh-CN',
        placeholder: "请选择车型",
        minimumResultsForSearch: -1,
        // loading remote data
        // see https://select2.github.io/options.html#ajax
        ajax: {
            url: "/carModel/car_model_all",
            processResults: function (data, page) {
                console.log(data);
                var parsed = data;
                var arr = [];
                for(var x in parsed){
                    arr.push(parsed[x]); //这个应该是个json对象
                }
                console.log(arr);
                return {
                    results: arr
                };
            }
        }
    });
    $(".car_plate").select2({
        // enable tagging
        tags: true,
        language: 'zh-CN',
        minimumResultsForSearch: -1,
        placeholder: "请选择车牌",
        // loading remote data
        // see https://select2.github.io/options.html#ajax
        ajax: {
            url: "/carPlate/car_plate_all",
            processResults: function (data, page) {
                console.log(data);
                var parsed = data;
                var arr = [];
                for(var x in parsed){
                    arr.push(parsed[x]); //这个应该是个json对象
                }
                console.log(arr);
                return {
                    results: arr
                };
            }
        },

    });
    $('#addDatetimepicker').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        initialDate: new Date(),
        autoclose: true,
        todayHighlight: true,
        todayBtn: true//显示今日按钮
    });
    $("input[type=reset]").trigger("click");
    $("#addWin").modal('show');
}

/**提交添加数据 */
function addCheckin() {
    $.post("/checkin/add",
        $("#addForm").serialize(),
        function(data){
            if(data.result == "success"){
                $('#addWin').modal('hide');
                swal(data.message, "", "success");
                $('#cusTable').bootstrapTable('refresh');
            }else if(data.result == "fail"){
                swal(data.message, "", "error");
            }
        },"json");

}

/** 给datetimepicker添加默认值 */
function getDate(){
    $("#addDatetimepicker").val(new Date());
}

/** 判断是否选中 */
function checkAppointment(combox) {
    var val = combox.value;
    if (val == "Y") {
        //调用函数，初始化表格
        initTableNotTollbar("appTable", "/appointment/query_pager");

        //当点击查询按钮的时候执行
        $("#search").bind("click", initTable);
        $("#appWin").modal('show');
    } else {
        $("#appWin").modal('hide');
        $("input[type=reset]").trigger("click");
    }
}

/** 关闭预约 */
function closeAppWin() {
    $("#appWin").modal('hide');
    $("#app").val("N");

}

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var product = selectRow[0];
        $("#updateForm").fill(product);
        $("#editWin").modal('show');
    }
}

/**提交编辑数据 */
function updateCheckin() {
    $.post("/product/update",
        $("#updateForm").serialize(),
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

/**
 * 批量删除数据
 */
function deleteProduct() {
    var rows = $("#cusTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        swal('删除失败', "请选择一条或多条数据进行删除", "error");
    } else {
        var ids = "";
        for(var i = 0, len = rows.length; i < len; i++){
            if(ids == ""){
                ids = rows[i].id;
            }else{
                ids += ","+rows[i].id
            }
            if(ids != ""){
                swal({title: "确定要删除所选数据?",
                        text: "删除后将无法恢复，请谨慎操作！",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是的，我要删除!",
                        cancelButtonText: "让我在考虑一下....",
                        closeOnConfirm: false },
                    function(){
                        $.get("/product/deleteById/"+rows[0].ids,
                            function(data){
                                swal(data.message, "您已经永久删除了这条信息。", "success");
                                $('#cusTable').bootstrapTable('refresh');
                            },"json");

                    });
            }
        }

    }
}
