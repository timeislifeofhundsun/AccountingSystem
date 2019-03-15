//获取cookie中的token
$(function () {
    var header = $.cookie('header');
    var token = $.cookie('token');
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    //设置日期
    $('#showDate').html("2018-05-30");
    getFileStatus();
    /* 读取接口文件状态 */
    function getFileStatus() {
        var ywrq = $('#showDate').text();
        if (ywrq != "") {
            $.ajax({
                url: "/FileStatus",
                data: {"ywrq": ywrq},
                type: "POST",
                success: function (response) {
                    var htmlStr = "";
                    console.log(response);
                    if (response.res) {
                        var data = response.data;
                        for (var i = 0; i < data.length; i++) {
                            var obj = data[i];
                            console.log(obj);
                            htmlStr = htmlStr + "<tr><td>" + obj.fileName + "</td>";
                            if (obj.filePath == null) {
                                htmlStr = htmlStr + "<td></td>"
                                    + "<td><i class=\"layui-icon layui-icon-face-smile\"></i></td></tr>";
                            } else {
                                htmlStr = htmlStr + "<td>" + obj.filePath + "</td>"
                                    + "<td><i class=\"layui-icon layui-icon-face-smile\"></i></td></tr>";
                            }
                        }
                        console.log(htmlStr);
                        $('#fileStutasTable').html(htmlStr);
                    }
                }
            });
        } else {
            layer.msg('温馨提示:请选择业务日期!');
        }
    }
})
layui.use(['layer','laydate'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer;
    var laydate = layui.laydate;

    /*方便计算时间默认为2018-05-30 */

    /*layui时间显示 */

    laydate.render({
        elem: '#date'
        ,position: 'static'
        ,change: function(value, date){ //监听日期被切换
            lay('#showDate').html(value);
        }
    });
    /* 清算 */
    $("#qsBtn").click(function(){
        var ywrq = $('#showDate').text();
        if(ywrq!=""){
            $.ajax({
                url:"/Rzqs",
                data:{"ywrq":ywrq,"ztbh":1000},
                type:"POST",
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
            layer.msg('温馨提示:请选择业务日期!');
        }

    })
})