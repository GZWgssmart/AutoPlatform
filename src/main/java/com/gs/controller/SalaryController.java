package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.IncomingOutgoing;
import com.gs.bean.OutgoingType;
import com.gs.bean.Salary;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.IncomingOutgoingService;
import com.gs.service.OutgoingTypeService;
import com.gs.service.SalaryService;
import com.gs.service.UserService;
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
 * 工资管理
 * Created by Star on 2017/4/17.
 */

@Controller
@RequestMapping("/salary")
public class SalaryController {

    private Logger logger = (Logger) LoggerFactory.getLogger(SalaryController.class);

    @Resource
    private SalaryService salaryService;

    @Resource
    private UserService userService;

    @Resource
    private OutgoingTypeService outgoingTypeService;

    @Resource
    private IncomingOutgoingService incomingOutgoingService;

    @RequestMapping(value = "show_salary", method = RequestMethod.GET)
    public String salary() {
        logger.info("工资管理页面");
        return "financeManage/salary";
    }

    @ResponseBody
    @RequestMapping(value="query_pager",method= RequestMethod.GET)
    public Pager4EasyUI<Salary> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有工");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(salaryService.count());
        List<Salary> incomingTypes = salaryService.queryByPager(pager);
        return new Pager4EasyUI<Salary>(pager.getTotalRecords(), incomingTypes);
    }

    @ResponseBody
    @RequestMapping(value="add_salary", method=RequestMethod.POST)
    public ControllerResult incomingTypeAdd(Salary salary){
        logger.info("添加工资");
        User user = userService.queryById(salary.getUserId());
        OutgoingType outgoingType = outgoingTypeService.queryByName("工资支出");
        salary.setTotalSalary(user.getUserSalary() + salary.getPrizeSalary() - salary.getMinusSalary());
        salaryService.insert(salary);
        IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
        incomingOutgoing.setOutTypeId(outgoingType.getOutTypeId());
        incomingOutgoing.setInOutCreatedUser("8d98f16b-24cf-11e7-a41a-507b9d3ffd38");
        incomingOutgoing.setInOutMoney(salary.getTotalSalary());
        incomingOutgoing.setInOutStatus("Y");
        incomingOutgoingService.insert(incomingOutgoing);
        return ControllerResult.getSuccessResult("添加成功");

    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
