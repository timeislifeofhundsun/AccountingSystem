var cacheStr = window.sessionStorage.getItem("cache"),
    oneLoginStr = window.sessionStorage.getItem("oneLogin");
layui.use(['form','jquery',"layer"],function() {
    var form = layui.form,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;
    //判断是否设置过头像，如果设置过则修改顶部、左侧和个人资料中的头像，否则使用默认头像
    if(window.sessionStorage.getItem('userFace') &&  $(".userAvatar").length > 0){
        $("#userFace").attr("src",window.sessionStorage.getItem('userFace'));
        $(".userAvatar").attr("src",$(".userAvatar").attr("src").split("images/")[0] + "images/" + window.sessionStorage.getItem('userFace').split("images/")[1]);
    }else{
        $("#userFace").attr("src","../../images/face.jpg");
    }

    //公告层
    function showNotice(){
        layer.open({
            type: 1,
            title: "系统公告",
            area: '300px',
            shade: 0.8,
            id: 'LAY_layuipro',
            btn: ['火速围观'],
            moveType: 1,
            content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;"><p class="layui-red">请使用模版前请务必仔细阅读首页右下角的《更新日志》，避免使用中遇到一些简单的问题造成困扰。</p></pclass></p><p>1.0发布以后发现很多朋友将代码上传到各种素材网站，当然这样帮我宣传我谢谢大家，但是有部分朋友上传到素材网站后将下载分值设置的相对较高，需要朋友们充钱才能下载。本人发现后通过和站长、网站管理员联系以后将分值调整为不需要充值才能下载或者直接免费下载。在此郑重提示各位：<span class="layui-red">本模版已进行作品版权证明，不管以何种形式获取的源码，请勿进行出售或者上传到任何素材网站，否则将追究相应的责任。</span></p></div>',
            success: function(layero){
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
                btn.on("click",function(){
                    tipsShow();
                });
            },
            cancel: function(index, layero){
                tipsShow();
            }
        });
    }
    function tipsShow(){
        window.sessionStorage.setItem("showNotice","true");
        if($(window).width() > 432){  //如果页面宽度不足以显示顶部“系统公告”按钮，则不提示
            layer.tips('系统公告躲在了这里', '#userInfo', {
                tips: 3,
                time : 1000
            });
        }
    }
    $(".showNotice").on("click",function(){
        showNotice();
    })
    $("#mopwdbtn").on("click",function(){
        showModifyLayer();
    });
    function clear () {
        $("#mopwd").val("")
        $("#newpwd1").val("")
        $("#newpwd2").val("")
    }
    function showModifyLayer() {
        var index = layer.open({
            type: 1,
            btn: ['取消','确定'],
            title: "修改密码",
            area: ["460px", "400px"],
            content: $("#modifypwdlayer"),
            //++enter
            success: function(layero, index){
                $(document).on('keydown', function(e){
                    if(e.keyCode == 13){
                        deleteFile(index);
                    }
                })
                getModifyPwd()
            },
            cancel: function(index, layero){
                clear();
            },
            yes: function (index) {
                clear();
                layer.close(index);
            },
            btn2: function (index) {
                var newpwd1 = $("#newpwd1").val().trim();
                $.ajax({
                    url: "/updatepwd",
                    data: {newpwd1:newpwd1},
                    type: 'PUT',
                    success:function (data) {
                        console.log(data);
                        if (data==1){
                            clear();
                            layer.close(index);
                            layer.msg('修改成功', {
                                time: 1000, //20s后自动关闭
                            });
                        }else{
                            clear();
                            layer.close(index);
                            layer.msg('修改失败', {
                                time: 1000, //20s后自动关闭
                            });
                        }
                    }
                });
                console.log(444);
            }
        });
    }
    function getModifyPwd() {
        var pwd;
        $("#mopwd").blur(function () {
            var mopwd = $("#mopwd").val().trim();
            if (mopwd.length == 0) {
                $("#mopwd-aux").css({
                    display: "block",
                    color: "#ff1010",
                }).html("请输入原密码")
            } else {
                //发送ajax验证密码
                $.ajax({
                    url: "/unlock",
                    data: {password:mopwd},
                    type: 'GET',
                    success:function (data) {
                        console.log(data);
                        if (data==1){
                            $("#mopwd-aux").css({
                                display: "block",
                                color: "#5FB878"
                            }).html("输入正确")
                            pwd = mopwd;

                        }else{
                            $("#mopwd-aux").css({
                                display: "block",
                                color: "#ff1010",
                            }).html("密码不正确")
                        }
                    }
                });
            }
        })

        var newpwd1 = $("#newpwd1").val().trim()
        $("#newpwd1").blur( function () {
            newpwd1 = $("#newpwd1").val().trim()
            if (newpwd1.length == 0) {
                $("#newpwd1-aux").css({
                    display: "block",
                    color: "#ff1010"
                }).html("请输入新密码")
            } else if (newpwd1 == pwd) {
                $("#newpwd2-aux").css({
                    display: "block",
                    color: "#ff1010"
                }).html("新密码不能与原密码相同")
                $("#newpwd1-aux").css({
                    display: "block",
                    color: "#ff1010"
                }).html("新密码不能与原密码相同")
            } else {
                $("#newpwd1-aux").css({
                    display: "block",
                    color: "#5FB878"
                }).html("输入正确")
            }
        })

        $("#newpwd2").focus(function () {
            var newpwd1 = $("#newpwd1").val().trim();
            if (newpwd1.length == 0) {
            $("#newpwd1-aux").css({
                display: "block",
                color: "#ff1010"
            }).html("请输入新密码")
        }
    })
        $("#newpwd2").blur( function () {
            var newpwd2 = $("#newpwd2").val().trim()
            if (newpwd2.length == 0) {
                $("#newpwd2-aux").css({
                    display: "block",
                    color: "#ff1010"
                }).html("请输入新密码")
            } else if (newpwd1 != newpwd2) {
                $("#newpwd2-aux").css({
                    display: "block",
                    color: "#ff1010"
                }).html("两次输入不一致")
                $("#newpwd1-aux").css({
                    display: "block",
                    color: "#ff1010"
                }).html("两次输入不一致")

            } else if (newpwd2 == pwd) {
                $("#newpwd2-aux").css({
                    display: "block",
                    color: "#ff1010"
                }).html("新密码不能与原密码相同")
                $("#newpwd1-aux").css({
                    display: "block",
                    color: "#ff1010"
                }).html("新密码不能与原密码相同")
            } else {
                $("#newpwd1-aux").css({
                    display: "block",
                    color: "#5FB878"
                }).html("输入正确")
                $("#newpwd2-aux").css({
                    display: "block",
                    color: "#5FB878"
                }).html("输入正确")
                $("#adduserlayer").data("new_pwd", newpwd1)
            }
        });
    }

    //锁屏
    function lockPage(){
        var userName = $.cookie('userName');
        layer.open({
            title : false,
            type : 1,
            content : '<div class="admin-header-lock" id="lock-box">'+
                            '<div class="admin-header-lock-img"><img src="images/face.jpg" class="userAvatar"/></div>'+
                            '<div class="admin-header-lock-name" id="lockUserName">'+userName+'</div>'+
                            '<div class="input_btn">'+
                                '<input type="password" class="admin-header-lock-input layui-input" autocomplete="off" placeholder="请输入密码解锁.." name="lockPwd" id="lockPwd" />'+
                                '<button class="layui-btn" id="unlock">解锁</button>'+
                            '</div>'+
                        '</div>',
            closeBtn : 0,
            shade : 0.9,
            success : function(){
                //判断是否设置过头像，如果设置过则修改顶部、左侧和个人资料中的头像，否则使用默认头像
                if(window.sessionStorage.getItem('userFace') &&  $(".userAvatar").length > 0){
                    $(".userAvatar").attr("src",$(".userAvatar").attr("src").split("images/")[0] + "images/" + window.sessionStorage.getItem('userFace').split("images/")[1]);
                }
            }
        })
        $(".admin-header-lock-input").focus();
    }
    $(".lockcms").on("click",function(){
        window.sessionStorage.setItem("lockcms",true);
        lockPage();
    })
    // 判断是否显示锁屏
    if(window.sessionStorage.getItem("lockcms") == "true"){
        lockPage();
    }
    // 解锁
    $("body").on("click","#unlock",function(){
        if($(this).siblings(".admin-header-lock-input").val() == ''){
            layer.msg("请输入解锁密码！");
            $(this).siblings(".admin-header-lock-input").focus();
        }else{
            $.ajax({
                url: "/unlock",
                data: {password:$(this).siblings(".admin-header-lock-input").val()},
                type: 'GET',
                success:function (obj) {
                    if (obj==1) {
                        window.sessionStorage.setItem("lockcms",false);
                        $(this).siblings(".admin-header-lock-input").val('');
                        layer.closeAll("page");
                    }else{
                        layer.msg("密码错误，请重新输入！");
                        $(this).siblings(".admin-header-lock-input").val('').focus();
                    }
                },
            })
        }
    });
    $(document).on('keydown', function(event) {
        var event = event || window.event;
        if(event.keyCode == 13) {
            $("#unlock").click();
        }
    });

    //退出
    $(".signOut").click(function(){
       /* window.sessionStorage.removeItem("menu");
        menu = [];
        window.sessionStorage.removeItem("curmenu");*/
       console.log(111);
       $('#logoutForm').submit();
    })

    //功能设定
    $(".functionSetting").click(function(){
        layer.open({
            title: "功能设定",
            area: ["380px", "280px"],
            type: "1",
            content :  '<div class="functionSrtting_box">'+
                            '<form class="layui-form">'+
                                '<div class="layui-form-item">'+
                                    '<label class="layui-form-label">开启Tab缓存</label>'+
                                    '<div class="layui-input-block">'+
                                        '<input type="checkbox" name="cache" lay-skin="switch" lay-text="开|关">'+
                                        '<div class="layui-word-aux">开启后刷新页面不关闭打开的Tab页</div>'+
                                    '</div>'+
                                '</div>'+
                                '<div class="layui-form-item">'+
                                    '<label class="layui-form-label">Tab切换刷新</label>'+
                                    '<div class="layui-input-block">'+
                                        '<input type="checkbox" name="changeRefresh" lay-skin="switch" lay-text="开|关">'+
                                        '<div class="layui-word-aux">开启后切换窗口刷新当前页面</div>'+
                                    '</div>'+
                                '</div>'+
                                '<div class="layui-form-item">'+
                                    '<label class="layui-form-label">单一登陆</label>'+
                                    '<div class="layui-input-block">'+
                                        '<input type="checkbox" name="oneLogin" lay-filter="multipleLogin" lay-skin="switch" lay-text="是|否">'+
                                        '<div class="layui-word-aux">开启后不可同时多个地方登录</div>'+
                                    '</div>'+
                                '</div>'+
                                '<div class="layui-form-item skinBtn">'+
                                    '<a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-normal" lay-submit="" lay-filter="settingSuccess">设定完成</a>'+
                                    '<a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-primary" lay-submit="" lay-filter="noSetting">朕再想想</a>'+
                                '</div>'+
                            '</form>'+
                        '</div>',
            success : function(index, layero){
                //如果之前设置过，则设置它的值
                $(".functionSrtting_box input[name=cache]").prop("checked",cacheStr=="true" ? true : false);
                $(".functionSrtting_box input[name=changeRefresh]").prop("checked",changeRefreshStr=="true" ? true : false);
                $(".functionSrtting_box input[name=oneLogin]").prop("checked",oneLoginStr=="true" ? true : false);
                //设定
                form.on("submit(settingSuccess)",function(data){
                    window.sessionStorage.setItem("cache",data.field.cache=="on" ? "true" : "false");
                    window.sessionStorage.setItem("changeRefresh",data.field.changeRefresh=="on" ? "true" : "false");
                    window.sessionStorage.setItem("oneLogin",data.field.oneLogin=="on" ? "true" : "false");
                    window.sessionStorage.removeItem("menu");
                    window.sessionStorage.removeItem("curmenu");
                    location.reload();
                    return false;
                });
                //取消设定
                form.on("submit(noSetting)",function(){
                    layer.closeAll("page");
                });
                //单一登陆提示
                form.on('switch(multipleLogin)', function(data){
                    layer.tips('温馨提示：此功能需要开发配合，所以没有功能演示，敬请谅解', data.othis,{tips: 1})
                });
                form.render();  //表单渲染
            }
        })
    })

    //判断是否修改过系统基本设置，去显示底部版权信息
    if(window.sessionStorage.getItem("systemParameter")){
        systemParameter = JSON.parse(window.sessionStorage.getItem("systemParameter"));
        $(".footer p span").text(systemParameter.powerby);
    }

    //更换皮肤
    function skins(){
        var skin = window.sessionStorage.getItem("skin");
        console.log("skins function : skin值为"+skin);
        if(skin){  //如果更换过皮肤
            if(window.sessionStorage.getItem("skinValue") != "自定义"){
                $("body").addClass(window.sessionStorage.getItem("skin"));
                $("#left").removeClass("layui-bg-black");
                $("#left").css("background-color",skin);
            }else{
                $(".layui-layout-admin .layui-header").css("background-color",skin);
                $("#left").removeClass("layui-bg-black");
                $("#left").css("background-color",skin);
                //$(".topLevelMenus").css("background-color",skin);//hideMenu
            }
        }else{
            $("#left").addClass("layui-bg-black");
        }
    }
    skins();
    $(".changeSkin").click(function(){
        layer.open({
            title : "更换皮肤",
            area : ["310px","280px"],
            type : "1",
            content : '<div class="skins_box">'+
                            '<form class="layui-form">'+
                                '<div class="layui-form-item">'+
                                    '<input type="radio" name="skin" value="默认" title="默认" lay-filter="default" checked="">'+
                                    '<input type="radio" name="skin" value="橙色" title="橙色" lay-filter="orange">'+
                                    '<input type="radio" name="skin" value="蓝色" title="蓝色" lay-filter="blue">'+
                                    '<input type="radio" name="skin" value="自定义" title="自定义" lay-filter="custom">'+
                                    '<div class="skinCustom">'+
                                        '<input type="text" class="layui-input bodyColor" name="bodyColor" placeholder="颜色（red、blue等方式）" />'+
                                    '</div>'+
                                '</div>'+
                                '<div class="layui-form-item skinBtn">'+
                                    '<a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-normal" lay-submit="" lay-filter="changeSkin">确定更换</a>'+
                                    '<a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-primary" lay-submit="" lay-filter="noChangeSkin">朕再想想</a>'+
                                '</div>'+
                            '</form>'+
                        '</div>',
            success : function(index, layero){
                var skin = window.sessionStorage.getItem("skin");
                if(window.sessionStorage.getItem("skinValue")){
                    $(".skins_box input[value="+window.sessionStorage.getItem("skinValue")+"]").attr("checked","checked");
                };
                if($(".skins_box input[value=自定义]").attr("checked")){
                    $(".skinCustom").css("visibility","inherit");
                    $(".bodyColor").val(skin);
                };
                form.render();
                $(".skins_box").removeClass("layui-hide");
                $(".skins_box .layui-form-radio").on("click",function(){
                    var skinColor;
                    if($(this).find("div").text() == "橙色"){
                        skinColor = "orange";
                        $("#left").css("background-color",skinColor);
                    }else if($(this).find("div").text() == "蓝色"){
                        skinColor = "blue";
                        $("#left").css("background-color",skinColor);
                    }else if($(this).find("div").text() == "默认"){
                        skinColor = "";
                        $("#left").addClass("layui-bg-black");
                    }
                    if($(this).find("div").text() != "自定义"){
                        $(".bodyColor").val('');
                        $("body").removeAttr("class").addClass("main_body "+skinColor+"");
                        $(".skinCustom").removeAttr("style");
                        $(".hideMenu,.layui-layout-admin .layui-header").removeAttr("style");
                    }else{
                        $(".skinCustom").css("visibility","inherit");
                    }
                })
                var skinStr,skinColor;
                $(".bodyColor").blur(function(){
                    $(".layui-layout-admin .layui-header").css("background-color",$(this).val()+" !important");
                    $(".hideMenu").css("background-color",$(this).val()+" !important");
                    $("#left").css("background-color",$(this).val()+" !important");
                    skinColor=$(this).val();
                })

                form.on("submit(changeSkin)",function(data){
                    if(data.field.skin != "自定义"){
                        if(data.field.skin == "橙色"){
                            skinColor = "orange";
                        }else if(data.field.skin == "蓝色"){
                            skinColor = "blue";
                        }else if(data.field.skin == "默认"){
                            skinColor = "";
                            $("#left").addClass("layui-bg-black");
                        }
                        window.sessionStorage.setItem("skin",skinColor);
                    }else{
                        skinStr = $(".bodyColor").val();
                        skinColor = skinStr;
                        window.sessionStorage.setItem("skin",skinColor);
                        $("body").removeAttr("class").addClass("main_body");
                        skins();
                    }
                    window.sessionStorage.setItem("skinValue",data.field.skin);
                    layer.closeAll("page");
                });
                form.on("submit(noChangeSkin)",function(){
                    $("body").removeAttr("class").addClass("main_body "+window.sessionStorage.getItem("skin")+"");
                    $("#left,.hideMenu,.layui-layout-admin .layui-header").removeAttr("style");
                    layer.closeAll("page");
                });
            },
            cancel : function(){
                $("body").removeAttr("class").addClass("main_body "+window.sessionStorage.getItem("skin")+"");
                $("#left,.hideMenu,.layui-layout-admin .layui-header").removeAttr("style");
            }
        })
    })

})