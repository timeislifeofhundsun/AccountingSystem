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

    form.verify({
    	xwbh : function(val){
            if(val == ''){
                return "席位编号不能为空";
            }else if(isNaN(val)){
            	return "请输入数字";
            }else if(val.length>10){
            	return "席位编号不能大于10位";
            }
        },
        xwName : function(val){
            if(val == ''){
                return "席位名称不能为空";
            }
        },
        qsbh : function(val){
            if(val == ''){
                return "券商编号不能为空";
            }else if(isNaN(val)){
            	return "请输入数字";
            }else if(val.length>10){
            	return "券商编号不能大于10位";
            }
        },
        qsName : function(val){
            if(val == ''){
                return "券商名称不能为空";
            }
        }
    })
    form.on("submit(AddTXwxx)",function(data){
        console.log(data.field);
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/TXwxx",
            data: {data:JSON.stringify(data.field)},
            type: 'POST',
            success:function (obj) {
                if (obj==1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("席位添加成功！");
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