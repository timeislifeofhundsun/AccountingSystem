/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明 
 * ========    =======  ============================================
 * 2019/3/25  wanggk25832  新增
 * ========    =======  ============================================
 */
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
            $("#yhs").val(data.yhs);
            $('#yhs').prop('disabled', true);
            $("#zgf").val(data.zgf);
            $('#zgf').prop('disabled', true);
            $("#quantity").val(data.quantity);
            $('#quantity').prop('disabled', true);
            $("#jsf").val(data.jsf);
            $('#jsf').prop('disabled', true);
            $("#ghf").val(data.ghf);
            $('#ghf').prop('disabled', true);
            $("#extendd").val(data.extendd);
            $('#extendd').prop('disabled', true);
            $("#yj").val(data.yj);
            $('#yj').prop('disabled', true);
            form.render();
        })
    });
}