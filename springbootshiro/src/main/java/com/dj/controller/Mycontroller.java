package com.dj.controller;

import com.dj.service.InfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author joker_dj
 * @create 2020-04-13日 1:22
 */
@Controller
public class Mycontroller {


    @Autowired
    InfoService service;

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello shiro");
        return "index";
    }

    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }
    @RequestMapping("/del")
    public String del(){
        return "user/delete";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/Login";
    }

    @RequestMapping("/login")
    public String login(String username,String password){
        try {
            //获取当前的用户
            Subject subject = SecurityUtils.getSubject();
            //封装用户的登录数据
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);//执行登录的方法 没有异常就成功了
            return "index";
        } catch (UnknownAccountException e) {
            /**
             * 异常信息
             * UnknownAccountException ：用户名不存在
             * IncorrectCredentialsException：密码错误
             */
            e.printStackTrace();
            System.out.println("用户名不存在");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
        }
        return "login";
    }

    @RequestMapping("/queryall")
    @ResponseBody
    public void query(){
        System.out.println(service.queryAll());

    }

    @RequestMapping("/Unauthorized")
    public String Unauthorized(){//没有权限跳转的页面
       return "noUnauthorized";
    }

    //注销
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginuser",null);//清空session
        return "login";
    }
}
