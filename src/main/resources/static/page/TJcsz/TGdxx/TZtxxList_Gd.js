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
    searchZtxx("");
    
    $('.search_btn').click(function(){
    	var ztbh=$("#ztbhSearch").val();  	
    	searchZtxx(ztbh);
    });
    //查询账套
    function searchZtxx(ztbh){
    	var path="/findTZtxxByZtbh";
    	if(ztbh==""){
    		path="/TZtxx";
    	}
		var index=layer.load(1);
		var tableIns = table.render({
	        elem: '#TZtxxList',
	        url : path,
	        method: 'GET',
	        where:{'ztbh':ztbh},
	        cellMinWidth : 95,
	        page : true,
	        height : "full-125",
	        limit : 10,
	        limits : [10,15,20,25],
	        id : "newsListTable",
	        cols : [[
	            {field: 'ztbh', title: '账套编号',  align:"center",width:150},
	            {field: 'name', title: '账套名称', align:'center',width:150},
	            {field: 'createdate', title: '创建时间', align:'center',width:250},
	            {field: 'enddate', title: '结束时间',  align:'center',width:250},
	            {field: 'jjdm', title: '基金代码', align:'center',width:200},
	            {field: 'money', title: '初始金额', align:'center',width:200},
	            {field: 'number', title: '初始份额', align:'center',width:200},
	            {title: '操作', width:114, templet:'#TZtxxListBar',fixed:"right",align:"center"}
	        ]]
	    });
		
		layer.close(index);
    }

    //选择账套
    function selectZt(data){
    	var ztbh = data.ztbh;
    	var name = data.name;
    	//拿到父窗口的$对象，利用jQuery为父窗口的input框赋值
    	var parent$ = window.parent.layui.jquery;
    	parent$("#ztbh").val(ztbh);
    	parent$("#name").val(ztbh+"_"+name);
    	var index = parent.layer.getFrameIndex(window.name); 
    	parent.layer.close(index);
    	
    }
      
    //列表中判断点击选择操作
    table.on('tool(TZtxxList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'checked'){ //选择
        	selectZt(data);
        }
    });
    
  
})