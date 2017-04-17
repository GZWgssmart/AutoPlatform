package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Xiao-Qiang on 2017/4/17.
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showAdminInfo() {
        logger.info("显示管理员信息");
        return "system/admin";
    }
}
