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
    
    $('.addTXwxx').click(function(){
    	addTXwxx();
    });
    
    //新增席位
    function addTXwxx(){
        var index = layui.layer.open({
            title : "添加席位",
            type : 2,
            content : "TXwxxAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回席位列表', '.layui-layer-setwin .layui-layer-close', {
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
	            {field: 'xwbh', title: '席位编号',  align:"center",width:100},
	            {field: 'xwName', title: '席位名称', align:'center'},
	            {field: 'qsbh', title: '券商编号', align:'center'},
	            {field: 'qsName', title: '券商名称',  align:'center'},
	            {title: '操作', width:170, templet:'#TXwxxListBar',fixed:"right",align:"center"}
	        ]]
	    });
		
		layer.close(index);
    }
    //点击编辑操作
    function EditXwxx(edit){
        var index = layui.layer.open({
            title : "修改席位信息",
            type : 2,
            content : "TXwxxEdit.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".xwbh").val(edit.xwbh);
                    body.find(".xwName").val(edit.xwName);
                    body.find(".qsbh").val(edit.qsbh);
                    body.find(".qsName").val(edit.qsName);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回席位信息列表', '.layui-layer-setwin .layui-layer-close', {
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
    
    
  //点击查看操作
    function showDetail(data){
        var index = layui.layer.open({
            title : "查看席位信息",
            type : 2,
            content : "TXwxxDetail.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(data){
                	body.find(".xwbh").val(data.xwbh);
                    body.find(".xwName").val(data.xwName);
                    body.find(".qsbh").val(data.qsbh);
                    body.find(".qsName").val(data.qsName);
                    form.render();
                }
                /*setTimeout(function(){
                    layui.layer.tips('点击此处返回账套信息列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)*/
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    }
    
  //删除操作
    function deleteXwxx(data){
    	var xwbh = data.xwbh;
    	var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
    	$.ajax({
            url: "/deleteTXwxx",
            data: {xwbh:xwbh},
            type: 'POST',
            success:function (obj) {
                if (obj==1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("席位删除成功！");
                        searchXwxx("");
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
    table.on('tool(TXwxxList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            EditXwxx(data);
        }else if(layEvent === 'detail'){
        	showDetail(data);
        }else if(layEvent === 'del'){
        	layer.confirm("确认删除该席位吗？",{icon: 3, title:'提示'}, function(index){
        		deleteXwxx(data);
        	});
        }
    });
    
  
})