package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.CompanyService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by WangGenshen on 5/17/16.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Resource
    private CompanyService companyService;

    private Logger logger = (Logger) LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index() {
        logger.info("进入首页");
        ModelAndView mav = new ModelAndView("index/index");
        mav.addObject("companys", companyService.queryByTop(6));
        return mav;
    }

    @RequestMapping(value = "adminHome", method = RequestMethod.GET)
    public String adminHome() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/index";
        }
        logger.info("进入管理员后台主页");
        return "index/home";
    }

    @RequestMapping(value = "customerHome", method = RequestMethod.GET)
    public String customerHome() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/index";
        }
        logger.info("进入车主后台主页");
        return "customerClient/home";
    }

}
