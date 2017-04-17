package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.MaterialReturn;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Xiao-Qiang on 2017/4/17.
 */
@Controller
@RequestMapping("materialReturn")
public class MaterialReturnController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaterialReturn.class);

    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showMaterialReturnInfo() {
        logger.info("显示退料信息");
        return "dispatchingPicking/material_return";
    }
}
