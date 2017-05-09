package com.gs.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ch.qos.logback.classic.Logger;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xiao-kang on 2017/4/22.
 */
@Controller
@RequestMapping("/reportStatistics")
public class ReportStatisticsController {

    private Logger logger = (Logger) LoggerFactory.getLogger(ReportStatisticsController.class);

    @RequestMapping(value = "finance_page", method = RequestMethod.GET)
    public String showInOutPage() {
        logger.info("显示财务统计页面");
        return "reportStatistics/finance_statistics";
    }

    @RequestMapping(value = "staff_page", method = RequestMethod.GET)
    public String showStaff() {
        logger.info("显示员工工单统计页面");
        return "reportStatistics/staff_statistics";
    }
}
