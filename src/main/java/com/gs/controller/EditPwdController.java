package com.gs.controller;
import ch.qos.logback.classic.Logger;
import com.gs.bean.Company;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.bean.UserRole;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.util.*;
import com.gs.service.CompanyService;
import com.gs.service.RoleService;
import com.gs.service.UserRoleService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;

/**
 * Created by Star on 2017/5/19.
 */
@Controller
@RequestMapping("pwd")
public class EditPwdController {

    private Logger logger = (Logger) LoggerFactory.getLogger(PeopleInfoController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "personal_pwd", method = RequestMethod.GET)
    private String personalPwd() {
        if (SessionGetUtil.isUser()) {
            logger.info(" 个人密码修改页面");
            return "index/editPwd";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    @ResponseBody
    @RequestMapping(value = "selfPwd", method = RequestMethod.GET)
    public ControllerResult querySelf(@Param("oldPwd") String oldPwd, @Param("repassword") String repassword, User user) {
        if (SessionGetUtil.isUser()) {
//            try {
                logger.info("修改密码");
                if(oldPwd.equals(user.getUserPwd())){
                    return ControllerResult.getSuccessResult("密码不一致");
                } else if (!repassword.equals(user.getUserPwd())){
                    userService.updatePwd(user.getUserPwd());
                }
                return ControllerResult.getSuccessResult("修改成功");
//            } catch (Exception e) {
//                logger.info("添加信息失败，出现了异常");
//                return ControllerResult.getFailResult("添加信息失败，出现了一个错误");
//            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登录信息已失效，请重新登录");
        }
    }
}
