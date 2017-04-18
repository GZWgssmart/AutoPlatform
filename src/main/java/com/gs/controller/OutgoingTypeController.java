package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.OutgoingType;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.OutgoingTypeService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xiao-kang on 2017/4/16.
 */
@Controller
@RequestMapping("/outgoingType")
public class OutgoingTypeController {

    private Logger logger = (Logger) LoggerFactory.getLogger(OutgoingTypeController.class);


    @Resource
    private OutgoingTypeService outgoingTypeService;



    @RequestMapping(value = "show_outgoingType", method = RequestMethod.GET)
    public String outgoingType() {
        logger.info("显示收入类型页面");
        return "financeManage/outgoing_type";
    }

    @ResponseBody
    @RequestMapping(value="query_pager",method= RequestMethod.GET)
    public Pager4EasyUI<OutgoingType> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有收入类型");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(outgoingTypeService.count());
        List<OutgoingType> outgoingTypes = outgoingTypeService.queryByPager(pager);
        return new Pager4EasyUI<OutgoingType>(pager.getTotalRecords(), outgoingTypes);
    }

    @ResponseBody
    @RequestMapping(value="add_outgoingType", method=RequestMethod.POST)
    public ControllerResult outgoingTypeAdd(OutgoingType outgoingType){
        logger.info("添加收入类型");
        outgoingType.setOutTypeStatus("Y");
        outgoingTypeService.insert(outgoingType);
        return ControllerResult.getSuccessResult("添加成功");
    }

    @ResponseBody
    @RequestMapping(value="update_outgoingType", method=RequestMethod.POST)
    public ControllerResult incomingUpdate(OutgoingType outgoingType){
        logger.info("更新收入类型");
        outgoingTypeService.update(outgoingType);
        return ControllerResult.getSuccessResult("更新成功");
    }

    @ResponseBody
    @RequestMapping(value="update_status", method=RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status")String status){
        logger.info("更新收入类型状态");
        if(status.equals("Y")){
            outgoingTypeService.active(id);
        }else if(status.equals("N")){
            outgoingTypeService.inactive(id);
        }
        return ControllerResult.getSuccessResult("更新成功");
    }



}
