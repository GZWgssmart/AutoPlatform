package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 温鑫
 * 工资管理
 * Created by Star on 2017/4/17.
 */

@Controller
@RequestMapping("peopleManage")
public class salaryController {

    private Logger logger = (Logger) LoggerFactory.getLogger(IncomingTypeController.class);

    @RequestMapping(value = "salary", method = RequestMethod.GET)
    private String salary() {
        logger.info(" 工资管理页面");
        return "peopleManage/salary";
    }
}
