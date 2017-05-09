package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.User;
import com.gs.bean.WorkInfo;
import com.gs.common.bean.*;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.UserService;
import com.gs.service.WorkInfoService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 温鑫
 * Created by Star on 2017/4/21.
 */
@Controller
@RequestMapping("peopleManage")
public class WorkInfoController {

    private Logger logger = (Logger) LoggerFactory.getLogger(WorkInfoController.class);

    @Resource
    private WorkInfoService workInfoService;


    @RequestMapping(value = "work", method = RequestMethod.GET)
    private String workInfo() {
        logger.info(" 工单显示");
        return "peopleManage/work";
    }

    @ResponseBody
    @RequestMapping(value = "workInfo_insert", method = RequestMethod.POST)
    public ControllerResult infoInsert(WorkInfo workInfo){
        logger.info("工单添加");
        workInfoService.insert(workInfo);
        return ControllerResult.getSuccessResult("添加成功");
    }

    @ResponseBody
    @RequestMapping(value = "workInfo_pager", method= RequestMethod.GET)
    public Pager4EasyUI<WorkInfo> info_pager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有工单");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(workInfoService.count());
        List<WorkInfo> workInfo = workInfoService.queryByPager(pager);
        return new Pager4EasyUI<WorkInfo>(pager.getTotalRecords(), workInfo);
    }

    @ResponseBody
    @RequestMapping(value = "workInfo_update", method = RequestMethod.POST)
    public ControllerResult info_update(WorkInfo workInfo){
        logger.info("信息修改");
        workInfoService.update(workInfo);
        return ControllerResult.getSuccessResult(" 修改成功");
    }

    @ResponseBody
    @RequestMapping(value = "workInfo_status", method = RequestMethod.GET)
    public ControllerResult info_status(@Param("id")String id, @Param("status")String status){
        logger.info("状态修改");
        if(status.equals("Y")){
            workInfoService.inactive(id);
        } else {
            workInfoService.active(id);
        }
        return ControllerResult.getSuccessResult(" 修改成功");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ResponseBody
    @RequestMapping(value="query_default",method= RequestMethod.GET)
    public List<LineBasic> queryAll(@Param("companyId")String companyId){
       // if(SessionGetUtil.isUser()) {
            logger.info("默认查询本月工单报表显示");
            List<LineBasic> lineBasics = new ArrayList<LineBasic>();
            LineBasic lineBasic = new LineBasic();
            LineBasic lineBasic1 = new LineBasic();
            lineBasic.setName("保养");
            dateDay("maintenance", companyId);
            lineBasic.setData(HighchartsData.doubleDayOutType);
            lineBasic1.setName("维修");
            dateDay("service", companyId);
            lineBasic1.setData(HighchartsData.doubleDayInType);
            lineBasic.setCategories(HighchartsData.strDay);
            lineBasic1.setCategories(HighchartsData.strDay);
            lineBasics.add(lineBasic);
            lineBasics.add(lineBasic1);
            return lineBasics;
        /*} else{
            logger.info("Session已失效，请重新登入");
            return null;
        }*/

    }

    @ResponseBody
    @RequestMapping(value="query_condition",method= RequestMethod.GET)
    public List<LineBasic> queryCondition(@Param("start")String start,@Param("end")String end,
                                          @Param("type")String type,@Param("companyId")String companyId){
        //if(SessionGetUtil.isUser()) {
            logger.info("根据年，月，季度，周，日查询所有收支记录报表显示");
            List<LineBasic> lineBasics = new ArrayList<LineBasic>();
            LineBasic lineBasic = new LineBasic();
            LineBasic lineBasic1 = new LineBasic();
            lineBasic.setName("保养");
            lineBasic1.setName("维修");
            if (start != null && !start.equals("") && end != null && !end.equals("") && type != null && !type.equals("")) {
                if (type.equals("year")) {
                    HighchartsData.setStrYear(start, end);
                    dataCondition(start, end, "保养", type, "year", "maintenance", companyId);
                    lineBasic.setData(HighchartsData.doubleYearOutType);
                    dataCondition(start, end, "维修", type, "year", "service", companyId);
                    lineBasic1.setData(HighchartsData.doubleYearInType);
                    lineBasic.setCategories(HighchartsData.strYear);
                    lineBasic1.setCategories(HighchartsData.strYear);
                } else if (type.equals("quarter")) {
                    dataCondition(start, end, "保养", type, "quarter", "maintenance", companyId);
                    lineBasic.setData(HighchartsData.doubleQuarterOutType);
                    dataCondition(start, end, "维修", type, "quarter", "service", companyId);
                    lineBasic1.setData(HighchartsData.doubleQuarterInType);
                    lineBasic.setCategories(HighchartsData.strQuarter);
                    lineBasic1.setCategories(HighchartsData.strQuarter);
                } else if (type.equals("month")) {
                    dataCondition(start, end, "保养", type, "month", "maintenance", companyId);
                    lineBasic.setData(HighchartsData.doubleMonthOutType);
                    dataCondition(start, end, "维修", type, "month", "service", companyId);
                    lineBasic1.setData(HighchartsData.doubleMonthInType);
                    lineBasic.setCategories(HighchartsData.strMonth);
                    lineBasic1.setCategories(HighchartsData.strMonth);
                } else if (type.equals("week")) {
                    HighchartsData.setStrWeek(start, end);
                    dataCondition(start, end, "保养", type, "week", "maintenance", companyId);
                    lineBasic.setData(HighchartsData.doubleWeekOutType);
                    dataCondition(start, end, "维修", type, "week", "service", companyId);
                    lineBasic1.setData(HighchartsData.doubleWeekInType);
                    lineBasic.setCategories(HighchartsData.strWeek);
                    lineBasic1.setCategories(HighchartsData.strWeek);
                } else if (type.equals("day")) {
                    dataCondition(start, end, "保养", type, "day", "maintenance", companyId);
                    lineBasic.setData(HighchartsData.doubleDayOutType);
                    dataCondition(start, end, "维修", type, "day", "service", companyId);
                    lineBasic1.setData(HighchartsData.doubleDayInType);
                    lineBasic.setCategories(HighchartsData.strDay);
                    lineBasic1.setCategories(HighchartsData.strDay);
                }
            }
            lineBasics.add(lineBasic);
            lineBasics.add(lineBasic1);
            return lineBasics;
       /* } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }*/
    }


    /*  默认查询本月的收入与支出
    * */
    public void dateDay(String type,String companyId){
        HighchartsData.doubleDayInType = new double[31];
        HighchartsData.doubleDayOutType = new double[31];
        List<WorkInfo> workInfos = null;
        if(type.equals("maintenance")){
            workInfos = workInfoService.queryByDefault("保养",companyId);
        }else if(type.equals("service")){
            workInfos = workInfoService.queryByDefault("维修",companyId);
        }
        int i = 0;
        double[] doubles = new double[workInfos.size()];
        String[] strs = new String[workInfos.size()];
        for(WorkInfo io: workInfos) {
            doubles[i] = io.getCoont();
            strs[i] = HighchartsData.dateFormat(io.getWorkCreatedTime(),"day");
            i++;
        }
        for(int j = 0,len = HighchartsData.strDay.length; j <len ; j++){
            for(int k = 0; k < strs.length; k++){
                if(HighchartsData.strDay[j].equals(strs[k])){
                    if(type.equals("service")){
                        HighchartsData.doubleDayInType[j] = doubles[k];
                    }else if(type.equals("maintenance")){
                        HighchartsData.doubleDayOutType[j] = doubles[k];
                    }

                }
            }
        }


    }
    /*
    *  按年，季度，月，周，日，查询
    * */
    public void dataCondition(String start,String end,String maintainOrFix,String type,String date,String inOut,String companyId){
        HighchartsData.doubleDayInType = new double[31];
        HighchartsData.doubleDayOutType = new double[31];
        HighchartsData. doubleMonthInType = new double[12];
        HighchartsData.doubleMonthOutType = new double[12];
        HighchartsData.doubleQuarterInType = new double[4];
        HighchartsData.doubleQuarterOutType = new double[4];
        HighchartsData.doubleYearInType = new double[HighchartsData.yearLen];
        HighchartsData.doubleYearOutType = new double[HighchartsData.yearLen];
        HighchartsData.doubleWeekInType = new double[HighchartsData.weekLen];
        HighchartsData.doubleWeekOutType = new double[HighchartsData.weekLen];
        List<WorkInfo> workInfos = workInfoService.queryByCondition(start,end,maintainOrFix,type,companyId);;
        int i = 0;
        double[] doubles = new double[workInfos.size()];
        String[] strs = new String[workInfos.size()];
        HighchartsData.len = 0;
        for(WorkInfo io: workInfos) {
            doubles[i] = io.getCoont();
            if(date.equals("month")) {
                strs[i] = HighchartsData.dateFormat(io.getWorkCreatedTime(), "month");
                HighchartsData.len = HighchartsData.strMonth.length;
            }else if(date.equals("day")){
                strs[i] = HighchartsData.dateFormat(io.getWorkCreatedTime(), "day");
                HighchartsData.len = HighchartsData.strDay.length;
            }else if(date.equals("quarter")){
                strs[i] = HighchartsData.dateFormat(io.getWorkCreatedTime(), "quarter");
                HighchartsData.len = HighchartsData.strQuarter.length;
            }else if(date.equals("year")){
                strs[i] = HighchartsData.dateFormat(io.getWorkCreatedTime(),"year");
                HighchartsData.len = HighchartsData.strYear.length;
            }else if(date.equals("week")){
                strs[i] = "第"+String.valueOf(HighchartsData.getWeek(HighchartsData.dateFormat(io.getWorkCreatedTime())))+"周";
                HighchartsData.len = HighchartsData.strWeek.length;
            }
            i++;
        }
        if(date.equals("quarter")) {
            HighchartsData.getQuarter(strs,doubles,inOut);
        }else if(date.equals("month")){
            HighchartsData.getMonth(strs,doubles,inOut);
        }else if(date.equals("day")){
            HighchartsData.getDay(strs,doubles,inOut);
        }else if(date.equals("year")){
            HighchartsData. getYear(strs,doubles,inOut);
        }else if(date.equals("week")){
            HighchartsData.getWeek(strs,doubles,inOut);
        }
    }
}
