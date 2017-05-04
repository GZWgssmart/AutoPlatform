package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.IncomingOutgoing;
import com.gs.bean.OutgoingType;
import com.gs.bean.Salary;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.DateFormatUtil;
import com.gs.common.util.ExcelExport;
import com.gs.common.util.ExcelRead;
import com.gs.common.util.ExcelUtil;
import com.gs.service.IncomingOutgoingService;
import com.gs.service.OutgoingTypeService;
import com.gs.service.SalaryService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        incomingOutgoing.setInOutCreatedUser("9fcd1df8-25c6-11e7-ba3e-708bcd91a73a");
        incomingOutgoing.setInOutMoney(salary.getTotalSalary());
        incomingOutgoingService.insert(incomingOutgoing);
        return ControllerResult.getSuccessResult("添加成功");

    }

    @ResponseBody
    @RequestMapping(value="update_salary", method=RequestMethod.POST)
    public ControllerResult incomingUpdate(Salary salary){
        logger.info("更新工资");
        User user = userService.queryById(salary.getUserId());
        salary.setTotalSalary(user.getUserSalary() + salary.getPrizeSalary() - salary.getMinusSalary());
        salaryService.update(salary);
        return ControllerResult.getSuccessResult("更新成功");
    }

    @RequestMapping(value = "export",method=RequestMethod.GET)
    public void exportExcel(HttpServletResponse response) {
        try {
            logger.info("导出工资");
            // 查询工资信息
            List<Salary> salarylist = salaryService.queryAll();
            String title = "员工工资信息";
            String[] rowsName = new String[]{"工资编号","员工名称", "奖金", "罚款", "总工资", "工资描述", "发放时间"};
            List<Object[]> dataList = new ArrayList<Object[]>();
            Object[] objs = null;
            for (int i = 0; i < salarylist.size(); i++) {
                Salary salary = salarylist.get(i);
                objs = new Object[rowsName.length];
                objs[0] = salary.getSalaryId();
                objs[1] = salary.getUser().getUserName();
                objs[2] = salary.getPrizeSalary();
                objs[3] = salary.getMinusSalary();
                objs[4] = salary.getTotalSalary();
                objs[5] = salary.getSalaryDes();
                objs[6] = DateFormatUtil.defaultFormat(salary.getSalaryTime());
                dataList.add(objs);
            }
            ExcelExport ex = new ExcelExport(title, rowsName, dataList, response);
            ex.exportData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @ResponseBody
    @RequestMapping(value="readExcel",method=RequestMethod.POST)
    public ControllerResult readExcel(MultipartFile fileSalary) throws IOException{
        //判断文件是否为空
        logger.info("导入工资");
        if(fileSalary == null){
            return ControllerResult.getFailResult("导入失败");
        }
        String name = fileSalary.getOriginalFilename();
        long size = fileSalary.getSize();
        if(name == null || ExcelUtil.EMPTY.equals(name) && size==0){
            return ControllerResult.getFailResult("导入失败");
        }
        //读取Excel数据到List中
        List<ArrayList<String>> list = null;
        try {
            list = new ExcelRead().readExcel(fileSalary);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Salary salary= null;
        //list中存的就是excel中的数据，可以根据excel中每一列的值转换成你所需要的值（从0开始），如：
        List<Salary> salaries = new ArrayList<Salary>();
        OutgoingType outgoingType = outgoingTypeService.queryByName("工资支出");
        for(ArrayList<String> arr:list){
            salary= new Salary();
            User user = userService.queryByPhone(arr.get(1));
            try {
                salary.setUserId(user.getUserId());
                salary.setPrizeSalary(Double.valueOf(arr.get(2)));
                salary.setMinusSalary(Double.valueOf(arr.get(3)));
                salary.setTotalSalary(Double.valueOf(arr.get(4)));
                salary.setSalaryDes(arr.get(5));
                salary.setSalaryTime(dateFormat(arr.get(6)));
            } catch (NullPointerException e) {
                return ControllerResult.getFailResult("导入失败");
            }
            IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
            incomingOutgoing.setOutTypeId(outgoingType.getOutTypeId());
            incomingOutgoing.setInOutCreatedUser("9fcd1df8-25c6-11e7-ba3e-708bcd91a73a");
            incomingOutgoing.setInOutMoney(salary.getTotalSalary());
            incomingOutgoingService.insert(incomingOutgoing);
            salaries.add(salary);
        }
        if(salaryService.saveBatchInsert(salaries)){
            return ControllerResult.getSuccessResult("导入成功");
        }else{
            return ControllerResult.getFailResult("导入失败");
        }
    }
    public Date dateFormat(String str){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
