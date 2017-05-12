package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Company;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.bean.UserRole;
import com.gs.common.Constants;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.EncryptUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.dao.CompanyDAO;
import com.gs.service.CompanyService;
import com.gs.service.RoleService;
import com.gs.service.UserRoleService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CompanyController.class);

    @Resource
    private CompanyService companyService;

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    private String CompanyQueryRole = Constants.SYSTEM_SUPER_ADMIN + "," + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.COMPANY_ADMIN;
    private String CompanyEditRole = Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;
    private String carCommonRole = Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.COMPANY_ADMIN;
    @RequestMapping(value = "home", method = RequestMethod.GET)
    private String home() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
        logger.info("访问公司的主页");
        return "company/home";
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showCompanyInfo() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }else{
            if(CheckRoleUtil.checkRoles(CompanyQueryRole)){
                logger.info("访问公司基本信息页面");
                return "company/company_info";
            }
            return "error/notPermission";
        }

    }

    @RequestMapping(value = "brand", method = RequestMethod.GET)
    private String showCarBrand() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效");
            return "index/notLogin";
        }else{
            if(CheckRoleUtil.checkRoles(carCommonRole)){
                logger.info("访问汽车品牌页面");
                return "company/car_brand";
            }
            return "error/notPermission";
        }

    }

    @RequestMapping(value = "color", method = RequestMethod.GET)
    private String showCarColor() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效");
            return "index/notLogin";
        }else{
            if(CheckRoleUtil.checkRoles(carCommonRole)){
                logger.info("访问汽车颜色页面");
                return "company/car_colour";
            }else{
                return "error/notPermission";
            }
        }

    }

    @RequestMapping(value = "model", method = RequestMethod.GET)
    private String showCarModel() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效");
            return "index/notLogin";
        }else{
            if(CheckRoleUtil.checkRoles(carCommonRole)){
                logger.info("访问汽车车型页面");
                return "company/car_model";
            }else{
                return "error/notPermission";
            }
        }

    }

    @RequestMapping(value = "plate", method = RequestMethod.GET)
    private String showCarPlate() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效");
            return "index/notLogin";
        }else{
            if(CheckRoleUtil.checkRoles(carCommonRole)){
                logger.info("访问车牌页面");
                return "company/car_plate";
            } else{
                return "error/notPermission";
            }
        }

    }

    @RequestMapping(value = "maintainItem", method = RequestMethod.GET)
    private String showMaintainItem() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }else{
            if(CheckRoleUtil.checkRoles(carCommonRole)){
                logger.info("访问维修项目页面");
                return "company/maintain_item";
            }else{
                return "error/notPermission";
            }
        }

    }

    @RequestMapping(value = "maintenanceItem", method = RequestMethod.GET)
    private String showMaintainFix() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }else{
            if(CheckRoleUtil.checkRoles(carCommonRole)){
                logger.info("访问保养项目页面");
                return "company/maintenance_item";
            }else{
                return "error/notPermission";
            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "InsertCompany", method = RequestMethod.POST)
    public ControllerResult InsetCompany(Company company) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }else{
            try {
                if(CheckRoleUtil.checkRoles(CompanyEditRole)){
                    logger.info("添加公司");
                    String companyId = UUIDUtil.uuid();
                    company.setCompanyId(companyId);
                    User user = new User();
                    String userId = UUIDUtil.uuid();
                    user.setUserId(userId);
                    user.setCompanyId(companyId);
                    user.setUserName(company.getCompanyPricipal());
                    user.setUserPhone(company.getCompanyTel());
                    user.setUserPwd(EncryptUtil.md5Encrypt("123456"));
                    Role role = roleService.queryByName(Constants.COMPANY_ADMIN);
                    UserRole userRole = new UserRole();
                    userRole.setUserId(userId);
                    userRole.setRoleId(role.getRoleId());
                    userService.insert(user);
                    userRoleService.insert(userRole);
                    companyService.insert(company);
                    return ControllerResult.getSuccessResult("添加公司成功");
                }else{
                    return ControllerResult.getFailResult("添加公司失败,您没有权限操作");
                }

            } catch (Exception e) {
                logger.info("添加失败，出现了一个错误");
                return ControllerResult.getFailResult("添加失败，出现了一个错误");
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "uploadCompany", method = RequestMethod.POST)
    public ControllerResult uploadCarModel(Company company, MultipartFile companyLogo) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if(CheckRoleUtil.checkRoles(CompanyEditRole)){
                companyService.update(company);
                logger.info("更新公司成功");
                return ControllerResult.getSuccessResult("更新公司成功");
            }else{
                return ControllerResult.getFailResult("更新公司失败，您没有权限操作");
            }

        } catch (Exception e) {
            logger.info("更新失败，出现了一个错误");
            return ControllerResult.getFailResult("更新失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<Company> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(CompanyQueryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        User user = SessionGetUtil.getUser();
        Pager pager = new Pager();
        logger.info("分页查询所有公司");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(companyService.count(user));
        List<Company> companyList = companyService.queryByPager(pager,user);
        return new Pager4EasyUI<Company>(pager.getTotalRecords(), companyList);
    }

    @ResponseBody
    @RequestMapping(value = "companyStatusModify", method = RequestMethod.GET)
    public ControllerResult companyStatusModify(@Param("id") String id, @Param("status") String status) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if(CheckRoleUtil.checkRoles(CompanyQueryRole)){
                if (status.equals("Y")) {
                    logger.info("公司冻结");
                    companyService.inactive(id);
                } else if (status.equals("N")) {
                    logger.info("公司激活");
                    companyService.active(id);
                }
                return ControllerResult.getSuccessResult("操作成功");
            }else{
                return ControllerResult.getFailResult("操作失败,你没有权限操作");
            }

        } catch (Exception e) {
            logger.info("操作失败，出现了一个错误");
            return ControllerResult.getFailResult("操作失败，出现了一个错误");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ResponseBody
    @RequestMapping(value = "company_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryUserAll() {

        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        try{
            User user = SessionGetUtil.getUser();
            logger.info("查询所有公司");
            List<Company> companyList = companyService.queryAll(user);
            List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
            for (Company companys : companyList) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(companys.getCompanyId());
                comboBox4EasyUI.setText(companys.getCompanyName());
                comboBox4EasyUIs.add(comboBox4EasyUI);
            }
            return comboBox4EasyUIs;
        }catch(Exception e){
            logger.info("查询失败,出现了一个错误");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value = "queryStatusPager", method = RequestMethod.GET)
    public Pager4EasyUI<Company> companyStatus(@Param("status") String status, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(CompanyQueryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        User user = SessionGetUtil.getUser();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(companyService.statusCount(status,user));
        List<Company> companys = companyService.queryByStatusPager(status, pager,user);
        return new Pager4EasyUI<Company>(pager.getTotalRecords(), companys);
    }


}


