<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>个人资料</title>


    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/message.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/style.min862f.css" rel="stylesheet" type="text/css">


</head>
<body>
<div class="wrapper" id="mySelf">
    <div class="modal-content">
        <div class="col-sm-12">
            <div class="ibox">
                <div class="ibox-content">
                    <form role="form" id="editSelf" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="userId" attr="user.userId" />
                        <input type="hidden" name="uIcon" attr="user.userIcon"/>
                        <div class="max_div">
                            <br/>
                            <div class="shell">
                                <div class="avatar">
                                    <div id="preview">
                                        <img id="icon" attr="user.userIcon" onclick="$('#previewImg').click();" name="file" style="border-radius: 50%;"/>
                                    </div>
                                    <input type="file" name="file" onchange="previewImage(this)" style="display: none;" id="previewImg">
                                </div>
                                <br/>
                                <div class="table_info">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <label class="control-label">邮箱：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="editEmail" attr="user.userEmail" name="userEmail"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">昵称：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userNickname" name="userNickname"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">性别：</label>
                                            <select style="display: initial;" class="form-control" name="userGender" attr="user.userGender" id="gender">
                                                <option value="N" selected = "selected">未知</option>
                                                <option value="M">男</option>
                                                <option value="F">女</option>
                                            </select>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">年龄：</label>
                                            <input class="form-control" style="display: initial;" disabled="disabled" id="age" type="text" attr="user.userAge" name="useAge"/>
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-3">
                                            <label class="control-label">手机号：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="editPhone" attr="user.userPhone" name="userPhone"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">身份证：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="editIdentity" attr="user.userIdentity" name="userIdentity"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">微信号：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.wechatOpenId" name="wechatOpenId"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">QQ号：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.qqOpenId" name="qqOpenId"/>
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-3">
                                            <label class="control-label">微博：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.weiboOpenId" name="weiboOpenId"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">生日：</label>
                                            <input class="form-control" style="display: initial;" type="text" disabled="disabled" id="birthday" attr="user.userBirthday" name="userBirthday"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">真实姓名：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userName" name="userName"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">所属公司：</label>
                                            <input id="editModalCompany" style="display: initial;" disabled="disabled" class="js-example-tags form-control"/>
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-3">
                                            <label class="control-label">入职时间：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text" id="form_datetime" name="userCreatedTime"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">最近登录：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text" id="form_loginedTime" name="userLoginedTime"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">基本工资：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userSalary" name="userSalary"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">居住地址：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text" attr="user.userAddress" name="userAddress"/>
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-3">
                                            <label class="control-label">所属职位：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">当前状态：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text" name="userStatus"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">所属职位：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">当前状态：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text" id="status" name="userStatus"/>
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="control-label">个人描述：</label>
                                            <textarea class="form-control" style="display: initial;" id="chang" type="textarea" maxlength="150" placeholder="限制字数为150" attr="user.userDes" name="userDes"></textarea>
                                            <em class="zi_em" id="textShu">150</em>
                                        </div>
                                    </div>
                                </div>
                                <br/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>



<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/index/message.js"></script>

</body>
</html>
