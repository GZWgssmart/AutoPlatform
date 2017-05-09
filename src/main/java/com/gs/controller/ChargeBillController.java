package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.SessionGetUtil;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-04-30. 收费单据控制器
 */
@Controller
@RequestMapping("bill")
public class ChargeBillController {
    private Logger logger = (Logger) LoggerFactory.getLogger(ChargeBillController.class);

    @Resource
    private ChargeBillService chargeBillService;

    @Resource
    private MaintainRecordService maintainRecordService;

    @Resource
    private CheckinService checkinService;

    @Resource
    private MaintainRemindService maintainRemindService;

    @Resource
    private IncomingTypeService incomingTypeService;

    @Resource
    private IncomingOutgoingService incomingOutgoingService;

    @RequestMapping(value = "bill_page", method = RequestMethod.GET)
    public String chargeBillPage() {
        if (SessionGetUtil.isUser()) {
            logger.info("访问收费单据页面");
            return "settlementCar/charge_document";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    @RequestMapping(value = "statement_page", method = RequestMethod.GET)
    public String statementPage() {
        if (SessionGetUtil.isUser()) {
            logger.info("访问对账单页面");
            return "financeManage/account_statement";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    @ResponseBody
    @RequestMapping(value="pager",method= RequestMethod.GET)
    public Pager4EasyUI<ChargeBill> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("status") String status){
        if (SessionGetUtil.isUser()) {
            logger.info("分页查询指定状态的收费单据数据");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            List<ChargeBill> chargeBillList = new ArrayList<ChargeBill>();
            if (status.equals("ALL")) {
                pager.setTotalRecords(chargeBillService.count());
                chargeBillList = chargeBillService.queryByPager(pager);
            } else {
                pager.setTotalRecords(chargeBillService.countByStatus(status));
                chargeBillList = chargeBillService.queryPagerByStatus(pager, status);
            }
            return new Pager4EasyUI<ChargeBill>(pager.getTotalRecords(), chargeBillList);
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "condition_pager", method = RequestMethod.GET)
    public Pager4EasyUI<ChargeBill> queryPagerByCondition(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,
                                                       @Param("userName")String userName, @Param("userPhone")String userPhone,
                                                       @Param("paymentMethod")String paymentMethod) {
        if (SessionGetUtil.isUser()) {
            logger.info("根据条件分页查询收费单据记录");
            ChargeBill chargeBill = new ChargeBill();
            chargeBill.setPaymentMethod(paymentMethod);
            MaintainRecord record = new MaintainRecord();
            Checkin checkin = new Checkin();
            checkin.setUserName(userName);
            checkin.setUserPhone(userPhone);
            record.setCheckin(checkin);
            chargeBill.setRecord(record);

            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            List<ChargeBill> chargeBills = new ArrayList<ChargeBill>();
            pager.setTotalRecords(chargeBillService.countByCondition(chargeBill));
            chargeBills = chargeBillService.queryPagerByCondition(pager, chargeBill);

            return new Pager4EasyUI<ChargeBill>(pager.getTotalRecords(), chargeBills);
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "update_status", method = RequestMethod.GET)
    public ControllerResult updateChargeBillStatus(String id, String status) {
        if (SessionGetUtil.isUser()) {
            logger.info("更新收费单据记录的状态");
            if (status.equals("Y")) {
                chargeBillService.inactive(id);
            } else {
                chargeBillService.active(id);
            }
            return ControllerResult.getSuccessResult("更新成功");
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult addChargeBill(@Param("chargeBill") ChargeBill chargeBill, @Param("userId") String userId, @Param("carMileage") String carMileage) {
        if (SessionGetUtil.isUser()) {
            try {
                logger.info("结算提车，生成收费单据，生成维修保养提醒记录");
                User loginUser = SessionGetUtil.getUser();
                MaintainRemind maintainRemind = new MaintainRemind();
                maintainRemind.setLastMaintainTime(new Date());
                maintainRemind.setCompanyId(loginUser.getCompanyId());
                maintainRemind.setUserId(userId);
                maintainRemind.setLastMaintainMileage(carMileage);

                IncomingType incomingType = incomingTypeService.queryByName(Constants.MAINTENANCE_IN);
                IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
                incomingOutgoing.setInTypeId(incomingType.getInTypeId());
                incomingOutgoing.setInOutCreatedUser(loginUser.getUserId());
                incomingOutgoing.setInOutMoney(chargeBill.getActualPayment());
                incomingOutgoing.setCompanyId(loginUser.getCompanyId());

                incomingOutgoingService.insert(incomingOutgoing);
                maintainRecordService.updatePickupTime(chargeBill.getRecordId());
                maintainRemindService.insert(maintainRemind);
                chargeBillService.insert(chargeBill);
                maintainRecordService.updateSpeedStatusById(Constants.COMPLETED, chargeBill.getRecordId());
                checkinService.inactive(chargeBill.getRecord().getCheckinId());
                return ControllerResult.getSuccessResult("已经成功结算，收费单据已经自动生成");
            } catch (Exception e) {
                logger.info("结算提车失败，出现了一个错误");
                return ControllerResult.getFailResult("结算提车失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ControllerResult editChargeBill(ChargeBill chargeBill) {
        if (SessionGetUtil.isUser()) {
            try {
                logger.info("修改收费单据");
                chargeBillService.update(chargeBill);
                return ControllerResult.getSuccessResult("修改成功");
            } catch (Exception e) {
                logger.info("修改收费单据失败，出现了一个错误");
                return ControllerResult.getFailResult("修改收费单据失败，出现了一个错误");
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
