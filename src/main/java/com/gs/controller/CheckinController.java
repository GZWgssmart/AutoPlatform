package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.EncryptUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.*;
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
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017-04-16.
 * <p>
 * 登记表对应的Controller
 */
@Controller
@RequestMapping("checkin")
public class CheckinController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CheckinController.class);

    @Resource
    private CheckinService checkinService;

    @Resource
    private MaintainRecordService maintainRecordService;

    @Resource
    private UserService userService;

    @Resource
    private AppointmentService appointmentService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleService roleService;

    @RequestMapping(value = "checkin_page", method = RequestMethod.GET)
    public String checkinPage() {
        if (SessionGetUtil.isUser()) {
            logger.info("访问登记页面");
            return "maintenanceReception/reception_register";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }

    }

    @ResponseBody
    @RequestMapping(value = "checkin_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Checkin> checkinPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("status") String status) {
        if (SessionGetUtil.isUser()) {
            logger.info("分页查询登记记录");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            List<Checkin> checkins = new ArrayList<Checkin>();
            if (status.equals("ALL")) {
                pager.setTotalRecords(checkinService.count());
                checkins = checkinService.queryByPager(pager);
            } else {
                pager.setTotalRecords(checkinService.countByStatus(status));
                checkins = checkinService.queryPagerByStatus(pager, status);
            }

            return new Pager4EasyUI<Checkin>(pager.getTotalRecords(), checkins);
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "condition_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Checkin> queryPagerByCondition(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize,
                                                       @Param("userName") String userName, @Param("userPhone") String userPhone,
                                                       @Param("carPlate") String carPlate, @Param("maintainOrFix") String maintainOrFix,
                                                       @Param("companyId") String companyId) {
        if (SessionGetUtil.isUser()) {
            logger.info("根据条件分页查询登记记录");
            Checkin checkin = new Checkin();
            checkin.setUserName(userName);
            checkin.setUserPhone(userPhone);
            checkin.setCarPlate(carPlate);
            checkin.setMaintainOrFix(maintainOrFix);
            checkin.setCompanyId(companyId);
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            List<Checkin> checkins = new ArrayList<Checkin>();
            pager.setTotalRecords(checkinService.countByCondition(checkin));
            checkins = checkinService.queryPagerByCondition(pager, checkin);

            return new Pager4EasyUI<Checkin>(pager.getTotalRecords(), checkins);
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult addCheckin(Checkin checkin, String isApp) {
        if (SessionGetUtil.isUser()) {
            try {
                logger.info("添加登记记录,自动生成" + checkin.getMaintainOrFix() + "记录和工单信息");
                User loginUser = SessionGetUtil.getUser();
                String checkinId = UUIDUtil.uuid();
                String userId = "";
                if (checkin.getUserId() != null && checkin.getUserId() != "") {
                    userId = checkin.getUserId();
                } else {
                    userId = UUIDUtil.uuid();
                    User user = new User();
                    user.setUserId(userId);
                    user.setCompanyId(loginUser.getCompanyId());
                    user.setUserPhone(checkin.getUserPhone());
                    user.setUserPwd(EncryptUtil.md5Encrypt("123456"));
                    user.setUserName(checkin.getUserName());

                    String roleId = roleService.queryByName(Constants.CAR_OWNER).getRoleId();
                    UserRole userRole = new UserRole();
                    userRole.setUserId(userId);
                    userRole.setRoleId(roleId);

                    userRoleService.insert(userRole);
                    userService.insert(user);
                }
                checkin.setCheckinId(checkinId);
                checkin.setUserId(userId);
                checkin.setCompanyId(loginUser.getCompanyId());

                MaintainRecord maintainRecord = new MaintainRecord();
                String recordId = UUIDUtil.uuid();
                maintainRecord.setRecordId(recordId);
                maintainRecord.setSpeedStatus(Constants.CHECKIN);
                maintainRecord.setCheckinId(checkinId);
                maintainRecord.setStartTime(new Date());
                maintainRecord.setCompanyId(loginUser.getCompanyId());

                if (isApp != null && !isApp.equals("") && isApp.equals("on")) {
                    appointmentService.updateSpeedStatusById(Constants.CHECKIN, checkin.getAppointmentId());
                }

                maintainRecordService.insert(maintainRecord);
                checkinService.insert(checkin);
                return ControllerResult.getSuccessResult("添加成功," + checkin.getMaintainOrFix() + "记录和工单信息已经自动生成");
            } catch (Exception e) {
                logger.info("添加登记记录失败，出现了一个错误");
                return ControllerResult.getFailResult("添加登记记录失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ControllerResult editCheckin(Checkin checkin) {
        if (SessionGetUtil.isUser()) {
            try {
                logger.info("修改登记记录");
                checkinService.update(checkin);
                return ControllerResult.getSuccessResult("修改成功");
            } catch (Exception e) {
                logger.info("修改失败，出现了一个错误");
                return ControllerResult.getFailResult("修改失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "update_status", method = RequestMethod.GET)
    public ControllerResult updateCheckinStatus(String checkinId, String status) {
        if (SessionGetUtil.isUser()) {
            try {
                logger.info("更新登记记录的状态");
                if (status.equals("Y")) {
                    checkinService.inactive(checkinId);
                } else {
                    checkinService.active(checkinId);
                }
                return ControllerResult.getSuccessResult("更新成功");
            } catch (Exception e) {
                logger.info("更新登记记录状态失败，出现了一个错误");
                return ControllerResult.getFailResult("更新登记记录状态失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
