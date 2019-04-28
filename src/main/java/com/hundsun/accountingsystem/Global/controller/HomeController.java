package com.hundsun.accountingsystem.Global.controller;

import com.hundsun.accountingsystem.Global.bean.TZqxx;
import com.hundsun.accountingsystem.Global.bean.TZtxxb;
import com.hundsun.accountingsystem.Global.mapper.TZqxxMapper;
import com.hundsun.accountingsystem.Global.mapper.TZtxxbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private TZqxxMapper tZqxxMapper;

    @Autowired
    private TZtxxbMapper tZtxxbMapper;
    /**
    * @Author yangjf25257
    * @MethodName index
     * @Param []
     * @Return java.lang.String
     * @Description 跳转到主页,并且项目启动的时候拿出对于的账套信息跟证券信息
     **/
    @RequestMapping("/")
    public String index(HttpSession session){
        List<TZqxx> zqxxes = tZqxxMapper.findAllTZqxx();
        session.setAttribute("zqxxes",zqxxes);
        List<TZtxxb> ztxxbs = tZtxxbMapper.findAll();
        session.setAttribute("ztxxbs",ztxxbs);
        return "index";
    }

    //跳转到登陆
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
    //权限测试
    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    //必须有这个权限才可以使用
    //@Secured("ROLE_ADMIN")
    //@PreAuthorize("hasRole('ADMIN')")
    public String getUser(Model model) {
        System.out.println("ROLE_ADMIN权限");
        return "home";
    }






}
