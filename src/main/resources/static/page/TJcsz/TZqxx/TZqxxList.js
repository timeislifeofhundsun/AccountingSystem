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
        url : '/TZqxx',
        method: 'GET',
        cellMinWidth : 95,
        toolbar: '#TZqxxbar',
        request: {
            pageName: 'indexpage', //页码的参数名称，默认：page
            limitName: 'sizepage' //每页数据量的参数名，默认：limit
        },
        page : {
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'],
            groups: 5
        },
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
            {field: 'zqjg', title: '证券简称', align:'center'},
            {field: 'zgb', title: '总股本', align:'center',width:140},
            {field: 'ltgs', title: '流通股数', align:'center',width:140},
            {field: 'mgmz', title: '每股面值', align:'center'},
            {field: 'fxrq', title: '发行日期', align:'center',width:140},
            {field: 'dqrq', title: '到期日期', align:'center',width:140},
            {field: 'hgts', title: '回购天数', align:'center'},
            {field: 'njxts', title: '年计息天数', align:'center',width:140},
            {field: 'nll', title: '年利率', align:'center'},
            {field: 'qxr', title: '起息日', align:'center',width:140},
            {field: 'fxfs', title: '付息方式', align:'center',templet:"#fxfs"},
            {field: 'fxjg', title: '债券发行价格', align:'center',width:140},
            {title: '操作', width:170, templet:'#TZqxxListBar',fixed:"right",align:"center"}
        ]]
    });
    $(".addTZqxx").click(function(){
        addTZqxx();
    })
    //添加证券信息
    function addTZqxx(edit){
        var index = layui.layer.open({
            title : "添加证券",
            type : 2,
            content : "TZqxxAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回证券列表', '.layui-layer-setwin .layui-layer-close', {
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
    //点击编辑操作
    function EditTJyfl(edit){
        var index = layui.layer.open({
            title : "修改证券信息",
            type : 2,
            totalRow: true,
            content : "TJyflEdit.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".ywcode").val(edit.ywcode);
                    body.find(".ywname").val(edit.ywname);
                    body.find(".jsfl").val(edit.jsfl);
                    body.find(".gh").val(edit.gh);
                    body.find(".yh").val(edit.yh);
                    body.find(".zg").val(edit.zg);
                    body.find(".yj").val(edit.yj);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回费率列表', '.layui-layer-setwin .layui-layer-close', {
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
    table.on('tool(TZqxxList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            EditTJyfl(data);
        }
    });

})