package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.AccessoriesBuy;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.UUIDUtil;
import com.gs.service.AccessoriesBuyService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
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
    @RequestMapping(value = "addAccessoriesBuyInfo", method = RequestMethod.POST)
    private ControllerResult addAccessoriesBuyInfo(AccessoriesBuy accessoriesBuy, String accName, String accTypeName, String companyId) throws ParseException {
        logger.info("添加采购信息");
        AccessoriesBuy ab = new AccessoriesBuy();
        if (accessoriesBuy.getAccBuyMoney() != 0 && accessoriesBuy.getAccBuyCount() >= 1 && accessoriesBuy.getAccBuyPrice() >= 0
                && accessoriesBuy.getAccBuyTotal() >= 0 && accessoriesBuy.getAccBuyDiscount() > 0 && accessoriesBuy.getAccBuyMoney() > 0
                && !accName.equals("") && !accTypeName.equals("")) {
            ab.setAccBuyCount(accessoriesBuy.getAccBuyCount());
            ab.setAccBuyPrice(accessoriesBuy.getAccBuyPrice());
            ab.setAccBuyTotal(accessoriesBuy.getAccBuyTotal());
            ab.setAccBuyDiscount(accessoriesBuy.getAccBuyDiscount());
            ab.setAccBuyMoney(accessoriesBuy.getAccBuyMoney());
            ab.setAccBuyId(UUIDUtil.uuid());
            accessoriesBuyService.insert(ab);
            return ControllerResult.getSuccessResult("添加成功");
        } else return ControllerResult.getFailResult("添加失败");
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
}