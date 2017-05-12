package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.bean.Company;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.bean.UserRole;
import com.gs.common.Constants;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.*;
import com.gs.service.RoleService;
import com.gs.service.UserRoleService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 温鑫
 * 车主信息
 * Created by xiao-kang on 2017/4/18.
 */
@Controller
@RequestMapping("customer")

public class CustomerController {


    private Logger logger = (Logger) LoggerFactory.getLogger(CustomerController.class);

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY
            + "," + Constants.COMPANY_RECEIVE + "," + Constants.COMPANY_ARTIFICER
            + "," + Constants.COMPANY_SALES + "," + Constants.COMPANY_HUMAN_MANAGER
            + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.COMPANY_ACCOUNTING
            + "," + Constants.COMPANY_BUYER + "," + Constants.SYSTEM_ORDINARY_ADMIN
            + "," + Constants.COMPANY_EMP;

    private String editRole = Constants.COMPANY_ADMIN;


    @RequestMapping(value = "customer_page", method = RequestMethod.GET)
    public String customerInfo() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole)) {
                logger.info(" 车主基本信息页面");
                return "customerInfoManage/customer_info";
            }
            return "error/notPermission";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }
    @ResponseBody
    @RequestMapping(value = "customerInfo_insert", method = RequestMethod.POST)
    public ControllerResult infoInsert(User user, UserRole userRole, Company company,HttpSession session){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("信息添加");
                    String customerId = UUIDUtil.uuid();
                    Role role = roleService.queryByName("carOwner");
                    User user1 = (User)session.getAttribute("user");
                    user.setCompanyId(user1.getCompanyId());
                    user.setUserId(customerId);
                    user.setUserIcon("img/default.png");
                    userRole.setUserId(user.getUserId());
                    userRole.setRoleId(role.getRoleId());
                    user.setUserPwd(EncryptUtil.md5Encrypt(user.getUserPwd()));
                    userService.insert(user);
                    userRoleService.insert(userRole);
                    return ControllerResult.getSuccessResult("添加成功");
                }
                return ControllerResult.getFailResult("添加信息失败，没有该权限操作");
            } catch (Exception e) {
                logger.info("添加信息失败，出现了异常");
                return ControllerResult.getFailResult("添加信息失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登录信息已失效，请重新登录");
        }
    }


    @ResponseBody
    @RequestMapping(value = "customerPhone_verification", method = RequestMethod.GET)
    public String verificationPhone(@Param("userPhone")String userPhone, @Param("editPhone") String editPhone) {
        try {
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editPhone.equals(userPhone)) {
                int count = userService.queryPhone(userPhone);
                if (count > 1 || count == 1) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("手机号验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "customerEmail_verification", method = RequestMethod.GET)
    public String verificationEmail(@Param("userEmail")String userEmail, @Param("editEmail") String editEmail) {
        try {
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editEmail.equals(userEmail)) {
                int count = userService.queryEmail(userEmail);
                if (count > 1 || count == 1) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("邮箱验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "customerIdentity_verification", method = RequestMethod.GET)
    public String verificationIdentity(@Param("userIdentity")String userIdentity, @Param("editIdentity")String editIdentity) {
        try {
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editIdentity.equals(userIdentity)) {
                int count = userService.queryIdentity(userIdentity);
                if (count > 1 || count == 1) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("身份证验证失败，出现了异常");
            return null;
        }
    }



    @ResponseBody
    @RequestMapping(value = "customerInfo_pager", method= RequestMethod.GET)
    public Pager4EasyUI<User> info_pager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询所有车主");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(userService.count(user));
                    List<User> users = userService.queryCustomerPager(pager, user);
                    return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
                }
                return null;
            } catch (Exception e) {
                logger.info("查询失败，出现了异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "customerInfo_update", method = RequestMethod.POST)
    public ControllerResult info_update(User user, MultipartFile file, HttpSession session) throws IOException {
        if (SessionGetUtil.isUser()) {
//            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("信息修改");
                    if(file != null){
                        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename() ;
                        String filePath = FileUtil.uploadPath(session,"\\" + fileName);
                        String icon = "uploads/"+ fileName;
                        if(!file.isEmpty()){
                            file.transferTo(new File(filePath));
                            user.setUserIcon(icon);
                            userService.update(user);
                        }
                    }else{
                        user.setUserIcon("img/default.png");
                    }
                    return ControllerResult.getSuccessResult(" 修改成功");
                }
                return ControllerResult.getFailResult("修改信息失败，没有该权限操作");
//            } catch (Exception e) {
//                logger.info("修改信息失败，出现了异常");
//                return ControllerResult.getFailResult("修改信息失败，出现了一个错误");
//            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登录信息已失效，请重新登录");
        }
    }

    @ResponseBody
    @RequestMapping(value = "customerInfo_status", method = RequestMethod.GET)
    public ControllerResult info_status(@Param("id")String id, @Param("status")String status){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("状态修改");
                    if(status.equals("Y")){
                        userService.inactive(id);
                    }else{
                        userService.active(id);
                    }
                    return ControllerResult.getSuccessResult("修改成功");
                }
                return ControllerResult.getFailResult("修改状态失败，没有权限操作");
            } catch (Exception e) {
                logger.info("修改状态失败，出现了一个错误");
                return ControllerResult.getFailResult("修改状态失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登录信息已失效，请重新登录");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
