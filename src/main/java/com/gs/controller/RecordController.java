package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Checkin;
import com.gs.bean.Complaint;
import com.gs.bean.MaintainRecord;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.DateFormatUtil;
import com.gs.common.util.DateParseUtil;
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
 * Created by JangoGuo on 2017/4/18.
 */
@Controller
@RequestMapping("record")
public class RecordController {

    private Logger logger = (Logger) LoggerFactory.getLogger(RecordController.class);


    @Resource
    private MaintainRecordService maintainRecordService;


    @RequestMapping(value = "record_page", method = RequestMethod.GET)
    public String recordPage() {
        logger.info("显示维修保养记录管理页面");
        return "maintenanceReception/mainterance_record";
    }

    @ResponseBody
    @RequestMapping(value="pager",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("status") String status){
        logger.info("分页查询指定状态的维修保养记录管理");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        List<MaintainRecord> maintainRecordList = new ArrayList<MaintainRecord>();
        if (status.equals("ALL")) {
            pager.setTotalRecords(maintainRecordService.count());
            maintainRecordList = maintainRecordService.queryByPager(pager);
        } else {
            pager.setTotalRecords(maintainRecordService.countByStatus(status));
            maintainRecordList = maintainRecordService.queryPagerByStatus(pager, status);
        }
        return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecordList);
    }

    @ResponseBody
    @RequestMapping(value="pager_track",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPagerByTractStatus(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询回访状态的维修保养记录管理");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        List<MaintainRecord> maintainRecordList = new ArrayList<MaintainRecord>();
        pager.setTotalRecords(maintainRecordService.countByTrackStatus("N"));
        maintainRecordList = maintainRecordService.queryPagerByTrackStatus(pager,"N");
        return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecordList);
    }


    @ResponseBody
    @RequestMapping(value = "condition_pager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPagerByCondition(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,
                                                       @Param("userName")String userName, @Param("carPlate")String carPlate,
                                                       @Param("maintainOrFix")String maintainOrFix,
                                                       @Param("companyId")String companyId) {
        logger.info("根据条件分页查询维修保养记录");
        MaintainRecord record = new MaintainRecord();
        Checkin checkin = new Checkin();
        checkin.setUserName(userName);
        checkin.setCarPlate(carPlate);
        checkin.setMaintainOrFix(maintainOrFix);
        record.setCheckin(checkin);
        record.setCompanyId(companyId);
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        List<MaintainRecord> records = new ArrayList<MaintainRecord>();
        pager.setTotalRecords(maintainRecordService.countByCondition(record));
        records = maintainRecordService.queryPagerByCondition(pager, record);

        return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), records);
    }

    @ResponseBody
    @RequestMapping(value="update_status", method=RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status")String status){
        logger.info("更新维修保养记录状态");
        if(status.equals("Y")){
            maintainRecordService.inactive(id);
        }else if(status.equals("N")){
            maintainRecordService.active(id);

        }
        return ControllerResult.getSuccessResult("更新成功");
    }

    @ResponseBody
    @RequestMapping(value="edit", method=RequestMethod.POST)
    public ControllerResult editMainteranceRecord(MaintainRecord maintainRecord){
        logger.info("更新维修保养记录");
        maintainRecord.setCompanyId("65dc09ac-23e2-11e7-ba3e-juyhgt91a73a");
        maintainRecordService.update(maintainRecord);
        return ControllerResult.getSuccessResult("更新成功");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}

































