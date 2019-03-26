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

    laydate.render({
        elem: '#datetime',
        format: 'yyyy-MM-dd',
        done: function(value, date){
            serachByDate(value);
        }
    });
    
    
    //初始化表格
    searchHqxx("");
    
    $('.search_btn').click(function(){
    	var zqdm=$("#zqdmSearch").val();  	
    	searchHqxx(zqdm);
    });
    
    $('.addTHqxx').click(function(){	
    	addHqxx();
    });
    
  //新增基金行情信息
    function addHqxx(){
        var index = layui.layer.open({
            title : "添加基金行情信息",
            type : 2,
            content : "THqszAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                /*setTimeout(function(){
                    layui.layer.tips('点击此处返回行情信息列表', '.layui-layer-setwin .layui-layer-close', {
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
    
  //根据日期查询基金行情信息
    function serachByDate(date){
		var index=layer.load(1);
		var tableIns = table.render({
	        elem: '#THqbxxList',
	        url : '/findByDate',
	        method: 'GET',
	        where:{'date':date},
	        cellMinWidth : 95,
	        page : true,
	        height : "full-125",
	        limit : 10,
	        limits : [10,15,20,25],
	        id : "newsListTable",
	        cols : [[
	            {type: "checkbox", fixed:"left", width:50},
	            {field: 'id',title: '编号', align: 'center'},
	            {field: 'zqdm', title: '证券代码',  align:"center"},
	            {field: 'zqmc', title: '证券名称', align:'center',width:250},
	            {field: 'hqrq', title: '行情日期', align:'center'},
	            {field: 'zrspj', title: '昨日收盘价',  align:'center'},
	            {field: 'jrkp', title: '今日开盘价', align:'center'},
	            {field: 'jrsp', title: '今日收盘价', align:'center'},
	            {field: 'cjje', title: '货币基金万份收益', align:'center'},
	            {title: '操作', width:170, templet:'#THqbxxListBar',fixed:"right",align:"center"}
	        ]],
	        done: function () {
	            $("[data-field='id']").css('display','none');
	        }
	    });
		
		layer.close(index);
    }
    
    
    //查询基金行情信息
    function searchHqxx(zqdm){
    	var path="/findTHqbxxByZqdm";
    	if(zqdm==""){
    		path="/THqbxx";
    	}
		var index=layer.load(1);
		var tableIns = table.render({
	        elem: '#THqbxxList',
	        url : path,
	        method: 'GET',
	        where:{'zqdm':zqdm},
	        cellMinWidth : 95,
	        page : true,
	        height : "full-125",
	        limit : 10,
	        limits : [10,15,20,25],
	        id : "newsListTable",
	        cols : [[
	            {type: "checkbox", fixed:"left", width:50},
	            {field: 'id',title: '编号', align: 'center'},
	            {field: 'zqdm', title: '证券代码',  align:"center"},
	            {field: 'zqmc', title: '证券名称', align:'center',width:250},
	            {field: 'hqrq', title: '行情日期', align:'center'},
	            {field: 'zrspj', title: '昨日收盘价',  align:'center'},
	            {field: 'jrkp', title: '今日开盘价', align:'center'},
	            {field: 'jrsp', title: '今日收盘价', align:'center'},
	            {field: 'cjje', title: '货币基金万份收益', align:'center'},
	            {title: '操作', width:170, templet:'#THqbxxListBar',fixed:"right",align:"center"}
	        ]],
	        /*done: function () {
	            $("[data-field='id']").css('display','none');
	        }*/
	    });
		
		layer.close(index);
    }
    //点击编辑操作
    function EditHqxx(edit){
        var index = layui.layer.open({
            title : "修改基金行情信息",
            type : 2,
            content : "THqszEdit.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find(".id").val(edit.id);
                    body.find(".zqdm").val(edit.zqdm);
                    body.find(".zqmc").val(edit.zqmc);
                    body.find(".hqrq").val(edit.hqrq);
                    body.find(".zrspj").val(edit.zrspj);
                    body.find(".jrkp").val(edit.jrkp);
                    body.find(".jrsp").val(edit.jrsp);
                    body.find(".cjje").val(edit.cjje);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回基金行情信息列表', '.layui-layer-setwin .layui-layer-close', {
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
    
    
    //列表中判断点击编辑操作
    table.on('tool(THqbxxList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            EditHqxx(data);
        }
    });
    
  
})