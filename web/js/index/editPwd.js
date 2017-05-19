var oldPwd = $("#oldPwd").val();
var pwd = $("#pwd").val();
var rePwd = $("#rePwd").val();


function editPwd() {
    alert(pwd + "......" + rePwd);
    if (pwd == rePwd) {
        $.get("/pwd/selfPwd?oldPwd="+oldPwd + "&rePwd="+rePwd, function (data) {
            if (data.result == "success") {
                swal("成功提示", data.message, "success");
            } else if (data.result == "fail") {
                swal(data.message, "", "error");
            }
        }, 'json');
    } else {
        swal('错误提示', "两次密码输入不一致", "error");
        return false;
    }
}

