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
    searchSgsq("");
    
    $('.select_btn').click(function(){
    	var index = layui.layer.open({
            title : "选择账套",
            type : 2,
            content : "TZtxxList_Sg.html",
        })
        
        layui.layer.full(index);   	
    	setTimeout(function(){
        layui.layer.tips('点击此处返回申购申请列表', '.layui-layer-setwin .layui-layer-close', {
            tips: 3
        	});
    	},500)
    });
    
    $("#name").on('change', function(){
        var ztbh = $("#ztbh").val();
        searchSgsq(ztbh);
    });
    
    
  //查询基金行情信息
    function searchSgsq(ztbh){
    	var path="/findTQsbByZqdm";
    	if(ztbh==""){
    		path="/TQsb";
    	}
		var index=layer.load(1);
		var tableIns = table.render({
	        elem: '#TSgsqList',
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
	            {field: 'id',title: '编号', align: 'center'},
	            {field: 'ztbh', title: '账套编号',  align:"center"},
	            {field: 'rq', title: '清算日期', align:'center',width:150},
	            {field: 'zqcode', title: '证券代码', align:'center'},
	            {field: 'ywlb', title: '业务类别',  align:'center'},
	            {field: 'quantity', title: '成交数量', align:'center',width:150},
	            {field: 'amount', title: '成交金额', align:'center',width:150},
	            {field: 'jsf', title: '经手费', align:'center'},
	            {field: 'ghf', title: '过户费', align:'center'},
	            {field: 'zgf', title: '证管费', align:'center'},
	            {field: 'yj', title: '佣金', align:'center'},
	            {title: '操作', width:170, templet:'#TSgsqListBar',fixed:"right",align:"center"}
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
	        elem: '#TSgsqList',
	        url : '/TQsbByDate',
	        method: 'GET',
	        where:{'date':value},
	        cellMinWidth : 95,
	        page : true,
	        height : "full-125",
	        limit : 10,
	        limits : [10,15,20,25],
	        id : "newsListTable",
	        cols : [[
	            {type: "checkbox", fixed:"left", width:50},
	            {field: 'id',title: '编号', align: 'center'},
	            {field: 'ztbh', title: '账套编号',  align:"center"},
	            {field: 'rq', title: '清算日期', align:'center',width:150},
	            {field: 'zqcode', title: '证券代码', align:'center'},
	            {field: 'ywlb', title: '业务类别',  align:'center'},
	            {field: 'quantity', title: '成交数量', align:'center',width:150},
	            {field: 'amount', title: '成交金额', align:'center',width:150},
	            {field: 'jsf', title: '经手费', align:'center'},
	            {field: 'ghf', title: '过户费', align:'center'},
	            {field: 'zgf', title: '证管费', align:'center'},
	            {field: 'yj', title: '佣金', align:'center'},
	            {title: '操作', width:170, templet:'#TSgsqListBar',fixed:"right",align:"center"}
	        ]],
	        /*done: function () {
	            $("[data-field='id']").css('display','none');
	        }*/
	    });
		
		layer.close(index);
    }
    
    //点击编辑操作
    function confirmSgsq(edit){
        var index = layui.layer.open({
            title : "申购申请确认",
            type : 2,
            content : "TSgsqConfirm.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find(".id").val(edit.id);
                    body.find(".ztbh").val(edit.ztbh);
                    body.find(".zqcode").val(edit.zqcode);
                    body.find(".rq").val(edit.rq);
                    body.find(".quantity").val(edit.quantity);
                    body.find(".amount").val(edit.amount);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回申购申请列表', '.layui-layer-setwin .layui-layer-close', {
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
    table.on('tool(TSgsqList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //确认
            confirmSgsq(data);
        }
    });
    
  
})