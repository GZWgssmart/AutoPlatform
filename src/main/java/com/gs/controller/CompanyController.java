package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Company;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.dao.CompanyDAO;
import com.gs.service.CompanyService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
 * Created by Administrator on 2017/4/1.
 */
@Controller
@RequestMapping("/company")
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

    @RequestMapping(value="maintainItem" , method = RequestMethod.GET)
    private String showMaintainItem (){
        logger.info("显示维修项目");
        return "company/maintain_item";
    }

    @RequestMapping(value="maintenanceItem" , method = RequestMethod.GET)
    private String showMaintainFix (){
        logger.info("显示保养项目");
        return "company/maintenance_item";
    }

    @ResponseBody
    @RequestMapping(value ="InsertCompany",method = RequestMethod.POST)
    public ControllerResult InsetCompany(Company company){
        System.out.println(company);
        logger.info("添加公司");
        company.setCompanyLogo("/upload/logo.jsp");
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
        List<Company> companyList = companyService.queryByPager(pager);
        return new Pager4EasyUI<Company>(pager.getTotalRecords(), companyList);
    }

    @ResponseBody
    @RequestMapping(value="companyStatusModify",method = RequestMethod.GET)
    public ControllerResult companyStatusModify(@Param("id") String id,@Param("status") String status){
        if(status.equals("Y")){
            logger.info("公司冻结成功");
            companyService.inactive(id);
            return ControllerResult.getSuccessResult("公司冻结成功");
        }else if(status.equals("N")){
            companyService.active(id);
            return ControllerResult.getSuccessResult("公司激活成功");
        }
        return ControllerResult.getFailResult("公司修改失败");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ResponseBody
    @RequestMapping(value = "company_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryUserAll() {
        logger.info("查询所有公司");
        List<Company> companyList = companyService.queryAll();
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (Company companys : companyList) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(companys.getCompanyId());
            comboBox4EasyUI.setText(companys.getCompanyName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }

}


