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
    searchXwxx("");
    
    $('.search_btn').click(function(){
    	var xwbh=$("#xwbhSearch").val();  	
    	searchXwxx(xwbh);
    });
    
    //查询席位
    function searchXwxx(xwbh){
    	var path="/findTXwxxByXwbh";
    	if(xwbh==""){
    		path="/TXwxx";
    	}
		var index=layer.load(1);
		var tableIns = table.render({
	        elem: '#TXwxxList',
	        url : path,
	        method: 'GET',
	        where:{'xwbh':xwbh},
	        cellMinWidth : 95,
	        page : true,
	        height : "full-125",
	        limit : 10,
	        limits : [10,15,20,25],
	        id : "newsListTable",
	        cols : [[
	            {type: "checkbox", fixed:"left", width:50},
	            {field: 'xwbh', title: '席位编号',  align:"center",width:200},
	            {field: 'xwName', title: '席位名称', align:'center',width:200},
	            {field: 'qsbh', title: '券商编号', align:'center',width:200},
	            {field: 'qsName', title: '券商名称',  align:'center',width:200},
	            {title: '操作', width:200, templet:'#TXwxxListBar',fixed:"right",align:"center"}
	        ]]
	    });
		
		layer.close(index);
    }
    
  //选择操作
    function selectXwxx(data){
    	var xwbh = data.xwbh;
    	var name = data.xwName;
    	//拿到父窗口的$对象，利用jQuery为父窗口的input框赋值
    	var parent$ = window.parent.layui.jquery;
    	parent$("#xwbh").val(xwbh);
    	parent$("#xwName").val(xwbh+"_"+name);
    	var index = parent.layer.getFrameIndex(window.name); 
    	parent.layer.close(index);
    	
    }
    
    //列表中判断点击选择操作
    table.on('tool(TXwxxList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'checked'){ //选择
            selectXwxx(data);
        }
    });
    
  
})