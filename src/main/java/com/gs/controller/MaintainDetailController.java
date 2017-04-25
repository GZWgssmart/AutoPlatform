package com.gs.controller;

import com.gs.bean.Checkin;
import com.gs.bean.MaintainDetail;
import com.gs.bean.MaintainRecord;
import com.gs.bean.WorkInfo;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.UUIDUtil;
import com.gs.service.MaintainDetailService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
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
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-04-24. 维修保养明细控制器
 */
@Controller
@RequestMapping("detail")
public class MaintainDetailController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaintainDetailController.class);

    @Resource
    private MaintainDetailService detailService;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult addMaintainDetail(MaintainDetail detail) {
        logger.info("添加维修保养明细");
        detailService.insert(detail);
        return ControllerResult.getSuccessResult("添加成功");
    }

    @ResponseBody
    @RequestMapping(value="pager",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainDetail> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("recordId") String recordId){
        logger.info("分页查询指定维修保养记录的所有明细");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(detailService.countByRecordId(recordId));
        List<MaintainDetail> maintainDetails = detailService.queryPagerByRecordId(pager, recordId);
        return new Pager4EasyUI<MaintainDetail>(pager.getTotalRecords(), maintainDetails);
    }

    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ControllerResult editMaintainDetail(MaintainDetail detail) {
        logger.info("修改维修保养明细记录");
        detailService.update(detail);
        return ControllerResult.getSuccessResult("修改成功");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
