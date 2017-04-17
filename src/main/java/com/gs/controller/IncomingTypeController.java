package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.IncomingType;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.IncomingTypeService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xiao-kang on 2017/4/16.
 */
@Controller
@RequestMapping("incomingType")
public class IncomingTypeController {

    private Logger logger = (Logger) LoggerFactory.getLogger(IncomingTypeController.class);


    @Resource
    private IncomingTypeService incomingTypeService;



    @RequestMapping(value = "show_incomingType", method = RequestMethod.GET)
    private String incomingType() {
        logger.info("显示支出类型页面");
        return "financeManage/income_type";
    }

    @ResponseBody
    @RequestMapping(value="query_pager",method= RequestMethod.GET)
    public Pager4EasyUI<IncomingType> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有支出类型");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(incomingTypeService.count());
        List<IncomingType> incomingTypes = incomingTypeService.queryByPager(pager);
        return new Pager4EasyUI<IncomingType>(pager.getTotalRecords(), incomingTypes);
    }

    @ResponseBody
    @RequestMapping(value="add_incomingType", method=RequestMethod.POST)
    public ControllerResult incomingTypeAdd(IncomingType incomingType){
        logger.info("添加支出类型");
        incomingType.setInTypeStatus("Y");
        incomingTypeService.insert(incomingType);
        return ControllerResult.getSuccessResult("添加成功");
    }

    @ResponseBody
    @RequestMapping(value="update_incomingType", method=RequestMethod.POST)
    public ControllerResult productUpdate(IncomingType incomingType){
        logger.info("更新支出类型");
        incomingTypeService.update(incomingType);
        return ControllerResult.getSuccessResult("更新成功");
    }
  /*  @ResponseBody
    @RequestMapping(value="deleteById/{id}", method=RequestMethod.GET)
    public ControllerResult productDelete(@PathVariable("id") int id){
        logger.info("删除支出类型");
        incomingTypeService.deleteById(id);
        return ControllerResult.getSuccessResult("删除成功");
    }*/


}
