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

})
layui.use(['form', 'layer', 'layedit', 'laydate', 'upload'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;
    form.on("select", function(data){
        console.log(data.elem.id); //得到select原始DOM对象
        if (data.elem.id=="zqcode") {
            console.log(data.value);
            console.log(this.innerHTML);
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
   /* $('#zqcode').change(function(){
        console.log($(this).children('option:selected').val());
    })
    $('#zqcode').click(function(){
        console.log($(this).children('option:selected').val());
    })*/
   form.on("select(jsfs)",function (data) {
       console.log(1111);
       console.log(data);
   });
    form.on("select(bs)",function (data) {
        console.log(1111);
        console.log(data);
    });
    form.verify({
        ztbh: function (val) {
            if (val == '') {
                return "证券内码不能为空";
            }
        },
        zqcode: function (val) {
            if (val == '') {
                return "证券代码不能为空";
            }
        },
        zqlb: function (val) {
            if (val == '') {
                return "证券类别不能为空";
            }
        },
        sclb: function (val) {
            if (val == '') {
                return "市场类别不能为空";
            }
        },
        zqjg: function (val) {
            if (val == '') {
                return "证券简称不能为空";
            }
        },
        zgb: function (val) {
            if (val == '') {
                return "总股本不能为空";
            }
        },
        ltgs: function (val) {
            if (val == '') {
                return "流通股数不能为空";
            }
        },
        mgmz: function (val) {
            if (val == '') {
                return "面值不能为空";
            }
        },
        fxrq: function (val) {
            if (val == '') {
                return "发行日期不能为空";
            }
        },
        dqrq: function (val) {
            if (val == '') {
                return "到期日期不能为空";
            }
        },
        hgts: function (val) {
            if (val == '') {
                return "回购天数不能为空";
            }
        },
        njxts: function (val) {
            if (val == '') {
                return "年计息天数不能为空";
            }
        },
        nll: function (val) {
            if (val == '') {
                return "年利率不能为空";
            }
        },
        qxr: function (val) {
            if (val == '') {
                return "起息日不能为空";
            }
        },
        fxfs: function (val) {
            if (val == '') {
                return "付款方式不能为空";
            }
        },
        fxjg: function (val) {
            if (val == '') {
                return "发行价格不能为空";
            }
        }
    })
    form.on("submit(AddTYzyshg)", function (data) {
        console.log(data.field);
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/TYzyshg",
            data: {TYzyshg: JSON.stringify(data.field)},
            type: 'POST',
            success: function (obj) {
                if (obj == 1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("回购添加成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    }, 500);
                }
            },
            complete: function (XMLHttpRequest, textStatus) {
                console.log(XMLHttpRequest);
                console.log(textStatus);
            }
        });
        return false;
    })
});