package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesBuy;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.UUIDUtil;
import com.gs.service.AccessoriesBuyService;
import com.gs.service.AccessoriesService;
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

/**
 * Created by Levc on 2017/4/17.
 */
@Controller
@RequestMapping("accessoriesBuy")
public class AccessoriesBuyController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AccessoriesBuyController.class);

    @Resource
    AccessoriesBuyService accessoriesBuyService;
    @Resource
    AccessoriesService accessoriesService;

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
        Accessories acc = new Accessories();
        if (state.equals("true")) {  // 如果为 true 库存添加
            System.out.println("库存添加");
            acc.setAccName(accessoriesBuy.getAccessories().getAccName());
            accessoriesBuy.setAccId(accessoriesBuy.getAccId());
            accessoriesBuyService.insert(accessoriesBuy);
            accessoriesService.insert(acc);
        } else if (state.equals("false")) { // 如果为false采购添加
            System.out.println("采购添加");
            acc.setAccId(UUIDUtil.uuid());
            acc.setAccName(accessoriesBuy.getAccessories().getAccName());
            acc.setAccUnit(accessoriesBuy.getAccUnit());
            accessoriesBuy.setAccessories(acc);
            accessoriesBuy.setAccId(acc.getAccId());
            accessoriesBuyService.insert(accessoriesBuy);
            accessoriesService.insert(acc);
        }
//        Accessories accessories = accessoriesService.queryById(accessoriesBuy.getAccId());
//        accessories.setAccIdle(accessories.getAccIdle() + accessoriesBuy.getAccBuyCount());
//        accessoriesService.update(accessories);
   /*
        Accessories accessories = new Accessories();
        accessories.setAccId(accessoriesBuy.getAccId());
        accessories.setAccUnit(accessoriesBuy.getAccUnit());
        accessories.setAccName(accessoriesBuy.getAccessories().getAccName());
        accessories.setAccBuyedTime(accessoriesBuy.getAccBuyTime());
        accessories.setAccPrice(accessoriesBuy.getAccBuyPrice());
        accessoriesService.insert(accessories);*/


//        accessoriesBuyService.insert(accessoriesBuy);

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
        Accessories acc = new Accessories();
        accessoriesBuyService.update(accessoriesBuy);
        acc.setAccUnit(accessoriesBuy.getAccUnit());
        accessoriesService.update(acc);
        return ControllerResult.getSuccessResult("更新成功");
    }

}