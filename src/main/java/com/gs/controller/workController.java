package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 温鑫
 * 工单查询
 * Created by Star on 2017/4/17.
 */

@Controller
@RequestMapping("peopleManage")
public class workController {

    private Logger logger = (Logger) LoggerFactory.getLogger(IncomingTypeController.class);

    @RequestMapping(value = "work", method = RequestMethod.GET)
    private String work() {
        logger.info(" 工单查询页面");
        return "peopleManage/work";
    }
}
