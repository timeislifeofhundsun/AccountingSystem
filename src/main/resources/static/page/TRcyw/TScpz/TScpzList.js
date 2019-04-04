//获取cookie中的tokenlayui-form
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

     /**
         * 账套选择
         */
     $('.select_btn').click(function(){
         var index = layui.layer.open({
            title : "选择账套",
            type : 2,
             content : "../../../common/TZtxx.html",
         })
     })

    //费率设置列表渲染
    var tableIns = table.render({
        elem: '#TZqxxList',
        url : '/TZqxx',
        method: 'GET',
        cellMinWidth : 95,
        request: {
            pageName: 'indexpage', //页码的参数名称，默认：page
            limitName: 'sizepage' //每页数据量的参数名，默认：limit
        },

        cols : [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'ztbh', title: '账套',  align:"center"},
            {field: 'rq', title: '日期', align:'center'},
            {field: 'kmms', title: '科目描述', align:'center',templet:"#zqlb"},
            {field: 'zy', title: '摘要',  align:'center',templet:"#sclb",width:140},
            {field: 'bs', title: '借贷', align:'center'},
            {field: 'je', title: '借方金额', align:'center',width:140},
            {field: 'je', title: '贷方金额', align:'center',width:140},
            {title: '操作', width:170, templet:'#TZqxxListBar',fixed:"right",align:"center"}
        ]]
    });

    $(".addPz").click(function(){
        addPz();
    })

    //添加证券信息
    function addPz(edit){
        var index = layui.layer.open({
            title : "添加凭证",
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
    function EditTZqxx(edit){
        var index = layui.layer.open({
            title : "修改证券信息",
            type : 2,
            totalRow: true,
            content : "TZqxxEdit.html",
            success : function(layero, index){
                var ZqxxFrom = layer.getChildFrame('', index);
                var iframeWindow = layero.find('iframe')[0].contentWindow;
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".zqnm").val(edit.zqnm);
                    body.find(".zqdm").val(edit.zqdm);
                    body.find(".zqlb").val(edit.zqlb);
                    body.find(".sclb").val(edit.sclb);
                    body.find(".zqjg").val(edit.zqjg);
                    body.find(".zgb").val(edit.zgb);
                    body.find(".ltgs").val(edit.ltgs);
                    body.find(".mgmz").val(edit.mgmz);
                    body.find(".fxrq").val(edit.fxrq);
                    body.find(".dqrq").val(edit.dqrq);
                    body.find(".hgts").val(edit.hgts);
                    body.find(".njxts").val(edit.njxts);
                    body.find(".nll").val(edit.nll);
                    body.find(".qxr").val(edit.qxr);
                    body.find(".fxfs").val(edit.fxfs);
                    body.find(".fxjg").val(edit.fxjg);
                    layui.use(['form'], function(){
                        iframeWindow.layui.form.render();
                    });
                    form.render();
                }
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

    //点击查看操作
    function showDetail(data){
        var index = layui.layer.open({
            title : "查看证券信息",
            type : 2,
            content : "TZqxxDetail.html",
            success : function(layero, index){
                var ZqxxFrom = layer.getChildFrame('', index);
                var iframeWindow = layero.find('iframe')[0].contentWindow;
                var body = layui.layer.getChildFrame('body', index);
                console.log(data);
                if(data){
                    body.find(".zqnm").val(data.zqnm);
                    body.find(".zqdm").val(data.zqdm);
                    body.find(".zqlb").val(data.zqlb);
                    body.find(".sclb").val(data.sclb);
                    body.find(".zqjg").val(data.zqjg);
                    body.find(".zgb").val(data.zgb);
                    body.find(".ltgs").val(data.ltgs);
                    body.find(".mgmz").val(data.mgmz);
                    body.find(".fxrq").val(data.fxrq);
                    body.find(".dqrq").val(data.dqrq);
                    body.find(".hgts").val(data.hgts);
                    body.find(".njxts").val(data.njxts);
                    body.find(".nll").val(data.nll);
                    body.find(".qxr").val(data.qxr);
                    body.find(".fxfs").val(data.fxfs);
                    body.find(".fxjg").val(data.fxjg);
                    layui.use(['form'], function(){
                        iframeWindow.layui.form.render();
                    });
                    form.render();
                }
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
    };

    //列表中判断点击编辑操作
    table.on('tool(TZqxxList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent == 'edit'){ //编辑
            EditTZqxx(data);
        }else if (layEvent=='delete'){
            console.log(data.zqnm);
            layer.confirm('确定删除此证券？',{icon:3, title:'提示信息'},function(index){
                $.ajax({
                    url: "/TZqxx",
                    data: {zqnm: JSON.stringify(data.zqnm),_method:"DELETE"},
                    type: 'POST',
                    success:function (obj) {
                        if (obj==1) {
                            tableIns.reload();
                            layer.close(index);
                        }
                    },
                });
            });
        }else if(layEvent === 'detail'){
        showDetail(data);
    }
    });

})