$(function () {
    var header = $.cookie('header');
    var token = $.cookie('token');
    console.log(header);
    console.log(token);
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
})
layui.use(['form','layer','layedit','laydate','upload'],function(){
    var form = layui.form,
    layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    laydate.render({
        elem: '#createdate',
        format: 'yyyy-MM-dd'
    });
    laydate.render({
        elem: '#enddate',
        format: 'yyyy-MM-dd'
    });
    form.verify({
    	name : function(val){
            if(val == ''){
                return "账套名称不能为空";
            }
        },
        createdate : function(val){
            if(val == ''){
                return "开始日期不能为空";
            }
        },
        jjdm : function(val){
            if(val == ''){
                return "基金代码不能为空";
            }
        },
        money : function(val){
            if(val == ''){
                return "初始金额不能为空";
            }else if(val.length>10){
            	return "初始金额不能大于10位数";
            }
        },
        number : function(val){
            if(val == ''){
                return "初始份额不能为空";
            }else if(val.length>10){
            	return "初始份额不能大于10位数";
            }
        }
    })
    form.on("submit(AddTZtxx)",function(data){
        console.log(data.field);
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/TZtxx",
            data: {data:JSON.stringify(data.field)},
            type: 'POST',
            success:function (obj) {
                if (obj==1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("账套添加成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    }, 500);
                }else{
                	setTimeout(function () {
                		top.layer.close(index);
                        top.layer.msg("创建失败，原因："+obj);
                    }, 500);                	
                }
            },
            complete: function(XMLHttpRequest, textStatus) {
                console.log(XMLHttpRequest);
                console.log(textStatus);
            },

        });
        return false;
    })
})