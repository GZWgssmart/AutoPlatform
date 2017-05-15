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

    initDateTimePicker("form_datetime","companyOpenDate","editForm");
    initDateTimePicker("form_datetime","companyOpenDate","addForm");
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
        $('#editCompanyOpenDate').val(formatterDate(product.companyOpenDate));
        $("#icon").attr("src","/" + product.companyLogo);
        $("#editForm").fill(product);
        $("#editWin").modal('show');
        validator("editForm");
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

function formatterImg(value, row, index){
    if(row.companyLogo !=null){
        return [
            '<img style="width:120px;height:60px;" src="/'+ value +'">'
        ]
    }
}

function companyOpDateFormatter(value, row, index){
    if(row.companyOpenDate !=null){
       var date =  formatterDate(value);
        return date;
    }
}

function showAddWin(){
    $('#companys').html('').trigger("change");
    $("#addWin").modal('show');
    validator("addForm");
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
    $('#companys').html('<option value="' + incomingType.companySize + '">' + incomingType.companySize + '</option>').trigger("change");initDateTimePicker("form_datetime","","editForm");
    $("#icon").attr("src","/" + incomingType.companyLogo);
    $('#editCompanyOpenDate').val(formatterDate(incomingType.companyOpenDate));
    $("#editForm").fill(incomingType);
    $("#editWin").modal('show');
    validator("editForm");
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
                $("#editButton").on("click", function () {
                    $("#editForm").data('bootstrapValidator').validate();
                    if ($("#editForm").data('bootstrapValidator').isValid()) {
                        $("#editButton").attr("disabled","disabled");
                    } else {
                        $("#editButton").removeAttr("disabled");
                    }
                    $('#editForm').ajaxSubmit({
                        url: '/company/uploadCompany',
                        type: 'post',
                        dataType: 'json',
                        success: function (data) {
                            if (data.result == "success") {
                                $('#editWin').modal('hide');
                                swal(data.message, "", "success");
                                $('#cusTable').bootstrapTable('refresh');
                                $('#editForm').data('bootstrapValidator').resetForm(true);
                            } else if (data.result == "fail") {
                                $('#editWin').modal('hide');
                                swal(data.message, "", "error");
                                $('#editForm').data('bootstrapValidator').resetForm(true);
                            }
                        }
                    })
                })
        }
    })
}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchCompanyName").val('');
    $("#searchDiv").hide();
    $("#showButton").show();
}

function searchCompany(){
    var companyName = $("#searchCompanyName").val();
    var userName = $("#searchUserName").val();
    initTable("cusTable","/company/search?companyName="+ companyName + "&userName=" + userName);
}



//图片上传预览    IE是用了滤镜。
function previewImage(file)
{
    var MAXWIDTH  = 100;
    var MAXHEIGHT = 50;
    var div = document.getElementById('preview');
    if (file.files && file.files[0])
    {
        div.innerHTML ='<img id=imghead onclick=$("#previewImg").click()>';
        var img = document.getElementById('imghead');
        img.onload = function(){
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width  =  rect.width;
            img.height =  rect.height;
//                 img.style.marginLeft = rect.left+'px';
            img.style.marginTop = rect.top+'px';
        }
        var reader = new FileReader();
        reader.onload = function(evt){img.src = evt.target.result;}
        reader.readAsDataURL(file.files[0]);
    }
    else //兼容IE
    {
        var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
        div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
    }
}

function clacImgZoomParam( maxWidth, maxHeight, width, height ){
    var param = {top:0, left:0, width:width, height:height};
    if( width>maxWidth || height>maxHeight ){
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;

        if( rateWidth > rateHeight ){
            param.width =  maxWidth;
            param.height = Math.round(height / rateWidth);
        }else{
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    return param;
}
