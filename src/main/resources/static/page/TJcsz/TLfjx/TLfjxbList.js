//获取cookie中的token
$(function () {
    var header = $.cookie('header');
    var token = $.cookie('token');
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    var authorities=$.cookie('authorities');
    authorities = authorities.substring(1,authorities.length-1);
    authorities = authorities.split(",");
    if ($.inArray("ROLE_ADMIN",authorities)!=0){
        $("#yhlv").prop("disabled",true);
        $("#hglv").prop("disabled",true);
        $("#jylv").prop("disabled",true);
        $("#jslv").prop("disabled",true);
        $("#xxplf").prop("disabled",true);
        $("#xxplcs").prop("disabled",true);
        $("#sjf").prop("disabled",true);
        $("#sjcs").prop("disabled",true);
        $("#SaveDiv").hide();
    }


})
layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl;
    $.ajax({
        url:"/TLfjxb",
        type:"GET",
        success: (function  (data) {
            console.log(data);
            $("#id").val(data.id);
            $("#yhlv").val(data.yhlv);
            $("#hglv").val(data.hglv);
            $("#jylv").val(data.jylv);
            $("#jslv").val(data.jslv);
            $("#xxplf").val(data.xxplf);
            $("#xxplcs").val(data.xxplcs);
            $("#sjf").val(data.sjf);
            $("#sjcs").val(data.sjcs);
        })
    });
    form .verify({
        yhlv: function (val) {
            if (val == '') {
                return "银行费率不能为空";
            } else if (val > 0.001) {
                return "银行费率不能大于0.001";
            }
        },
        hglv: function (val) {
            if (val == '') {
                return "回购费率不能为空";
            } else if (val > 0.001) {
                return "回购费率不能大于0.001";
            }
        },
        jylv: function (val) {
            if (val == '') {
                return "交易费率不能为空";
            } else if (val > 0.001) {
                return "交易费率不能大于0.001";
            }
        },
        jslv: function (val) {
            if (val == '') {
                return "结算费率不能为空";
            } else if (val > 0.001) {
                return "结算费率不能大于0.001";
            }
        },
        xxplf:function (val) {
            if (val == '') {
                return "信息披露费不能为空";
            }
        },
        xxplcs:function (val) {
            if (val == '') {
                return "信息披露次数不能为空";
            }
        },
        sjf:function (val) {
            if (val == '') {
                return "审计费不能为空";
            }
        },
        sjcs:function (val) {
            if (val == '') {
                return "审计次数不能为空";
            }
        },
        
    });
    form.on("submit(SaveTLfjx)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/TLfjxb",
            data: {TLfjxb:JSON.stringify(data.field)},
            type: 'PUT',
            success:function (obj) {
                if (obj==1) {
                    top.layer.msg("信息保存成功！");
                }
            }
        });
        return false;
    });

})