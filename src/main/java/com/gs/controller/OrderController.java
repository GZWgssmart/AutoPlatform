package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Asa on 2017/5/8.
 */
@Controller
@RequestMapping("order")
public class OrderController {

    private Logger logger = (Logger) LoggerFactory.getLogger(OrderController.class);

    @RequestMapping("/order")
    public String supplierType() {
        logger.info("进入下单统计页");
        return "supply/order_statistics";
    }

}
