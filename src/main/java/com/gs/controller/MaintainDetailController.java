package com.gs.controller;

import com.gs.bean.*;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.MaintainDetailService;
import com.gs.service.MaintainFixAccService;
import com.gs.service.MaterialListService;
import com.gs.service.WorkInfoService;
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
import java.util.ArrayList;
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

    @Resource
    private MaintainFixAccService maintainFixAccService;

    @Resource
    private MaterialListService materialListService;

    @Resource
    private WorkInfoService workInfoService;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult addMaintainDetail(MaintainDetail detail) {
        if (SessionGetUtil.isUser()) {
            logger.info("添加维修保养明细");
            detailService.insert(detail);
            return ControllerResult.getSuccessResult("添加成功");
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value="pager",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainDetail> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("recordId") String recordId){
        if (SessionGetUtil.isUser()) {
            logger.info("分页查询指定维修保养记录的所有明细");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            pager.setTotalRecords(detailService.countByRecordId(recordId));
            List<MaintainDetail> maintainDetails = detailService.queryPagerByRecordId(pager, recordId);
            return new Pager4EasyUI<MaintainDetail>(pager.getTotalRecords(), maintainDetails);
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ControllerResult editMaintainDetail(MaintainDetail detail) {
        if (SessionGetUtil.isUser()) {
            logger.info("修改维修保养明细记录");
            detailService.update(detail);
            return ControllerResult.getSuccessResult("修改成功");
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "confirm", method = RequestMethod.GET)
    public ControllerResult userConfirm(@Param("recordId") String recordId, @Param("maintainIds") String maintainIds) {
        if (SessionGetUtil.isUser()) {
            logger.info("用户签字确认");
            String[] maintainIds1 = maintainIds.split(",");
            List<MaintainFixAcc> maintainFixAccs = maintainFixAccService.queryAllByMaintainId(maintainIds1);
            List<MaterialList> materialLists = new ArrayList<MaterialList>();
            for (MaintainFixAcc maintainFixAcc : maintainFixAccs) {
                MaterialList materialList = new MaterialList();
                materialList.setRecordId(recordId);
                materialList.setAccId(maintainFixAcc.getAccId());
                materialList.setMaterialCount(maintainFixAcc.getAccCount());
                materialLists.add(materialList);
            }
            WorkInfo workInfo = new WorkInfo();
            workInfo.setRecordId(recordId);

            workInfoService.insert(workInfo);
            materialListService.batchInsert(materialLists);
            return ControllerResult.getSuccessResult("用户已经确认签字，工单信息和物料清单已经自动生成");
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
