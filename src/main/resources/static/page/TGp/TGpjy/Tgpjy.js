layui.use(['form','table','laydate'],function(){
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var $ = layui.$
    /**
     *账套
     */

    $('.select_btn').click(function(){
    	var index = layui.layer.open({
            title : "选择账套",
            type : 2,
            content : "../../../common/TZtxx.html",
        })
        
        layui.layer.full(index);   	
    	setTimeout(function(){
        layui.layer.tips('点击此处返回股东信息列表', '.layui-layer-setwin .layui-layer-close', {
            tips: 3
        	});
    	},500)
    });
    
    /**
     * 日期
     */
    laydate.render({
        elem: '#ywrq' //指定元素
        ,value: '2018-05-30'
    });


    $("#queryBtn").click(function () {
        /**
         * 表格
         */
        table.render({
            elem: '#data'
            ,url:'/rest/gpRest/gpjy?ywrq='+$("#ywrq").val()+"&ztbh="+$("#ztbh").val()
            ,title: '用户数据表'
            ,cols: [[
                {field:'rq', title:'日期'}
                ,{field:'zqmc', title:'证券名称'}
                ,{field:'bs', title:'买卖方向'}
                ,{field:'zqdm', title:'证券代码'}
                ,{field:'cjje', title:'成交金额'}
                ,{field:'cjsl', title:'成交数量'}
                ,{field:'yhs', title:'印花费'}
                ,{field:'jsf', title:'经手费'}
                ,{field:'ghf', title:'过户费'}
                ,{field:'zgf', title:'证管费'}
                ,{field:'yj', title:'佣金'}
            ]]
            ,page: false
        });
    })


});
