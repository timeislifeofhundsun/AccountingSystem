//获取cookie中的token
$(function () {
    var header = $.cookie('header');
    var token = $.cookie('token');
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
})
var form, $;

layui.use(['form', 'layer','laydate'], function () {
    form = layui.form;
    laydate = layui.laydate;
    var layer = parent.layer === undefined ? layui.layer : top.layer;
    $ = layui.jquery;
    form .verify({
    	zrspj: function (val) {
            if (val == '') {
                return "昨日收盘价不能为空";
            }
        },
        jrkp: function (val) {
            if (val == '') {
                return "今日开盘价不能为空";
            }
        },
        jrsp: function (val) {
            if (val == '') {
                return "今日收盘价不能为空";
            }
        },
        cjje: function (val) {
            if (val == '') {
                return "货币基金万份收益不能为空";
            }
        }
    });
    
    //提交更改账套信息
    form.on("submit(updateTHqxx)", function (data) {
        top.layer.msg(JSON.stringify(data.field));
        console.log(data);
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/THqxx",
            data: {Hqxx:JSON.stringify(data.field)},
            type: 'PUT',
            success:function (obj) {
                if (obj==1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("行情信息更改成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    }, 500);
                }else{
                	setTimeout(function () {
                		top.layer.close(index);
                        top.layer.msg("修改失败，原因："+obj);
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