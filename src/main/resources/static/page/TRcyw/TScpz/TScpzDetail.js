/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明 
 * ========    =======  ============================================
 * 2019/4/8  wanggk25832  新增
 * ========    =======  ============================================
 */
$(function () {
    var header = $.cookie('header');
    var token = $.cookie('token');
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
})
//获取父页面的传值
function getValue(data) {
    var pzdata = JSON.parse(data);
    console.log(pzdata);
    layui.use(['form', 'layer', 'laydate','table'], function () {
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery,
            table = layui.table;
        table.render({
            elem: '#pz'
            , cols: [[
                 {field: 'zy', title: '摘要', align: "center",width:"50%"}
                , {field: 'kmmc', title: '科目名称', align: "center"}
                , {field: 'jfje', title: '借方金额', align: "center"}
                , {field: 'dfje', title: '贷方金额', align: "center"}
            ]]
            , data:pzdata
            , page: false
            , title: '凭证详情'
        });
    });
    if (pzdata!=null){
        var rq = pzdata[0].rq;
        var rqs = rq.split("-");
        var year1 = rqs[0];
        var month1 = rqs[1];
        $("#pzid").html(pzdata[0].pzid);
        $("#year").html(year1);
        $("#month").html(month1);
        var zy = pzdata[0].zy;
        var zys = zy.split("][");
        var zqcode = zys[1].substring(0,zys[1].length-1);
        var max = 0;
        for (var i = 0 ;i < pzdata.length ; i++){
            var pzd = pzdata[i];
            if (pzd.BS=="借") {
                max = max + pzd.jfje;
            }
        }
        $("#zqcode").html(zqcode);
        $("#ztcode").html(pzdata[0].ztbh);
        $("#amount").html(max);
    }
}