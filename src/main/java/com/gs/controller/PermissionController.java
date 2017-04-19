package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.ModuleService;
import com.gs.service.PermissionService;
import com.gs.service.RolePermissionService;
import com.gs.service.RoleService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/4/17.
 */
@Controller
@RequestMapping("permission")
public class PermissionController {

    private Logger logger = (Logger) LoggerFactory.getLogger(PermissionController.class);

    @Resource
    private RoleService roleService;
    @Resource
    private ModuleService moduleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RolePermissionService rolePermissionService;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public ModelAndView showPermissionInfo() {
        logger.info("显示权限信息");
        logger.info("查询所有角色");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("system/permission");
        List<Role> roles = roleService.queryAll();
        mav.addObject("roles", roles);
        List<Module> modules = moduleService.queryAll();
        mav.addObject("modules", modules);
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "roleIdOrModuleId_permission", method = RequestMethod.GET)
    public List<PermissionInfo> queryByRoleIdOrModuleId(@Param("roleId") String roleId, @Param("moduleId") String moduleId) {
        List<Permission> permissions = permissionService.queryByModuleId(moduleId);
        List<String> str = rolePermissionService.queryByRoleIdOrMeduleId(roleId, moduleId);
        List<PermissionInfo> pis = new ArrayList<PermissionInfo>();
        for (Permission p : permissions) {
            int i = 0;
            PermissionInfo pi = new PermissionInfo();
            pi.setPermissionId(p.getPermissionId());
            pi.setPermissionName(p.getPermissionZHName());
            for (String s : str) {
                i++;
                if (p.getPermissionZHName().equals(s)) {
                    pi.setStatus(1);
                    break;
                } else if (i == str.size()) {
                    pi.setStatus(0);
                }
            }
            pis.add(pi);
        }
        return pis;
    }

    @ResponseBody
    @RequestMapping(value = "addByRole_permission", method = RequestMethod.GET)
    public ControllerResult addPermission(@Param("permissionIds") String[] permissionIds, @Param("roleId") String roleId) {
        List<RolePermission> rps = new ArrayList<RolePermission>();
        for (int i = 0; i < permissionIds.length; i++) {
            RolePermission rp = new RolePermission();
            rp.setRoleId(roleId);
            rp.setPermissionId(permissionIds[i]);
            rps.add(rp);
        }
        rolePermissionService.addByRoleIdAndPermissionId(rps);
        return ControllerResult.getSuccessResult("成功添加");
    }

    @ResponseBody
    @RequestMapping(value = "delByRole_permission", method = RequestMethod.GET)
    public ControllerResult delPermission(@Param("permissionIds") String[] permissionIds, @Param("roleId") String roleId) {
        rolePermissionService.delByRoleIdAndPermissionId(permissionIds, roleId);
        return ControllerResult.getSuccessResult("成功移除");
    }


}
