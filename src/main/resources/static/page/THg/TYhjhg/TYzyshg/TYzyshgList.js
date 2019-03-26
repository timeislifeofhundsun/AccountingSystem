/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明 
 * ========    =======  ============================================
 * 2019/3/25  wanggk25832  新增
 * ========    =======  ============================================
 */
//获取cookie中的token
$(function () {
    var header = $.cookie('header');
    var token = $.cookie('token');
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

layui.use(['form','layer','laydate','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        table = layui.table;
    laydate.render({
        elem: '#ckrq',
        format: 'yyyy-MM-dd',
    });
    var myDate = new Date();
    var date = myDate.toLocaleDateString();
    console.log(date);
    var re=new RegExp("/","g");
    var newdate=date.replace(re,"-");
    console.log(newdate);
    //费率设置列表渲染
    var tableIns = table.render({
        elem: '#TYzyshgList',
        url : '/TYzyshg',
        method: 'GET',
        cellMinWidth : 95,
        request: {
            pageName: 'indexpage', //页码的参数名称，默认：page
            limitName: 'sizepage' //每页数据量的参数名，默认：limit
        },
        where:{
            ckrq : newdate
        },
        page : true,
        height : "full-125",
        limit : 10,
        limits : [10,15,20,25],
        id : "TYzyshgList",
        cols : [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'ztbh', title: '账套编号',  align:"center"},
            {field: 'zqcode', title: '回购代码', align:'center'},
            {field: 'bs', title: '回购方向', align:'center',templet:"#zqlb"},
            {field: 'quantity', title: '回购天数',  align:'center',templet:"#sclb",width:140},
            {field: 'lumpsum', title: '结算机构', align:'center'},
            {field: 'extenda', title: '交易日期', align:'center',width:140},
            {field: 'extendb', title: '到期日期', align:'center',width:140},
            {field: 'amount', title: '成交金额', align:'center'},
            {field: 'yhs', title: '到期金额', align:'center',width:140},
            {field: 'zgf', title: '券面总额', align:'center',width:140},
            {field: 'jsf', title: '结算手续费', align:'center'},
            {field: 'ghf', title: '交易手续费', align:'center',width:140},
            {field: 'yj', title: '结算方式', align:'center'},
            {field: 'extendd', title: '资金账号', align:'center',width:140},
            {title: '操作', width:170, templet:'#TYzyshgListBar',fixed:"right",align:"center"}
        ]],
        done:function () {
            laydate.render({
                elem: '#ckrq',
                format: 'yyyy-MM-dd',
            });
        }
    });
    $(".addTYzyshg").click(function(){
        addTYzyshg();
    })
    //添加证券信息
    function addTYzyshg(edit){
        var index = layui.layer.open({
            title : "添加质押式回购",
            type : 2,
            content : "TYzyshgAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回质押式回购列表', '.layui-layer-setwin .layui-layer-close', {
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
    function EditTYzyshg(edit){
        var index = layui.layer.open({
            title : "修改质押式回购信息",
            type : 2,
            totalRow: true,
            content : "TYzyshgEdit.html",
            success : function(layero, index){
                var ZqxxFrom = layer.getChildFrame('', index);
                var iframeWindow = layero.find('iframe')[0].contentWindow;
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".ztbh").val(edit.ztbh);
                    body.find(".zqcode").val(edit.zqcode);
                    body.find(".bs").val(edit.bs);
                    body.find(".quantity").val(edit.quantity);
                    body.find(".lumpsum").val(edit.lumpsum);
                    body.find(".extenda").val(edit.extenda);
                    body.find(".extendb").val(edit.extendb);
                    body.find(".amount").val(edit.amount);
                    body.find(".yhs").val(edit.yhs);
                    body.find(".zgf").val(edit.zgf);
                    body.find(".jsf").val(edit.jsf);
                    body.find(".ghf").val(edit.ghf);
                    body.find(".yj").val(edit.yj);
                    body.find(".extendd").val(edit.extendd);
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
            content : "TYzyshgDetail.html",
            success : function(layero, index){
                var ZqxxFrom = layer.getChildFrame('', index);
                var iframeWindow = layero.find('iframe')[0].contentWindow;
                var body = layui.layer.getChildFrame('body', index);
                console.log(data);
                if(data){
                    body.find(".ztbh").val(edit.ztbh);
                    body.find(".zqcode").val(edit.zqcode);
                    body.find(".bs").val(edit.bs);
                    body.find(".quantity").val(edit.quantity);
                    body.find(".lumpsum").val(edit.lumpsum);
                    body.find(".extenda").val(edit.extenda);
                    body.find(".extendb").val(edit.extendb);
                    body.find(".amount").val(edit.amount);
                    body.find(".yhs").val(edit.yhs);
                    body.find(".zgf").val(edit.zgf);
                    body.find(".jsf").val(edit.jsf);
                    body.find(".ghf").val(edit.ghf);
                    body.find(".yj").val(edit.yj);
                    body.find(".extendd").val(edit.extendd);
                    layui.use(['form'], function(){
                        iframeWindow.layui.form.render();
                    });
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回质押式回购列表', '.layui-layer-setwin .layui-layer-close', {
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
    table.on('tool(TYzyshgList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent == 'edit'){ //编辑
            EditTYzyshg(data);
        }else if (layEvent=='delete'){
            console.log(data.zqnm);
            layer.confirm('确定删除此回购？',{icon:3, title:'提示信息'},function(index){
                $.ajax({
                    url: "/TYzyshg",
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


    var $ = layui.$, active = {
        reload: function(){
            //执行重载
            table.reload('TYzyshgList', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    ckrq : document.getElementById("ckrq").value
                }
            });
        }
    };

    $('#ckrqbtn').on('click', function(){
        // console.log(document.getElementById("ckrq").value);
        // table.reload('TYzyshgList', {
        //     page: {
        //         curr: 1 //重新从第 1 页开始
        //     }
        //     ,where: {
        //         ckrq : document.getElementById("ckrq").value
        //     }
        // });
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

})