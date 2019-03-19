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

    //账套信息列表渲染
    var tableIns = table.render({
        elem: '#TZtxxList',
        url : '/TZtxx',
        method: 'GET',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        toolbar: 'default',
        limit : 10,
        limits : [10,15,20,25],
        id : "newsListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'ztbh', title: '账套编号',  align:"center"},
            {field: 'name', title: '账套名称', align:'center'},
            {field: 'createdate', title: '创建时间', align:'center'},
            {field: 'enddate', title: '结束时间',  align:'center'},
            {field: 'jjdm', title: '基金代码', align:'center'},
            {field: 'money', title: '初始金额', align:'center'},
            {field: 'number', title: '初始份额', align:'center'},
            {title: '操作', width:170, templet:'#TZtxxListBar',fixed:"right",align:"center"}
        ]]
    });
    //点击编辑操作
    function EditZtxx(edit){
        var index = layui.layer.open({
            title : "修改账套信息",
            type : 2,
            content : "TZtxxEdit.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".ztbh").val(edit.ztbh);
                    body.find(".name").val(edit.name);
                    body.find(".createdate").val(edit.createdate);
                    body.find(".enddate").val(edit.enddate);
                    body.find(".jjdm").val(edit.jjdm);
                    body.find(".money").val(edit.money);
                    body.find(".number").val(edit.number);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回账套信息列表', '.layui-layer-setwin .layui-layer-close', {
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
    table.on('tool(TZtxxList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            EditZtxx(data);
        }
    });
    
  //监听头工具栏事件
    table.on('toolbar(TZtxxList)', function(obj){
      var checkStatus = table.checkStatus(obj.config.id)
      ,data = checkStatus.data; //获取选中的数据
      switch(obj.event){
        case 'add':
          layer.msg('添加');
        break;
        case 'update':
          if(data.length === 0){
            layer.msg('请选择一行');
          } else if(data.length > 1){
            layer.msg('只能同时编辑一个');
          } else {
            layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
          }
        break;
        case 'delete':
          if(data.length === 0){
            layer.msg('请选择一行');
          } else {
            layer.msg('删除');
          }
        break;
      };
    });

})