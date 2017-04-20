package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
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
        logger.info("查询所有模块");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("system/permission");
        List<Role> roles = roleService.queryAll();
        mav.addObject("roles", roles);
        List<Module> modules = moduleService.queryAll();
        mav.addObject("modules", modules);
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "query_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Permission> queryPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        logger.info("分页查询所有权限");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(permissionService.count());
        List<Permission> permissions = permissionService.queryByPager(pager);
        return new Pager4EasyUI<Permission>(pager.getTotalRecords(), permissions);
    }

    @ResponseBody
    @RequestMapping(value = "module_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Permission> queryByModulePager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("moduleId") String moduleId) {
        logger.info("根据模块来分页查询权限");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(permissionService.countModule(moduleId));
        List<Permission> permissions = permissionService.queryByModulePager(moduleId, pager);
        return new Pager4EasyUI<Permission>(pager.getTotalRecords(), permissions);
    }

    @ResponseBody
    @RequestMapping(value = "status_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Permission> queryByStatusPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("moduleId") String status) {
        logger.info("根据状态来分页查询权限");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(permissionService.countStatus(status));
        List<Permission> permissions = permissionService.queryByStatusPager(status, pager);
        return new Pager4EasyUI<Permission>(pager.getTotalRecords(), permissions);
    }

    @ResponseBody
    @RequestMapping(value = "update_status", method = RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status") String status) {
        logger.info("更新权限状态");
        if (status.equals("Y")) {
            permissionService.active(id);
        } else if (status.equals("N")) {
            permissionService.inactive(id);
        }
        return ControllerResult.getSuccessResult("更新成功");
    }

    @ResponseBody
    @RequestMapping(value = "roleIdOrModuleId_permission", method = RequestMethod.GET)
    public List<PermissionInfo> queryByRoleIdOrModuleId(@Param("roleId") String roleId, @Param("moduleId") String moduleId) {
        logger.info("根据角色和模块查询拥有的权限");
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
        for (String s : permissionIds) {
            System.out.println(s);
        }
        if (permissionIds.length == 1) {
            logger.info("添加单个权限");
        } else if (permissionIds.length > 1) {
            logger.info("添加所有权限");
        }
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
        if (permissionIds.length == 1) {
            logger.info("删除单个权限");
        } else if (permissionIds.length > 1) {
            logger.info("删除所有权限");
        }
        rolePermissionService.delByRoleIdAndPermissionId(permissionIds, roleId);
        return ControllerResult.getSuccessResult("成功移除");
    }
}
