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
        format: 'yyyy-MM-dd'
    });
    
    
    /*
     * 初始化表格，根据业务类别从清算表里面查出数据，并展示到table里面
     * 申购申请的业务类别有两种：非货币基金申购（4201），货币基金申购（4101）
     * */
    searchCcsj("");
    
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
        searchCcsj(ztbh);
    });
    
    $(".jjgz_btn").click(function(){
    	var datetime = $("#datetime").val();   	
    	if(datetime == ""){
    		layer.msg('请先选择时间', {
                time: 1000, //1s后自动关闭
            });
    		return;
    	}
    	jjgz(datetime);
    });
    
  //查询持仓信息
    function searchCcsj(ztbh){
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
	            {field: 'ljgz', title: '累计估增（红利）', align:'center',width:250}
	           /* {title: '操作', width:170, templet:'#TShqrListBar',fixed:"right",align:"center"}*/
	        ]],
	        /*done: function () {
	            $("[data-field='id']").css('display','none');
	        }*/
	    });
		
		layer.close(index);
    }
    
    jjgz_btn
    
    //点击估值操作
    function jjgz(date){
    	var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
    	$.ajax({
            url: "/Tjjgz",
            data: {date:date},
            type: 'POST',
            success:function (obj) {
                if (obj==1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("当天的基金估值成功！");
                        searchCcsj("");
                    }, 500);
                }else{
                	setTimeout(function () {
                		top.layer.close(index);
                        top.layer.msg("估值失败，原因："+obj);
                    }, 500);                	
                }
            },
            complete: function(XMLHttpRequest, textStatus) {
                console.log(XMLHttpRequest);
                console.log(textStatus);
            },

        });
    }
    
    
    /*//列表中判断点击编辑操作
    table.on('tool(TShqrList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //赎回
            ccSh(data);
        }
    });*/
    
  
})