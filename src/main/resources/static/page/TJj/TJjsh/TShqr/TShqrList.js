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
    
    
    /*
     * 初始化表格，根据业务类别从清算表里面查出数据，并展示到table里面
     * 申购申请的业务类别有两种：非货币基金申购（4201），货币基金申购（4101）
     * */
    searchShqr("");
    
    $('.select_btn').click(function(){
    	var index = layui.layer.open({
            title : "选择账套",
            type : 2,
            content : "TZtxxList_Sh.html",
        })
        
        layui.layer.full(index);   	
    	setTimeout(function(){
        layui.layer.tips('点击此处返回持仓列表', '.layui-layer-setwin .layui-layer-close', {
            tips: 3
        	});
    	},500)
    });
    
    $("#name").on('change', function(){
        var ztbh = $("#ztbh").val();
        searchShqr(ztbh);
    });
    
    
  //查询持仓信息
    function searchShqr(ztbh){
    	var path="/findTShqrByZtbh";
    	if(ztbh==""){
    		path="/TShqr";
    	}
		var index=layer.load(1);
		var tableIns = table.render({
	        elem: '#TShqrList',
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
	            {field: 'id',title: '编号', align: 'center',width:80},
	            {field: 'ztbh', title: '账套编号',  align:"center"},
	            {field: 'fsrq', title: '发生日期', align:'center',width:150},
	            {field: 'zqdm', title: '证券代码', align:'center'},
	            {field: 'cysl', title: '持有数量', align:'center',width:150},
	            {field: 'zqcb', title: '证券成本', align:'center',width:150},
	            {field: 'extenda', title: '基金信息', align:'center',width:250},
	            {title: '操作', width:170, templet:'#TShqrListBar',fixed:"right",align:"center"}
	        ]],
	        /*done: function () {
	            $("[data-field='id']").css('display','none');
	        }*/
	    });
		
		layer.close(index);
    }
    
    
    function serachByDate(value){
    	var index=layer.load(1);
		var tableIns = table.render({
	        elem: '#TShqrList',
	        url : '/TShqr',
	        method: 'POST',
	        where:{'date':value},
	        cellMinWidth : 95,
	        page : true,
	        height : "full-125",
	        limit : 10,
	        limits : [10,15,20,25],
	        id : "newsListTable",
	        cols : [[
	        	{type: "checkbox", fixed:"left", width:50},
	            {field: 'id',title: '编号', align: 'center',width:80},
	            {field: 'ztbh', title: '账套编号',  align:"center"},
	            {field: 'fsrq', title: '发生日期', align:'center',width:150},
	            {field: 'zqdm', title: '证券代码', align:'center'},
	            {field: 'cysl', title: '持有数量', align:'center',width:150},
	            {field: 'zqcb', title: '证券成本', align:'center',width:150},
	            {field: 'extenda', title: '基金信息', align:'center',width:250},
	            {title: '操作', width:170, templet:'#TShqrListBar',fixed:"right",align:"center"}
	        ]],
	        /*done: function () {
	            $("[data-field='id']").css('display','none');
	        }*/
	    });
		
		layer.close(index);
    }
    
    //点击赎回操作
    function ccSh(edit){
        var index = layui.layer.open({
            title : "赎回确认",
            type : 2,
            content : "TShConfirm.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find(".id").val(edit.id);
                    body.find(".ztbh").val(edit.ztbh);
                    body.find(".zqdm").val(edit.zqdm);
                    body.find(".fsrq").val(edit.fsrq);
                    body.find(".cysl").val(edit.cysl);
                    body.find(".zqcb").val(edit.zqcb);
                    body.find(".extenda").val(edit.extenda);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回赎回列表', '.layui-layer-setwin .layui-layer-close', {
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
    table.on('tool(TShqrList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //赎回
            ccSh(data);
        }
    });
    
  
})