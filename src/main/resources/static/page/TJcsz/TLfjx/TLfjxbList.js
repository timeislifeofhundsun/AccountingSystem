//获取cookie中的token
$(function () {
    var header = $.cookie('header');
    var token = $.cookie('token');
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
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