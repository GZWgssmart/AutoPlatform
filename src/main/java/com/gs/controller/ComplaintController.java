package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Complaint;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.ComplaintService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.session.Session;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by JangoGuo on 2017/4/18.
 */
@Controller
@RequestMapping("/complaint")
public class ComplaintController {

    private Logger logger = (Logger) LoggerFactory.getLogger(ComplaintController.class);


    @Resource
    private ComplaintService complaintService;


    @RequestMapping(value = "show_complaint", method = RequestMethod.GET)
    public String complaint() {
        logger.info("显示投诉页面");
        return "customer/complaint";
    }

    @ResponseBody
    @RequestMapping(value="query_pager",method= RequestMethod.GET)
    public Pager4EasyUI<Complaint> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有投诉");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(complaintService.count());
        List<Complaint> complaintList = complaintService.queryByPager(pager);
        return new Pager4EasyUI<Complaint>(pager.getTotalRecords(), complaintList);
    }

    @ResponseBody
    @RequestMapping(value="add_customer", method=RequestMethod.POST)
    public ControllerResult ConplaintAdd(Complaint complaint, HttpSession session){
        logger.info("用户添加投诉");
        User user = (User) session.getAttribute("user");
        complaint.setUserId(user.getUserId());
        complaintService.insert(complaint);
        return ControllerResult.getSuccessResult("添加成功");
    }

    @ResponseBody
    @RequestMapping(value="add_admin", method=RequestMethod.POST)
    public ControllerResult ConplaintReply(Complaint complaint, HttpSession session){
        logger.info("员工回复");
        User user = (User) session.getAttribute("user");
        complaint.setUserId(user.getUserId());
        complaintService.updateReply(complaint);
        return ControllerResult.getSuccessResult("回复成功");
    }

}

































