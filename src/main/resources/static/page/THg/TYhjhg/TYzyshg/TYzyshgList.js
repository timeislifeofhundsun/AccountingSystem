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
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

layui.use(['form', 'layer', 'laydate', 'table'], function () {
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
    var re = new RegExp("/", "g");
    var newdate = date.replace(re, "-");
    var dates = newdate.split("-");
    if (dates[1] < 10) {
        dates[1] = "0" + dates[1];
    }
    if (dates[2] < 10) {
        dates[2] = "0" + dates[2];
    }
    newdate = dates[0] + "-" + dates[1] + "-" + dates[2];
    //费率设置列表渲染
    var tableIns = table.render({
        elem: '#TYzyshgList',
        url: '/TYzyshg',
        method: 'GET',
        cellMinWidth: 95,
        request: {
            pageName: 'indexpage', //页码的参数名称，默认：page
            limitName: 'sizepage' //每页数据量的参数名，默认：limit
        },
        where: {
            ckrq: newdate
        },
        page: true,
        height: "full-125",
        limit: 10,
        limits: [10, 15, 20, 25],
        id: "TYzyshgList",
        cols: [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'id', title: 'ID', align: "center", hide: true},
            {field: 'ztbhname', title: '账套编号', align: "center",width:140},
            {field: 'zqcodename', title: '回购代码', align: 'center',width:140},
            {field: 'bs', title: '回购方向', align: 'center', templet: "#bs"},
            {field: 'quantity', title: '回购天数', align: 'center', width: 140},
            {field: 'lumpsum', title: '结算机构', align: 'center', templet: "#lumpsum"},
            {field: 'extenda', title: '交易日期', align: 'center', width: 140},
            {field: 'extendb', title: '到期日期', align: 'center', width: 140},
            {field: 'amount', title: '成交金额', align: 'center'},
            {field: 'yhs', title: '到期金额', align: 'center', width: 140},
            {field: 'zgf', title: '券面总额', align: 'center', width: 140},
            {field: 'jsf', title: '结算手续费', align: 'center', width: 140},
            {field: 'ghf', title: '交易手续费', align: 'center', width: 140},
            {field: 'yj', title: '结算方式', align: 'center', templet: "#yj"},
            {field: 'extendd', title: '资金账号', align: 'center', width: 140},
            {title: '操作', width: 170, templet: '#TYzyshgListBar', fixed: "right", align: "center"}
        ]],
        done: function () {
            laydate.render({
                elem: '#ckrq',
                format: 'yyyy-MM-dd',
            });
        }
    });
    $(".addTYzyshg").click(function () {
        addTYzyshg();
    })

    //添加证券信息
    function addTYzyshg(edit) {
        var index = layui.layer.open({
            title: "新增质押式回购",
            type: 2,
            content: "TYzyshgAdd.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function () {
                    layui.layer.tips('点击此处返回质押式回购列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(index);
        })
    }

    //点击编辑操作
    function EditTYzyshg(edit) {
        var index = layui.layer.open({
            title: "修改质押式回购信息",
            type: 2,
            totalRow: true,
            content: "TYzyshgEdit.html",
            success: function (layero, index) {
                var iframe = window['layui-layer-iframe' + index];
                iframe.getValue(edit);
                setTimeout(function () {
                    layui.layer.tips('点击此处返回回购列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(index);
        })
    }

    //点击查看操作
    function showDetail(data) {
        var index = layui.layer.open({
            title: "查看回购信息",
            type: 2,
            content: "TJzyshgDetail.html",
            success: function (layero, index) {
                var iframe = window['layui-layer-iframe' + index];
                iframe.getValue(data);
                setTimeout(function () {
                    layui.layer.tips('点击此处返回质押式回购列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(index);
        })
    };
    //列表中判断点击编辑操作
    table.on('tool(TYzyshgList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent == 'edit') { //编辑
            EditTYzyshg(data);
        } else if (layEvent == 'delete') {
            layer.confirm('确定删除此回购？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: "/TYzyshg",
                    data: {id: JSON.stringify(data.id), _method: "DELETE"},
                    type: 'POST',
                    success: function (obj) {
                        if (obj == 1) {
                            var type = "reload";
                            active[type] ? active[type].call(this) : '';
                            layer.close(index);
                        } else if (obj == 101) {
                            top.layer.msg("银行存款不足！");
                        } else if (obj == 102) {
                            top.layer.msg("数据处理失败！");
                        } else if (obj == 103) {
                            top.layer.msg("金额扣款或增额失败！");
                        } else if (obj == 104) {
                            top.layer.msg("数据库没有数据！");
                        } else {
                            top.layer.msg("操作失败，请稍后再试！");
                        }
                    },
                });
            });
        } else if (layEvent === 'detail') {
            showDetail(data);
        }
    });


    var $ = layui.$, active = {
        reload: function () {
            var keyword = $('#keyword').val();
            var reloaddate;
            if (document.getElementById("ckrq").value == "") {
                reloaddate = newdate;
            } else {
                reloaddate = document.getElementById("ckrq").value;
            }
            console.log(reloaddate);
            //执行重载
            table.reload('TYzyshgList', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    ckrq: reloaddate,
                    keyword:keyword
                }
            });
        }
    };

    $('#ckrqbtn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    $("#search").click(function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

})