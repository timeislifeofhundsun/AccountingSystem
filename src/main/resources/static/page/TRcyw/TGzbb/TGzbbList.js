//获取cookie中的tokenlayui-form
var arr;
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

    //显示报表

    $(".showBb").click(function () {
        /**
         * 表格
         */
        var ztbh = $("#ztbh").val();
        if (ztbh == "") {
            layer.msg('请先选择账套', {
                time: 1000, //2s后自动关闭
            });
            return;
        }

        var TGzbbList = table.render({
            id: "TGzbbList",
            elem: '#data'
            , url: '/get_bb/?ztbh=' + $("#ztbh").val()
            , title: '用户数据表'
            , cellMinWidth: 60
            , cols: [[
                {field: 'kmmc', title: '科目名称', align: "left", width: "19%"}
                , {field: 'sl', title: '数量', align: "right", style: 'color: red;', width: "16%"}
                , {field: 'zqcb', title: '成本', align: "right", style: 'color: red;', width: "16%"}
                , {field: 'ljgz', title: '估值增值', align: "right", style: 'color: red;', width: "16%"}
                , {field: 'ljjx', title: '累计计息', align: "right", style: 'color: red;', width: "16%"}
                , {field: 'dwcb', title: '单位成本', align: "right", style: 'color: red;', width: "16%"}
            ]]
            , done: function (res, curr, count) {
                // 渲染行颜色
                var index = 0;
                arr = res.data;
                $.each(res.data, function () {
                    var curr = this.kmmc;
                    var tr = $(".layui-table tbody").find("tr").eq(index);
                    if (curr.indexOf("汇总") != -1) {
                        tr.css("font-weight", "bold");
                    }
                    index++;
                });
            }
            , page: false

        });
    })
})
$("#outDate").click(function(){
	var toData = [];
	for(var i = 0 ; i < arr.length ; i++){
		var temp = arr[i];
		var kmmc = temp.kmmc;
		var sl = temp.sl==undefined ? ' ':temp.sl;
		var zqcb = temp.zqcb==undefined ? ' ':temp.zqcb;
		var ljgz = temp.ljgz==undefined ? ' ':temp.ljgz;
		var ljjx = temp.ljjx==undefined ? ' ':temp.ljjx;
		var dwcb = temp.dwcb==undefined ? ' ':temp.dwcb;
		var data = {
			'kmmc':kmmc,
			'sl':sl,
			'zqcb':zqcb,
			'ljgz':ljgz,
			'ljjx':ljjx,
			'dwcb':dwcb,
		}
		toData[i]=data
	}
	var option={
	    fileName:'估值表',
	    datas:[{
	        sheetHeader:['会计科目','数量','证券成本','累计估增','累计计息','单位成本'],
	        sheetData:toData
	    }]
	};
	var toExcel=new ExportJsonExcel(option);
	toExcel.saveExcel();
})