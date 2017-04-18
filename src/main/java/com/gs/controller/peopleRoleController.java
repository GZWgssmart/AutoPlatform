package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 温鑫
 * 人员角色管理
 * Created by Star on 2017/4/17.
 */

@Controller
@RequestMapping("peopleManage")
public class peopleRoleController {

    private Logger logger = (Logger) LoggerFactory.getLogger(IncomingTypeController.class);

    @RequestMapping(value = "people_role", method = RequestMethod.GET)
    private String peopleRole() {
        logger.info(" 人员角色管理页面");
        return "peopleManage/people_role";
    }
}
