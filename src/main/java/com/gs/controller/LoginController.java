package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.bean.UserRole;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.message.IndustrySMS;
import com.gs.common.util.EncryptUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.common.web.ServletContextUtil;
import com.gs.service.CompanyService;
import com.gs.service.RoleService;
import com.gs.service.UserRoleService;
import com.gs.service.UserService;
import com.jh.email.Mail;
import com.jh.email.MailSender;
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
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
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

    @Resource
    private CompanyService companyService;

    private String phoneCode;

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
                    user.setUserStatus("N");
                    user.setUserEmail(number);
                } else {
                    if (userService.queryPhone(number) > 0) {
                        return ControllerResult.getFailResult("注册失败，该手机号已经存在");
                    } else {
                        if (code == null || code.equals("")) {
                            return ControllerResult.getFailResult("注册失败，请输入验证码");
                        } else {
                            if (!code.equals(phoneCode)) {
                                return ControllerResult.getFailResult("注册失败，验证码有误");
                            }
                            user.setUserStatus("Y");
                            user.setUserPhone(number);
                        }
                    }
                }
                user.setUserIcon("/img/default.png");

                Role role = roleService.queryByName(Constants.CAR_OWNER);
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(role.getRoleId());

                userService.insert(user);
                userRoleService.insert(userRole);

                if (email) {
                    String ip = ServletContextUtil.getServerIp();
                    Mail mail = new Mail();
                    mail.setRecipients(user.getUserEmail());
                    mail.setSubject("注册提醒");
                    mail.setType(Mail.HTML);
                    Multipart multipart = new MimeMultipart();
                    BodyPart part1 = new MimeBodyPart();
                    try {
                        part1.setContent("<p>欢迎入驻【创意科技】,请点击一下连接完成激活</p><a href='http://" + ip + ":8080/login/ok?userId=" + userId + "'>点击完成验证</a>", mail.getType());
                        multipart.addBodyPart(part1);
                        mail.setMultipart(multipart);
                    } catch (MessagingException e) {
                    }
                    MailSender mailSender = new MailSender();
                    mailSender.sendEmailByType(Constants.MAIL_TYPE, mail, Constants.MAIL_SENDER, Constants.MAIL_PASSWORD);
                    return ControllerResult.getSuccessResult("注册成功，请到邮箱中进行验证");
                } else {
                    Subject subject = SecurityUtils.getSubject();
                    user.setUserLoginedTime(new Date());
                    subject.login(new UsernamePasswordToken(user.getUserId(), user.getUserPwd()));
                    Session session = subject.getSession();
                    session.setAttribute("user", user);
                    userService.updateLoginTime(user.getUserId());

                    return ControllerResult.getSuccessResult("注册成功，请不要操作，");
                }
            } else {
                return ControllerResult.getFailResult("注册失败，两次密码不一致");
            }
        } else {
            return ControllerResult.getFailResult("注册失败，请输入账号和密码");
        }
    }

    @ResponseBody
    @RequestMapping("sendCode")
    public ControllerResult sendCode(@Param("userPhone") String userPhone) {
        logger.info("获取短信验证码");
        phoneCode = String.valueOf((int)((Math.random()*9+1)*100000));
        String to = userPhone;
        String smsContent = "【创意科技】您的验证码为" + phoneCode + "，请于30分钟内正确输入，如非本人操作，请忽略此短信。";
        IndustrySMS is = new IndustrySMS(to, smsContent);
        is.execute();
        return ControllerResult.getSuccessResult("验证码发送成功，请注意查收");
    }

    @RequestMapping("ok")
    public String ok(String userId) {
        logger.info("验证成功");
        User user = userService.queryById(userId);
        if (user != null) {
            userService.active(userId);
        }
        return "index/index";
    }

    @RequestMapping("logout")
    public ModelAndView logout(HttpSession session) {
        logger.info("注销");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        subject.getSession().removeAttribute("user");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index/index");
        mav.addObject("companys", companyService.queryByTop(6));
        return mav;
    }
}
