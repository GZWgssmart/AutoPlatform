package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesSale;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.UUIDUtil;
import com.gs.service.*;
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
@RequestMapping("accessoriesSale")
public class AccessoriesSaleController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AccessoriesSaleController.class);

    @Resource
    private AccessoriesSaleService accessoriesSaleService;

    @Resource
    private AccessoriesService accessoriesService;

    @Resource
    private AccessoriesTypeService accessoriesTypeService;

    @Resource
    private CompanyService companyService;

    @Resource
    private UserService userService;

    /**
     * 显示配件采购管理
     *
     * @return String
     */
    @RequestMapping("showAccessoriesSaleHome")
    private String showAccessoriesSaleHome() {
        logger.info("显示采购主页");
        return "accessories/accessories_sale";
    }

    @ResponseBody
    @RequestMapping(value = "pager", method = RequestMethod.GET)
    private Pager4EasyUI<AccessoriesSale> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        logger.info("分页查询采购信息");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(accessoriesSaleService.count());
        List<AccessoriesSale> accessoriesSales = accessoriesSaleService.queryByPager(pager);
        return new Pager4EasyUI<AccessoriesSale>(pager.getTotalRecords(), accessoriesSales);
    }


    @ResponseBody
    @RequestMapping(value = "updateStatus", method = RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status") String status) {
        logger.info("更新收入类型状态");
        if (status.equals("Y")) {
            accessoriesSaleService.active(id);
        } else if (status.equals("N")) {
            accessoriesSaleService.inactive(id);
        }
        return ControllerResult.getSuccessResult("更新成功");
    }


    @ResponseBody
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public ControllerResult remove(@Param("id") String id, @Param("status") String status) {
        if (status.equals("N")) {
            return ControllerResult.getFailResult("采购信息正在审核中，无法删除");
        } else {
            accessoriesSaleService.deleteById(id);
            return ControllerResult.getSuccessResult("删除成功");
        }
    }

    @ResponseBody
    @RequestMapping(value = "addSale", method = RequestMethod.POST)
    public ControllerResult addSale(AccessoriesSale accessoriesSale, String accLastCount) {
        accessoriesSale.setAccSaleId(UUIDUtil.uuid());
        accessoriesSaleService.insert(accessoriesSale);

        int intAccLastCount = Integer.valueOf(accLastCount);
        accessoriesService.updateIdle(accessoriesSale.getAccId(), intAccLastCount);

        return ControllerResult.getSuccessResult("添加成功");
    }


    @ResponseBody
    @RequestMapping(value = "isReAdd", method = RequestMethod.GET)
    public ControllerResult isRerAdd(@Param("userId") String userId, @Param("userName") String userName) {
        int ass = accessoriesSaleService.queryByUserIdIsSameResult(userId, userName);
        System.out.println(ass);
        if (ass != 0) {
            return ControllerResult.getSuccessResult("可用");
        } else return ControllerResult.getFailResult("重复用户");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
