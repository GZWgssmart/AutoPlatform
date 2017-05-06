$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/peopleManage/workInfo_pager");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);

    initSelect2("work_user", "请选择员工", "/peopleManage/user_all", 565);
});



function operateFormatter(value, row, index) {
    if (row.workStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-danger  btn-sm">冻结</button>',
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-success  btn-sm">激活</button>',
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var workStatus = 'N';
        $.get("/peopleManage/workInfo_status?id=" + row.workId + "&status=" + workStatus,
            function (data) {
                if (data.result == "success") {

                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var workStatus = 'Y';
        $.get("/peopleManage/workInfo_status?id=" + row.workId + "&status=" + workStatus,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .showUpdateInfo': function (e, value, row, index) {
        var work = row;
        $("#editForm").fill(work);
        $('#editCarBrand').html('<option value="' + work.user.userId + '">' + work.user.userName + '</option>').trigger("change");
        validator("editForm");
        $("#editWin").modal('show');
    }
}




/**编辑数据 */
function showEditWin() {
    validator("editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length > 0) {
        var work = selectRow[0];
        $("#editForm").fill(work);
        $("#editWin").modal("show");
    } else {
        swal('指派失败', "只能选择一条数据进行指派", "error");
    }
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
    })
        .on('success.form.bv', function (e) {
            if (formId == "editForm") {
                formSubmit("/peopleManage/workInfo_update", formId, "editWin");
            }


        })

}