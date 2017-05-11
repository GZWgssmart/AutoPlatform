package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.common.util.SessionGetUtil;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Asa on 2017/5/10.
 */
@Controller
@RequestMapping("customerClient")
public class CustomerClientController {

    private Logger logger = (Logger) LoggerFactory.getLogger(SupplyTypeController.class);

    @RequestMapping("/home")
    public String supplierInfo() {
            logger.info("进入车主后台");
            return "customerClient/home";

    }
}
