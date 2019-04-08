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

            layui.layer.full(index);
             	setTimeout(function(){
                 layui.layer.tips('点击此处返回凭证页面', '.layui-layer-setwin .layui-layer-close', {
                     tips: 3
                 	});
             	},500)
     })

    /**
     * 日期
     */
    laydate.render({
        elem: '#rq' //指定元素
        ,value: '2018-05-30'
    });

    //生成凭证
    $(".addPz").click(function(){
        var rq = $("#rq").val();
        var ztbh = $("#ztbh").val();
        if(rq!=""){
            if(ztbh!=''){
                layer.msg('生成凭证中......');
                var request = {"rq":rq,"ztbh":ztbh};
                $.ajax({
                    type:"POST",
                    url:"/inerst_pz",
                    data:JSON.stringify(request),
                    contentType : "application/json",
                    success:function (data) {
                        console.log(data);
                        if(data.res){
                            layer.msg('生成成功!');
                        }else{
                            console.log(data.msg)
                            layer.msg(data.msg);
                        }
                    }
                });
            }else{
                layer.msg('温馨提示:请选择账套!');
            }
        }else{
            layer.msg('温馨提示:请选择业务日期!');
            }
    })

    //显示凭证
    $(".showPz").click(function(){
       /**
         * 表格
         */
        table.render({
            elem: '#data'
            ,url:'/get_pz/?rq='+$("#rq").val()+"&ztbh="+$("#ztbh").val()
            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'ztbh', title:'账套',  align:"center"}
                ,{field:'rq', title:'日期', align:"center"}
                ,{field:'kmmc', title:'科目名称', align:"center"}
                ,{field:'zy', title:'摘要', align:"center"}
                ,{field:'BS', title:'借贷', align:"center"}
                ,{field:'jfje', title:'借方金额', align:"center"}
                ,{field:'dfje', title:'贷方金额', align:"center"}
            ]]
            , done: function (res, curr, count) {// 表格渲染完成之后的回调
                    // $(".layui-table th").css("font-weight", "bold");// 设定表格标题字体加粗
                    LayUIDataTable.SetJqueryObj($);// 第一步：设置jQuery对象
                    var currentRowDataList = LayUIDataTable.ParseDataTable(function (index, currentData, rowData) {
                    })
                    LayUIDataTable.HideField('ztbh');// 隐藏列-单列模式
                    // 对相关数据进行判断处理--此处对mk2大于30的进行高亮显示
                    $.each(currentRowDataList, function (index, obj) {
                        if (obj['pzid'].value % 2 != 0) {
                            obj['ztbh'].row.css({"background-color": "#009966", 'color': '#99CCCC'});
                        }
                        if (obj['pzid'].value % 2 == 0) {
                            obj['ztbh'].row.css({"background-color": "#F7B940", 'color': '#CCFFFF'});
                        }
                    })
                }// end done
            ,page: false
        });
    })

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