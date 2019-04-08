//获取cookie中的token
$(function () {
    //设置日期
    $('#showDate').html("2018-05-30");
    getFileStatus();
})
$("#reset").click(function(){
	console.log("resetBtn");
})


layui.use(['layer','laydate','form'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer;
    var laydate = layui.laydate;
    var form = layui.form;

    /**
     * 账套选择
     */
    $('.select_btn').click(function(){
    	var index = layui.layer.open({
            title : "选择账套",
            type : 2,
            content : "../../../common/TZtxx.html",
        })
        
        layui.layer.full(index);   	
    	setTimeout(function(){
        layui.layer.tips('点击此处返回股票交易', '.layui-layer-setwin .layui-layer-close', {
            tips: 3
        	});
    	},500)
    });
    
    /*layui时间显示 */
    laydate.render({
        elem: '#date'
        ,value: '2018-05-30'
        ,position: 'static'
        ,change: function(value, date){ //监听日期被切换
            lay('#showDate').html(value);
            getFileStatus();
        }
    });
    /* 清算 */
    $("#qsBtn").click(function(){
        var ywrq = $('#showDate').text();
        var ztbh = $("#ztbh").val()
        if(ywrq!=""){
        	if(ztbh!=''){
        		layer.msg('清算中......');
            	var request = {"ywrq":ywrq,"ztbh":ztbh};
                $.ajax({
                	type:"POST",
                    url:"/rest/RzqsRest/Rzqs",
                    data:JSON.stringify(request),
                    contentType : "application/json",
                    success:function (data) {
                        console.log(data);
                        if(data.res){
                            layer.msg('清算成功!');
                        }else{
                            console.log(data.msg)
                            layer.msg(data.msg);
                        }
                    }
                });
        	}else{
        		layer.msg('温馨提示:请选择账套!');
        	}
        }else{
            layer.msg('温馨提示:请选择业务日期!');
        }

    })
})


/**
 * 读取接口文件状态
 */
function getFileStatus() {
    var ywrq = $('#showDate').text();
    var request = {"ywrq": ywrq};
    if (ywrq != "") {
        $.ajax({
            type: "POST",
            url: "/rest/RzqsRest/getFileStatus",
            data: JSON.stringify(request),
            contentType : "application/json",
            success: function (response) {
                var htmlStr = "";
                console.log(response);
                if (response.res) {
                    var data = response.data;
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        htmlStr = htmlStr + "<tr><td>" + obj.fileName + "</td>";
                        if (obj.filePath == null) {
                            htmlStr = htmlStr + "<td></td>"
                                + "<td style = "+"color:red"+"><h2>&nbsp&nbsp×</h2></td></tr>";
                        } else {
                            htmlStr = htmlStr + "<td>" + obj.filePath + "</td>"
                                + "<td style = "+"color:green"+"><h2>&nbsp&nbsp√</h2></td></tr>";
                        }
                    }
                    $('#fileStutasTable').html(htmlStr);
                }
            }
        });
    } else {
        layer.msg('温馨提示:请选择业务日期!');
    }
}