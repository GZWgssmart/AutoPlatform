package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.MaintainFix;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;

import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.MaintainFixService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by root on 2017/4/21.
 */
@Controller
@RequestMapping("/maintainFix")
public class MaintainFixController {
    private Logger logger = (Logger) LoggerFactory.getLogger(MaintainFixController.class);
    @Resource
    private MaintainFixService maintainFixService;

    @ResponseBody
    @RequestMapping(value = "InsertMaintainItem",method = RequestMethod.POST)
    public ControllerResult InsertMaintainFix(MaintainFix maintainFix){
        maintainFix.setMaintainOrFix("Y");//Y表示维修，N表示保养
        maintainFixService.insert(maintainFix);
        return ControllerResult.getSuccessResult("添加维修项目成功");
    }

    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ControllerResult UpdateMaintainFix(MaintainFix maintainFix){
        maintainFixService.update(maintainFix);
        return ControllerResult.getSuccessResult("更新保养项目成功");
    }

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainFix> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        Pager pager = new Pager();
        logger.info("分页查询所有维修项目");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(maintainFixService.count());
        List<MaintainFix> maintainFixList = maintainFixService.queryByPager(pager);
        return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixList);
    }

    @ResponseBody
    @RequestMapping(value = "queryByMaintenanceItemPager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainFix> queryByMaintenanceItemPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        Pager pager = new Pager();
        logger.info("分页查询所有保养项目");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(maintainFixService.count());
        List<MaintainFix> maintainFixList = maintainFixService.queryByPager(pager);
        return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixList);
    }
}
