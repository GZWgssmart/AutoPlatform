package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.bean.*;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.*;
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
import java.util.UUID;

/**
 * Created by Levc on 2017/4/17.
 */
@Controller
@RequestMapping("accessoriesBuy")
public class AccessoriesBuyController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AccessoriesBuyController.class);

    @Resource
    private AccessoriesBuyService accessoriesBuyService;

    @Resource
    private AccessoriesService accessoriesService;

    @Resource
    private AccessoriesTypeService accessoriesTypeService;

    @Resource
    private CompanyService companyService;

    @Resource
    private SupplyService supplyService;

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
    @RequestMapping(value = "isAccAdd", method = RequestMethod.POST)
    private ControllerResult isAccAdd(AccessoriesBuy accessoriesBuy, @Param("state") String state) {
        logger.info("添加采购信息");

        String accId = accessoriesBuy.getAccId();

        Accessories acc = accessoriesBuy.getAccessories();

        AccessoriesBuy ab = accessoriesBuyService.queryById(accId);
        Accessories ac = accessoriesService.queryById(accId);

        if (state.equals("true")) {  // 如果为 true 库存添加
            logger.info("库存添加");

            if (ab == null) {
                accessoriesBuy.setAccBuyCount(acc.getAccIdle() + accessoriesBuy.getAccBuyCount());
                accessoriesBuy.setCompanyId(ac.getCompanyId());
                accessoriesBuy.setAccUnit(ac.getAccUnit());

                accessoriesBuyService.insert(accessoriesBuy);

                return ControllerResult.getSuccessResult("添加成功");

            } else if (ab.getAccId().equals(ac.getAccId())) {
                return ControllerResult.getFailResult("不能重复添加同一条数据");
            }

        } else if (state.equals("false")) { // 如果为false采购添加
            logger.info("采购添加");

            acc.setAccId(UUIDUtil.uuid());
            acc.setAccName(accessoriesBuy.getAccessories().getAccName());
            acc.setAccUnit(accessoriesBuy.getAccUnit());

            AccessoriesType accessoriesType = acc.getAccessoriesType();
            accessoriesType.setAccTypeName(acc.getAccessoriesType().getAccTypeName());
            accessoriesType.setAccTypeId(UUIDUtil.uuid());

            accessoriesBuy.setAccessories(acc);
            accessoriesBuy.setAccId(acc.getAccId());
            acc.setAccTypeId(accessoriesType.getAccTypeId());

            Supply supply = acc.getSupply();

            accessoriesBuyService.insert(accessoriesBuy);
            accessoriesService.insert(acc);
            accessoriesTypeService.insert(accessoriesType);
//            supplyService.insert(supply);

            return ControllerResult.getSuccessResult("添加成功");
        }
        return ControllerResult.getSuccessResult("添加成功");
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ResponseBody
    @RequestMapping(value = "updateStatus", method = RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status") String status) {
        logger.info("更新收入类型状态");
        if (status.equals("Y")) {
            accessoriesBuyService.active(id);
        } else if (status.equals("N")) {
            accessoriesBuyService.inactive(id);
        }
        return ControllerResult.getSuccessResult("更新成功");
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult updateAccessoriesBuyInfo(AccessoriesBuy accessoriesBuy) {
        Accessories acc = accessoriesBuy.getAccessories();
        AccessoriesType accessoriesType = acc.getAccessoriesType();

        int total = accessoriesBuy.getAccBuyCount() + acc.getAccIdle();

        acc.setAccIdle(total);
        accessoriesBuy.setAccBuyCount(total);
        acc.setAccUnit(accessoriesBuy.getAccUnit());
        acc.setAccTypeId(accessoriesType.getAccTypeId());

        accessoriesBuyService.update(accessoriesBuy);
        accessoriesService.update(acc);
        accessoriesTypeService.update(accessoriesType);

        return ControllerResult.getSuccessResult("更新成功");
    }

    @ResponseBody
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public ControllerResult remove(@Param("id") String id, @Param("status") String status) {
        if (status.equals("N")) {
            return ControllerResult.getFailResult("采购信息正在审核中，无法删除");
        } else {
            accessoriesBuyService.deleteById(id);
            return ControllerResult.getSuccessResult("删除成功");
        }
    }

    @ResponseBody
    @RequestMapping(value = "batchDelete", method = RequestMethod.GET)
    public ControllerResult batchDelete(@Param("accBuyArr") String[] accBuyArr) {
        accessoriesBuyService.batchDeleteAcc(accBuyArr);
        return ControllerResult.getSuccessResult("删除成功");
    }

    @ResponseBody
    @RequestMapping(value = "onlyCheck", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesBuy> onlyCheck(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(accessoriesBuyService.countByCheckState("Y"));
        List<AccessoriesBuy> accessoriesBuys = accessoriesBuyService.queryByCheckStatePager(pager);
        return new Pager4EasyUI<AccessoriesBuy>(pager.getTotalRecords(), accessoriesBuys);
    }

    @ResponseBody
    @RequestMapping(value = "onlyBuy", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesBuy> onlyBuy(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(accessoriesBuyService.countByBuyState("Y"));
        List<AccessoriesBuy> accessoriesBuys = accessoriesBuyService.queryByBuyStatePager(pager);
        return new Pager4EasyUI<AccessoriesBuy>(pager.getTotalRecords(), accessoriesBuys);
    }

    @ResponseBody
    @RequestMapping(value = "byAccNameSearch", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesBuy> byAccNameSearch(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("accName") String accName, @Param("buyTimeStart") String buyTimeStart, @Param("buyTimeEnd") String buyTimeEnd) {
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(accessoriesBuyService.countByBuyTimeScope(accName, buyTimeStart, buyTimeEnd));
        List<AccessoriesBuy> accessoriesBuys = accessoriesBuyService.queryByBuyTimeScopeByAccNamePager(pager, accName, buyTimeStart, buyTimeEnd);
        return new Pager4EasyUI<AccessoriesBuy>(pager.getTotalRecords(), accessoriesBuys);
    }

    @ResponseBody
    @RequestMapping(value="query_default_count",method= RequestMethod.GET)
    public List<LineBasic> queryAll(@Param("companyId")String companyId){
        // if(SessionGetUtil.isUser()) {
        logger.info("默认查询本月下单统计报表显示");
        List<LineBasic> lineBasics = new ArrayList<LineBasic>();
        LineBasic lineBasic = new LineBasic();
        lineBasic.setName("下单");
        dateDay(companyId,"count");
        lineBasic.setData(HighchartsData.doubleDayOne);
        lineBasic.setCategories(HighchartsData.strDay);
        lineBasics.add(lineBasic);
        return lineBasics;
        /*} else{
            logger.info("Session已失效，请重新登入");
            return null;
        }*/

    }

    @ResponseBody
    @RequestMapping(value="query_condition_count",method= RequestMethod.GET)
    public List<LineBasic> queryConditionCount(@Param("start")String start,@Param("end")String end,
                                          @Param("type")String type,@Param("companyId")String companyId){
        if(SessionGetUtil.isUser()) {
        logger.info("根据年，月，季度，周，日查询所有下单报表显示");
        List<LineBasic> lineBasics = new ArrayList<LineBasic>();
        LineBasic lineBasic = new LineBasic();
        lineBasic.setName("下单");
        if (start != null && !start.equals("") && end != null && !end.equals("") && type != null && !type.equals("")) {
            if (type.equals("year")) {
                HighchartsData.setStrYear(start, end);
                dataCondition(start, end, "count", type, "year", "two", companyId);
                lineBasic.setData(HighchartsData.doubleYearTwo);
                lineBasic.setCategories(HighchartsData.strYear);
            } else if (type.equals("quarter")) {
                dataCondition(start, end, "count", type, "quarter", "two", companyId);
                lineBasic.setData(HighchartsData.doubleQuarterTwo);
                lineBasic.setCategories(HighchartsData.strQuarter);
            } else if (type.equals("month")) {
                dataCondition(start, end, "count", type, "month", "two", companyId);
                lineBasic.setData(HighchartsData.doubleMonthTwo);
                lineBasic.setCategories(HighchartsData.strMonth);
            } else if (type.equals("week")) {
                HighchartsData.setStrWeek(start, end);
                dataCondition(start, end, "count", type, "week", "two", companyId);
                lineBasic.setData(HighchartsData.doubleWeekTwo);
                lineBasic.setCategories(HighchartsData.strWeek);
            } else if (type.equals("day")) {
                dataCondition(start, end, "count", type, "day", "two", companyId);
                lineBasic.setData(HighchartsData.doubleDayTwo);
                lineBasic.setCategories(HighchartsData.strDay);
            }
        }
        lineBasics.add(lineBasic);
        return lineBasics;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value="query_default_pay",method= RequestMethod.GET)
    public List<LineBasic> queryPay(@Param("companyId")String companyId){
       if(SessionGetUtil.isUser()) {
        logger.info("默认查询本月支付统计报表显示");
        List<LineBasic> lineBasics = new ArrayList<LineBasic>();
        LineBasic lineBasic = new LineBasic();
        lineBasic.setName("支付");
        dateDay(companyId,"pay");
        lineBasic.setData(HighchartsData.doubleDayTwo);
        lineBasic.setCategories(HighchartsData.strDay);
        lineBasics.add(lineBasic);
        return lineBasics;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value="query_condition_pay",method= RequestMethod.GET)
    public List<LineBasic> queryConditionPay(@Param("start")String start,@Param("end")String end,
                                          @Param("type")String type,@Param("companyId")String companyId){
        if(SessionGetUtil.isUser()) {
            logger.info("根据年，月，季度，周，日查询所有支付报表显示");
            List<LineBasic> lineBasics = new ArrayList<LineBasic>();
            LineBasic lineBasic = new LineBasic();
            lineBasic.setName("支付");
            if (start != null && !start.equals("") && end != null && !end.equals("") && type != null && !type.equals("")) {
                if (type.equals("year")) {
                    HighchartsData.setStrYear(start, end);
                    dataCondition(start, end, "pay", type, "year", "one", companyId);
                    lineBasic.setData(HighchartsData.doubleYearOne);
                    lineBasic.setCategories(HighchartsData.strYear);
                } else if (type.equals("quarter")) {
                    dataCondition(start, end, "pay", type, "quarter", "one", companyId);
                    lineBasic.setData(HighchartsData.doubleQuarterOne);
                    lineBasic.setCategories(HighchartsData.strQuarter);
                } else if (type.equals("month")) {
                    dataCondition(start, end, "pay", type, "month", "one", companyId);
                    lineBasic.setData(HighchartsData.doubleMonthOne);
                    lineBasic.setCategories(HighchartsData.strMonth);
                } else if (type.equals("week")) {
                    HighchartsData.setStrWeek(start, end);
                    dataCondition(start, end, "pay", type, "week", "one", companyId);
                    lineBasic.setData(HighchartsData.doubleWeekOne);
                    lineBasic.setCategories(HighchartsData.strWeek);
                } else if (type.equals("day")) {
                    dataCondition(start, end, "pay", type, "day", "one", companyId);
                    lineBasic.setData(HighchartsData.doubleDayOne);
                    lineBasic.setCategories(HighchartsData.strDay);
                }
            }
            lineBasics.add(lineBasic);
            return lineBasics;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    /*  默认查询本月的下单,支付
   * */
    public void dateDay(String companyId,String type){
        HighchartsData.doubleDayOne = new double[31];
        List<AccessoriesBuy> accessoriesBuys = null;
        if(type.equals("count")) {
            accessoriesBuys = accessoriesBuyService.queryByDefaultCount(companyId);
        }else if(type.equals("pay")){
            accessoriesBuys = accessoriesBuyService.queryByDefaultPay(companyId);
        }

        int i = 0;
        double[] doubles = new double[accessoriesBuys.size()];
        String[] strs = new String[accessoriesBuys.size()];
        for(AccessoriesBuy io: accessoriesBuys) {
            if(type.equals("count")){
                doubles[i] = io.getCoont();
            }else if(type.equals("pay")){
                doubles[i] = io.getAccBuyMoney();
            }

            strs[i] = HighchartsData.dateFormat(io.getAccBuyCreatedTime(),"day");
            i++;
        }
        for(int j = 0,len = HighchartsData.strDay.length; j <len ; j++){
            for(int k = 0; k < strs.length; k++){
                if(HighchartsData.strDay[j].equals(strs[k])){
                    if(type.equals("count")){
                        HighchartsData.doubleDayOne[j] = doubles[k];
                    }else if(type.equals("pay")){
                        HighchartsData.doubleDayTwo[j] = doubles[k];
                    }
                }
            }
        }
    }

    /*
    *  按年，季度，月，周，日，查询,下单，支付
    * */
    public void dataCondition(String start,String end,String species,String type,String date,String inOut,String companyId){
        HighchartsData.doubleDayOne = new double[31];
        HighchartsData.doubleDayTwo = new double[31];
        HighchartsData. doubleMonthOne = new double[12];
        HighchartsData.doubleMonthTwo = new double[12];
        HighchartsData.doubleQuarterOne = new double[4];
        HighchartsData.doubleQuarterTwo = new double[4];
        HighchartsData.doubleYearOne = new double[HighchartsData.yearLen];
        HighchartsData.doubleYearTwo = new double[HighchartsData.yearLen];
        HighchartsData.doubleWeekOne = new double[HighchartsData.weekLen];
        HighchartsData.doubleWeekTwo = new double[HighchartsData.weekLen];
        List<AccessoriesBuy> accessoriesBuys = new ArrayList<AccessoriesBuy>();
        if(species.equals("count")) {
           accessoriesBuys =accessoriesBuyService.queryByConditionCount(start, end, type, companyId);
        }else if(species.equals("pay")){
            accessoriesBuys = accessoriesBuyService.queryByConditionPay(start,end,type,companyId);
        }
        int i = 0;
        double[] doubles = new double[accessoriesBuys.size()];
        String[] strs = new String[accessoriesBuys.size()];
        HighchartsData.len = 0;
        for(AccessoriesBuy io: accessoriesBuys) {
            if(species.equals("count")) {
                doubles[i] = io.getCoont();
            }else if (species.equals("pay")){
                doubles[i] = io.getAccBuyMoney();
            }
            if(date.equals("month")) {
                strs[i] = HighchartsData.dateFormat(io.getAccBuyCreatedTime(), "month");
                HighchartsData.len = HighchartsData.strMonth.length;
            }else if(date.equals("day")){
                strs[i] = HighchartsData.dateFormat(io.getAccBuyCreatedTime(), "day");
                HighchartsData.len = HighchartsData.strDay.length;
            }else if(date.equals("quarter")){
                strs[i] = HighchartsData.dateFormat(io.getAccBuyCreatedTime(), "quarter");
                HighchartsData.len = HighchartsData.strQuarter.length;
            }else if(date.equals("year")){
                strs[i] = HighchartsData.dateFormat(io.getAccBuyCreatedTime(),"year");
                HighchartsData.len = HighchartsData.strYear.length;
            }else if(date.equals("week")){
                strs[i] = "第"+String.valueOf(HighchartsData.getWeek(HighchartsData.dateFormat(io.getAccBuyCreatedTime())))+"周";
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