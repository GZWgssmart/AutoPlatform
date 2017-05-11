package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.bean.Company;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.bean.UserRole;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.EncryptUtil;
import com.gs.common.util.FileUtil;
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

    @RequestMapping(value = "customer_page", method = RequestMethod.GET)
    public String customerInfo() {
        if (SessionGetUtil.isUser()) {
            logger.info(" 车主基本信息页面");
            return "customerInfoManage/customer_info";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }
    @ResponseBody
    @RequestMapping(value = "customerInfo_insert", method = RequestMethod.POST)
    public ControllerResult infoInsert(User user, UserRole userRole, Company company){
        if (SessionGetUtil.isUser()) {
            logger.info("信息添加");
            String customerId = UUIDUtil.uuid();
            Role role = roleService.queryByName("carOwner");
            user.setCompanyId(company.getCompanyId());
            user.setUserId(customerId);
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(role.getRoleId());
            user.setUserPwd(EncryptUtil.md5Encrypt(user.getUserPwd()));
            userService.insert(user);
            userRoleService.insert(userRole);
            return ControllerResult.getSuccessResult("添加成功");
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登录信息已失效，请重新登录");
        }
    }


    @ResponseBody
    @RequestMapping(value = "customerPhone_verification", method = RequestMethod.GET)
    public String verificationPhone(@Param("userPhone")String userPhone, @Param("editPhone") String editPhone) {
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
    }

    @ResponseBody
    @RequestMapping(value = "customerEmail_verification", method = RequestMethod.GET)
    public String verificationEmail(@Param("userEmail")String userEmail, @Param("editEmail") String editEmail) {
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
    }

    @ResponseBody
    @RequestMapping(value = "customerIdentity_verification", method = RequestMethod.GET)
    public String verificationIdentity(@Param("userIdentity")String userIdentity, @Param("editIdentity")String editIdentity) {
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
    }



    @ResponseBody
    @RequestMapping(value = "customerInfo_pager", method= RequestMethod.GET)
    public Pager4EasyUI<User> info_pager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有车主");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(userService.count());
        List<User> users = userService.queryCustomerPager(pager);
        return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
    }

    @ResponseBody
    @RequestMapping(value = "customerInfo_update", method = RequestMethod.POST)
    public ControllerResult info_update(User user, MultipartFile file, HttpSession session) throws IOException {
        if (SessionGetUtil.isUser()) {
            logger.info("信息修改");
            if(file != null){
                String fileName = UUID.randomUUID().toString() + file.getOriginalFilename() ;
                String filePath = FileUtil.uploadPath(session,"\\" + fileName);
                String icon = "uploads/"+ fileName;
                if(!file.isEmpty()){
                    file.transferTo(new File(filePath));
                    user.setUserIcon(icon);
                }
            }else{
                user.setUserIcon("img/default.png");
            }
            userService.update(user);
            return ControllerResult.getSuccessResult(" 修改成功");
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登录信息已失效，请重新登录");
        }
    }

    @ResponseBody
    @RequestMapping(value = "customerInfo_status", method = RequestMethod.GET)
    public ControllerResult info_status(@Param("id")String id, @Param("status")String status){
        if (SessionGetUtil.isUser()) {
            logger.info("状态修改");
            if(status.equals("Y")){
                userService.inactive(id);
            }else{
                userService.active(id);
            }
            return ControllerResult.getSuccessResult(" 修改成功");
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
