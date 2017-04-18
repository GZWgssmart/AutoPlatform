$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/role/query_pager");

    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);

});

function thisStatus(value, row, index) {
    if (value == 'Y') {
        return "可用";
    } else {
        return "不可用";
    }
}

function operateFormatter(value, row, index) {
    if (row.moduleStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm" style="margin-right:15px;" >冻结</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm" style="margin-right:15px;" >激活</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var roleStatus = 'N';
        $.get("/role/update_status?id=" + row.roleId + "&status=" + roleStatus,
            function (data) {
                if (data.result == "success") {

                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var roleStatus = 'Y';
        $.get("/role/update_status?id=" + row.roleId + "&status=" + roleStatus,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    // swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    }
}

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length == 0) {
        swal('编辑失败', "必须选择一条数据进行编辑", "error");
        return false;
    } else if (selectRow.length > 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
    } else {
        var product = selectRow[0];
        $("#updateForm").fill(product);
        $("#editWin").modal('show');
    }
}

/**提交编辑数据 */
function updateRole() {
    $.post("/role/update_role",
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
function addRole() {
    $.post("/role/add_role",
        $("#addForm").serialize(),
        function(data){
            if(data.result == "success"){
                $('#addWin').modal('hide');
                swal(data.message, "", "success");
                $('#cusTable').bootstrapTable('refresh');
                $("input[type=reset]").trigger("click");
            }else if(data.result == "fail"){
                swal(data.message, "", "error");
            }
        },"json");

}
