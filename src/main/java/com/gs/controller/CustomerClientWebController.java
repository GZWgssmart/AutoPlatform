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
            return "index/index";

    }

    @RequestMapping("/features")
    public String webFeatures() {
        logger.info("进入功能特性页面");
        return "customerClient/features";

    }

    @RequestMapping("/contact")
    public String webContact() {
        logger.info("进入联系我们页面");
        return "index/contact";

    }

    @RequestMapping("/tour")
    public String webTour() {
        logger.info("进入平台特性");
        return "index/tour";

    }


}
