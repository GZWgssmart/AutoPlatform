/**
 * Created by root on 2017/4/19.
 */

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/company/queryByPager");
    destoryValidator("addWin","addForm");
    destoryValidator("editWin","editForm");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});
function companyAll(){
    initTable("cusTable","/company/queryByPager");
}
/**查看不可用的公司*/
function statusUsableness(){
    var ids = 'Y';
    initTable("cusTable","/company/queryStatusPager?status="+ids);
}
/**查看可用的公司*/
function statusAvailable(){
    var ids = "N";
    initTable("cusTable","/company/queryStatusPager?status="+ids);
}

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var product = selectRow[0];
        $('#companys').html('<option value="' + product.companySize + '">' + product.companySize + '</option>').trigger("change");
        $("#editForm").fill(product);
        validator("editForm");
        $("#editWin").modal('show');
    }
}

function operating(value, row, index) {
    if (row.companyStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm btn-danger">冻结</button>&nbsp;&nbsp;',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default  btn-sm btn-primary">编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm btn-success" >激活</button>&nbsp;&nbsp;',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default  btn-sm btn-primary">编辑</button>'
        ].join('');
    }
}


function showAddWin(){
    initDateTimePicker("form_datetime","","addForm");
    $('#companys').html('').trigger("change");
    validator("addForm");
    $("#addWin").modal('show');
}


window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var companyStatus = 'N';
        $.get("/company/companyStatusModify?id=" + row.companyId + "&status=" + row.companyStatus,
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
        var companyStatus = 'Y';
        $.get("/company/companyStatusModify?id=" + row.companyId + "&status=" + row.companyStatus,
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
            companyName: {
                message: '公司名称失败',
                validators: {
                    notEmpty: {
                        message: '公司名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '公司名称长度必须在2到4位之间'
                    }
                }
            },
            companyDes: {
                message: '公司描述失败',
                validators: {
                    notEmpty: {
                        message: '公司描述不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: '公司描述长度必须在1到500位之间'
                    }
                }
            },
            companyAddress: {
                message: '公司地址失败',
                validators: {
                    notEmpty: {
                        message: '公司地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 200,
                        message: '公司地址长度必须在1到200位之间'
                    }
                }
            },
            companyTel: {
                message: '公司联系方式失败',
                validators: {
                    notEmpty: {
                        message: '公司联系方式不能为空'
                    },
                    regexp: {
                        regexp: /^\d{3,4}-?\d{7,9}$/,
                        message: '号码格式错误'
                    }
                }
            },
            companyPricipal: {
                message: '公司负责人失败',
                validators: {
                    notEmpty: {
                        message: '公司负责人不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 200,
                        message: '公司负责人长度必须在1到10位之间'
                    }
                }
            },
            companyWebsite: {
                message: '公司官网URL失败',
                validators: {
                    required: true,
                    url: true,
                    notEmpty: {
                        message: '公司官网URL不能为空'
                    }
                }
            },
            companyOpenDate:{
                message: '公司创建时间',
                validators: {
                    notEmpty: {
                        message: '公司创建时间不能为空'
                    }
                }
            },
            companySize:{
                message: '公司规模失败',
                validators: {
                    notEmpty: {
                        message: '公司规模不能为空'
                    }
                }
            },
            companyLongitude:{
                message: '公司经度失败',
                validators: {
                    notEmpty: {
                        message: '公司经度不能为空'
                    },
                    number:true
                }
            },
            companyLatitude:{
                message:'公司纬度失败',
                validators:{
                    notEmpty:{
                        message:'纬度不能为空'
                    },number:true
                }
            }

        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/company/InsertCompany", formId, "addWin");
            } else if (formId == "editForm") {
                formSubmit("/company/uploadCompany", formId, "editWin");
                oFileInput.Init("edit_companyLogo", "/company/uploadCompany");
            }
        })
}


//初始化文件上传控件
$(function () {
    //0.初始化fileinput
    var oFileInput = new FileInput();
    oFileInput.Init("edit_companyLogo", "/company/uploadCompany");
});

//初始化fileinput
var FileInput = function () {
    var oFile = new Object();
    //初始化fileinput控件（第一次初始化）
    oFile.Init = function (ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            showUpload: true, //是否显示上传按钮
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: true,//是否显示拖拽区域
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            maxFileCount: 10, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount: true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        }).on("fileuploaded", function (event, data) {
            // data 为controller返回的json
            if (data.response.result == 'success') {
                alert('处理成功');
            }
        });
    }
    return oFile;
};