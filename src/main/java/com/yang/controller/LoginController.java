package com.yang.controller;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.yang.common.Result;
import com.yang.pojo.Teacher;
import com.yang.pojo.User;
import com.yang.service.Impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author YANG HANG
 */
@RestController
public class LoginController {
    @Autowired
    UserServiceImpl userService;


    @RequestMapping("/login")
    public Result login(@RequestBody User user ,HttpServletRequest request){

        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();

        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserid(),user.getPwd());
        System.out.println(request.getSession().getId());
        System.out.println(SecurityUtils.getSubject().getSession().getId());
        if (user.getCode()!=request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY)){
            System.out.println(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY));
            return Result.error("401","验证码错误");
        }
        //执行登录方法，如果没有异常就说明登录成功了
        try {
            subject.login(token);
            return Result.success(userService.queryUserById(token.getUsername()));
            //    用户名不存在
        }catch (UnknownAccountException e){
            return Result.error("401","用户名错误");
            //    密码错误
        }catch (IncorrectCredentialsException e) {
           return Result.error("401","密码错误");
        }catch (NumberFormatException e){
            return Result.error("401","账号格式错误");
        }

    }



}
