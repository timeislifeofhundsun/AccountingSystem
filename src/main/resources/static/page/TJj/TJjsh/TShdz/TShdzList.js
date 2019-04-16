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
    searchShxx("");
    
    $('.select_btn').click(function(){
    	var index = layui.layer.open({
            title : "选择账套",
            type : 2,
            content : "TZtxxList_Dz.html",
        })
        
        layui.layer.full(index);   	
    	setTimeout(function(){
        layui.layer.tips('点击此处返回赎回确认列表', '.layui-layer-setwin .layui-layer-close', {
            tips: 3
        	});
    	},500)
    });
    
    $("#name").on('change', function(){
        var ztbh = $("#ztbh").val();
        searchShxx(ztbh);
    });
    
    
  //查询基金赎回信息
    function searchShxx(ztbh){
    	var path="/findShxxByZtbh";
    	if(ztbh==""){
    		path="/TShxx";
    	}
		var index=layer.load(1);
		var tableIns = table.render({
	        elem: '#TShdzList',
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
	            {field: 'ztbh',title: '账套编号',  align:"center",width:100},
	            {field: 'ztbhname', title: '账套名称',  align:"center",width:200},
	            {field: 'rq', title: '赎回日期', align:'center',width:150},
	            {field: 'zqcode', title: '证券代码', align:'center'},
	            {field: 'ywlb', title: '业务类别',  align:'center'},
	            {field: 'quantity', title: '赎回数量', align:'center',width:150},
	            {field: 'amount', title: '赎回金额', align:'center',width:150},
	            {field: 'jsf', title: '经手费', align:'center'},
	            {field: 'zgf', title: '证管费', align:'center'},
	            {field: 'zqqsk', title: '证券清算款', align:'center',width:200},
	            {field: 'yj', title: '佣金', align:'center'},
	            {title: '操作', width:170, templet:'#TShdzListBar',fixed:"right",align:"center"}
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
	        elem: '#TShdzList',
	        url : '/TShxxByDate',
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
	            {field: 'rq', title: '赎回日期', align:'center',width:150},
	            {field: 'zqcode', title: '证券代码', align:'center'},
	            {field: 'ywlb', title: '业务类别',  align:'center'},
	            {field: 'quantity', title: '赎回数量', align:'center',width:150},
	            {field: 'amount', title: '赎回金额', align:'center',width:150},
	            {field: 'jsf', title: '经手费', align:'center'},
	            {field: 'zgf', title: '证管费', align:'center'},
	            {field: 'zqqsk', title: '证券清算款', align:'center',width:200},
	            {field: 'yj', title: '佣金', align:'center'},
	            {title: '操作', width:170, templet:'#TShdzListBar',fixed:"right",align:"center"}
	        ]],
	        /*done: function () {
	            $("[data-field='id']").css('display','none');
	        }*/
	    });
		
		layer.close(index);
    }
    
    //点击确认操作
    function confirmShxx(edit){
        var index = layui.layer.open({
            title : "赎回到账确认",
            type : 2,
            content : "TShxxConfirm.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find(".id").val(edit.id);
                    body.find(".ztbh").val(edit.ztbh);
                    body.find(".zqcode").val(edit.zqcode);
                    body.find(".ywlb").val(edit.zqcode);
                    body.find(".rq").val(edit.rq);
                    body.find(".quantity").val(edit.quantity);
                    body.find(".amount").val(edit.amount);
                    body.find(".zqqsk").val(edit.zqqsk);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回赎回信息列表', '.layui-layer-setwin .layui-layer-close', {
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
    
    
    //列表中判断点击确认操作
    table.on('tool(TShdzList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //确认
        	confirmShxx(data);
        }
    });
    
  
})