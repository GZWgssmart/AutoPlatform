<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>人员基本信息管理</title>


    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/city-picker.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/people_info.css" rel="stylesheet" type="text/css">


</head>
<body>

<div class="container">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th  data-field="userCreatedTime" data-sortable="true">
                人员编号
            </th>
            <th data-field="userName" >
                姓名
            </th>
            <th data-field="userEmail" >
                邮箱
            </th>
            <th data-field="userGender" data-formatter="gender" >
                性别
            </th>
            <th data-field="userPhone" >
                手机号
            </th>
            <th data-field="company.companyName" >
                所属公司
            </th>
            <th data-field="userSalary" >
                基本工资
            </th>
            <th data-field="userStatus" data-formatter="status">
                当前状态
            </th>
            <th data-field="operate" data-formatter="operateFormatter" data-events="operateEvents">
                操作
            </th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
            <a><button onclick="showAddWin();" type="button" id="add" class="btn btn-default" >
                <i class="glyphicon glyphicon-plus"></i> 添加
            </button></a>
            <a><button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                <i class="glyphicon glyphicon-pencil"></i> 修改
            </button></a>
        </div>
        </tbody>
    </table>
</div>



<div id="editWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">修改信息</h3>
                        <form role="form" id="editForm" >
                            <input type="hidden" attr="user.userId" name="userId" />
                            <div class="form-group">
                                <label class="control-label">头像：</label>
                                <input type="text" attr="user.userIcon" name="userIcon" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">姓名：</label>
                                <input type="text" attr="user.userName" name="userName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">邮箱：</label>
                                <input type="text" attr="user.userEmail" name="userEmail" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">密码：</label>
                                <input type="text" attr="user.userPwd" name="userPwd" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">性别：</label>
                                <select attr="user.userGender" id="usergender" name="userGender" class="form-control">
                                    <option value="N" selected = "selected">未知</option>
                                    <option value="M">男</option>
                                    <option value="F">女</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">手机号：</label>
                                <input type="text"  name="userPhone" attr="user.userPhone"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">QQ：</label>
                                <input type="text" name="qqOpenId" attr="user.qqOpenId"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">微博：</label>
                                <input type="text" name="weiboOpenId" attr="user.weiboOpenId"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">微信：</label>
                                <input type="text" name="wechatOpenId" attr="user.wechatOpenId"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">居住地址：</label>
                                <div style="position: relative;">
                                    <input data-toggle="city-picker" class="address"  id="userAddress" name="userAddress" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">基本工资：</label>
                                <input type="text" attr="user.userSalary" name="userSalary" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">身份证：</label>
                                <input type="text"  name="userIdentity" attr="user.userIdentity"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">描述：</label>
                                <textarea type="text" name="userDes" attr="user.userDes"
                                          class="form-control"></textarea>
                            </div>
                            <div class="form-group">
                                <label class="control-label">所属公司：</label>
                                <select id="editCompany" class="js-example-tags form-control user_company" name="companyId"></select>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <span id="error1" style="color: red;"></span>
                                <br/>
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" onclick="edit()" id="editButton" class="btn btn-primary" value="修改"/>
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="addWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">添加员工</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label class="control-label">姓名：</label>
                                <input type="text"  name="userName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">邮箱：</label>
                                <input type="email" name="userEmail"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <p><label class="control-label">密码：</label></p>
                                <input type="password" id="pwd" name="userPwd"
                                       class="form-control" style="width: 75%; display: initial;"/>
                                <button type="button" onclick="defaultPwd()"style="float: right" class="btn btn-success" data-toggle="tooltip" data-placement="top" title="默认密码为123456">使用默认密码</button>
                            </div>
                            <div class="form-group">
                                <label class="control-label">性别：</label>
                                <select class="form-control" name="userGender">
                                    <option value="N" selected = "selected">未知</option>
                                    <option value="M">男</option>
                                    <option value="F">女</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">手机号：</label>
                                <input type="text" name="userPhone"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">基本工资：</label>
                                <input type="text" name="userSalary"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">居住地址：</label>
                                <div style="position: relative;">
                                    <input data-toggle="city-picker" class="address" name="userAddress">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">身份证：</label>
                                <input type="text" name="userIdentity"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">所属公司：</label>
                                <select id="addCompany" class="js-example-tags form-control user_company" name="companyId"></select>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton"id="destroy" class="btn btn-primary" onclick="add()" value="添加"/>
                                </input>
                                <input type="reset" name="reset" style="display: none"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<div id="myModal" class="modal fade" aria-hidden="true">
    <div class="modal-dialog" style="width: 92%;margin-top: 25px">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h4 class="m-t-none m-b">管理员个人信息</h4>
                        <div class="form_info">
                            <form role="form" method="post" id="editModal" class="form_form" onkeydown="if(event.keyCode==13){return false;}" enctype="multipart/form-data">
                                <input type="hidden" name="userId" attr="user.userId" />
                                <div class="form_img">
                                    <div id="preview">
                                        <img alt="image" attr="user.userIcon" name="file" style="border-radius: 50%;"/>
                                    </div>
                                    <input type="file" name="file" onchange="previewImage(this)" style="display: none;" id="previewImg">
                                </div>
                                <div class="info">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label class="control-label">邮箱：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text" attr="user.userEmail" name="userEmail"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label">昵称：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userNickname" name="userNickname"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label">性别：</label>
                                            <select style="display: initial;" class="form-control" name="userGender" attr="user.userGender" id="gender">
                                                <option value="N" selected = "selected">未知</option>
                                                <option value="M">男</option>
                                                <option value="F">女</option>
                                            </select>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label class="control-label">年龄：</label>
                                            <input class="form-control" style="display: initial;" disabled="disabled" id="age" type="text" attr="user.userAge" name="useAge"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -14px">手机号：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userPhone" name="userPhone"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -14px">身份证：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="identity" attr="user.userIdentity" name="userIdentity"/>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -14px">微信号：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.wechatOpenId" name="wechatOpenId"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -8px">QQ号：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.qqOpenId" name="qqOpenId"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label">微博：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.weiboOpenId" name="weiboOpenId"/>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label class="control-label">生日：</label>
                                            <input class="form-control" style="display: initial;" type="text" disabled="disabled" id="birthday" attr="user.userBirthday" name="userBirthday"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">真实姓名：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userName" name="userName"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">所属公司：</label>
                                            <select id="editModalCompany" class="js-example-tags form-control userModal_company" name="companyId"></select>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label class="control-label"  style="margin-left: -28px">入职时间：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text" id="form_datetime" name="userCreatedTime"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">最近登录：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text" attr="user.userLoginedTime" name="userLoginedTime"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">基本工资：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userSalary" name="userSalary"/>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">居住地址：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userAddress" name="userAddress"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">所属职位：</label>
                                            <input class="form-control" style="display: initial;" type="text"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">当前状态：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text" id="status" name="userStatus"/>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="control-label" style="margin-left: -28px">个人描述：</label>
                                            <textarea class="form-control" style="display: initial;" id="chang" type="textarea" maxlength="150" placeholder="限制字数为150" attr="user.userDes" name="userDes"></textarea>
                                            <em class="zi_em" id="textShu">150</em>
                                        </div>
                                    </div>
                                </div>
                                <button class="form_save" data-dismiss="modal" aria-hidden="true">关闭</button>
                                <button class="form_save" type="button" id="editModalButton" onclick="editModal()">保存</button>
                            </form>
                            <button class="button_form" id="button" onclick="$('#previewImg').click();">更换头像</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/peopleManage/peopleInfo.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/city-picker.data.js"></script>
<script src="<%=path %>/js/city-picker.js"></script>
<script src="<%=path %>/js/jquery.form.min.js"></script>
</body>
</html>
