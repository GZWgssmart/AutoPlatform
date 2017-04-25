package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.SupplyType;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.PagerUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.SupplyTypeService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Asa on 2017/4/14.
 */
@Controller
@RequestMapping("supplyType")
public class SupplyTypeController {

    private Logger logger = (Logger) LoggerFactory.getLogger(SupplyTypeController.class);

    @Resource
    private SupplyTypeService supplyTypeService;

    @RequestMapping("/type")
    public String supplierType() {
        logger.info("进入供应商分类页");
        return "supply/supply_type";
    }

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<SupplyType> queryByPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        logger.info("分页查询供应商分类");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(supplyTypeService.count());
        List<SupplyType> supplyTypes = supplyTypeService.queryByPager(pager);
        return new Pager4EasyUI<SupplyType>(pager.getTotalRecords(), supplyTypes);
    }

    @ResponseBody
    @RequestMapping("add")
    public ControllerResult add(SupplyType supplyType) {
        logger.info("添加供应商分类");
        supplyType.setCompanyId(UUIDUtil.uuid());
        supplyTypeService.insert(supplyType);
        return ControllerResult.getSuccessResult("添加成功");
    }

    @ResponseBody
    @RequestMapping("edit")
    public ControllerResult edit(SupplyType supplyType) {
        logger.info("修改供应商分类");
        supplyTypeService.update(supplyType);
        return ControllerResult.getSuccessResult("修改成功");
    }

    @ResponseBody
    @RequestMapping(value =  "updateStatus", method = RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id")String id, @Param("status")String status) {
        logger.info("更新供应商分类状态");
        if (status.equals("Y")) {
            supplyTypeService.active(id);
        }else if (status.equals("N")) {
            supplyTypeService.inactive(id);
        }
        return ControllerResult.getSuccessResult("更新成功");
    }

}