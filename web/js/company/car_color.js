
function showAddWin(){
    $("#addWin").modal('show');
    validator("addForm");
    $("input[type=reset]").trigger("click");
}
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/carColor/queryByPager");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});

function colorAll(){
    initTable("cusTable","/carColor/queryByPager");
}

/**查询可用*/
function statusUsableness(){
    var status = 'Y';
    initTable("cusTable","/carColor/queryByStatusPager?status="+status);
}

/**查询不可用*/
function statusAvailable(){
    var status = 'N';
    initTable("cusTable","/carColor/queryByStatusPager?status="+status);
}

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    var product = selectRow[0];
    validator("editForm");
    $("#editForm").fill(product);
    $("#editWin").modal('show');
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
            '<button type="button" class="updateInactive btn btn-default  btn-sm btn-danger" >冻结</button>&nbsp;&nbsp;',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default btn-sm btn-primary ">编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm btn-success" >激活</button>&nbsp;&nbsp;',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default btn-sm btn-primary ">编辑</button>'
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
            }, "json")
    },
    'click .showUpdateIncomingType1': function (e, value, row, index) {
        var incomingType = row;
        validator("editForm");
        $("#editForm").fill(incomingType);
        $("#editWin").modal('show');
    }
}

function validator(formId) {
    $("#addButton").removeAttr("disabled");
    $("#editButton").removeAttr("disabled");
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            colorName: {
                message: '颜色名称失败',
                validators: {
                    notEmpty: {
                        message: '颜色名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '颜色名称长度必须在2到4位之间'
                    }
                }
            },
            colorDes: {
                message: '颜色描述失败',
                validators: {
                    notEmpty: {
                        message: '颜色描述不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: '颜色描述长度必须在1到500位之间'
                    }
                }
            },
            colorHex: {
                message: '颜色Hex失败',
                validators: {
                    notEmpty: {
                        message: '颜色Hex不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: '颜色Hex长度必须在1到500位之间'
                    }

                }
            },
            colorRGB: {
                message: '颜色RGB失败',
                validators: {
                    notEmpty: {
                        message: '颜色RGB不能为空'
                    }

                },
                stringLength: {
                    min: 1,
                    max: 500,
                    message: '颜色RGB长度必须在1到500位之间'
                }
            }


        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/carColor/insertCarColor", formId, "addWin");
            } else if (formId == "editForm") {
                formSubmit("/carColor/uploadCarColor", formId, "editWin");

            }
        })

}


$('#colorpalette').colorPalette()
    .on('selectColor', function(e) {
        $('#selected-color').val(e.color);
        $('#selected-colorRGB').val(colorHexToRGB(e.color));
        $("#span").css("background-color", e.color);
    });

$('#colorpalette').colorPalette()
    .on('selectColor', function(e) {
        $('#selected-color1').val(e.color);
        $('#selected-colorRGB1').val(colorHexToRGB(e.color));
        $("#span").css("background-color", e.color);
    });
