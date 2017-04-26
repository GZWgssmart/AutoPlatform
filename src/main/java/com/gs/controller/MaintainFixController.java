package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.MaintainFix;
import com.gs.common.bean.ComboBox4EasyUI;
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
import java.util.ArrayList;
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
        maintainFix.setMaintainOrFix("维修");
        maintainFixService.insert(maintainFix);
        return ControllerResult.getSuccessResult("添加维修项目成功");
    }

    @ResponseBody
    @RequestMapping(value = "InsertMaintain",method = RequestMethod.POST)
    public ControllerResult InsertMaintain(MaintainFix maintainFix){
        maintainFix.setMaintainOrFix("保养");
        maintainFixService.insert(maintainFix);
        return ControllerResult.getSuccessResult("添加保养项目成功");
    }

    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ControllerResult UpdateMaintainFix(MaintainFix maintainFix){
        System.out.println(maintainFix);
        maintainFixService.update(maintainFix);
        return ControllerResult.getSuccessResult("更新保养项目成功");
    }

    @ResponseBody
    @RequestMapping(value="StatusModify",method = RequestMethod.GET)
    public ControllerResult companyStatusModify(@Param("id") String id,@Param("status") String status){
        if(status.equals("Y")){
            logger.info("冻结成功");
            maintainFixService.inactive(id);
            return ControllerResult.getSuccessResult("冻结成功");
        }else if(status.equals("N")){
            maintainFixService.active(id);
            return ControllerResult.getSuccessResult("激活成功");
        }
        return ControllerResult.getFailResult("冻结失败");
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
        List<MaintainFix> maintainFixList = maintainFixService.queryBymaintainPager(pager);
        return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixList);
    }

    @ResponseBody
    @RequestMapping(value = "maintain_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryUserAll() {
        logger.info("查询所有维修保养项目，封装成ComboBox");
        List<MaintainFix> maintainFices = maintainFixService.queryAll();
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (MaintainFix maintainFix : maintainFices) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(maintainFix.getMaintainId());
            comboBox4EasyUI.setText(maintainFix.getMaintainName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }
}
