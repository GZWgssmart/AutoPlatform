package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesBuy;
import com.gs.bean.AccessoriesType;
import com.gs.bean.Company;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.UUIDUtil;
import com.gs.service.AccessoriesBuyService;
import com.gs.service.AccessoriesService;
import com.gs.service.AccessoriesTypeService;
import com.gs.service.CompanyService;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Levc on 2017/4/17.
 */
@Controller
@RequestMapping("accessoriesBuy")
public class AccessoriesBuyController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AccessoriesBuyController.class);

    @Resource
    private AccessoriesBuyService accessoriesBuyService;

    @Resource
    private AccessoriesService accessoriesService;

    @Resource
    private AccessoriesTypeService accessoriesTypeService;

    @Resource
    private CompanyService companyService;

    /**
     * 显示配件采购管理
     *
     * @return String
     */
    @RequestMapping("showAccessoriesBuyHome")
    private String showAccessoriesBuyHome() {
        logger.info("显示采购主页");
        return "accessories/accessories_buy";
    }

    @ResponseBody
    @RequestMapping(value = "isAccAdd", method = RequestMethod.POST)
    private ControllerResult isAccAdd(AccessoriesBuy accessoriesBuy, @Param("state") String state) {
        logger.info("添加采购信息");

        Accessories acc = accessoriesBuy.getAccessories();

        if (state.equals("true")) {  // 如果为 true 库存添加
            logger.info("库存添加");
            System.out.println("库存添加");
            acc.setAccName(accessoriesBuy.getAccessories().getAccName());
            accessoriesBuy.setAccId(accessoriesBuy.getAccId());
            accessoriesBuyService.insert(accessoriesBuy);
            accessoriesService.insert(acc);
            return ControllerResult.getSuccessResult("添加成功");
        } else if (state.equals("false")) { // 如果为false采购添加
            logger.info("采购添加");
            System.out.println("采购添加");

            acc.setAccId(UUIDUtil.uuid());
            acc.setAccName(accessoriesBuy.getAccessories().getAccName());
            acc.setAccUnit(accessoriesBuy.getAccUnit());

            AccessoriesType accessoriesType = accessoriesBuy.getAccessories().getAccessoriesType();
            accessoriesType.setAccTypeName(accessoriesBuy.getAccessories().getAccessoriesType().getAccTypeName());
            accessoriesType.setAccTypeId(UUIDUtil.uuid());

            accessoriesBuy.setAccessories(acc);
            accessoriesBuy.setAccId(acc.getAccId());
            acc.setAccTypeId(accessoriesType.getAccTypeId());

            accessoriesBuyService.insert(accessoriesBuy);
            accessoriesService.insert(acc);
            accessoriesTypeService.insert(accessoriesType);

            return ControllerResult.getSuccessResult("添加成功");
        }
        return ControllerResult.getSuccessResult("添加成功");
    }

    @ResponseBody
    @RequestMapping(value = "pager", method = RequestMethod.GET)

    private Pager4EasyUI<AccessoriesBuy> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        logger.info("分页查询采购信息");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(accessoriesBuyService.count());
        List<AccessoriesBuy> accessoriesBuys = accessoriesBuyService.queryByPager(pager);
        return new Pager4EasyUI<AccessoriesBuy>(pager.getTotalRecords(), accessoriesBuys);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ResponseBody
    @RequestMapping(value = "updateStatus", method = RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status") String status) {
        logger.info("更新收入类型状态");
        if (status.equals("Y")) {
            accessoriesBuyService.active(id);
        } else if (status.equals("N")) {
            accessoriesBuyService.inactive(id);
        }
        return ControllerResult.getSuccessResult("更新成功");
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult updateAccessoriesBuyInfo(AccessoriesBuy accessoriesBuy) {
        Accessories acc = accessoriesBuy.getAccessories();
        AccessoriesType accessoriesType = acc.getAccessoriesType();

        int total = accessoriesBuy.getAccBuyCount() + acc.getAccIdle();

        acc.setAccIdle(total);
        accessoriesBuy.setAccBuyCount(total);
        acc.setAccUnit(accessoriesBuy.getAccUnit());
        acc.setAccTypeId(accessoriesType.getAccTypeId());

        accessoriesBuyService.update(accessoriesBuy);
        accessoriesService.update(acc);
        accessoriesTypeService.update(accessoriesType);

        return ControllerResult.getSuccessResult("更新成功");
    }

    @ResponseBody
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public ControllerResult remove(@Param("id") String id, @Param("status") String status) {
        if (status.equals("N")) {
            return ControllerResult.getFailResult("采购信息正在审核中，无法删除");
        } else {
            accessoriesBuyService.deleteById(id);
            return ControllerResult.getSuccessResult("删除成功");
        }
    }

    @ResponseBody
    @RequestMapping(value = "batchDelete", method = RequestMethod.GET)
    public ControllerResult batchDelete(@Param("accBuyArr") String[] accBuyArr) {
        accessoriesBuyService.batchDeleteAcc(accBuyArr);
        return ControllerResult.getSuccessResult("删除成功");
    }

    @ResponseBody
    @RequestMapping(value = "onlyCheck", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesBuy> onlyCheck(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(accessoriesBuyService.countByCheckState("Y"));
        List<AccessoriesBuy> accessoriesBuys = accessoriesBuyService.queryByCheckStatePager(pager);
        return new Pager4EasyUI<AccessoriesBuy>(pager.getTotalRecords(), accessoriesBuys);
    }

    @ResponseBody
    @RequestMapping(value = "onlyBuy", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesBuy> onlyBuy(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(accessoriesBuyService.countByBuyState("Y"));
        List<AccessoriesBuy> accessoriesBuys = accessoriesBuyService.queryByBuyStatePager(pager);
        return new Pager4EasyUI<AccessoriesBuy>(pager.getTotalRecords(), accessoriesBuys);
    }

    @ResponseBody
    @RequestMapping(value = "byAccNameSearch", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesBuy> byAccNameSearch(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("accName") String accName, @Param("buyTimeStart") String buyTimeStart, @Param("buyTimeEnd") String buyTimeEnd) {
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(accessoriesBuyService.countByBuyTimeScope(accName, buyTimeStart, buyTimeEnd));
        List<AccessoriesBuy> accessoriesBuys = accessoriesBuyService.queryByBuyTimeScopeByAccNamePager(pager, accName, buyTimeStart, buyTimeEnd);
        return new Pager4EasyUI<AccessoriesBuy>(pager.getTotalRecords(), accessoriesBuys);
    }
}