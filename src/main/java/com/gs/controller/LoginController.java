package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.util.EncryptUtil;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Xiao-Qiang on 2017/5/2.
 */
@Controller
@RequestMapping("login")
public class LoginController {

    private Logger logger = (Logger) LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "show_login", method = RequestMethod.GET)
    public String showLogin() {
        logger.info("显示登陆页面");
        return "index/login";
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ControllerResult login(@Param("number")String number, @Param("pwd")String pwd) {
        logger.info("登陆");
        if (number != null && !number.equals("") && pwd != null && !pwd.equals("")) {
            Subject subject = SecurityUtils.getSubject();
            User user = new User();
            user.setUserEmail(number);
            user.setUserPhone(number);
            user.setUserPwd(EncryptUtil.md5Encrypt(pwd));
            User u = userService.queryLogin(user);
            if (u != null) {
                userService.updateLoginTime(u.getUserId());
                u.setUserLoginedTime(new Date());
                subject.login(new UsernamePasswordToken(u.getUserEmail(), u.getUserPwd()));
                Session session = subject.getSession();
                session.setAttribute("user", u);
                return ControllerResult.getSuccessResult("登陆成功!");
            } else {
                return ControllerResult.getFailResult("登录失败,账号或密码错误!");
            }
        } else {
            return ControllerResult.getFailResult("登录失败,账号或密码错误!");
        }
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home() {
        return "index/home";
    }


    @RequestMapping("logout")
    public String logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        subject.getSession().removeAttribute("user");
        return "index/login";
    }
}
