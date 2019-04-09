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
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //初始化表格
    searchUser();
    
    $('.search_btn').click(function(){	
    	
    });
       
    //新增账套
    function addTZtxx(){
        var index = layui.layer.open({
            title : "新建账套",
            type : 2,
            content : "TZtxxAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回账套列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    }
    
    //查询账套
    function searchUser(){
		var index=layer.load(1);
		var tableIns = table.render({
	        elem: '#TZtxxList',
	        url : "/TUser",
	        method: 'GET',
	        cellMinWidth : 95,
	        page : false,
	        height : "full-125",
	        limit : 10,
	        limits : [10,15,20,25],
	        id : "newsListTable",
	        cols : [[
	            {type: "checkbox", fixed:"left", width:50},
	            {field: 'ztbh', title: '账套编号',  align:"center",width:100},
	            {field: 'name', title: '账套名称', align:'center'},
	            {field: 'createdate', title: '创建时间', align:'center'},     
	            {title: '操作', width:170, templet:'#TZtxxListBar',fixed:"right",align:"center"}
	        ]]
	    });
		
		layer.close(index);
    }
    //点击编辑操作
    function EditZtxx(edit){
        var index = layui.layer.open({
            title : "修改账套信息",
            type : 2,
            content : "TZtxxEdit.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".ztbh").val(edit.ztbh);
                    body.find(".name").val(edit.name);
                    body.find(".createdate").val(edit.createdate);
                    body.find(".enddate").val(edit.enddate);
                    body.find(".jjdm").val(edit.jjdm);
                    body.find(".money").val(edit.money);
                    body.find(".number").val(edit.number);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回账套信息列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    }
    
  //删除操作
    function deleteZtxx(data){
    	var ztbh = data.ztbh;
    	var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
    	$.ajax({
            url: "/deleteTZtxx",
            data: {ztbh:ztbh},
            type: 'POST',
            success:function (obj) {
                if (obj==1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("账套删除成功！");
                        searchZtxx("");
                    }, 500);
                }else{
                	setTimeout(function () {
                		top.layer.close(index);
                        top.layer.msg("删除失败，原因："+obj);
                    }, 500);                	
                }
            },
            complete: function(XMLHttpRequest, textStatus) {
                console.log(XMLHttpRequest);
                console.log(textStatus);
            },

        });
    	
    }
    
    //列表中判断点击编辑操作
    table.on('tool(TZtxxList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            EditZtxx(data);
        }else if(layEvent === 'del'){
        	layer.confirm("确认删除该用户吗？",{icon: 3, title:'提示'}, function(index){
        		deleteZtxx(data);
        	});
        }
    });
    
  
})