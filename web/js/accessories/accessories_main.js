/**
 * Created by Levc on 2017/5/10.
 */
// 初始化开关按钮
function inintBsSwitch(id, onSwitchChange) {
    $('#' + id).bootstrapSwitch({
        onText: '是',
        offText: '否',
        onColor: 'success',
        offColor: 'danger',
        size: 'normal',
        onSwitchChange: onSwitchChange
    });
}
// 空方法 等待实现自己的版本
var onSwitchChange = function (event, state) {
}

// 获取某个表单内的所有inputName值
function getFormInputNames(formId) {
    var ne = [];
    $("#" + formId + " input").each(function () {
        ne.push(this.name);
    })
    return ne;
}

// 循环数组内的匹配值
function eachNames(names, finName) {
    var ne = "";
    $.each(names, function (name, value) {
        if (value == finName) {
            ne = value;
            return false;
        }
    });
    return ne;
}

// 自动计算库存数量
function autoEditCalculationCount(totalCount, saleCount, alCount) {
    var aCount = alCount;
    var tCount = $("#" + totalCount).val();
    var sCount = "";
    var rs = "";

    $("#" + saleCount).bind("input ", function () {
        sCount = $("#" + saleCount).val();
        if (sCount != null && sCount != "") {
            rs = aCount - sCount;
            $("#" + totalCount).val(rs);
        } else if (sCount == "" && sCount == null) {
            console.log(aCount);
            $("#" + totalCount).val(aCount);
        }
    })
}

// 禁用开关
function disableSwitch(modalId, switchId) {
    $("#" + modalId).on("hide.bs.modal", function () {
        $("#" + switchId).bootstrapSwitch("state", false);
    });
}

// 启用开关
function enableSwitch(modalId, switchId) {
    $("#" + modalId).on("hide.bs.modal", function () {
        $("#" + switchId).bootstrapSwitch("state", true);
    });
}