package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.IncomingOutgoing;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.LineBasic;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.DateFormatUtil;
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


    private  String[] strDay = new String[]{"01","02","03","04","05","06","07","08","09",
            "10","11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30","31"};

    private  String[] strQuarter = new String[]{"1~3月春季","4~6月夏季","7~9月秋季","10~12月冬季"};

    private  String[] strMonth = new String[]{"01月","02月","03月","04月","05月","06月","07月","08月","09月",
            "10月","11月","12月"};

    private String[] strYear;
    private int yearLen;
    private double[] doubleYearInType;
    private double[] doubleYearOutType;

    private String[] strWeek;
    private int weekLen;
    private double[] doubleWeekInType;
    private double[] doubleWeekOutType;

    private double[] doubleMonthInType = new double[12];
    private double[] doubleMonthOutType = new double[12];

    private double[] doubleDayInType = new double[31];
    private double[] doubleDayOutType = new double[31];

    private double[] doubleQuarterInType = new double[4];
    private double[] doubleQuarterOutType = new double[4];

    private int len;

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
        logger.info("分页查询所有收支记录");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(incomingOutgoingService.count());
        List<IncomingOutgoing> incomingTypes = incomingOutgoingService.queryByPager(pager);
        return new Pager4EasyUI<IncomingOutgoing>(pager.getTotalRecords(), incomingTypes);
    }

    @ResponseBody
    @RequestMapping(value="update_inOut", method=RequestMethod.POST)
    public ControllerResult incomingUpdate(IncomingOutgoing incomingOutgoing){
        logger.info("更新收支记录");
        incomingOutgoingService.update(incomingOutgoing);
        return ControllerResult.getSuccessResult("更新成功");
    }

    @ResponseBody
    @RequestMapping(value="update_status", method=RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status")String status){
        logger.info("更新收支类型状态");
        if(status.equals("Y")){
            incomingOutgoingService.active(id);
        }else if(status.equals("N")){
            incomingOutgoingService.inactive(id);
        }
        return ControllerResult.getSuccessResult("更新成功");
    }


    @ResponseBody
    @RequestMapping(value="query_inOutType",method= RequestMethod.GET)
    public Pager4EasyUI<IncomingOutgoing> queryByInOutType(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,@Param("type")String type){
        logger.info("根据类型分页查询所有收支记录");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
        incomingOutgoing.setInOutType(type);
        pager.setTotalRecords(incomingOutgoingService.countByInOutType(incomingOutgoing));
        List<IncomingOutgoing> incomingTypes = incomingOutgoingService.queryByInOutType(pager,incomingOutgoing);
        return new Pager4EasyUI<IncomingOutgoing>(pager.getTotalRecords(), incomingTypes);
    }



    @ResponseBody
    @RequestMapping(value="query_default",method= RequestMethod.GET)
    public List<LineBasic> queryAll(@Param("companyId")String companyId){
        logger.info("查询所有收支记录报表显示");
        List<LineBasic> lineBasics = new ArrayList<LineBasic>();
        LineBasic lineBasic = new LineBasic();
        LineBasic lineBasic1 = new LineBasic();
        lineBasic.setName("支出");
        dateDay("outgoing",companyId);
        lineBasic.setData(doubleDayOutType);
        lineBasic1.setName("收入");
        dateDay("incoming",companyId);
        lineBasic1.setData(doubleDayInType);
        lineBasic.setCategories(strDay);
        lineBasic1.setCategories(strDay);
        lineBasics.add(lineBasic);
        lineBasics.add(lineBasic1);
        return lineBasics;
    }

    @ResponseBody
    @RequestMapping(value="query_status", method=RequestMethod.GET)
    public Pager4EasyUI<IncomingOutgoing> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,@Param("status")String status){
        logger.info("根据收入类型状态查询");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        List<IncomingOutgoing> incomingOutgoings = null;
        if(status.equals("Y")){
            incomingOutgoings = incomingOutgoingService.queryPagerStatus(status,pager);
            pager.setTotalRecords(incomingOutgoingService.countStatus(status));
        }else if(status.equals("N")){
            incomingOutgoings = incomingOutgoingService.queryPagerStatus(status,pager);
            pager.setTotalRecords(incomingOutgoingService.countStatus(status));
        }
        return new Pager4EasyUI<IncomingOutgoing>(pager.getTotalRecords(), incomingOutgoings);
    }
    @ResponseBody
    @RequestMapping(value="query_condition",method= RequestMethod.GET)
    public List<LineBasic> queryCondition(@Param("start")String start,@Param("end")String end,
                                          @Param("type")String type,@Param("companyId")String companyId){
        logger.info("根据年，月，季度，周，日查询所有收支记录报表显示");
        List<LineBasic> lineBasics = new ArrayList<LineBasic>();
        LineBasic lineBasic = new LineBasic();
        LineBasic lineBasic1 = new LineBasic();
        lineBasic.setName("支出");
        lineBasic1.setName("收入");
        if(start != null && !start.equals("") && end != null && !end.equals("") && type != null && !type.equals("")){
            if(type.equals("year")){
                setStrYear(start,end);
                dataCondition(start,end,1,type,"year","outgoing",companyId);
                lineBasic.setData(doubleYearOutType);
                dataCondition(start,end,2,type,"year","incoming",companyId);
                lineBasic1.setData(doubleYearInType);
                lineBasic.setCategories(strYear);
                lineBasic1.setCategories(strYear);
            }else if(type.equals("quarter")){
                dataCondition(start,end,1,type,"quarter","outgoing",companyId);
                lineBasic.setData(doubleQuarterOutType);
                dataCondition(start,end,2,type,"quarter","incoming",companyId);
                lineBasic1.setData(doubleQuarterInType);
                lineBasic.setCategories(strQuarter);
                lineBasic1.setCategories(strQuarter);
            } else if(type.equals("month")){
                dataCondition(start,end,1,type,"month","outgoing",companyId);
                lineBasic.setData(doubleMonthOutType);
                dataCondition(start,end,2,type,"month","incoming",companyId);
                lineBasic1.setData(doubleMonthInType);
                lineBasic.setCategories(strMonth);
                lineBasic1.setCategories(strMonth);
            }else if(type.equals("week")){
                setStrWeek(start,end);
                dataCondition(start,end,1,type,"week","outgoing",companyId);
                lineBasic.setData(doubleWeekOutType);
                dataCondition(start,end,2,type,"week","incoming",companyId);
                lineBasic1.setData(doubleWeekInType);
                lineBasic.setCategories(strWeek);
                lineBasic1.setCategories(strWeek);
            }else if(type.equals("day")){
                dataCondition(start,end,1,type,"day","outgoing",companyId);
                lineBasic.setData(doubleDayOutType);
                dataCondition(start,end,2,type,"day","incoming",companyId);
                lineBasic1.setData(doubleDayInType);
                lineBasic.setCategories(strDay);
                lineBasic1.setCategories(strDay);
            }
        }
        lineBasics.add(lineBasic);
        lineBasics.add(lineBasic1);
        return lineBasics;
    }


            /*  默认查询本月的收入与支出
            * */
    public void dateDay(String type,String companyId){
        doubleDayInType = new double[31];
        doubleDayOutType = new double[31];
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
            strs[i] = dateFormat(io.getInOutCreatedTime(),"day");
            i++;
        }
        for(int j = 0; j < strDay.length; j++){
            for(int k = 0; k < strs.length; k++){
                if(strDay[j].equals(strs[k])){
                    if(type.equals("incoming")){
                        doubleDayInType[j] = doubles[k];
                    }else if(type.equals("outgoing")){
                        doubleDayOutType[j] = doubles[k];
                    }

                }
            }
        }


    }
        /*
        *  按年，季度，月，周，日，查询
        * */
    public void dataCondition(String start,String end,int inOutType,String type,String date,String inOut,String companyId){
        doubleDayInType = new double[31];
        doubleDayOutType = new double[31];
        doubleMonthInType = new double[12];
        doubleMonthOutType = new double[12];
        doubleQuarterInType = new double[4];
        doubleQuarterOutType = new double[4];
        doubleYearInType = new double[yearLen];
        doubleYearOutType = new double[yearLen];
        doubleWeekInType = new double[weekLen];
        doubleWeekOutType = new double[weekLen];
        List<IncomingOutgoing> incomingOutgoings = incomingOutgoingService.queryByCondition(start,end,inOutType,type,companyId);;
        int i = 0;
        double[] doubles = new double[incomingOutgoings.size()];
        String[] strs = new String[incomingOutgoings.size()];
        len = 0;
        for(IncomingOutgoing io: incomingOutgoings) {
            doubles[i] = io.getInOutMoney();
            if(date.equals("month")) {
                strs[i] = dateFormat(io.getInOutCreatedTime(), "month");
                len = strMonth.length;
            }else if(date.equals("day")){
                strs[i] = dateFormat(io.getInOutCreatedTime(), "day");
                len = strDay.length;
            }else if(date.equals("quarter")){
                strs[i] = dateFormat(io.getInOutCreatedTime(), "quarter");
                len = strQuarter.length;
            }else if(date.equals("year")){
                strs[i] = dateFormat(io.getInOutCreatedTime(),"year");
                len = strYear.length;
            }else if(date.equals("week")){
                strs[i] = "第"+String.valueOf(getWeek(dateFormat(io.getInOutCreatedTime())))+"周";
                len = strWeek.length;
            }
            i++;
        }
        if(date.equals("quarter")) {
            getQuarter(strs,doubles,inOut);
        }else if(date.equals("month")){
            getMonth(strs,doubles,inOut);
        }else if(date.equals("day")){
            getDay(strs,doubles,inOut);
        }else if(date.equals("year")){
            getYear(strs,doubles,inOut);
        }else if(date.equals("week")){
            getWeek(strs,doubles,inOut);
        }



    }

    public String dateFormat(Date date,String type){
        String str = DateFormatUtil.defaultFormat(date);
        String time = null;
        if(type.equals("day")){
            time = str.substring(8,10);
        }else if(type.equals("month")){
            time = str.substring(5,8);
        }else if(type.equals("quarter")){
            time = str.substring(5,7);
        }else if(type.equals("year")){
            time = str.substring(0,4);
        }
        return time;
    }

    public String dateFormat(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    // 获取周报表数据
    public void getWeek(String[] strs,double[] doubles,String inOut) {
        for (int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if (strWeek[j].equals(strs[k])) {
                    if (inOut.equals("incoming")) {
                        doubleWeekInType[j] = doubles[k];
                    } else if (inOut.equals("outgoing")) {
                        doubleWeekOutType[j] = doubles[k];
                    }
                }
            }
        }
    }

    // 获取年报表数据
    public void getYear(String[] strs,double[] doubles,String inOut) {
        for (int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if (strYear[j].equals(strs[k])) {
                    if (inOut.equals("incoming")) {
                        doubleYearInType[j] = doubles[k];
                    } else if (inOut.equals("outgoing")) {
                        doubleYearOutType[j] = doubles[k];
                    }
                }
            }
        }
    }

    // 获取季度报表数据
    public void getQuarter(String[] strs,double[] doubles,String inOut){
        for(int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if (strs[k].equals("01") || strs[k].equals("02") || strs[k].equals("03")) {
                    if (inOut.equals("incoming")) {
                        doubleQuarterInType[0] = doubles[k];
                    } else if (inOut.equals("outgoing")) {
                        doubleQuarterOutType[0] = doubles[k];
                    }
                } else if (strs[k].equals("04") || strs[k].equals("05") || strs[k].equals("06")) {
                    if (inOut.equals("incoming")) {
                        doubleQuarterInType[1] = doubles[k];
                    } else if (inOut.equals("outgoing")) {
                        doubleQuarterOutType[1] = doubles[k];
                    }
                } else if (strs[k].equals("07") || strs[k].equals("08") || strs[k].equals("09")) {
                    if (inOut.equals("incoming")) {
                        doubleQuarterInType[2] = doubles[k];
                    } else if (inOut.equals("outgoing")) {
                        doubleQuarterOutType[2] = doubles[k];
                    }
                } else if (strs[k].equals("10") || strs[k].equals("11") || strs[k].equals("12")) {
                    if (inOut.equals("incoming")) {
                        doubleQuarterInType[3] = doubles[k];
                    } else if (inOut.equals("outgoing")) {
                        doubleQuarterOutType[3] = doubles[k];
                    }
                }
            }

        }
    }

    // 获取月报表数据
    public void getMonth(String[] strs,double[] doubles,String inOut) {
        for (int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if (strMonth[j].equals(strs[k])) {
                    if (inOut.equals("incoming")) {
                        doubleMonthInType[j] = doubles[k];
                    } else if (inOut.equals("outgoing")) {
                        doubleMonthOutType[j] = doubles[k];
                    }
                }
            }
        }
    }

    // 获取日报表数据
    public void getDay(String[] strs,double[] doubles,String inOut) {
        for (int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if(strDay[j].equals(strs[k])){
                    if(inOut.equals("incoming")){
                        doubleDayInType[j] = doubles[k];
                    }else if(inOut.equals("outgoing")){
                        doubleDayOutType[j] = doubles[k];
                    }
                }
            }
        }
    }

    /*
    *
    * 获取选中的时间是本年的第几周
    * */
    public int getWeek(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_YEAR);
        return weekOfMonth;
    }


    /*
    * 获取选择的开始年和结束年的所有
    * */
    public void setStrYear(String start,String end){
        String strStartTime = start.substring(0,4);
        String strEndTime = end.substring(0,4);
        int startTime = Integer.valueOf(strStartTime);
        int endTime = Integer.valueOf(strEndTime);
        yearLen =  endTime - startTime+1;
        strYear = new String[yearLen];
        for(int i = 0; i < strYear.length; i++){
            strYear[i] = String.valueOf(startTime);
            startTime++;
        }
    }
    /*
       * 获取选择的开始周和结束周的所有
       * */
    public void setStrWeek(String start,String end){
        int starkWeek =  getWeek(start);
        int endWeek = getWeek(end);
        weekLen =  endWeek - starkWeek+1;
        strWeek = new String[weekLen];
        for(int i = 0; i < strWeek.length; i++){
            strWeek[i] = "第"+ String.valueOf(starkWeek) + "周";
            starkWeek++;
        }

    }
}
