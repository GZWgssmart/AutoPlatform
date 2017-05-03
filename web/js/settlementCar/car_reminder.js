/**
 * Created by Administrator on 2017-04-30.
 */
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/record/pager_speedStatus");

    initSelect2("company", "请选择汽修公司", "/company/company_all", "150");
});

/** 显示是否回访 */
function formatterTrack(value, row, index) {
    if (value == "Y") {
        return "是";
    } else {
        return "<span style='color: red'>否</span>";
    }
}

/** 根据条件搜索 */
function searchCondition() {
    var userName = $("#searchUserName").val();
    var carPlate = $("#searchCarPlate").val();
    var maintainOrFix = $("#searchMaintainOrFix").val();
    var companyId = $("#searchCompanyId").val();
    var speedStatus = "待结算";
    initTable("cusTable", "/record/condition_pager?userName=" + userName + "&carPlate=" + carPlate + "&maintainOrFix=" + maintainOrFix + "&companyId=" + companyId + "&speedStatus=" + speedStatus);

}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchUserName").val('');
    $("#searchCarPlate").val('');
    $("#searchMaintainOrFix").val('all');
    $('#searchCompanyId').html('').trigger("change");
    $("#searchDiv").hide();
    $("#showButton").show();
}