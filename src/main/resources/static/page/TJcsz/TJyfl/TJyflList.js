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
        elem: '#TJyflList',
        url : '/TJyfl',
        method: 'GET',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 10,
        limits : [10,15,20,25],
        id : "newsListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'ywcode', title: '业务代码',  align:"center"},
            {field: 'ywname', title: '业务名称', align:'center'},
            {field: 'jsfl', title: '经手费率', align:'center'},
            {field: 'gh', title: '过户费率',  align:'center'},
            {field: 'yh', title: '印花税率', align:'center'},
            {field: 'zg', title: '证管费率', align:'center'},
            {field: 'yj', title: '佣金费率', align:'center'},
            {title: '操作', width:170, templet:'#TJyflListBar',fixed:"right",align:"center"}
        ]]
    });
    //点击编辑操作
    function EditTJyfl(edit){
        var index = layui.layer.open({
            title : "修改费率",
            type : 2,
            content : "TZqxxEdit.html",
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
    table.on('tool(TJyflList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            EditTJyfl(data);
        }
    });

})