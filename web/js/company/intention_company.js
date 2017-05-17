/**
 * Created by Administrator on 2017-05-17.
 */
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/intention/intention_pager?status=ALL");
});

/** 返回按钮 */
function operateFormatter(value, row, index) {
    if (row.intentionStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-danger btn-sm" style="margin-right:15px;" >冻结</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-success btn-sm" style="margin-right:15px;" >激活</button>'
        ].join('');
    }
}

/** 更改状态 */
window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        $.get("/intention/update_status?id=" + row.intentionId + "&status=" + row.intentionStatus,
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
    'click .updateInactive': function (e, value, row, index) {
        $.get("/intention/update_status?id=" + row.intentionId + "&status=" + row.intentionStatus,
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
    }
}

/** 根据条件搜索 */
function searchCheckin() {
    var name = $("#searchName").val();
    var phone = $("#searchPhone").val();
    var email = $("#searchEmail").val();
    initTable("cusTable", "/intention/condition_pager?name=" + name + "&phone=" + phone + "&email=" + email);

}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchName").val('');
    $("#searchPhone").val('');
    $("#searchEmail").val('');
    $("#searchDiv").hide();
    $("#showButton").show();
}
