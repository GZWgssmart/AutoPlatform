package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.CarBrand;
import com.gs.bean.Checkin;
import com.gs.bean.Company;
import com.gs.bean.IncomingType;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.UUIDUtil;
import com.gs.service.CarBrandService;
import com.gs.service.CheckinService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-04-16.
 *
 * 登记表对应的Controller
 */
@Controller
@RequestMapping("checkin")
public class CheckinController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CheckinController.class);

    @Resource
    private CheckinService checkinService;

    @RequestMapping(value = "checkin_page", method = RequestMethod.GET)
    public String checkinPage() {
        logger.info("访问登记页面");
        return "maintenanceReception/reception_register";
    }

    @ResponseBody
    @RequestMapping(value = "checkin_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Checkin> checkinPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        logger.info("分页查询登记记录");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(checkinService.count());
        List<Checkin> checkins = checkinService.queryByPager(pager);
        return new Pager4EasyUI<Checkin>(pager.getTotalRecords(), checkins);
    }

    @ResponseBody
    @RequestMapping("add")
    public ControllerResult addCheckin(Checkin checkin) {

        logger.info("添加登记记录");
        Company company = new Company();
        company.setCompanyId("65dc09ac-23e2-11e7-ba3e-juyhgt91a73a");
        checkin.setCompany(company);
        checkin.setCheckinStatus("Y");
        checkinService.insert(checkin);
        return ControllerResult.getSuccessResult("添加成功");
    }



}
