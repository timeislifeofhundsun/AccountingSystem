$(function () {
    var header = $.cookie('header');
    var token = $.cookie('token');
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
})
layui.use(['form','layer','layedit','laydate','upload'],function(){
    var form = layui.form,
    layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    laydate.render({
        elem: '#fxrq',
        format: 'yyyy-MM-dd'
    });
    laydate.render({
        elem: '#dqrq',
        format: 'yyyy-MM-dd'
    });
    laydate.render({
        elem: '#qxr',
        format: 'yyyy-MM-dd'
    });
    form.on("select(zqlb)",function (data) {
        console.log(data);
    });
    form.verify({
        zqnm : function(val){
            if(val == ''){
                return "证券内码不能为空";
            }
        },
        zqdm : function(val){
            if(val == ''){
                return "证券代码不能为空";
            }
        },
        zqlb : function(val){
            if(val == ''){
                return "证券类别不能为空";
            }
        },
        sclb : function(val){
            if(val == ''){
                return "市场类别不能为空";
            }
        },
        zqjg : function(val){
            if(val == ''){
                return "证券简称不能为空";
            }
        },
        zgb : function(val){
            if(val == ''){
                return "总股本不能为空";
            }
        },
        ltgs : function(val){
            if(val == ''){
                return "流通股数不能为空";
            }
        },
        mgmz : function(val){
            if(val == ''){
                return "面值不能为空";
            }
        },
        fxrq : function(val){
            if(val == ''){
                return "发行日期不能为空";
            }
        },
        dqrq : function(val){
            if(val == ''){
                return "到期日期不能为空";
            }
        },
        hgts : function(val){
            if(val == ''){
                return "回购天数不能为空";
            }
        },
        njxts : function(val){
            if(val == ''){
                return "年计息天数不能为空";
            }
        },
        nll : function(val){
            if(val == ''){
                return "年利率不能为空";
            }
        },
        qxr : function(val){
            if(val == ''){
                return "起息日不能为空";
            }
        },
        fxfs : function(val){
            if(val == ''){
                return "付款方式不能为空";
            }
        },
        fxjg : function(val){
            if(val == ''){
                return "发行价格不能为空";
            }
        }
    })
    form.on("submit(AddTZqxx)",function(data){
        console.log(data.field);
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "/TZqxx",
            data: {TZqxx:JSON.stringify(data.field)},
            type: 'POST',
            success:function (obj) {
                if (obj==1) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("证券添加成功！");
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
    })
})