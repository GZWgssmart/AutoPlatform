var sts = $("#status").val();
if(sts == 'Y'){
    $("#status").val("可用");
}else{
    $("#status").val("不可用");
}

var date = $("#form_datetime").val();
var datetime = $("#form_loginedTime").val();
$("#form_datetime").val(formatterDate(date));
$("#form_loginedTime").val(formatterDate(datetime));


var card = $("#editIdentity").val();
if(card != null && card != '') {
    var myDate = new Date();
    var month = myDate.getMonth() + 1;
    var day = myDate.getDate();
    var age = myDate.getFullYear() - card.substring(6, 10) - 1;
    if (card.substring(10, 12) < month || card.substring(10, 12) == month && card.substring(12, 14) <= day) {
        age++;
    }
    var birthday = card.substring(6, 10) + "-" + card.substring(10, 12) + "-" + card.substring(12, 14);
    $("#birthday").val(birthday);
    $("#age").val(age);
}

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



function selfMessage() {
    $('#editSelf').ajaxSubmit({
        url: '/message/queryBy_self',
        type: 'post',
        dataType: 'json',
        success: function (data) {
            if (data.result == "success") {
                swal(data.message, "下次登录系统自动同步修改信息", "success");
            } else if (data.result == "fail") {
                swal(data.message, "请填写正确的信息", "error");
            }
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

