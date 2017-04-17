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
@RequestMapping("permission")
public class PermissionController {

    private Logger logger = (Logger) LoggerFactory.getLogger(PermissionController.class);

    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showPermissionInfo() {
        logger.info("显示权限信息");
        return "system/permission";
    }
}
