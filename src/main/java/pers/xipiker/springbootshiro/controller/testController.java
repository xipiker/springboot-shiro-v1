package pers.xipiker.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testController {

    @ResponseBody
    @RequestMapping("/helloWorld")
    public String helloWorld(){
        return "hello world!";
    }

    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("name", "this is test");
        return "test";
    }

    @RequestMapping("/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login";
    }

    /**
     * 验证登录
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, Model model){
        /**
         * 编写Shiro认证操作
         */
        // 1.获取subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 3.执行登录方法
        try {
            subject.login(token);
            // 登录成功，跳转到test.html
            return "redirect:/test";
        }catch (UnknownAccountException e){
            //登录失败:用户名不存在
            model.addAttribute("msg", "登录失败！用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //登录失败:密码错误
            model.addAttribute("msg", "登录失败！密码错误");
            return "login";
        }
    }


}
