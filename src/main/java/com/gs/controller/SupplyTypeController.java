package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.SupplyType;
import com.gs.bean.User;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.PagerUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.SupplyTypeService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public Pager4EasyUI<SupplyType> queryByPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("status")String status) {
        logger.info("分页查询供应商分类");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        List<SupplyType> supplyTypes = new ArrayList<SupplyType>();
        if (status.equals("ALL")) {
            pager.setTotalRecords(supplyTypeService.count());
            supplyTypes = supplyTypeService.queryByPager(pager);
        } else {
            pager.setTotalRecords(supplyTypeService.countByStatus(status));
            supplyTypes = supplyTypeService.queryPagerByStatus(pager, status);
        }
        return new Pager4EasyUI<SupplyType>(pager.getTotalRecords(), supplyTypes);
    }

    @ResponseBody
    @RequestMapping("add")
    public ControllerResult add(SupplyType supplyType) {
        if (SessionGetUtil.isUser()) {
            try {
                logger.info("添加供应商分类");
                User loginUser = SessionGetUtil.getUser();
                supplyType.setCompanyId(loginUser.getCompanyId());
                supplyTypeService.insert(supplyType);
                return ControllerResult.getSuccessResult("添加成功");
            }catch (Exception e) {
                logger.info("添加供应商失败，出现了一个错误");
                return ControllerResult.getFailResult("添加供应商失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "conditionPager", method = RequestMethod.GET)
    public Pager4EasyUI<SupplyType> queryPagerByCondition(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,
                                                       @Param("supplyTypeName")String supplyTypeName, @Param("companyId")String companyId) {
        logger.info("根据条件分页查询供应商分类");
        SupplyType supplyType = new SupplyType();
        supplyType.setSupplyTypeName(supplyTypeName);
        supplyType.setCompanyId(companyId);
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        List<SupplyType> supplyTypes = new ArrayList<SupplyType>();
        pager.setTotalRecords(supplyTypeService.countByCondition(supplyType));
        supplyTypes = supplyTypeService.queryPagerByCondition(pager, supplyType);
        return new Pager4EasyUI<SupplyType>(pager.getTotalRecords(), supplyTypes);
    }

    @ResponseBody
    @RequestMapping("edit")
    public ControllerResult edit(SupplyType supplyType) {
        if (SessionGetUtil.isUser()) {
            try {
                logger.info("修改供应商分类");
                User loginUser = SessionGetUtil.getUser();
                supplyType.setCompanyId(loginUser.getCompanyId());
                supplyTypeService.update(supplyType);
                return ControllerResult.getSuccessResult("修改成功");
            }catch (Exception e) {
                logger.info("修改供应商失败，出现了一个错误");
                return ControllerResult.getFailResult("添加供应商失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
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

    @ResponseBody
    @RequestMapping(value = "queryAll", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAll() {
        logger.info("查询供应商分类");
        List<SupplyType> supplyTypeList = supplyTypeService.queryAll();
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (SupplyType supplyTypes : supplyTypeList) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(supplyTypes.getSupplyTypeId());
            comboBox4EasyUI.setText(supplyTypes.getSupplyTypeName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }

}