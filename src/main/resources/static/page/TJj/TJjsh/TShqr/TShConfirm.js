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
        elem: '#fsrq',
        format: 'yyyy-MM-dd',
    });
    
    var tcysl;
    
    form .verify({
        cysl: function (val) {
            tcysl = val;
        }, 
        fsrq: function (val) {
        	if (val == '') {
        		return "发生日期不能为空";
        	}
            
        },
        shfe: function (val) {
            if (val == '') {
                return "赎回份额不能为空";
            }
            var num = val - tcysl;
            if(num>0){
            	return "赎回份额不能大于持有数量";
            }
        }        
    });
       
    //提交确认信息
    form.on("submit(confirmTSh)", function (data) {
        top.layer.msg(JSON.stringify(data.field));
        console.log(data);
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/TConfirmTSh",
            data: {shxx:JSON.stringify(data.field)},
            type: 'POST',
            success:function (obj) {
                if (obj==1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("赎回成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    }, 500);
                }else{
                	setTimeout(function () {
                		top.layer.close(index);
                        top.layer.msg("赎回失败，原因："+obj);
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