package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.bean.UserRole;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.RoleService;
import com.gs.service.UserRoleService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Xiao-Qiang on 2017/4/17.
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AdminController.class);

    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleService roleService;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String showAdminInfo() {
        logger.info("显示管理员信息");
        return "system/admin";
    }

    @ResponseBody
    @RequestMapping(value = "query_pager", method= RequestMethod.GET)
    public Pager4EasyUI<User> queryAdminPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有管理员");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(userService.countAdmin());
        List<User> users = userService.queryByAdminPager(pager);
        return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
    }

    @ResponseBody
    @RequestMapping(value = "company_pager", method= RequestMethod.GET)
    public Pager4EasyUI<User> queryCompanyAdminPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询汽修公司管理员");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(userService.countCompanyAdmin());
        List<User> users = userService.queryByCompanyAdminPager(pager);
        return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
    }

    @ResponseBody
    @RequestMapping(value = "system_pager", method= RequestMethod.GET)
    public Pager4EasyUI<User> querySystemAdminPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询系统管理员");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(userService.countSystemAdmin());
        List<User> users = userService.queryBySystemAdminPager(pager);
        return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
    }

    @ResponseBody
    @RequestMapping(value = "add_admin", method = RequestMethod.POST)
    public ControllerResult addAdmin(@Param("user") User user, @Param("adminTypeId") String adminTypeId) {
        logger.info("添加管理员");
        user.setUserId(UUIDUtil.uuid());
        userService.insertAdmin(user);
        UserRole ur = new UserRole();
        ur.setRoleId(adminTypeId);
        ur.setUserId(user.getUserId());
        userRoleService.insert(ur);
        return ControllerResult.getSuccessResult("添加成功");
    }

    @ResponseBody
    @RequestMapping(value = "update_status", method = RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status") String status) {
        if (status.equals("Y")) {
            logger.info("激活管理员状态");
            userService.active(id);
        } else if (status.equals("N")) {
            logger.info("冻结管理员状态");
            userService.inactive(id);
        }
        return ControllerResult.getSuccessResult("更新成功");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ResponseBody
    @RequestMapping(value = "queryRoleByUserId", method = RequestMethod.GET)
    public String queryRoleByUserId() {
        Role role = roleService.queryByUserId(SessionGetUtil.getUser().getUserId());
        if (role.getRoleName().equals("systemSuperAdmin")) {
            return "2";
        } else if (role.getRoleName().equals("systemOrdinaryAdmin")) {
            return "1";
        } else {
            return "0";
        }

    }
}
