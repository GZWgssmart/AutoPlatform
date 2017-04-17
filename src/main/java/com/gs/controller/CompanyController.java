package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/4/1.
 */
@Controller
@RequestMapping("company")
public class CompanyController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CompanyController.class);

    @RequestMapping(value = "home", method = RequestMethod.GET)
    private String home() {
        logger.info("访问公司的主页");
        return "company/home";
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showCompanyInfo() {
        logger.info("显示公司基本信息");
        return "company/company_info";
    }

    @RequestMapping(value = "brand", method = RequestMethod.GET)
    private String showCarBrand() {
        logger.info("显示汽车品牌");
        return "company/car_brand";
    }

    @RequestMapping(value = "color", method = RequestMethod.GET)
    private String showCarColor() {
        logger.info("显示汽车颜色");
        return "company/car_colour";
    }

    @RequestMapping(value = "model", method = RequestMethod.GET)
    private String showCarModel() {
        logger.info("显示汽车车型");
        return "company/car_model";
    }
}
