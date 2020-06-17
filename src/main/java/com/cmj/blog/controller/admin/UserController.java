package com.cmj.blog.controller.admin;

import com.cmj.blog.doamin.User;
import com.cmj.blog.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cmj")
public class UserController {

    @Autowired
    private UserService userService;


    //访问 /cmj  跳转到登录页面
    @RequestMapping
    public String loginPage(){
        return "admin/login";
    }

    //访问登录方法
    @RequestMapping("/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password") String password,
                        HttpSession httpSession,
                        RedirectAttributes redirectAttributes){
        User user = userService.checkUser(username, DigestUtils.md5DigestAsHex(password.getBytes()));
//        查看加密密码
//        System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));
        //成功登录，设置对象进session
        if(user !=null){
            user.setPassword(null);
            httpSession.setAttribute("user",user);
            return "admin/index";
        }else {
            //错误，重定向 ，重新跳转到登录页面
            redirectAttributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/cmj";
        }

    }

    //退出登录
    @RequestMapping("/logOut")
    public String logOut(HttpSession httpSession){
        //清空对象
        httpSession.removeAttribute("user");
        //重定向到 登录页面
        return "redirect:/cmj";

    }
}
