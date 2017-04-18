package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Permission;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager4EasyUI;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Xiao-Qiang on 2017/4/17.
 */
@Controller
@RequestMapping("permission")
public class PermissionController {

    private Logger logger = (Logger) LoggerFactory.getLogger(PermissionController.class);


    @ResponseBody
    @RequestMapping(value = "role_permission")
    public Pager4EasyUI<Permission> queryByRolePermission() {

        return null;
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String showPermissionInfo() {
        logger.info("显示权限信息");
        return "system/permission";
    }

    @ResponseBody
    @RequestMapping(value = "add_permission", method = RequestMethod.POST)
    public ControllerResult addPermission() {

        return ControllerResult.getSuccessResult("成功添加");
    }

    @ResponseBody
    @RequestMapping(value = "del_permission", method = RequestMethod.POST)
    public ControllerResult delPermission() {

        return ControllerResult.getSuccessResult("成功移除");
    }

}
