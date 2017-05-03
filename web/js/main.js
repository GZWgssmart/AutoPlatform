/** 初始化Select2 */
function initSelect2(clazz, title, url, width) {
    $("." + clazz).select2({
        // enable tagging
        tags: true,
        width: width + 'px',
        language: 'zh-CN',
        minimumResultsForSearch: -1,
        placeholder: title,
        // loading remote data
        // see https://select2.github.io/options.html#ajax
        ajax: {
            url: url,
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
        },

    });
}

/** 初始化DatatimePicker */
function initDateTimePicker(clazz, name, formId) {
    $('.' + clazz).datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        initialDate: new Date(),
        autoclose: true,
        todayHighlight: true,
        todayBtn: true//显示今日按钮
    }).on('hide',function(e) {
        $('#' + formId).data('bootstrapValidator')
            .updateStatus(name, 'NOT_VALIDATED',null)
            .validateField(name);
    });;
}

/** 初始化DatatimePicker，不需要验证的 */
function initDateTimePickerNotValitor(clazz) {
    $('.' + clazz).datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        initialDate: new Date(),
        autoclose: true,
        todayHighlight: true,
        todayBtn: true//显示今日按钮
    });
}

/** 初始化表格，默认id为toolbar的为工具栏 */
function initTable(tableId, url) {
    //先销毁表格
    $('#' + tableId).bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#" + tableId).bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: url, //获取数据的Servlet地址
        striped: false,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber:1, //当前第几页
        pageList: [10, 15, 20, 25, 30],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: true,  //显示刷新按钮
        strictSearch: true,
        clickToSelect: true,  //是否启用点击选中行
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar : "#toolbar",// 指定工具栏
        sidePagination: "server", //表示服务端请求

        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType : "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                orderNum : $("#orderNum").val()
            };
            return param;
        },
    });
}

/** 初始化没有工具栏的表格 */
function initTableNotTollbar(tableId, url) {
    //先销毁表格
    $('#' + tableId).bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#" + tableId).bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: url, //获取数据的Servlet地址
        striped: false,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber:1, //当前第几页
        pageList: [10, 15, 20, 25, 30],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: true,  //显示刷新按钮
        strictSearch: true,
        clickToSelect: true,  //是否启用点击选中行
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server", //表示服务端请求

        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType : "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                orderNum : $("#orderNum").val()
            };
            return param;
        },
    });
}

/** 初始化指定工具栏的表格 */
function initTableSetToolbar(tableId, url, toolbarId) {
    //先销毁表格
    $('#' + tableId).bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#" + tableId).bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: url, //获取数据的Servlet地址
        striped: false,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber:1, //当前第几页
        pageList: [10, 15, 20, 25, 30],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: true,  //显示刷新按钮
        strictSearch: true,
        clickToSelect: true,  //是否启用点击选中行
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar : "#" + toolbarId,// 指定工具栏
        sidePagination: "server", //表示服务端请求

        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType : "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                orderNum : $("#orderNum").val()
            };
            return param;
        },
    });
}

/**
 * 时间格式化，传递进来的时间,返回yyyy-MM-dd HH:mm
 * @param value
 * @returns
 */
function formatterDate(value) {
    if (value == undefined || value == null || value == '') {
        return "";
    }
    else {
        var date = new Date(value);
        var year = date.getFullYear().toString();
        var month = (date.getMonth() + 1);
        var day = date.getDate().toString();
        var hour = date.getHours().toString();
        var minutes = date.getMinutes().toString();
        var seconds = date.getSeconds().toString();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        if (hour < 10) {
            hour = "0" + hour;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (seconds < 10) {
            seconds = "0" + seconds;
        }
        return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
    }
}
/**
 * 时间格式化，传递进来的时间,返回yyyy-MM-dd
 * @param value
 * @returns
 */
function formatterDate1(value) {
    if (value == undefined || value == null || value == '') {
        return "";
    }
    else {
        var date = new Date(value);
        var year = date.getFullYear().toString();
        var month = (date.getMonth() + 1);
        var day = date.getDate().toString();
        var hour = date.getHours().toString();
        var minutes = date.getMinutes().toString();
        var seconds = date.getSeconds().toString();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        if (hour < 10) {
            hour = "0" + hour;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (seconds < 10) {
            seconds = "0" + seconds;
        }
        return year + "-" + month + "-" + day;
    }
}

/** 返回状态 */
function status(value, row, index) {
    if (value == "Y") {
        return "可用";
    } else {
        return "<span style='color: red;'>不可用</span>";
    }
}

/** form表单提交 */
function formSubmit(url, formId, winId) {
    $.post(url,
        $("#" + formId).serialize(),
        function (data) {
            if (data.result == "success") {
                $('#' + winId).modal('hide');
                swal(data.message, "", "success");
                $('#cusTable').bootstrapTable('refresh');
                $('#' + formId).data('bootstrapValidator').resetForm(true);
            } else if (data.result == "fail") {
                swal(data.message, "", "error");
            }
        }, "json");
}

/** 修改提交按钮 */
function edit() {
    $("#editForm").data('bootstrapValidator').validate();
    if ($("#editForm").data('bootstrapValidator').isValid()) {
        $("#editButton").attr("disabled","disabled");
    } else {
        $("#editButton").removeAttr("disabled");
    }
}
/** 添加提交按钮 */
function add() {
    $("#addForm").data('bootstrapValidator').validate();
    if ($("#addForm").data('bootstrapValidator').isValid()) {
        $("#addButton").attr("disabled","disabled");
    } else {
        $("#addButton").removeAttr("disabled");
    }
}
/** 修改按钮状态 */
function buttonStatus(formId, buttonId) {
    $("#" + formId).data('bootstrapValidator').validate();
    if ($("#" + formId).data('bootstrapValidator').isValid()) {
        $("#" + buttonId).attr("disabled","disabled");
    } else {
        $("#" + buttonId).removeAttr("disabled");
    }
}

/** 显示搜索的form */
function showSearchForm() {
    $('#searchSupplyTypeId').html('').trigger("change");
    $('#searchCompanyId').html('').trigger("change");
    $("input[type=reset]").trigger("click");
    $("#searchDiv").show();
    $("#showButton").hide();
}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchDiv").hide();
    $("#showButton").show();
}

/** 当窗口隐藏时销毁验证 */
function destoryValidator(winId, formId) {
    $('#' + winId).on('hidden.bs.modal', function() {
        $("#" + formId).data('bootstrapValidator').destroy();
        $('#' + formId).data('bootstrapValidator', null);
    });
}

/** 状态根据状态搜索 */
function searchStatus(url) {
    initTable("cusTable", url);
}


