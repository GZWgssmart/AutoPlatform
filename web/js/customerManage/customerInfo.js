$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/customerManage/customerInfo_pager");

    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});


function random() {
    var arr = ['0','1','2','3','4','5','6','7','8','9'];
    var str = '';
    for(var i = 0 ; i < 4 ; i ++ ){
        str += ''+arr[Math.floor(Math.random() * arr.length)];
    }
    return str;
}

function gender(value, row, index) {
    if (row.userGender == 'M') {
        return '男'
    }else if (row.userGender == 'F'){
        return '女'
    }else if (row.userGender == 'N'){
        return '未知'
    }
}

function operating(value, row, index) {
    if (row.userStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm" >冻结</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm" >激活</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var userStatus = 'N';
        $.get("/customerManage/customerInfo_status?id=" + row.userId + "&status=" + userStatus,
            function (data) {
                if (data.result == "success") {

                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var userStatus = 'Y';
        $.get("/customerManage/customerInfo_status?id=" + row.userId + "&status=" + userStatus,
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

// function queryByStatusPager(status) {
//     $('#cusTable').bootstrapTable('refresh', {url: '/peopleManage/peopleInfo_status?status=' + status});
// }

$(document).ready(function () {
    //调用函数，初始化表格
    initTable();

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
    $.post("/customerManage/customerInfo_update",
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
    $.post("/customerManage/customerInfo_insert",
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