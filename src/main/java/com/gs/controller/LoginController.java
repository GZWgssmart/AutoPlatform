package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.bean.UserRole;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.util.EncryptUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.RoleService;
import com.gs.service.UserRoleService;
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
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Xiao-Qiang on 2017/5/2.
 */
@Controller
@RequestMapping("login")
public class LoginController {

    private Logger logger = (Logger) LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

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
                if (u.getUserStatus().equals("Y")) {
                    Role role = roleService.queryByUserId(u.getUserId());
                    if (!role.getRoleName().equals(Constants.CAR_OWNER)) {
                        userService.updateLoginTime(u.getUserId());
                        u.setUserLoginedTime(new Date());
                        subject.login(new UsernamePasswordToken(u.getUserId(), u.getUserPwd()));
                        Session session = subject.getSession();
                        session.setAttribute("user", u);
                        return ControllerResult.getSuccessResult("adminHome");
                    } else {
                        userService.updateLoginTime(u.getUserId());
                        u.setUserLoginedTime(new Date());
                        subject.login(new UsernamePasswordToken(u.getUserId(), u.getUserPwd()));
                        Session session = subject.getSession();
                        session.setAttribute("user", u);
                        return ControllerResult.getSuccessResult("customerHome");
                    }
                } else {
                    return ControllerResult.getFailResult("登录失败,此账号已被冻结!");
                }
            } else {
                return ControllerResult.getFailResult("登录失败,账号或密码错误!");
            }
        } else {
            return ControllerResult.getFailResult("登录失败,请输入账号或密码!");
        }
    }

    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ControllerResult register(@Param("number")String number, @Param("pwd")String pwd, @Param("password") String password, @Param("code") String code) {
        logger.info("车主注册");
        if (number != null && !number.equals("") && pwd != null && !pwd.equals("") && password != null && !password.equals("")) {
            if (pwd.equals(password)) {
                Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
                Matcher m = p.matcher(number);
                boolean email = m.matches();

                User user = new User();
                String userId = UUIDUtil.uuid();
                user.setUserId(userId);
                user.setUserPwd(EncryptUtil.md5Encrypt(password));
                if (email) {
                    if (userService.queryEmail(number) > 0) {
                        return ControllerResult.getFailResult("注册失败，该邮箱已经存在");
                    }
                    user.setUserEmail(number);
                } else {
                    if (userService.queryPhone(number) > 0) {
                        return ControllerResult.getFailResult("注册失败，该手机号已经存在");
                    } else {
                        if (code == null || code.equals("")) {
                            return ControllerResult.getFailResult("注册失败，请输入验证码");
                        } else {
                            user.setUserPhone(number);
                        }
                    }
                }

                Role role = roleService.queryByName(Constants.CAR_OWNER);
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(role.getRoleId());

                userService.insert(user);
                userRoleService.insert(userRole);

                Subject subject = SecurityUtils.getSubject();
                user.setUserLoginedTime(new Date());
                subject.login(new UsernamePasswordToken(user.getUserId(), user.getUserPwd()));
                Session session = subject.getSession();
                session.setAttribute("user", user);
                userService.updateLoginTime(user.getUserId());
                return ControllerResult.getSuccessResult("注册成功，请不要操作，");
            } else {
                return ControllerResult.getFailResult("注册失败，两次密码不一致");
            }
        } else {
            return ControllerResult.getFailResult("注册失败，请输入账号和密码");
        }
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        logger.info("注销");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        subject.getSession().removeAttribute("user");
        return "index/index";
    }
}
