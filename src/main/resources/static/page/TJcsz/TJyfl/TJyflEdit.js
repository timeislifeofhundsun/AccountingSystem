//获取cookie中的token
$(function () {
    var header = $.cookie('header');
    var token = $.cookie('token');
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
})
var form, $;

layui.use(['form', 'layer'], function () {
    form = layui.form;
    var layer = parent.layer === undefined ? layui.layer : top.layer;
    $ = layui.jquery;
    form .verify({
        jsfl: function (val) {
            if (val == '') {
                return "交易费率不能为空";
            } else if (val > 1) {
                return "交易费率不能大于1";
            }
        },
        gh: function (val) {
            if (val == '') {
                return "过户费率不能为空";
            } else if (val > 1) {
                return "过户费率不能大于1";
            }
        },
        yh: function (val) {
            if (val == '') {
                return "印花费率不能为空";
            } else if (val > 1) {
                return "印花费率不能大于1";
            }
        },
        zg: function (val) {
            if (val == '') {
                return "证管费率不能为空";
            } else if (val > 1) {
                return "证管费率不能大于1";
            }
        },
        yj: function (val) {
            if (val == '') {
                return "佣金费率不能为空";
            } else if (val > 1) {
                return "佣金费率不能大于1";
            }
        },
    });
    //提交更改费率
    form.on("submit(updateTJyfl)", function (data) {
        top.layer.msg(JSON.stringify(data.field));
        console.log(data);
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/TJyfl",
            data: {TJyfl:JSON.stringify(data.field)},
            type: 'PUT',
            success:function (obj) {
                if (obj==1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("费率更改成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    }, 500);
                }
            },
            complete: function(XMLHttpRequest, textStatus) {
                console.log(XMLHttpRequest);
                console.log(textStatus);
            },

        });

        return false;
    });
})