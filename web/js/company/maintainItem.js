/**
 * Created by root on 2017/4/24.
 */
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/maintainFix/queryByPager");
    initSelect2("company", "请选择汽修公司", "/company/company_all", "150");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});

/**提交添加数据 */
function addProduct() {
    $.post("/maintainFix/InsertMaintainItem",
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

