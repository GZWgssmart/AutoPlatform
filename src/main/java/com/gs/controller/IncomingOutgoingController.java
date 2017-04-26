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
import java.util.ArrayList;
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
        logger.info("更新收入类型状态");
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
    public List<LineBasic> queryAll(){
        logger.info("查询所有收支记录报表显示");
        List<LineBasic> lineBasics = new ArrayList<LineBasic>();
        LineBasic lineBasic = new LineBasic();
        LineBasic lineBasic1 = new LineBasic();
        lineBasic.setName("支出");
        lineBasic.setText("本月收入");
        lineBasic.setData(date(incomingOutgoingService.queryByDefault(1)));
        lineBasic1.setName("收入");
        lineBasic1.setData(date(incomingOutgoingService.queryByDefault(2)));
        lineBasic1.setText("本月收入");
        lineBasic.setCategories(str(incomingOutgoingService.queryByDefault(1)));
        lineBasic1.setCategories(str(incomingOutgoingService.queryByDefault(2)));
        lineBasics.add(lineBasic);
        lineBasics.add(lineBasic1);
        return lineBasics;
    }
    @ResponseBody
    @RequestMapping(value="query_condition",method= RequestMethod.GET)
    public List<LineBasic> queryCondition(@Param("start")String start,@Param("end")String end,@Param("type")String type){
        logger.info("根据年，月，季度，周，日查询所有收支记录报表显示");
        List<LineBasic> lineBasics = new ArrayList<LineBasic>();
        LineBasic lineBasic = new LineBasic();
        LineBasic lineBasic1 = new LineBasic();
        lineBasic.setName("支出");
        lineBasic1.setName("收入");
        if(start != null && !start.equals("") && end != null && !end.equals("") && type != null && !type.equals("")){
            if(type.equals("year")){
                lineBasic.setData(data(start,end,1,type));
                lineBasic1.setData(data(start,end,2,type));
            }else if(type.equals("quarter")){
                lineBasic.setData(data(start,end,1,type));
                lineBasic1.setData(data(start,end,2,type));
            } else if(type.equals("month")){
                lineBasic.setData(data(start,end,1,type));
                lineBasic1.setData(data(start,end,2,type));
            }else if(type.equals("week")){
                lineBasic.setData(data(start,end,1,type));
                lineBasic1.setData(data(start,end,2,type));
            }else if(type.equals("day")){
                lineBasic.setData(data(start,end,1,type));
                lineBasic1.setData(data(start,end,2,type));
            }
        }
        lineBasics.add(lineBasic);
        lineBasics.add(lineBasic1);
        return lineBasics;
    }




    public double[] date(List<IncomingOutgoing> service){
        List<IncomingOutgoing> incomingOutgoings = service;
        int i = 0;
        double[] doubles = new double[incomingOutgoings.size()];
        for(IncomingOutgoing io: incomingOutgoings) {
            doubles[i] = io.getInOutMoney();
            i++;
        }
        return doubles;
    }

    public String[] str(List<IncomingOutgoing> service){
        List<IncomingOutgoing> incomingOutgoings = service;
        int i = 0;
        String[] strs = new String[incomingOutgoings.size()];
        for(IncomingOutgoing io: incomingOutgoings) {
            strs[i] = DateFormatUtil.defaultFormat(io.getInOutCreatedTime());
            i++;
        }
        return strs;
    }

    public double[] data(String start,String end,int inOutType,String type){
        List<IncomingOutgoing> incomingOutgoings = incomingOutgoingService.queryByCondition(start,end,inOutType,type);
        int i = 0;
        double[] doubles = new double[incomingOutgoings.size()];
        for(IncomingOutgoing io: incomingOutgoings) {
            doubles[i] = io.getInOutMoney();
            i++;
        }
        return doubles;
    }

}
