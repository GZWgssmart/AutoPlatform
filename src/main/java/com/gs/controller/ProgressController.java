package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.MaintainRecord;
import com.gs.bean.WorkInfo;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.MaintainRecordService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 温鑫
 * 维修保养进度管理
 * Created by Star on 2017/4/26.
 */
@Controller
@RequestMapping("progress")
public class ProgressController {


    @Resource
    private MaintainRecordService maintainRecordService;

    private Logger logger = (Logger) LoggerFactory.getLogger(ProgressController.class);

    @RequestMapping(value = "progress_page", method = RequestMethod.GET)
    public String progressInfo() {
        logger.info(" 维修保养进度页面");
        return "maintenanceProgress/car_maintenance_progress";
    }

    @ResponseBody
    @RequestMapping(value="progress_pager",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("status") String status, @Param("maintainRecord") MaintainRecord maintainRecord, HttpServletRequest request){
        logger.info("分页查询");
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
    @RequestMapping(value = "progress_byInfo", method = RequestMethod.GET)
    public ControllerResult ByInfo(@Param("id")String id, MaintainRecord maintainRecord, HttpServletRequest request){
        logger.info("根据id查询");
        System.out.println("aaaa");
        request.setAttribute("maintainRecord", "aaaaaaaaa");
        maintainRecordService.queryById(id);
        return ControllerResult.getSuccessResult("已查询");
    }

}
