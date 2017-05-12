package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.MaintainFix;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;

import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.MaintainFixService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2017/4/21.
 */
@Controller
@RequestMapping("/maintainFix")
public class MaintainFixController {
    private Logger logger = (Logger) LoggerFactory.getLogger(MaintainFixController.class);
    @Resource
    private MaintainFixService maintainFixService;

    private String queryRole  = Constants.COMPANY_ADMIN +"," + Constants.SYSTEM_SUPER_ADMIN +"," + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.COMPANY_HUMAN_MANAGER +"," + Constants.COMPANY_ACCOUNTING
            + Constants.COMPANY_EMP + "," + Constants.COMPANY_SALES;

    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.COMPANY_ARTIFICER;
    @ResponseBody
    @RequestMapping(value = "InsertMaintainItem", method = RequestMethod.POST)
    public ControllerResult InsertMaintainFix(MaintainFix maintainFix) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }else{
            try {
                if(CheckRoleUtil.checkRoles(editRole)){
                    User user = SessionGetUtil.getUser();
                    logger.info("添加维修项目");
                    maintainFix.setMaintainOrFix("维修");
                    maintainFix.setCompanyId(user.getCompanyId());
                    maintainFixService.insert(maintainFix);
                    return ControllerResult.getSuccessResult("添加成功");
                }else{
                    return ControllerResult.getFailResult("添加失败，你没有权限操作");
                }

            } catch (Exception e) {
                logger.info("添加失败，出现了一个错误");
                return ControllerResult.getFailResult("添加失败，出现了一个错误");
            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "InsertMaintain", method = RequestMethod.POST)
    public ControllerResult InsertMaintain(MaintainFix maintainFix) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }else{
            try {
                if(CheckRoleUtil.checkRoles(editRole)){
                    User user = SessionGetUtil.getUser();
                    logger.info("添加保养项目");
                    maintainFix.setCompanyId(user.getCompanyId());
                    maintainFix.setMaintainOrFix("保养");
                    maintainFixService.insert(maintainFix);
                    return ControllerResult.getSuccessResult("添加成功");
                }else{
                    return ControllerResult.getFailResult("添加失败，你没有权限操作");
                }

            } catch (Exception e) {
                logger.info("添加失败，出现了一个错误");
                return ControllerResult.getFailResult("添加失败，出现了一个错误");
            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult UpdateMaintainFix(MaintainFix maintainFix) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }else{
            try {
                if(CheckRoleUtil.checkRoles(editRole)){
                    logger.info("更新保养项目");
                    maintainFixService.update(maintainFix);
                    return ControllerResult.getSuccessResult("更新成功");
                }else{
                    return ControllerResult.getFailResult("更新失败，你没有权限操作");
                }

            } catch (Exception e) {
                logger.info("更新失败，出现了一个错误");
                return ControllerResult.getFailResult("更新失败，出现了一个错误");
            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "StatusModify", method = RequestMethod.GET)
    public ControllerResult companyStatusModify(@Param("id") String id, @Param("status") String status) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }else{
            if(CheckRoleUtil.checkRoles(editRole)){
                if (status.equals("Y")) {
                    logger.info("冻结项目");
                    maintainFixService.inactive(id);
                } else if (status.equals("N")) {
                    logger.info("激活项目");
                    maintainFixService.active(id);
                }
                return ControllerResult.getSuccessResult("操作成功");
            }else{
                return ControllerResult.getFailResult("操作失败，你没有权限操作");
            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainFix> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()|| !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        User user = SessionGetUtil.getUser();
        logger.info("分页查询所有维修项目");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(maintainFixService.count(user));
        List<MaintainFix> maintainFixList = maintainFixService.queryByPager(pager,user);
        return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixList);
    }

    @ResponseBody
    @RequestMapping(value = "queryByMaintenanceItemPager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainFix> queryByMaintenanceItemPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()|| !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        logger.info("分页查询所有保养项目");
        User user = SessionGetUtil.getUser();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(maintainFixService.MaintainCont(user));
        List<MaintainFix> maintainFixList = maintainFixService.queryBymaintainPager(pager,user);
        return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixList);
    }

    @ResponseBody
    @RequestMapping(value = "maintain_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryUserAll() {
        if (!SessionGetUtil.isUser()|| !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        logger.info("查询所有维修保养项目，封装成ComboBox");
        User user = SessionGetUtil.getUser();
        List<MaintainFix> maintainFices = maintainFixService.queryAll(user);
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (MaintainFix maintainFix : maintainFices) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(maintainFix.getMaintainId());
            comboBox4EasyUI.setText(maintainFix.getMaintainName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }
}
