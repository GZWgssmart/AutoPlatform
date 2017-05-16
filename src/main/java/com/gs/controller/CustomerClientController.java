package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.AccessoriesType;
import com.gs.bean.Appointment;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Asa on 2017/5/10.
 */
@Controller
@RequestMapping("customerClient")
public class CustomerClientController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CustomerClientController.class);

    @RequestMapping("/home")
    public String supplierInfo() {
            logger.info("进入车主后台");
            return "customerClient/home";
    }

    @RequestMapping(value = "appointment", method = RequestMethod.GET)
    private String appointment() {
        logger.info("我的预约");
        return "customerClient/appointment";
    }

    @RequestMapping(value ="checkin", method = RequestMethod.GET)
    private String checkin() {
        logger.info("我的接待");
        return "customerClient/checkin";
    }

    @RequestMapping(value = "progress", method = RequestMethod.GET)
    public String progress(){
        logger.info("我的维修保养进度");
        return "customerClient/progress";
    }
}
