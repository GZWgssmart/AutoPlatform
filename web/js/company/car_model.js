/**
 * Created by root on 2017/4/18.
 */
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
        }

    });
    $("input[type=reset]").trigger("click");
    $("#addWin").modal('show');
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

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/carModel/queryByPager");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});

/**提交编辑数据 */
function updateProduct() {
    $.post("/carModel/uploadCarModel",
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

/**提交添加数据 */
function addProduct() {
    $.post("/carModel/insertCarModel",
        $("#addForm").serialize(),
        function (data) {
            if (data.result == "success") {
                $('#addWin').modal('hide');
                swal(data.message, "", "success");
                $('#cusTable').bootstrapTable('refresh');
            } else if (data.result == "fail") {
                swal(data.message, "", "error");
            }
        }, "json");
}

function operateFormatter(value, row, index) {
    if (row.modelStatus == 'Y') {
        return [
            '可用'
        ].join('');
    }else if(row.modelStatus == 'N'){
        return [
            '不可用'
        ].join('');
    }
}

function operating(value, row, index) {
    if (row.modelStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm" >冻结</button>',
            '<button onclick="showEditWin();" type="button" class="btn btn-default  btn-sm" >编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm" >激活</button>',
            '<button onclick="showEditWin();" type="button" class="btn btn-default  btn-sm" >编辑</button>'
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
    }
}
