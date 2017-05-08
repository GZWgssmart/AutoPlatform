package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Supply;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.DateUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.SupplyService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Asa on 2017/4/17.
 */
@Controller
@RequestMapping("supply")
public class SupplyController {
        private Logger logger = (Logger) LoggerFactory.getLogger(SupplyTypeController.class);

        @Resource
        private SupplyService supplyService;

        @RequestMapping("/info")
        public String supplierInfo() {
            logger.info("进入供应商信息页");
            return "supply/supply_info";
        }

        @ResponseBody
        @RequestMapping("queryByPager")
        public Pager4EasyUI<Supply> queryByPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("status")String status) {
            logger.info("分页查询供应商分类");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            List<Supply> supplys = new ArrayList<Supply>();
            if (status.equals("ALL")) {
                pager.setTotalRecords(supplyService.count());
                supplys = supplyService.queryByPager(pager);
            } else {
                pager.setTotalRecords(supplyService.countByStatus(status));
                supplys = supplyService.queryPagerByStatus(pager, status);
            }
            return new Pager4EasyUI<Supply>(pager.getTotalRecords(), supplys);
        }

    @ResponseBody
    @RequestMapping(value = "conditionPager", method = RequestMethod.GET)
    public Pager4EasyUI<Supply> queryPagerByCondition(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,
                                                          @Param("supplyName")String supplyName, @Param("supplyPricipal")String supplyPricipal,
                                                          @Param("supplyTypeId")String supplyTypeId, @Param("companyId")String companyId) {
        logger.info("根据条件分页查询供应商分类");
        Supply supply = new Supply();
        supply.setSupplyName(supplyName);
        supply.setSupplyPricipal(supplyPricipal);
        supply.setSupplyTypeId(supplyTypeId);
        supply.setCompanyId(companyId);
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        List<Supply> supplys = new ArrayList<Supply>();
        pager.setTotalRecords(supplyService.countByCondition(supply));
        supplys = supplyService.queryPagerByCondition(pager, supply);
        return new Pager4EasyUI<Supply>(pager.getTotalRecords(), supplys);
    }

        @ResponseBody
        @RequestMapping("add")
        public ControllerResult add(Supply supply) {
            logger.info("添加供应商");
            supply.setSupplyTypeId("023e65a9-331a-11e7-b907-0a0027000015");
            supply.setCompanyId("76eeb0f2-3315-11e7-b907-0a0027000015");
            supply.setSupplyStatus("Y");
            supplyService.insert(supply);
            return ControllerResult.getSuccessResult("添加成功");
        }

        @ResponseBody
        @RequestMapping("edit")
        public ControllerResult update(Supply supply) {
            logger.info("修改供应商");
            supplyService.update(supply);
            return ControllerResult.getSuccessResult("修改成功");
        }

        @ResponseBody
        @RequestMapping(value = "updateStatus", method = RequestMethod.GET)
        public ControllerResult updateStatus(@Param("id")String id, @Param("status")String status) {
            logger.info("更新供应商状态");
            if (status.equals("Y")) {
                supplyService.active(id);
            }else if (status.equals("N")) {
                supplyService.inactive(id);
            }
            return ControllerResult.getSuccessResult("更新成功");
        }

    @ResponseBody
    @RequestMapping(value = "queryAll", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAll() {
        logger.info("查询供应商");
        List<Supply> supplyList = supplyService.queryAll();
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (Supply supplys : supplyList) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(supplys.getSupplyId());
            comboBox4EasyUI.setText(supplys.getSupplyName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }

}
