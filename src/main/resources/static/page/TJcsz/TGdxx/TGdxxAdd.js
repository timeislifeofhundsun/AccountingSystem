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
    	gddm : function(val){
            if(val == ''){
                return "股东代码不能为空";
            }
        },
        name : function(val){
            if(val == ''){
                return "股东名称不能为空";
            }
        },
        xwbh : function(val){
            if(val == ''){
                return "席位编号不能为空";
            }
        }
    })
    
    $('.selectXwxx').click(function(){
    	var index = layui.layer.open({
            title : "选择席位",
            type : 2,
            content : "TXwxxList_Gd.html"
        })
        
        layui.layer.full(index);   	
    	setTimeout(function(){
        layui.layer.tips('点击此处返回股东添加列表', '.layui-layer-setwin .layui-layer-close', {
            tips: 3
        	});
    	},500)
    });
    
    form.on("submit(AddTGdxx)",function(data){
        console.log(data.field);
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/TGdxx",
            data: {data:JSON.stringify(data.field)},
            type: 'POST',
            success:function (obj) {
                if (obj==1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("股东添加成功！");
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