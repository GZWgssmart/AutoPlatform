package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 温鑫
 * 车主信息
 * Created by xiao-kang on 2017/4/18.
 */
@Controller
@RequestMapping("customerManage")

public class CustomerController {


    private Logger logger = (Logger) LoggerFactory.getLogger(IncomingTypeController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "customer_info", method = RequestMethod.GET)
    private String customerInfo() {
        logger.info(" 车主基本信息页面");
        return "CustomerManage/customer_info";
    }
    @ResponseBody
    @RequestMapping(value = "customerInfo_insert", method = RequestMethod.POST)
    public ControllerResult infoInsert(User user){
        logger.info("信息添加");
        userService.insert(user);
        return ControllerResult.getSuccessResult("添加成功");
    }

    @ResponseBody
    @RequestMapping(value = "customerInfo_pager", method= RequestMethod.GET)
    public Pager4EasyUI<User> info_pager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有车主");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(userService.count());
        List<User> users = userService.queryByPager(pager);
        return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
    }

    @ResponseBody
    @RequestMapping(value = "customerInfo_update", method = RequestMethod.POST)
    public ControllerResult info_update(User user){
        logger.info("信息修改");
        userService.update(user);
        return ControllerResult.getSuccessResult(" 修改成功");
    }

    @ResponseBody
    @RequestMapping(value = "customerInfo_status", method = RequestMethod.GET)
    public ControllerResult info_status(@Param("id")String id, @Param("status")String status){
        logger.info("状态修改");
        if(status.equals("Y")){
            userService.inactive(id);
        }else if (status.equals("N")){
            userService.active(id);
        }
        return ControllerResult.getSuccessResult(" 修改成功");
    }
}
