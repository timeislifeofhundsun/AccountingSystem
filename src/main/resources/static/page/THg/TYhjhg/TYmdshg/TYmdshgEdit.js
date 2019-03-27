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
})
//获取父页面的传值
function getValue(data) {
    layui.use(['form', 'layer', 'laydate'], function () {
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer,
            laydate = layui.laydate,
            $ = layui.jquery;
        $.when($.ajax({
            url: "/getTZtxxList",
            type: "GET",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#ztbh").append("<option value='" + data[i].ztbh + "'>" + data[i].name + "</option>");
                }

                form.render('select');
            }
        }),
        $.ajax({
            url: "/TZqxxList",
            type: "GET",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].zqlb == 3 && data[i].sclb == 3) {
                        $("#zqcode").append("<option value='" + data[i].zqdm + "'>" + data[i].zqjg + "</option>");
                    }
                }
                form.render('select');
            }
        }),
        $.ajax({
            url: "/getByZtbh",
            type: "GET",
            data: {ztbh: data.ztbh},
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#extendd").append("<option value='" + data[i].gddm + "'>" + data[i].gddm + "</option>");
                }
                form.render('select');
            }
        })).then(function (){
            laydate.render({
                elem: '#extenda',
                format: 'yyyy-MM-dd',
                value:data.extenda
            });
            $('#extenda').prop('disabled', true);
            laydate.render({
                elem: '#extendb',
                format: 'yyyy-MM-dd',
                value:data.extendb
            });
            $('#extendb').prop('disabled', true);
            $("#id").val(data.id);
            $('#id').prop('disabled', true);
            $("#sclb").val(data.sclb);
            $('#sclb').prop('disabled', true);
            $("#ztbh").val(data.ztbh);
            $('#ztbh').prop('disabled', true);
            $("#zqcode").val(data.zqcode);
            $('#zqcode').prop('disabled', true);
            $("#bs").val(data.bs);
            $('#bs').prop('disabled', true);
            $("#lumpsum").val(data.lumpsum);
            $('#lumpsum').prop('disabled', true);
            $("#amount").val(data.amount);
            $('#amount').prop('disabled', true);
            $("#cjsr").val(data.cjsr);
            $('#cjsr').prop('disabled', true);
            $("#yhs").val(data.yhs);
            $('#yhs').prop('disabled', true);
            $("#zgf").val(data.zgf);
            $("#quantity").val(data.quantity);
            $('#quantity').prop('disabled', true);
            $("#jsf").val(data.jsf);
            $("#ghf").val(data.ghf);
            $("#extendd").val(data.extendd);
            $("#yj").val(data.yj);
            form.render();
        })
    });
}
layui.use(['form', 'layer', 'laydate'], function () {
    var form = layui.form;
    var layer = parent.layer === undefined ? layui.layer : top.layer;
    var $ = layui.jquery;
    laydate = layui.laydate;
    //获取账套

    form.verify({
        id: function (val) {
            if (val == '') {
                return "ID不能为空";
            }
        },
        ztbh: function (val) {
            if (val == '') {
                return "账套编号不能为空";
            }
        },
        zqcode: function (val) {
            if (val == '') {
                return "回购品种不能为空";
            }
        },
        bs: function (val) {
            if (val == '') {
                return "回购方向不能为空";
            }
        },
        lumpsum: function (val) {
            if (val == '') {
                return "结算机构不能为空";
            }
        },
        amount: function (val) {
            if (val == '') {
                return "成交金额不能为空";
            }
        },
        jsf: function (val) {
            if (val == '') {
                return "结算手续费不能为空";
            }
        },
        ghf: function (val) {
            if (val == '') {
                return "交易手续费不能为空";
            }
        },
        sclb: function (val) {
            if (val == '') {
                return "交易市场不能为空";
            }
        },
        extendd: function (val) {
            if (val == '') {
                return "资金账号不能为空";
            }
        },
        yj: function (val) {
            if (val == '') {
                return "结算方式不能为空";
            }
        }
    })
    //提交更改证券信息
    form.on("submit(EditTYmdshg)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/TYmdshg",
            data: {TYmdshg: JSON.stringify(data.field)},
            type: 'PUT',
            success: function (obj) {
                if (obj == 1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("回购信息更改成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    }, 500);
                }
            }
        });
        return false;
    });
})