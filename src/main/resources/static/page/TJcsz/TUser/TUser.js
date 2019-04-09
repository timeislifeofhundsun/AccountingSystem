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
    	addUser();
    });
       
    //新增用户
    function addUser(){
        var index = layui.layer.open({
            title : "添加用户",
            area: ['400px', '300px'], //宽高
            type : 2,
            
            content : "TUserAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                
            }
        })
     
    }
    
    //查询用户
    function searchUser(){
		var index=layer.load(1);
		var tableIns = table.render({
	        elem: '#TZtxxList',
	        url : "/TUser",
	        method: 'GET',
	        cellMinWidth : 95,
	        page : true,
	        height : "full-125",
	        limit : 10,
	        limits : [10,15,20,25],
	        id : "newsListTable",
	        cols : [[
	            {type: "checkbox", fixed:"left", width:50},
	            {field: 'uid', title: '用户ID',  align:"center"},
	            {field: 'username', title: '用户名',  align:"center"},
	            {field: 'password', title: '密码', align:'center'},
	            {field: 'email', title: '邮件', align:'center'},     
	            {title: '操作', width:170, templet:'#TZtxxListBar',fixed:"right",align:"center"}
	        ]]
	    });
		
		layer.close(index);
    }
    
  //删除用户
    function deleteUser(data){
    	var uid = data.uid;
    	var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
    	$.ajax({
            url: "/deleteUser",
            data: {uid:uid},
            type: 'POST',
            success:function (obj) {
                if (obj==1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("用户删除成功！");
                        searchUser("");
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
        if(layEvent === 'del'){
        	layer.confirm("确认删除该用户吗？",{icon: 3, title:'提示'}, function(index){
        		deleteUser(data);
        	});
        }
    });
    
  
})