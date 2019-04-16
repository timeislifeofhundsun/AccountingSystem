//获取cookie中的tokenlayui-form
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
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

     /**
         * 账套选择
         */
     $('.select_btn').click(function(){
         var index = layui.layer.open({
            title : "选择账套",
            type : 2,
             content : "../../../common/TZtxx.html",
         })

            layui.layer.full(index);
             	setTimeout(function(){
                 layui.layer.tips('点击此处返回凭证页面', '.layui-layer-setwin .layui-layer-close', {
                     tips: 3
                 	});
             	},500)
     })

    //显示报表
    $(".showBb").click(function(){
       /**
         * 表格
         */
    	var ztbh = $("#ztbh").val();
    	if(ztbh==""){
            layer.msg('请先选择账套', {
                time: 1000, //2s后自动关闭
            });
    		return;
    	}

        table.render({
                    elem: '#data'
                    ,url:'/get_bb/?ztbh='+$("#ztbh").val()
                    ,title: '用户数据表'
                    ,cols: [[
                        {type: 'checkbox', fixed: 'left'}
                        ,{field:'kmmc', title:'科目名称'}
                        ,{field:'sl', title:'数量',style:'color: red;'}
                        ,{field:'zqcb', title:'成本',style:'color: red;'}
                        ,{field:'ljgz', title:'估值增值',style:'color: red;'}
                        ,{field:'ljjx', title:'累计计息', style:'color: red;'}
                    ]]
                    ,page: false
                    
                });
    })
})