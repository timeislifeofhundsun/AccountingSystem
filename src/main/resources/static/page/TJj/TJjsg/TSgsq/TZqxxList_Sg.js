/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明 
 * ========    =======  ============================================
 * 2019/3/19  wanggk25832  新增
 * ========    =======  ============================================
 */
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

    //费率设置列表渲染
    var tableIns = table.render({
        elem: '#TZqxxList',
        url : '/TZqxx_Hq',
        method: 'GET',
        cellMinWidth : 95,
        request: {
            pageName: 'indexpage', //页码的参数名称，默认：page
            limitName: 'sizepage' //每页数据量的参数名，默认：limit
        },
        page : true,
        height : "full-125",
        limit : 10,
        limits : [10,15,20,25],
        id : "newsListTable",
        cols : [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'zqnm', title: '证券内码',  align:"center"},
            {field: 'zqdm', title: '证券代码', align:'center'},
            {field: 'zqlb', title: '证券类别', align:'center',templet:"#zqlb"},
            {field: 'sclb', title: '市场类别',  align:'center',templet:"#sclb",width:140},
            {field: 'zqjg', title: '证券简称', align:'center',width:250},
            {field: 'fxrq', title: '发行日期', align:'center',width:140},
            {field: 'dqrq', title: '到期日期', align:'center',width:140},
            {field: 'njxts', title: '年计息天数', align:'center',width:140},
            {title: '操作', width:170, templet:'#TZqxxListBar',fixed:"right",align:"center"}
        ]]
    });
    $(".addTZqxx").click(function(){
        addTZqxx();
    })
  
  //选择操作
    function selectTZqxx(data){
    	var zqdm = data.zqdm;
    	var zqmc = data.zqjg;
    	//拿到父窗口的$对象，利用jQuery为父窗口的input框赋值
    	var parent$ = window.parent.layui.jquery;
    	parent$("#zqcode").val(zqdm);
    	parent$("#zqmc").val(zqmc);
    	var index = parent.layer.getFrameIndex(window.name); 
    	parent.layer.close(index);
    	
    }
    //列表中判断点击编辑操作
    table.on('tool(TZqxxList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent == 'selectZq'){ //选择
            selectTZqxx(data);
        }
    });

})