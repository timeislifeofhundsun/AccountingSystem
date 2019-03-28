/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明 
 * ========    =======  ============================================
 * 2019/3/25  wanggk25832  新增
 * ========    =======  ============================================
 */
$(function () {
    var header = $.cookie('header');
    var token = $.cookie('token');
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

layui.use(['form', 'layer', 'layedit', 'laydate', 'upload'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    //计算到期日期
    function Addtime(day) {
        var Holiday = ["2019/4/5", "2019/5/1", "2019/5/2", "2019/5/3", "2019/6/7", "2019/9/13",
            "2019/10/1", "2019/10/2", "2019/10/3", "2019/10/4", "2019/10/7"
        ];
        var Workday = ["2019/4/28", "2019/5/5", "2019/9/29", "2019/10/12"];
        var nowTime = new Date().getTime(); //当天的时间戳
        var t, ymd, i = 0;
        while (i != day) {
            i++;
            dayTime = i * 24 * 60 * 60 * 1000;
            t = new Date(nowTime + dayTime);
            var week = t.getDay();
            var ymd = t.toLocaleDateString();
            if ((week == 6 || week == 0) && $.inArray(ymd, Workday) != 0) {
                day++;
            } else if ($.inArray(ymd, Workday) == 0) {
            } else if ($.inArray(ymd, Holiday) == 0) {
                day++;
            } else {
            }
        }
        return ymd;
    }

    form.on("select", function (data) {
        if (data.elem.id == "zqcode") {
            var text = this.innerText.substr(1, this.innerText.length - 1);
            var days = text.replace(/\b(0+)/gi, "");
            $("#quantity").val(days);
            $('#quantity').attr("disabled", true);
            var myDate = new Date();
            var date = myDate.toLocaleDateString();
            var re = new RegExp("/", "g");
            var newdate = date.replace(re, "-");
            var dqdate = Addtime(days);
            var newdqdate = dqdate.replace(re, "-");
            laydate.render({
                elem: '#extenda',
                format: 'yyyy-MM-dd',
                value: newdate
            });
            $('#extenda').prop('disabled', true);
            laydate.render({
                elem: '#extendb',
                format: 'yyyy-MM-dd',
                value: newdqdate
            });
            $('#extendb').prop('disabled', true);
            $("#sclb").val(3);
            $('#sclb').prop('disabled', true);
            form.render('select');
        } else if (data.elem.id == "ztbh") {
            $.ajax({
                url: "/getByZtbh",
                type: "GET",
                data: {ztbh: data.value},
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        $("#extendd").append("<option value='" + data[i].gddm + "'>" + data[i].gddm + "</option>");
                    }
                    form.render('select');
                }
            });
        }
    });
    laydate.render({
        elem: '#extenda',
        format: 'yyyy-MM-dd'
    });
    laydate.render({
        elem: '#extendb',
        format: 'yyyy-MM-dd'
    });
    //获取账套
    $.ajax({
        url: "/getTZtxxList",
        type: "GET",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#ztbh").append("<option value='" + data[i].ztbh + "'>" + data[i].name + "</option>");
            }

            form.render('select');
        }
    });
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
    });
    $("#amount").change(function () {
        if ($("#amount").val() == "") {
            $("#cjsr").val("");
            $("#jsf").val("");
            $("#ghf").val("");
        } else {
            var jylv, jslv;
            $.ajax({
                url: "/TLfjxb",
                type: "GET",
                success: function (data) {
                    if ($("#yhs").val() != "") {
                        if (Number($("#yhs").val()) < Number($("#amount").val())) {
                            $("#amount").val("");

                            layer.msg('成交金额必须小于到期金额', {
                                time: 1000, //20s后自动关闭
                            });
                            return;
                        } else {
                            $("#cjsr").val(Number($("#yhs").val() * 1) - Number($("#amount").val() * 1));

                        }
                    }
                    jylv = data.jylv;
                    jslv = data.jslv;
                    $("#jsf").val(Number(($("#amount").val())) * Number(jslv));
                    $("#ghf").val(Number(($("#amount").val())) * Number(jylv));
                    $("#jsf").prop("disabled", true);
                    $("#ghf").prop("disabled", true);
                }
            });
        }
    });
    $("#yhs").change(function () {
        if ($("#yhs").val() == "") {
            $("#cjsr").val("");
        } else {
            if ($("#amount").val() == "") {
                layer.msg('请输入成交金额', {
                    time: 1000, //20s后自动关闭
                });
                return;
            } else {
                if (Number($("#yhs").val()) < Number($("#amount").val())) {
                    $("#cjsr").val("");
                    $("#yhs").val("");
                    layer.msg('到期金额必须大于成交金额', {
                        time: 1000, //20s后自动关闭
                    });
                    return;
                } else {
                    $("#cjsr").val(Number($("#yhs").val() * 1) - Number($("#amount").val() * 1));
                }
            }
        }
    });
    form.verify({
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
        yhs: function (val) {
            if (val == '') {
                return "到期金额不能为空";
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
    form.on("submit(AddTYmdshg)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/TYmdshg",
            data: {TYmdshg: JSON.stringify(data.field)},
            type: 'POST',
            success: function (obj) {
                if (obj == 1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("银行买断式回购添加成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    }, 500);
                }
            }
        });
        return false;
    })
});