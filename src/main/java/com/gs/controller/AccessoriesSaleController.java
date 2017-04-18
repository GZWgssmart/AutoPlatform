package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.AccessoriesSale;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.UUIDUtil;
import com.gs.service.AccessoriesSaleService;
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
@RequestMapping("accessoriesSale")
public class AccessoriesSaleController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AccessoriesSaleController.class);

    @Resource
    AccessoriesSaleService accessoriesSaleService;

    /**
     * 显示配件销售管理
     *
     * @return String
     */
    @RequestMapping("showAccessoriesSaleHome")
    private String showAccessoriesSaleHome() {
        logger.info("显示销售主页");
        return "accessories/accessories_sale";
    }

    @ResponseBody
    @RequestMapping(value = "addAccessoriesSaleInfo", method = RequestMethod.POST)
    private ControllerResult addAccessoriesSaleInfo(AccessoriesSale accessoriesSale, String accName, String accTypeName, String companyId) throws ParseException {
        logger.info("添加销售信息");
        AccessoriesSale ab = new AccessoriesSale();
        if (accessoriesSale.getAccSaleMoney() != 0 && accessoriesSale.getAccSaleCount() >= 1 && accessoriesSale.getAccSalePrice() >= 0
                && accessoriesSale.getAccSaleTotal() >= 0 && accessoriesSale.getAccSaleDiscount() > 0 && accessoriesSale.getAccSaleMoney() > 0
                && !accName.equals("") && !accTypeName.equals("")) {
            ab.setAccSaleCount(accessoriesSale.getAccSaleCount());
            ab.setAccSalePrice(accessoriesSale.getAccSalePrice());
            ab.setAccSaleTotal(accessoriesSale.getAccSaleTotal());
            ab.setAccSaleDiscount(accessoriesSale.getAccSaleDiscount());
            ab.setAccSaleMoney(accessoriesSale.getAccSaleMoney());
            ab.setAccSaleId(UUIDUtil.uuid());
            accessoriesSaleService.insert(ab);
            return ControllerResult.getSuccessResult("添加成功");
        } else return ControllerResult.getFailResult("添加失败");
    }

    @ResponseBody
    @RequestMapping(value = "pager", method = RequestMethod.GET)
    private Pager4EasyUI<AccessoriesSale> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        logger.info("分页查询销售信息");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(accessoriesSaleService.count());
        List<AccessoriesSale> accessoriesSales = accessoriesSaleService.queryByPager(pager);
        return new Pager4EasyUI<AccessoriesSale>(pager.getTotalRecords(), accessoriesSales);
    }
}