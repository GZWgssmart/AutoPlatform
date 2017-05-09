package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.IncomingOutgoing;
import com.gs.common.bean.*;
import com.gs.common.util.DateFormatUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.IncomingOutgoingService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by xiao-kang on 2017/4/18.
 */
@Controller
@RequestMapping("/incomingOutgoing")
public class IncomingOutgoingController {

    private Logger logger = (Logger) LoggerFactory.getLogger(IncomingOutgoingController.class);



    @Resource
    private IncomingOutgoingService incomingOutgoingService;

    @RequestMapping(value = "show_incomingOutgoing", method = RequestMethod.GET)
    public String incomingType() {
        logger.info("显示收支管理页面");
        return "financeManage/incoming_outgoing";
    }

    @ResponseBody
    @RequestMapping(value="query_pager",method= RequestMethod.GET)
    public Pager4EasyUI<IncomingOutgoing> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        if(SessionGetUtil.isUser()) {
            logger.info("分页查询所有收支记录");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            pager.setTotalRecords(incomingOutgoingService.count());
            List<IncomingOutgoing> incomingTypes = incomingOutgoingService.queryByPager(pager);
            return new Pager4EasyUI<IncomingOutgoing>(pager.getTotalRecords(), incomingTypes);
        } else{
            logger.info("session已失效，请重新登入");
            return null;

        }
    }

    @ResponseBody
    @RequestMapping(value="update_inOut", method=RequestMethod.POST)
    public ControllerResult incomingUpdate(IncomingOutgoing incomingOutgoing){
        if(SessionGetUtil.isUser()) {
            logger.info("更新收支记录");
            incomingOutgoingService.update(incomingOutgoing);
            return ControllerResult.getSuccessResult("更新成功");
        } else{
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value="update_status", method=RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status")String status){
        if(SessionGetUtil.isUser()) {
            logger.info("更新收支类型状态");
            if (status.equals("Y")) {
                incomingOutgoingService.active(id);
            } else if (status.equals("N")) {
                incomingOutgoingService.inactive(id);
            }
            return ControllerResult.getSuccessResult("更新成功");
        } else{
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }


    @ResponseBody
    @RequestMapping(value="query_inOutType",method= RequestMethod.GET)
    public Pager4EasyUI<IncomingOutgoing> queryByInOutType(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,@Param("type")String type){
        if(SessionGetUtil.isUser()) {
            logger.info("根据类型分页查询所有收支记录");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
            incomingOutgoing.setInOutType(type);
            pager.setTotalRecords(incomingOutgoingService.countByInOutType(incomingOutgoing));
            List<IncomingOutgoing> incomingTypes = incomingOutgoingService.queryByInOutType(pager, incomingOutgoing);
            return new Pager4EasyUI<IncomingOutgoing>(pager.getTotalRecords(), incomingTypes);
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }



    @ResponseBody
    @RequestMapping(value="query_default",method= RequestMethod.GET)
    public List<LineBasic> queryAll(@Param("companyId")String companyId){
        if(SessionGetUtil.isUser()) {
            logger.info("默认查询本月收支记录报表显示");
            List<LineBasic> lineBasics = new ArrayList<LineBasic>();
            LineBasic lineBasic = new LineBasic();
            LineBasic lineBasic1 = new LineBasic();
            lineBasic.setName("支出");
            dateDay("outgoing", companyId);
            lineBasic.setData(HighchartsData.doubleDayOutType);
            lineBasic1.setName("收入");
            dateDay("incoming", companyId);
            lineBasic1.setData(HighchartsData.doubleDayInType);
            lineBasic.setCategories(HighchartsData.strDay);
            lineBasic1.setCategories(HighchartsData.strDay);
            lineBasics.add(lineBasic);
            lineBasics.add(lineBasic1);
            return lineBasics;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value="query_status", method=RequestMethod.GET)
    public Pager4EasyUI<IncomingOutgoing> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,@Param("status")String status){
        if(SessionGetUtil.isUser()) {
            logger.info("根据收入类型状态查询");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            List<IncomingOutgoing> incomingOutgoings = null;
            if (status.equals("Y")) {
                incomingOutgoings = incomingOutgoingService.queryPagerStatus(status, pager);
                pager.setTotalRecords(incomingOutgoingService.countStatus(status));
            } else if (status.equals("N")) {
                incomingOutgoings = incomingOutgoingService.queryPagerStatus(status, pager);
                pager.setTotalRecords(incomingOutgoingService.countStatus(status));
            }
            return new Pager4EasyUI<IncomingOutgoing>(pager.getTotalRecords(), incomingOutgoings);
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }
    @ResponseBody
    @RequestMapping(value="query_condition",method= RequestMethod.GET)
    public List<LineBasic> queryCondition(@Param("start")String start,@Param("end")String end,
                                          @Param("type")String type,@Param("companyId")String companyId){
        if(SessionGetUtil.isUser()) {
            logger.info("根据年，月，季度，周，日查询所有收支记录报表显示");
            List<LineBasic> lineBasics = new ArrayList<LineBasic>();
            LineBasic lineBasic = new LineBasic();
            LineBasic lineBasic1 = new LineBasic();
            lineBasic.setName("支出");
            lineBasic1.setName("收入");
            if (start != null && !start.equals("") && end != null && !end.equals("") && type != null && !type.equals("")) {
                if (type.equals("year")) {
                    HighchartsData.setStrYear(start, end);
                    dataCondition(start, end, 1, type, "year", "outgoing", companyId);
                    lineBasic.setData(HighchartsData.doubleYearOutType);
                    dataCondition(start, end, 2, type, "year", "incoming", companyId);
                    lineBasic1.setData(HighchartsData.doubleYearInType);
                    lineBasic.setCategories(HighchartsData.strYear);
                    lineBasic1.setCategories(HighchartsData.strYear);
                } else if (type.equals("quarter")) {
                    dataCondition(start, end, 1, type, "quarter", "outgoing", companyId);
                    lineBasic.setData(HighchartsData.doubleQuarterOutType);
                    dataCondition(start, end, 2, type, "quarter", "incoming", companyId);
                    lineBasic1.setData(HighchartsData.doubleQuarterInType);
                    lineBasic.setCategories(HighchartsData.strQuarter);
                    lineBasic1.setCategories(HighchartsData.strQuarter);
                } else if (type.equals("month")) {
                    dataCondition(start, end, 1, type, "month", "outgoing", companyId);
                    lineBasic.setData(HighchartsData.doubleMonthOutType);
                    dataCondition(start, end, 2, type, "month", "incoming", companyId);
                    lineBasic1.setData(HighchartsData.doubleMonthInType);
                    lineBasic.setCategories(HighchartsData.strMonth);
                    lineBasic1.setCategories(HighchartsData.strMonth);
                } else if (type.equals("week")) {
                    HighchartsData.setStrWeek(start, end);
                    dataCondition(start, end, 1, type, "week", "outgoing", companyId);
                    lineBasic.setData(HighchartsData.doubleWeekOutType);
                    dataCondition(start, end, 2, type, "week", "incoming", companyId);
                    lineBasic1.setData(HighchartsData.doubleWeekInType);
                    lineBasic.setCategories(HighchartsData.strWeek);
                    lineBasic1.setCategories(HighchartsData.strWeek);
                } else if (type.equals("day")) {
                    dataCondition(start, end, 1, type, "day", "outgoing", companyId);
                    lineBasic.setData(HighchartsData.doubleDayOutType);
                    dataCondition(start, end, 2, type, "day", "incoming", companyId);
                    lineBasic1.setData(HighchartsData.doubleDayInType);
                    lineBasic.setCategories(HighchartsData.strDay);
                    lineBasic1.setCategories(HighchartsData.strDay);
                }
            }
            lineBasics.add(lineBasic);
            lineBasics.add(lineBasic1);
            return lineBasics;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }


            /*  默认查询本月的收入与支出
            * */
    public void dateDay(String type,String companyId){
        HighchartsData.doubleDayInType = new double[31];
        HighchartsData.doubleDayOutType = new double[31];
        List<IncomingOutgoing> incomingOutgoings = null;
        if(type.equals("incoming")){
            incomingOutgoings = incomingOutgoingService.queryByDefault(2,companyId);
        }else if(type.equals("outgoing")){
            incomingOutgoings = incomingOutgoingService.queryByDefault(1,companyId);
        }
        int i = 0;
        double[] doubles = new double[incomingOutgoings.size()];
        String[] strs = new String[incomingOutgoings.size()];
        for(IncomingOutgoing io: incomingOutgoings) {
            doubles[i] = io.getInOutMoney();
            strs[i] = HighchartsData.dateFormat(io.getInOutCreatedTime(),"day");
            i++;
        }
        for(int j = 0,len = HighchartsData.strDay.length; j <len ; j++){
            for(int k = 0; k < strs.length; k++){
                if(HighchartsData.strDay[j].equals(strs[k])){
                    if(type.equals("incoming")){
                        HighchartsData.doubleDayInType[j] = doubles[k];
                    }else if(type.equals("outgoing")){
                        HighchartsData.doubleDayOutType[j] = doubles[k];
                    }

                }
            }
        }


    }
        /*
        *  按年，季度，月，周，日，查询
        * */
    public void dataCondition(String start,String end,int inOutType,String type,String date,String inOut,String companyId){
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
        List<IncomingOutgoing> incomingOutgoings = incomingOutgoingService.queryByCondition(start,end,inOutType,type,companyId);;
        int i = 0;
        double[] doubles = new double[incomingOutgoings.size()];
        String[] strs = new String[incomingOutgoings.size()];
        HighchartsData.len = 0;
        for(IncomingOutgoing io: incomingOutgoings) {
            doubles[i] = io.getInOutMoney();
            if(date.equals("month")) {
                strs[i] = HighchartsData.dateFormat(io.getInOutCreatedTime(), "month");
                HighchartsData.len = HighchartsData.strMonth.length;
            }else if(date.equals("day")){
                strs[i] = HighchartsData.dateFormat(io.getInOutCreatedTime(), "day");
                HighchartsData.len = HighchartsData.strDay.length;
            }else if(date.equals("quarter")){
                strs[i] = HighchartsData.dateFormat(io.getInOutCreatedTime(), "quarter");
                HighchartsData.len = HighchartsData.strQuarter.length;
            }else if(date.equals("year")){
                strs[i] = HighchartsData.dateFormat(io.getInOutCreatedTime(),"year");
                HighchartsData.len = HighchartsData.strYear.length;
            }else if(date.equals("week")){
                strs[i] = "第"+String.valueOf(HighchartsData.getWeek(HighchartsData.dateFormat(io.getInOutCreatedTime())))+"周";
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
