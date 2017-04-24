/**
 * Created by root on 2017/4/18.
 */
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/carPlate/queryByPager");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});

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
function updateProduct() {
    $.post("/carPlate/uploadCarPlate",
        $("#updateForm").serialize(),
        function (data) {
            if (data.result == "success") {
                $('#editWin').modal('hide');
                swal(data.message, "", "success");
                $('#cusTable').bootstrapTable('refresh');
            } else if (data.result == "fail") {
                swal(data.message, "", "error");
            }
        }, "json");
}

/**提交添加数据 */
function addProduct() {
    $.post("/carPlate/insertCarPlate",
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
    if (row.plateStatus == 'Y') {
        return [
            '可用'
        ].join('');
    } else {
        return [
            '不可用'
        ].join('');
    }
}

function operating(value, row, index) {
    if (row.plateStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm btn-danger" >冻结</button>',
            '<button type="button" onclick="showEditWin()" class="btn btn-default btn-sm btn-primary ">编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm btn-success" >激活</button>',
            '<button type="button" onclick="showEditWin()" class="btn btn-default btn-sm btn-primary ">编辑</button>'
        ].join('');
    }
}



window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var Status = 'N';
        $.get("/carPlate/plateStatusModify?id=" + row.plateId + "&status=" + Status,
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
        $.get("/carPlate/plateStatusModify?id=" + row.plateId + "&status=" + Status,
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