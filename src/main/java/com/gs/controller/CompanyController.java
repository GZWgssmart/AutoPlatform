package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Company;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.dao.CompanyDAO;
import com.gs.service.CompanyService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
@Controller
@RequestMapping("company")
public class CompanyController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CompanyController.class);

    @Resource
    private CompanyService companyService;

    @RequestMapping(value = "home", method = RequestMethod.GET)
    private String home() {
        logger.info("访问公司的主页");
        return "company/home";
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showCompanyInfo() {
        logger.info("显示公司基本信息");
        return "company/company_info";
    }

    @RequestMapping(value = "brand", method = RequestMethod.GET)
    private String showCarBrand() {
        logger.info("显示汽车品牌");
        return "company/car_brand";
    }

    @RequestMapping(value = "color", method = RequestMethod.GET)
    private String showCarColor() {
        logger.info("显示汽车颜色");
        return "company/car_colour";
    }

    @RequestMapping(value = "model", method = RequestMethod.GET)
    private String showCarModel() {
        logger.info("显示汽车车型");
        return "company/car_model";
    }

    @RequestMapping(value = "plate", method = RequestMethod.GET)
    private String showCarPlate() {
        logger.info("显示车牌");
        return "company/car_plate";
    }

    @RequestMapping(value ="InsertCompany",method = RequestMethod.POST)
    public ControllerResult InsetCompany(Company company){
        logger.info("添加公司");
        companyService.insert(company);
        return ControllerResult.getSuccessResult("添加公司成功");
    }

    @ResponseBody
    @RequestMapping(value = "uploadCompany", method = RequestMethod.POST)
    public ControllerResult uploadCarModel(Company company) {
        companyService.update(company);
        logger.info("更新公司成功");
        return ControllerResult.getSuccessResult("更新公司成功");
    }

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<Company> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        Pager pager = new Pager();
        logger.info("分页查询所有公司");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(companyService.count());
        List<Company> carModelList = companyService.queryByPager(pager);
        return new Pager4EasyUI<Company>(pager.getTotalRecords(), carModelList);
    }
}
