package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Asa on 2017/5/8.
 */
@Controller
@RequestMapping("pay")
public class PayController {

    private Logger logger = (Logger) LoggerFactory.getLogger(PayController.class);

    @RequestMapping("/pay")
    public String supplierType() {
        logger.info("进入支付统计页");
        return "supply/pay_statistics";
    }


}
