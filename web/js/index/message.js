$(document).ready(function (e, value, row, index) {
    var user = row;
    var selectGender = document.getElementById("gender");
    selectGender.value = user.userGender;
    editEmail = user.userEmail;
    editPhone = user.userPhone;
    editIdentity = user.userIdentity;
    $("#role").val(row.role.roleDes);
    $("#form_datetime").val(formatterDate(user.userCreatedTime));
    $("#editModal").fill(user);
    $("#icon").attr("src","/"+user.userIcon);
    var company = document.getElementById("editModalCompany");
    company.value = user.company.companyName;
    var loginedTime = document.getElementById("form_loginedTime");
    loginedTime.value = user.userLoginedTime;
    $("#form_loginedTime").val(formatterDate(user.userLoginedTime));
    $('#editModalCompany').html('<option value="' + user.company.companyId + '">' + user.company.companyName + '</option>').trigger("change");
    validator("editSelf");
    if(user.userStatus == 'Y'){
        $("#status").val("可用");
    }else{
        $("#status").val("不可用");
    }
    var change = user.userStatus;
    if (change == 'N'){
        return 'Y';
    }
});

/** 表单验证 */
function validator(formId) {
    $("#addButton").removeAttr("disabled");
    $("#editButton").removeAttr("disabled");
    $("#editModalButton").removeAttr("disabled");
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
        },
        fields: {
            userAddress: {
                validators: {
                    notEmpty: {
                        message: '居住地不能为空'
                    }
                }
            },
            userPwd: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 16,
                        message: '密码长度为6-16位'
                    }
                }
            },
            userName: {
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    },
                    regexp: {
                        regexp: /^[\u4e00-\u9fa5]{2,4}$/,
                        message: '姓名格式错误'
                    }
                }
            },
            userPhone: {
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    regexp: {
                        regexp: /^1(3|4|5|7|8)\d{9}$/,
                        message: '手机号格式错误'
                    },
                    threshold: 11,
                    remote: {
                        url: '/peopleManage/peoplePhone_verification?editPhone='+editPhone,
                        message: '该手机号已存在',
                        delay :  2000,
                        type: 'GET'
                    }
                }
            },
            userEmail: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    regexp: {
                        regexp: /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/,
                        message: '邮箱格式错误'
                    },
                    threshold: 6,
                    remote: {
                        url: '/peopleManage/peopleEmail_verification?editEmail='+editEmail,
                        message: '该邮箱已存在',
                        delay :  2000,
                        type: 'GET'
                    }
                }
            },
            userIdentity: {
                validators: {
                    notEmpty: {
                        message: '身份证不能为空'
                    },
                    regexp: {
                        regexp: /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/,
                        message: '身份证格式错误'
                    },
                    threshold: 18,
                    remote: {
                        url: '/peopleManage/peopleIdentity_verification?editIdentity='+editIdentity,
                        message: '该身份证已存在',
                        delay :  2000,
                        type: 'GET'
                    }
                }
            },
            wechatOpenId: {
                validators: {
                    regexp: {
                        regexp: /^[a-zA-Z\d_.]{5,}$/,
                        message: '微信号格式错误'
                    }
                }
            },
            qqOpenId: {
                validators: {
                    regexp: {
                        regexp: /[1-9][0-9]{4,}/,
                        message: 'QQ号格式错误'
                    }
                }
            },
            weiboOpenId: {
                validators: {
                    regexp: {
                        regexp: /[a-zA-z0-9_\d_.]{5,}/,
                        message: '微博号格式错误'
                    }
                }
            },
            userSalary: {
                validators: {
                    regexp: {
                        regexp: /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/,
                        message: '工资格式错误'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if(formId == 'editSelf') {
                $('#editSelf').ajaxSubmit({
                    url: '/message/queryBy_self',
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        if (data.result == "success") {
                            $('#mySelf').modal('hide');
                            swal(data.message, "", "success");
                            $('#mySelf').bootstrapTable('refresh');
                            $('#editSelf').data('bootstrapValidator').resetForm(true);
                        } else if (data.result == "fail") {
                            $('#mySelf').modal('hide');
                            swal(data.message, "内容不匹配", "error");
                            $('#editSelf').data('bootstrapValidator').resetForm(true);
                        }
                    }
                })
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

