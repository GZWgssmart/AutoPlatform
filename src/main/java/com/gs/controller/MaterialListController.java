package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Xiao-Qiang on 2017/4/17.
 */
@Controller
@RequestMapping("materialList")
public class MaterialListController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaterialListController.class);

    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showMaterialListInfo() {
        logger.info("显示物料清单信息");
        return "dispatchingPicking/material_list";
    }
}
