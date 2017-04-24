/**
 * Created by root on 2017/4/24.
 */
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/maintainFix/queryByPager");
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

function showAddWin() {
    $(".company").select2({
        // enable tagging
        tags: true,
        language: 'zh-CN',
        placeholder: "请选择公司",
        minimumResultsForSearch: -1,
        // loading remote data
        // see https://select2.github.io/options.html#ajax
        ajax: {
            url: "/company/company_all",
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
