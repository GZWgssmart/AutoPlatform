/**
 * Created by xiao-qiang 2017/4/19.
 */
var contextPath = '';
var ypAll = new Array();
var npAll = new Array();
var roleId2;
var roleObj2;
var moduleId2;
var moduleObj2;

function showAduqPermission() {
    $("#allotPermission").hide();
    initTable("cusTable", contextPath + "/permission/query_pager");

    initSelect2("module_all", "选择模块查询", contextPath + "/module/query_all", "150");
    $("#aduqPermission").show();
    var aa = $("#moduleSelect").id();
    alert(aa);
}

function showAllotPermission() {
    $("#aduqPermission").hide();
    $("#allotPermission").show();
}

function selectRole(roleObj, roleId) {
    roleObj2 = roleObj;
    roleId2 = roleId;
    $("#roleType").val($(roleObj2).text());
    if (moduleId2 != null && moduleId2 != "") {
        queryByRoleIdOrModuleId(roleId2, moduleId2);
    }
    $("#selectModule").show();
}

function selectModule(moduleObj, moduleId) {
    moduleObj2 = moduleObj;
    moduleId2 = moduleId;
    $("#moduleType").val($(moduleObj2).text());
    if (roleId2 != null && roleId2 != "") {
        queryByRoleIdOrModuleId(roleId2, moduleId2);
    }
}

function queryByRoleIdOrModuleId(roleId, moduleId) {
    $.get(contextPath + "/permission/roleIdOrModuleId_permission?roleId=" + roleId + "&moduleId=" + moduleId,
        function (data) {
            if (data.length > 0) {
                drawPermission(data);
                $("#permissionDes").show();
            } else {
                $("#permissionY").html("");
                $("#permissionN").html("");
                $("#permissionDes").hide();
            }
        }, "json");
}

function drawPermission(data) {

    var yStr = "";
    var nStr = "";
    var count1 = 0;
    var count2 = 0;
    $.each(data, function (index, item) {
        var str = "'" + data[index].permissionId + "'";
        if (data[index].status == 1) {
            npAll[count1] = data[index].permissionId;
            yStr += '<span onclick="delById(' + str + ',this);" class="label label-success">' + data[index].permissionName + '&nbsp;&nbsp;&nbsp;<i class="fa fa-minus-circle"></i></span>';
            count1++;
        } else if (data[index].status == 0) {
            ypAll[count2] = data[index].permissionId;
            nStr += '<span onclick="addById(' + str + ',this);" class="label label-warning">' + data[index].permissionName + '&nbsp;&nbsp;&nbsp;<i class="fa fa-plus-circle"></i></span>';
            count2++;
        }
    });
    yStr += "<p style='clear:both'></p>";
    nStr += "<p style='clear:both'></p>";
    /*ypAll = ypAll.substring(0, ypAll.length - 1);
     npAll = npAll.substring(0, npAll.length - 1);*/
    $("#permissionY").html(yStr);
    $("#permissionN").html(nStr);
    $("#btnDiv").show();
}

function addById(permissionId, obj) {
    var str = "" + permissionId;
    $.get(contextPath + "/permission/addByRole_permission?permissionIds=" + str + "&roleId=" + roleId2,
        function (data) {
        }, "json");
    selectRole(roleObj2, roleId2);
    selectModule(moduleObj2, moduleId2);
}

function delById(permissionId, obj) {
    var str = "" + permissionId;
    $.get(contextPath + "/permission/delByRole_permission?permissionIds=" + str + "&roleId=" + roleId2,
        function (data) {
        }, "json");
    selectRole(roleObj2, roleId2);
    selectModule(moduleObj2, moduleId2);
}

/*function commonAdd(permissionIds) {

 }

 function commonDel(permissionIds) {

 }*/

function operateFormatter(value, row, index) {
    if (row.permissionStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm" style="margin-right:15px;" >冻结</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm" style="margin-right:15px;" >激活</button>'
        ].join('');
    }
}

function addAll() {
    $.get(contextPath + "/permission/addByRole_permission?permissionIds=" + ypAll + "&roleId=" + roleId2,
        function (data) {
        }, "json");
    selectRole(roleObj2, roleId2);
    selectModule(moduleObj2, moduleId2);
}

function delAll() {
    $.get(contextPath + "/permission/delByRole_permission?permissionIds=" + npAll + "&roleId=" + roleId2,
        function (data) {
        }, "json");
    selectRole(roleObj2, roleId2);
    selectModule(moduleObj2, moduleId2);
}

function selectModule(combox) {
    initTable("cusTable", contextPath + "/permission/module_pager?moduleId=" + combox.value);
}