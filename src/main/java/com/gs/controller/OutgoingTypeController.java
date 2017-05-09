package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.OutgoingType;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.OutgoingTypeService;
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
    public Pager4EasyUI<OutgoingType> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if (SessionGetUtil.isUser()) {
            logger.info("分页查询所有收入类型");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            pager.setTotalRecords(outgoingTypeService.count());
            List<OutgoingType> outgoingTypes = outgoingTypeService.queryByPager(pager);
            return new Pager4EasyUI<OutgoingType>(pager.getTotalRecords(), outgoingTypes);
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value="add_outgoingType", method=RequestMethod.POST)
    public ControllerResult outgoingTypeAdd(OutgoingType outgoingType){
        if(SessionGetUtil.isUser()) {
            logger.info("添加收入类型");
            outgoingType.setOutTypeStatus("Y");
            outgoingTypeService.insert(outgoingType);
            return ControllerResult.getSuccessResult("添加成功");
        }else{
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value="update_outgoingType", method=RequestMethod.POST)
    public ControllerResult outgoingUpdate(OutgoingType outgoingType){
        if(SessionGetUtil.isUser()) {
            logger.info("更新收入类型");
            outgoingTypeService.update(outgoingType);
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
            logger.info("更新收入类型状态");
            if (status.equals("Y")) {
                outgoingTypeService.active(id);
            } else if (status.equals("N")) {
                outgoingTypeService.inactive(id);
            }
            return ControllerResult.getSuccessResult("更新成功");
        } else{
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value="query_status", method=RequestMethod.GET)
    public Pager4EasyUI<OutgoingType> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,@Param("status")String status){
        if(SessionGetUtil.isUser()) {
            logger.info("根据支出类型状态查询");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            List<OutgoingType> outgoingTypes = null;
            if (status.equals("Y")) {
                outgoingTypes = outgoingTypeService.queryPagerStatus(status, pager);
                pager.setTotalRecords(outgoingTypeService.countStatus(status));
            } else if (status.equals("N")) {
                outgoingTypes = outgoingTypeService.queryPagerStatus(status, pager);
                pager.setTotalRecords(outgoingTypeService.countStatus(status));
            } else if (status.equals("ALL")) {
                pager.setTotalRecords(outgoingTypeService.count());
                outgoingTypes = outgoingTypeService.queryByPager(pager);
            }
            return new Pager4EasyUI<OutgoingType>(pager.getTotalRecords(), outgoingTypes);
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }


    @ResponseBody
    @RequestMapping(value = "outType_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryUserAll() {
        logger.info("查询所有支出类型");
        List<OutgoingType> outgoingTypes = outgoingTypeService.queryAll();
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (OutgoingType out : outgoingTypes) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(out.getOutTypeId());
            comboBox4EasyUI.setText(out.getOutTypeName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }
}
