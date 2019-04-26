//获取cookie中的token
$(function () {
    var header = $.cookie('header');
    var token = $.cookie('token');
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
})
var form, $;

layui.use(['form', 'layer','laydate'], function () {
    form = layui.form;
    laydate = layui.laydate;
    var layer = parent.layer === undefined ? layui.layer : top.layer;
    $ = layui.jquery;
    laydate.render({
        elem: '#createdate',
        format: 'yyyy-MM-dd'
    });
    laydate.render({
        elem: '#enddate',
        format: 'yyyy-MM-dd'
    });
    
    form .verify({
    	name: function (val) {
            if (val == '') {
                return "账套名称不能为空";
            }
        },
        createdate: function (val) {
            if (val == '') {
                return "创建时间不能为空";
            }
        },
        jjdm: function (val) {
            if (val == '') {
                return "基金代码不能为空";
            }
        },
        money: function (val) {
            if (val == '') {
                return "初始金额不能为空";
            }else if(isNaN(val)){
            	return "请输入数字";
            }else if(val.length>10){
            	return "初始份额不能大于10位数";
            }
        },
        number: function (val) {
            if (val == '') {
                return "初始份额不能为空";
            }else if(isNaN(val)){
            	return "请输入数字";
            }else if(val.length>10 || val>2147483647){
            	return "初始份额过大";
            }
        },
    });
    
    //提交更改账套信息
    form.on("submit(updateTZtxx)", function (data) {
        top.layer.msg(JSON.stringify(data.field));
        console.log(data);
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/TZtxx",
            data: {Ztxx:JSON.stringify(data.field)},
            type: 'PUT',
            success:function (obj) {
                if (obj==1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("账套信息更改成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    }, 500);
                }else{
                	setTimeout(function () {
                		top.layer.close(index);
                        top.layer.msg("修改失败，原因："+obj);
                    }, 500);                	
                }
            },
            complete: function(XMLHttpRequest, textStatus) {
                console.log(XMLHttpRequest);
                console.log(textStatus);
            },

        });

        return false;
    });
})