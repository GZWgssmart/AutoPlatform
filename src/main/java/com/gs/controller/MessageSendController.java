package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Complaint;
import com.gs.bean.MessageSend;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.ComplaintService;
import com.gs.service.MessageSendService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JangoGuo on 2017/4/18.
 */
@Controller
@RequestMapping("/MessageSend")
public class MessageSendController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MessageSendController.class);


    @Resource
    private MessageSendService messageSendService;


    @RequestMapping(value = "show_MessageSend", method = RequestMethod.GET)
    public String messageSend() {
        logger.info("显示短信发送页面");
        return "customer/message_send";
    }

    @ResponseBody
    @RequestMapping(value="query_pager",method= RequestMethod.GET)
    public Pager4EasyUI<MessageSend> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有投诉");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(messageSendService.count());
        List<MessageSend> complaintList = messageSendService.queryByPager(pager);
        return new Pager4EasyUI<MessageSend>(pager.getTotalRecords(), complaintList);
    }

    @ResponseBody
    @RequestMapping(value = "update_messageSend", method = RequestMethod.GET)
    public ControllerResult updateMessageSend(MessageSend messageSend){
        logger.info("更新工资");

        return ControllerResult.getSuccessResult("更新成功");
    }




}

































