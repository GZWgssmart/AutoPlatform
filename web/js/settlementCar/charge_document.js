$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/bill/pager?status=ALL");

    destoryValidator("addWin", "addForm");
    destoryValidator("editWin", "editForm");

});

/** 格式化操作栏的按钮 */
function operateFormatter(value, row, index) {
    if (row.chargeBillStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-danger btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showEditWin btn btn-primary btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-success btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showEditWin btn btn-primary btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }

}
/** 点击事件监听 */
window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        $.get("/bill/update_status?id=" + row.chargeBillId + "&status=" + row.chargeBillStatus,
            function(data){
                if(data.result == "success"){
                    $('#cusTable').bootstrapTable('refresh');
                }else if(data.result == "fail"){
                    swal(data.message, "", "error");
                }
            },"json");
    },
    'click .updateInactive': function (e, value, row, index) {
        $.get("/bill/update_status?id=" + row.chargeBillId + "&status=" + row.chargeBillStatus,
            function(data){
                if(data.result == "success"){
                    $('#cusTable').bootstrapTable('refresh');
                }else if(data.result == "fail"){
                    swal(data.message, "", "error");
                }
            },"json");
    },
    'click .showEditWin': function (e, value, row, index) {

        var chargeBill = row;
        $("#editForm").fill(chargeBill);
        $("#editWin").modal('show');
    }
}

/** 根据条件搜索 */
function searchCondition() {
    var userName = $("#searchUserName").val();
    var userPhone = $("#searchUserPhone").val();
    var paymentMethod = $("#searchPaymentMethod").val();
    initTable("cusTable", "/bill/condition_pager?userName=" + userName + "&userPhone=" + userPhone + "&paymentMethod=" + paymentMethod);

}