package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.util.EncryptUtil;
import com.gs.service.RoleService;
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

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by JanGo on 2017/5/10.
 */
@Controller
@RequestMapping("customerClientWeb")
public class CustomerClientWebController {

    private Logger logger = (Logger) LoggerFactory.getLogger(SupplyTypeController.class);

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @RequestMapping("/index")
    public String supplierInfo() {
            logger.info("进入公司前台显示");
            return "customerClient/index";

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
                Role role = roleService.queryByUserId(u.getUserId());
                if (!role.getRoleName().equals(Constants.CAR_OWNER)) {
                    userService.updateLoginTime(u.getUserId());
                    u.setUserLoginedTime(new Date());
                    subject.login(new UsernamePasswordToken(u.getUserPhone(), u.getUserPwd()));
                    Session session = subject.getSession();
                    session.setAttribute("user", u);
                    return ControllerResult.getSuccessResult("登陆成功!");
                } else {
                    return ControllerResult.getFailResult("登录失败,只能是管理员登入!");
                }
            } else {
                return ControllerResult.getFailResult("登录失败,账号或密码错误!");
            }
        } else {
            return ControllerResult.getFailResult("登录失败,请输入账号或密码!");
        }
    }

}
