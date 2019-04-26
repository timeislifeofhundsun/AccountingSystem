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
        elem: '#rq',
        format: 'yyyy-MM-dd',
    });
    
    form .verify({
    	ztbh: function (val) {
            if (val == '') {
                return "账套信息不能为空";
            }
        },
        zqcode: function (val) {
            if (val == '') {
                return "证券代码不能为空";
            }
        },
        zqmc: function (val) {
            if (val == '') {
                return "证券名称不能为空";
            }
        },
        rq: function (val) {
            if (val == '') {
                return "申购日期不能为空";
            }
        },
        quantity: function (val) {
            if (val == '') {
                return "申购份额不能为空";
            }
        },
        amount: function (val) {
            if (val == '') {
                return "申购金额不能为空";
            }else if(isNaN(val)){
            	return "请输入数字";
            }else if(val > 10000000000){
            	return "申购金额过大！";
            }
        }        
    });
    
    $('.selectZqxx').click(function(){
    	var index = layui.layer.open({
            title : "选择证券",
            type : 2,
            content : "TZqxxList_Sg.html"
        })
        
        layui.layer.full(index);   	
    });
    
    
    //提交更改账套信息
    form.on("submit(addTSgsq)", function (data) {
        top.layer.msg(JSON.stringify(data.field));
        console.log(data);
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/TQsb",
            data: {Sgsq:JSON.stringify(data.field)},
            type: 'POST',
            success:function (obj) {
                if (obj==1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("申购成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    }, 500);
                }else{
                	setTimeout(function () {
                		top.layer.close(index);
                        top.layer.msg("申购失败，原因："+obj);
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