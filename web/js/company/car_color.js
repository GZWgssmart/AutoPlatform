/**
 * Created by root on 2017/4/18.
 */
function showAddWin(){
    // validator("addForm");
    $("#addWin").modal('show');
    $("input[type=reset]").trigger("click");
}
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/carColor/queryByPager");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    var product = selectRow[0];
    $("#editForm").fill(product);
    $("#editWin").modal('show');
}


/**提交编辑数据 */
function updateProduct() {
    $.post("/carColor/uploadCarColor",
        $("#editForm").serialize(),
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
    $.post("/carColor/insertCarColor",
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
    if (row.colorStatus == 'Y') {
        return [
            '可用'
        ].join('');
    } else {
        return [
            '不可用'
        ].join('');
    }
}

function StatusIncomeing() {
    var rows = $("#cusTable").bootstrapTable('getSelections');
    if (rows.length < 1) {
        swal('冻结失败', "请选择一条或多条数据进行冻结", "error");
    } else {
        var ids = "";
        for (var i = 0, len = rows.length; i < len; i++) {
            if (ids == "") {
                ids = rows[i].id;
            } else {
                ids += "," + rows[i].id
            }
            if (ids != "") {
                $.get(contextPath + "/carBrand/StatusInactive" + rows[0].ids,
                    function (data) {
                        swal(data.message, "", "success");
                        $('#cusTable').bootstrapTable('refresh');
                    }, "json");
            }
        }

    }
}

/**
 * 十六进制颜色转换为RGB颜色
 * @param color 要转换的十六进制颜色
 * @return RGB颜色
 */
function colorHexToRGB(color){
    color=color.toUpperCase();
    var regexpHex=/^#[0-9a-fA-F]{3,6}$/;//Hex
    if(regexpHex.test(color)){
        var hexArray=new Array();
        var count=1;
        for(var i=1;i<=3;i++){
            if(color.length-2*i>3-i){
                hexArray.push(Number("0x"+color.substring(count,count+2)));
                count+=2;
            }else{
                hexArray.push(Number("0x"+color.charAt(count)+color.charAt(count)));
                count+=1;
            }
        }
        return hexArray.join(",");
    }else{
        return color;
    }
}

function colorFormatter(value, row, index) {
    return "<span style='display: inline-block; width: 25px; height: 25px; background-color: " + value + ";'></span>";
}

function operating(value, row, index) {
    if (row.colorStatus == 'Y') {
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
        $.get("/carColor/colorStatusModify?id=" + row.colorId + "&status=" + Status,
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
        $.get("/carColor/colorStatusModify?id=" + row.colorId + "&status=" + Status,
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