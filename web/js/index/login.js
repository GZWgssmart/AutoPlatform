/**
 * Created by Xiao-Qiang on 2017/5/5.
 */
var contextPath = '';

function redirectIndex(needRedirect) {
    if (needRedirect == "redirect") {
        $.messager.alert("提示", "登录信息无效，请重新登录", "info", function () {
            top.location.href = "/login/logout";
        });
    }
}

function login() {
    $.post(contextPath + "/login/login",
        $("#login_form").serialize(),
        function (data) {
            if (data.result == "success") {
                if (data.message == "adminHome") {
                    window.location.href = contextPath + "/adminHome";
                } else if (data.message == "customerHome") {
                    window.location.href = contextPath + "/customerHome";
                }
            } else {
                $("#errMsg").html(data.message);
            }
        }
    );
}

var count = 3;
function cCount(val) {
    $("#successMsg").html(val + count + "s后自动登入");
    if (count == 0) {
        count = 3;
    } else {
        count--;
    }
    setTimeout(function () {
        if (count >= 0) {
            cCount(val);
        }
    }, 1000)
}
/** 注册逻辑 */
function register() {
    $.post(contextPath + "/login/register",
        $("#register_form").serialize(),
        function (data) {
            if (data.result == "success") {
                if (data.message == "注册成功，请到邮箱中进行验证") {
                    $("#successMsg").html(data.message);
                } else {
                    cCount(data.message);
                    setTimeout(function () {
                        window.location.href = contextPath + "/customerHome";
                    }, 3000);
                }
            } else {
                $("#errMsg1").html(data.message);
            }
        }
    );
}
var userPhone;
/** 验证输入的账号 */
function variNumber(number) {
    if (isEmail(number)) {
        $("#errMsg1").html("");
        $("#codeDiv").hide();
    } else if (isPhone(number)) {
        userPhone = number;
        $("#errMsg1").html("");
        $("#codeDiv").show();
    } else {
        $("#errMsg1").html("请输入正确的手机号或邮箱");
    }
}

/** 判断是否是邮箱 */
function isEmail(str) {
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.([a-zA-Z0-9_-])+)+$/;
    return reg.test(str);
}

/** 判断是否是手机号 */
function isPhone(str) {
    var reg = /^1[3|4|5|7|8][0-9]{9}$/;
    return reg.test(str);
}

/** 获取验证码 */
var countdown = 60;
function getCode(val) {
    sendCode(countdown);
    if (countdown == 0) {
        val.removeAttribute("disabled");
        val.value = "获取验证码";
        countdown = 60;
    } else {
        val.setAttribute("disabled", true);
        val.value = "重新发送(" + countdown + ")";
        countdown--;
    }
    setTimeout(function () {
        if (val.value != "获取验证码") {
            getCode(val);
        } else {
            $("#successMsg").html('');
        }
    }, 1000)
}

/** 发送短信 */
function sendCode(val) {
    if (val == 60) {
        if (userPhone != null && userPhone != "" && userPhone != undefined) {
            $.get("/login/sendCode?userPhone=" + userPhone, function (data) {
                if (data.result == "success") {
                    $("#successMsg").html(data.message);
                }
            }, "json");
        }
    }
}

var codeNumber;
/** 获取验证码 */
var countdown1 = 60;
function getCode1(val) {
    sendCode1(countdown1);
    if (countdown1 == 0) {
        val.removeAttribute("disabled");
        val.value = "获取验证码";
        countdown1 = 60;
    } else {
        val.setAttribute("disabled", true);
        val.value = "重新发送(" + countdown1 + ")";
        countdown1--;
    }
    setTimeout(function () {
        if (val.value != "获取验证码") {
            getCode(val);
        }
    }, 1000)
}

/** 发送查询进度的验证短信 */
function sendCode1(val) {
    if (val == 60) {
        codeNumber = getCodeNumber();
        var userPhone = $("#phone").val();
        if (userPhone != null && userPhone != "" && userPhone != undefined) {
            $.get("/customerClientWeb/sendCode?userPhone=" + userPhone + "&codeNumber=" + codeNumber, function (data) {
                if (data.result == "success") {
                    $("#successMsg").html(data.message);
                }
            }, "json");
        }
    }
}

/** 查询 */
function customerCar() {
    var phone = $("#phone").val();
    var code = $("#code").val();
    if (code == codeNumber) {
        $("#error").html('');
        $("#successMsg").html('');
        $("#phone").val('');
        $("#code").val('');
        var url = '/customerClientWeb/userPage?phone=' + phone;
        document.getElementById("iframeTable").src = url;
    } else {
        $("#error").html('您输入的验证码有误，请重新输入');
    }
}

/** 清除提示信息 */
function clearSuccess(val) {
    if (val.value != "") {
        $("#successMsg").html("");
    }
}

/** 检查手机号 */
function checkPhone(phone) {
    if (isPhone(phone.value)) {
        $("#error").html('');
        $("#getButton").removeAttr("disabled");
    } else {
        $("#error").html('请输入正确的手机号');
        $("#getButton").attr("disabled","disabled");
    }
}

/** 获取6位数验证码 */
function getCodeNumber() {
    var str = Math.floor((Math.random() * 9 + 1) * 100000);
    return str;
}