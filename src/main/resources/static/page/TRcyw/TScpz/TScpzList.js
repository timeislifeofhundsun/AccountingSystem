//获取cookie中的tokenlayui-form
$(function () {
    var header = $.cookie('header');
    var token = $.cookie('token');
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
})
layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    /**
     * 账套选择
     */
    $('.select_btn').click(function () {
        var index = layui.layer.open({
            title: "选择账套",
            type: 2,
            content: "../../../common/TZtxx.html",
        })

        layui.layer.full(index);
        setTimeout(function () {
            layui.layer.tips('点击此处返回凭证页面', '.layui-layer-setwin .layui-layer-close', {
                tips: 3
            });
        }, 500)
    })

    /**
     * 日期
     */
    laydate.render({
        elem: '#rq' //指定元素
        , value: '2018-05-30'
    });

    //生成凭证
    $(".addPz").click(function () {
    	var ztbh = $("#ztbh").val();
    	if(ztbh==""){
            layer.msg('请先选择账套', {
                time: 1000, //2s后自动关闭
            });
    		return;
    	}
    	
        var rq = $("#rq").val();
        var ztbh = $("#ztbh").val();
        if (rq != "") {
            if (ztbh != '') {
                layer.msg('生成凭证中......');
                var request = {"rq": rq, "ztbh": ztbh};
                $.ajax({
                    type: "POST",
                    url: "/inerst_pz",
                    data: JSON.stringify(request),
                    contentType: "application/json",
                    success: function (data) {
                        console.log(data);
                        if (data.res) {
                            layer.msg('生成成功!');
                        } else {
                            console.log(data.msg)
                            layer.msg(data.msg);
                        }
                    }
                });
            } else {
                layer.msg('温馨提示:请选择账套!');
            }
        } else {
            layer.msg('温馨提示:请选择业务日期!');
        }
    })

    //显示凭证
    $(".showPz").click(function () {
        /**
         * 表格
         */
    	var ztbh = $("#ztbh").val();
    	if(ztbh==""){
            layer.msg('请先选择账套', {
                time: 1000, //2s后自动关闭
            });
    		return;
    	}  
        table.render({
            elem: '#data'
            , url: '/get_pz/?rq=' + $("#rq").val() + "&ztbh=" + $("#ztbh").val()
            , title: '用户数据表'
            , cellMinWidth: 95
            , cols: [[
                {field: 'pzid', title: '凭证编号', align: "center"}
                , {field: 'ztbh', title: '账套', align: "center"}
                , {field: 'rq', title: '日期', align: "center"}
                , {field: 'kmmc', title: '科目名称', align: "center"}
                , {field: 'zy', title: '摘要', align: "center", width: "35%"}
                , {field: 'BS', title: '借贷', align: "center"}
                , {field: 'jfje', title: '借方金额', align: "center"}
                , {field: 'dfje', title: '贷方金额', align: "center"}
            ]]
            , done: function (res, curr, count) {
                // 渲染行颜色
                var curr = 0;
                var index = 0;
                var color = "#D2E9FF";
                $.each(res.data, function () {
                    var tr = $(".layui-table tbody").find("tr").eq(index);
                    if (curr == 0) {
                        curr = this.pzid;
                        tr.css("background", color);
                    } else {
                        if (curr == this.pzid) {
                            tr.css("background", color);
                        } else {
                            if (color == "#D2E9FF") {
                                color = "white";
                            } else {
                                color = "#D2E9FF";
                            }
                            tr.css("background", color);
                            curr = this.pzid;
                        }
                    }
                    index++;
                });
                //hover事件
                $('.layui-table-body').find("table").find("tbody").children("tr").hover(function(){
                    color = $(this).css("background");
                        $(this).css("background", "#F0F0F0");
                },function(){
                    $(this).css("background", color);

                });
                //双击查看事件
                $('.layui-table-body').find("table").find("tbody").children("tr").on('dblclick', function () {
                    var id = JSON.stringify($('.layui-table-body').find("table").find("tbody").find(".layui-table-hover").data('index'));
                    var obj = res.data[id];
                    var pzid = res.data[id].pzid;
                    var rows = new Array();
                    for (var i = 0; i< count ; i++){
                        if (res.data[i].pzid == pzid) {
                            rows.push(res.data[i]);
                        }
                    }
                    layui.layer.open({
                        title:"查看凭证",
                        skin:"layui-layer-demo",
                        type: 2,
                        area: ['800px', '400px'],
                        shadeClose: true, //开启遮罩关闭
                        content: "TScpzDetail.html",
                        success: function (layero, index) {
                            var iframe = window['layui-layer-iframe' + index];
                            iframe.getValue(JSON.stringify(rows));
                            setTimeout(function () {
                                layui.layer.tips('点击此处返回凭证列表', '.layui-layer-setwin .layui-layer-close', {
                                    tips: 3
                                });
                            }, 500)
                        }
                    });
                })
            }
            , page: false
        });
    })

    table.on('row(data)', function (obj) {
        var data = obj.data;

        layer.alert(JSON.stringify(data), {
            title: '当前行数据：'
        });

        //标注选中样式
        obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
    });

})