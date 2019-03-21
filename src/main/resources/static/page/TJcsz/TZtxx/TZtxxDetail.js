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
    
    //关闭弹出层
    $('#closeBtn').click(function(){
    	var index = parent.layer.getFrameIndex(window.name); 
    	parent.layer.close(index);
    });
})