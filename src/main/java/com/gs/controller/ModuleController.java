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
@RequestMapping("module")
public class ModuleController {

    private Logger logger = (Logger) LoggerFactory.getLogger(ModuleController.class);

    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showModuleInfo() {
        logger.info("显示模块信息");
        return "system/module";
    }
}
