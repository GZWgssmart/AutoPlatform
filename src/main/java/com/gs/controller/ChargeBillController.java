package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.ChargeBill;
import com.gs.bean.Checkin;
import com.gs.bean.MaintainRecord;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.ChargeBillService;
import com.gs.service.MaintainRecordService;
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

    @RequestMapping(value = "bill_page", method = RequestMethod.GET)
    public String chargeBillPage() {
        logger.info("访问收费单据页面");
        return "settlementCar/charge_document";
    }

    @ResponseBody
    @RequestMapping(value="pager",method= RequestMethod.GET)
    public Pager4EasyUI<ChargeBill> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("status") String status){
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
    }

    @ResponseBody
    @RequestMapping(value = "condition_pager", method = RequestMethod.GET)
    public Pager4EasyUI<ChargeBill> queryPagerByCondition(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,
                                                       @Param("userName")String userName, @Param("userPhone")String userPhone,
                                                       @Param("paymentMethod")String paymentMethod) {
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
    }

    @ResponseBody
    @RequestMapping(value = "update_status", method = RequestMethod.GET)
    public ControllerResult updateChargeBillStatus(String id, String status) {
        logger.info("更新收费单据记录的状态");
        if (status.equals("Y")) {
            chargeBillService.inactive(id);
        } else {
            chargeBillService.active(id);
        }
        return ControllerResult.getSuccessResult("更新成功");
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult addChargeBill(ChargeBill chargeBill) {
        logger.info("添加收费单据");
        chargeBillService.insert(chargeBill);
        maintainRecordService.updateSpeedStatusById(Constants.COMPLETED, chargeBill.getRecordId());
        return ControllerResult.getSuccessResult("已经成功结算，收费单据已经自动生成");
    }

    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ControllerResult editChargeBill(ChargeBill chargeBill) {
        logger.info("修改收费单据");
        chargeBillService.update(chargeBill);
        return ControllerResult.getSuccessResult("修改成功");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
