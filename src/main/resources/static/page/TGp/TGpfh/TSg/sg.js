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
            content : "../../../../common/TZtxx.html",
        })
        
        layui.layer.full(index);   	
    	setTimeout(function(){
        layui.layer.tips('点击此处返回股票交易', '.layui-layer-setwin .layui-layer-close', {
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
            ,url:'/rest/gpRest/hg?ywrq='+$("#ywrq").val()+"&ztbh="+$("#ztbh").val()+"&ywlb=1203"
            ,title: '用户数据表'
            ,cols: [[
                {field:'rq', title:'登记日期'}
                ,{field:'extenda', title:'送股日期'}
                ,{field:'zqmc', title:'证券名称'}
                ,{field:'zqdm', title:'证券代码'}
                ,{field:'cjsl', title:'送股数量'}
            ]]
            ,page: false
        });
    })


});
