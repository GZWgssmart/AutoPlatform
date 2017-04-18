package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.RoleService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 温鑫
 * 人员角色管理
 * Created by Star on 2017/4/17.
 */

@Controller
@RequestMapping("role")
public class RoleController {

    @Resource
    private RoleService roleService;

    private Logger logger = (Logger) LoggerFactory.getLogger(IncomingTypeController.class);

    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showRoleInfo() {
        logger.info(" 人员角色管理页面");
        return "system/role";
    }
    @ResponseBody
    @RequestMapping(value = "add_role", method = RequestMethod.POST)
    public ControllerResult addRole(Role role){
        logger.info("角色添加");
        roleService.insert(role);
        return ControllerResult.getSuccessResult("添加成功");
    }


    @ResponseBody
    @RequestMapping(value = "query_pager", method= RequestMethod.GET)
    public Pager4EasyUI<Role> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有角色");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(roleService.count());
        List<Role> roles = roleService.queryByPager(pager);
        return new Pager4EasyUI<Role>(pager.getTotalRecords(), roles);
    }

    @ResponseBody
    @RequestMapping(value = "update_role", method = RequestMethod.POST)
    public ControllerResult updateRole(Role role){
        logger.info("角色修改");
        roleService.update(role);
        return ControllerResult.getSuccessResult(" 修改成功");
    }

    @ResponseBody
    @RequestMapping(value = "update_status", method = RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id")String id, @Param("status")String status){
        logger.info("状态修改");
        if(status.equals("Y")){
            roleService.inactive(id);
        }else if (status.equals("N")){
            roleService.active(id);
        }
        return ControllerResult.getSuccessResult(" 修改成功");

    }

}
