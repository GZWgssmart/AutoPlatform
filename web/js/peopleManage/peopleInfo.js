$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/peopleManage/peopleInfo_pager");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);

    initSelect2("user_company", "请选择公司", "/company/company_all", "565");
    initSelect2("userModal_company", "请选择公司", "/company/company_all", "130");
});




function gender(value, row, index) {
    if (row.userGender == 'M') {
        return '男'
    }else if (row.userGender == 'F'){
        return '女'
    }else if (row.userGender == 'N'){
        return '未知'
    }
}


function fmtDate(){
    var curLength = $("#chang").val().length;
    var shu = 150-curLength;
    $("#textShu").text(shu);

    var textareaObj=document.getElementById("chang");
    var remainObj=document.getElementById("textShu");
    var num=0;
    if(/msie/i.test()){
        textareaObj.onpropertychange=function(){
            num=150-this.value.length;
            remainObj.innerHTML=num;
        }
    }else{
        textareaObj.oninput=function(){
            num=150-this.value.length;
            remainObj.innerHTML=num;
        }
    }

}

function operateFormatter(value, row, index) {
    if (row.userStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-danger  btn-sm">冻结</button>',
            '<button style="margin-left: 10px" type="button" class="showUpdateInfo btn btn-primary">查看详情</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-success  btn-sm">激活</button>',
            '<button style="margin-left: 10px" type="button"  class="showUpdateInfo btn btn-primary">查看详情</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var userStatus = 'N';
        $.get("/peopleManage/peopleInfo_status?id=" + row.userId + "&status=" + userStatus,
            function (data) {
                if (data.result == "success") {

                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var userStatus = 'Y';
        $.get("/peopleManage/peopleInfo_status?id=" + row.userId + "&status=" + userStatus,
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
        var user = row;
        var selectGender = document.getElementById("gender");
        selectGender.value = user.userGender;
        $("#form_datetime").val(formatterDate(user.userCreatedTime));
        $("#editModal").fill(user);
        $('#editModalCompany').html('<option value="' + user.company.companyId + '">' + user.company.companyName + '</option>').trigger("change");
        validator("editModal");
        $("#myModal").modal('show');
        if(user.userStatus == 'Y'){
            $("#status").val("可用");
        }else{
            $("#status").val("不可用");
        }
        fmtDate();
        formatterDate();
    }
}

/** 修改提交 */
function editModal() {
    $("#editModal").data('bootstrapValidator').validate();
    if ($("#editModal").data('bootstrapValidator').isValid()) {
        $("#editModalButton").attr("disabled","disabled");
    } else {
        $("#editModalButton").removeAttr("disabled");
    }
}





/**编辑数据 */
function showEditWin() {
    validator("editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var user = selectRow[0];
        var gender = document.getElementById("usergender");
        gender.value = user.userGender;
        $("#editForm").fill(user);
        $('#editCompany').html('<option value="' + user.company.companyId + '">' + user.company.companyName + '</option>').trigger("change");
        $("#editWin").modal('show');
    }
}

function defaultPwd() {
    var password = "123456";
    $("#pwd").val(password);
}

/**提交添加数据 */
function showAddWin() {
    validator("addForm");
    $('#addCompany').html('').trigger("change");
    $("input[type=reset]").trigger("click");
    $("#addWin").modal('show');
}

/** 表单验证 */
function validator(formId) {
    $("#addButton").removeAttr("disabled");
    $("#editButton").removeAttr("disabled");
    $("#editModalButton").removeAttr("disabled");
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            userName: {
                message: '车主验证失败',
                validators: {
                    notEmpty: {
                        message: '车主姓名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 4,
                        message: '车主长度必须在2到4位之间'
                    }
                }
            },
            userPhone: {
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '手机号只能是11位'
                    }
                }
            },
            brandId: {
                validators: {
                    notEmpty: {
                        message: '品牌不能为空'
                    }

                }
            },
            colorId: {
                validators: {
                    notEmpty: {
                        message: '颜色不能为空'
                    }

                }
            },
            modelId: {
                validators: {
                    notEmpty: {
                        message: '车型不能为空'
                    }

                }
            },
            plateId: {
                validators: {
                    notEmpty: {
                        message: '车牌不能为空'
                    }

                }
            },
            carPlate: {
                validators: {
                    notEmpty: {
                        message: '车牌号码不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 5,
                        message: '车牌号码只能是5位'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '车牌不能是中文'
                    }
                }
            },
            oilCount: {
                validators: {
                    notEmpty: {
                        message: '汽车油量不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '油量只能是数字'
                    }

                }
            },
            carMileage: {
                validators: {
                    notEmpty: {
                        message: '汽车行驶里程不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '行驶里程只能是数字'
                    }

                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/peopleManage/peopleInfo_insert", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/peopleManage/peopleInfo_update", formId, "editWin");
            } else if(formId == 'editModal'){
                formSubmit("/peopleManage/peopleInfo_update", formId, "myModal");
            }


        })
}

//图片上传预览    IE是用了滤镜。
function previewImage(file)
{
    var MAXWIDTH  = 250;
    var MAXHEIGHT = 250;
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

