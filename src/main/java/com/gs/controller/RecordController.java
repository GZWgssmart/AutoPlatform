package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Checkin;
import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.bean.info.SendRemind;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.MaintainRecordService;
import com.gs.service.UserService;
import com.gs.thread.SendEmailThread;
import com.jh.email.Mail;
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
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JangoGuo on 2017/4/18. 维修保养记录控制器
 */
@Controller
@RequestMapping("record")
public class RecordController {

    private Logger logger = (Logger) LoggerFactory.getLogger(RecordController.class);

    @Resource
    private MaintainRecordService maintainRecordService;

    @Resource
    private UserService userService;

    // 可以查看的角色：董事长、接待员、普通管理员、超级管理员、技师
    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_RECEIVE + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.COMPANY_ARTIFICER;

    // 可以操作的角色：董事长、接待员、技师
    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_RECEIVE + "," + Constants.COMPANY_ARTIFICER;

    @RequestMapping(value = "record_page", method = RequestMethod.GET)
    public String recordPage() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("显示维修保养记录管理页面");
                return "maintenanceReception/mainterance_record";
            }
            return "error/notPermission";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    @ResponseBody
    @RequestMapping(value="pager",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("status") String status){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询指定状态的维修保养记录管理");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<MaintainRecord> maintainRecordList = new ArrayList<MaintainRecord>();
                    if (status.equals("ALL")) {
                        pager.setTotalRecords(maintainRecordService.count(user));
                        maintainRecordList = maintainRecordService.queryByPager(pager, user);
                    } else {
                        pager.setTotalRecords(maintainRecordService.countByStatus(status, user));
                        maintainRecordList = maintainRecordService.queryPagerByStatus(pager, status, user);
                    }
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecordList);
                }
                return null;
            } catch (Exception e) {
                logger.info("查询维修保养记录失败，出现了一个异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value="pager_track",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPagerByTractStatus(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询回访状态的维修保养记录管理");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<MaintainRecord> maintainRecordList = new ArrayList<MaintainRecord>();
                    pager.setTotalRecords(maintainRecordService.countByTrackStatus("N", user));
                    maintainRecordList = maintainRecordService.queryPagerByTrackStatus(pager, "N", user);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecordList);
                }
                return null;
            } catch (Exception e) {
                logger.info("分页查询回访状态的维修保养记录管理， 出现了一个异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value="pager_message",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPagerBymessage(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询维修保养过用户");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<MaintainRecord> maintainRecordList = new ArrayList<MaintainRecord>();
                    pager.setTotalRecords(maintainRecordService.countByStatus("Y", user));
                    maintainRecordList = maintainRecordService.queryPagerByStatus(pager, "Y", user);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecordList);
                }
                return null;
            } catch (Exception e) {
                logger.info("分页查询维修保养过用户失败，出现了一个异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "condition_pager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPagerByCondition(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,
                                                       @Param("userName")String userName, @Param("carPlate")String carPlate,
                                                       @Param("maintainOrFix")String maintainOrFix,
                                                       @Param("companyId")String companyId, @Param("speedStatus") String speedStatus) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("根据条件分页查询维修保养记录");
                    User user = SessionGetUtil.getUser();
                    MaintainRecord record = new MaintainRecord();
                    Checkin checkin = new Checkin();
                    checkin.setUserName(userName);
                    checkin.setCarPlate(carPlate);
                    checkin.setMaintainOrFix(maintainOrFix);
                    record.setCheckin(checkin);
                    record.setCompanyId(companyId);
                    record.setSpeedStatus(speedStatus);
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<MaintainRecord> records = new ArrayList<MaintainRecord>();
                    pager.setTotalRecords(maintainRecordService.countByCondition(record, user));
                    records = maintainRecordService.queryPagerByCondition(pager, record, user);

                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), records);
                }
                return null;
            } catch (Exception e) {
                logger.info("根据条件分页查询维修保养记录失败，出现了一个异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @RequestMapping(value = "reminder_page", method = RequestMethod.GET)
    public String reminderPage() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("显示维已完成的修保养记录页面");
                return "settlementCar/car_reminder";
            }
            return "error/notPermission";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    @ResponseBody
    @RequestMapping(value = "pager_speedStatus",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPagerBySpeedStatus(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("speedStatus")String speedStatus){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询进度状态的维修保养记录管理");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<MaintainRecord> maintainRecordList = new ArrayList<MaintainRecord>();
                    String[] ss = speedStatus.split(",");
                    pager.setTotalRecords(maintainRecordService.countBySpeedStatus(ss, user));
                    maintainRecordList = maintainRecordService.queryPagerBySpeedStatus(pager, ss, user);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecordList);
                }
                return null;
            } catch (Exception e) {
                logger.info("分页查询进度状态的维修保养记录管理失败，出现了一个异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "update_status", method=RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status")String status){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("更新维修保养记录状态");
                    if (status.equals("Y")) {
                        maintainRecordService.inactive(id);
                    } else if (status.equals("N")) {
                        maintainRecordService.active(id);

                    }
                    return ControllerResult.getSuccessResult("更新成功");
                }
                logger.info("更新维修保养记录状态失败，没有权限操作");
                return ControllerResult.getFailResult("更新维修保养记录状态失败，没有权限操作");
            } catch (Exception e) {
                logger.info("更新维修保养记录状态失败，出现了一个错误");
                return ControllerResult.getFailResult("更新维修保养记录状态失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "edit", method=RequestMethod.POST)
    public ControllerResult editMainteranceRecord(MaintainRecord maintainRecord){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("更新维修保养记录");
                    maintainRecordService.update(maintainRecord);
                    return ControllerResult.getSuccessResult("更新成功");
                }
                logger.info("更新维修保养记录失败，没有权限操作");
                return ControllerResult.getFailResult("更新维修保养记录失败，没有权限操作");
            } catch (Exception e) {
                logger.info("更新维修保养记录失败，出现了一个错误");
                return ControllerResult.getFailResult("更新维修保养记录失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "send_remind", method = RequestMethod.POST)
    public ControllerResult sendRemind(SendRemind sendRemind) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("发送提车提醒");
                    String[] userIds = sendRemind.getUserId().split(",");
                    String[] recordIds = sendRemind.getRecordId().split(",");
                    String[] remindMethod = sendRemind.getRemindMethod().split(",");
                    String[] carPlates = sendRemind.getCarPlate().split(",");
                    List<Mail> mails = new ArrayList<Mail>();
                    for (int i = 0, len = userIds.length; i < len; i++) {
                        User user = userService.queryById(userIds[i]);
                        if (remindMethod.length == 2) {

                        } else {
                            if (remindMethod[0].equals("email")) {
                                Mail mail = new Mail();
                                mail.setRecipients(user.getUserEmail());
                                mail.setSubject(sendRemind.getRemindTitle());
                                mail.setType(Mail.HTML);
                                Multipart multipart = new MimeMultipart();
                                BodyPart part1 = new MimeBodyPart();
                                sendRemind.setRemindContent("<p>尊敬的" + user.getUserName() + "车主，车牌号为" + carPlates[i] + ",您的爱车已经整装待发，如果您有时间，请来领它回家哦^_^，如有问题，请联系0797-5201314</p>");
                                try {
                                    part1.setContent(sendRemind.getRemindContent(), mail.getType());
                                    multipart.addBodyPart(part1);
                                    mail.setMultipart(multipart);
                                    mails.add(mail);
                                } catch (MessagingException e) {
                                    e.printStackTrace();
                                    return ControllerResult.getFailResult("邮箱提醒发送失败");
                                }
                            } else if (remindMethod[0].equals("message")) {

                            }
                        }
                        maintainRecordService.updateSpeedStatusById(Constants.ALREADY_REMIND, recordIds[i]);
                    }
                    new Thread(new SendEmailThread(mails)).start();
                    return ControllerResult.getSuccessResult("提车提醒已成功发送");
                }
                logger.info("发送提车提醒失败，没有权限操作");
                return ControllerResult.getFailResult("发送提车提醒失败，没有权限操作");
            } catch (Exception e) {
                logger.info("发送提车提醒失败，出现了一个错误");
                return ControllerResult.getFailResult("发送提车提醒失败，出现了一个错误");
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

































