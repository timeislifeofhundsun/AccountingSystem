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
    searchGdxx("");
    
    //选择账套
    $('.search_btn').click(function(){	
    	var index = layui.layer.open({
            title : "选择账套",
            type : 2,
            content : "TZtxxList_Gd.html",
        })
        
        layui.layer.full(index);   	
    	setTimeout(function(){
        layui.layer.tips('点击此处返回股东信息列表', '.layui-layer-setwin .layui-layer-close', {
            tips: 3
        	});
    	},500)
    	
    });
    
    //查询股东
    $('.selectTGdxx').click(function(){
    	var ztbh=$('#ztbh').val();
    	searchGdxx(ztbh);
    });
    var ztbh;
    $('.addTGdxx').click(function(){
    	ztbh=$('#ztbh').val();
    	if(ztbh==""){
            layer.msg('请先选择股东', {
                time: 1000, //2s后自动关闭
            });
    		return;
    	}    	
    	addTGdxx(ztbh);
    });
    
    
    //新增股东
    function addTGdxx(ztbh){
        var index = layui.layer.open({
            title : "添加股东",
            type : 2,
            content : "TGdxxAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                body.find(".ztbh").val(ztbh);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回股东列表', '.layui-layer-setwin .layui-layer-close', {
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
    
    //查询股东
    function searchGdxx(ztbh){
    	var path="/findByZtbh";
    	if(ztbh==""){
    		path="/TGdxx";
    	}
		var index=layer.load(1);
		var tableIns = table.render({
	        elem: '#TGdxxList',
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
	            {type: "checkbox", fixed:"left", width:50},
	            {field: 'gddm', title: '股东代码',  align:"center",width:250},
	            {field: 'ztbh', title: '账套编号', align:'center'},
	            {field: 'name', title: '股东账户名称', align:'center'},
	            {field: 'xwbh', title: '席位编号',  align:'center'},
	            {title: '操作', width:170, templet:'#TGdxxListBar',fixed:"right",align:"center"}
	        ]]
	    });
		
		layer.close(index);
    }
    //点击编辑操作
    function EditGdxx(edit){
        var index = layui.layer.open({
            title : "修改股东信息",
            type : 2,
            content : "TGdxxEdit.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".gddm").val(edit.gddm);
                    body.find(".ztbh").val(edit.ztbh);
                    body.find(".name").val(edit.name);
                    body.find(".xwbh").val(edit.xwbh);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回股东信息列表', '.layui-layer-setwin .layui-layer-close', {
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
            title : "查看股东信息",
            type : 2,
            content : "TGdxxDetail.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(data){
                	body.find(".gddm").val(data.gddm);
                    body.find(".ztbh").val(data.ztbh);
                    body.find(".name").val(data.name);
                    body.find(".xwbh").val(data.xwbh);
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
    function deleteGdxx(data){
    	var gddm = data.gddm;
    	var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
    	$.ajax({
            url: "/deleteTGdxx",
            data: {gddm:gddm},
            type: 'POST',
            success:function (obj) {
                if (obj==1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("股东删除成功！");
                        searchGdxx("");
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
    table.on('tool(TGdxxList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            EditGdxx(data);
        }else if(layEvent === 'detail'){
        	showDetail(data);
        }else if(layEvent === 'del'){
        	layer.confirm("确认删除该股东信息吗？",{icon: 3, title:'提示'}, function(index){
        		deleteGdxx(data);
        	});
        }
    });
    
  
})