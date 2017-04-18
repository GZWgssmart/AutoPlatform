package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.IncomingOutgoing;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.IncomingOutgoingService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
        logger.info("分页查询所有收支类型");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(incomingOutgoingService.count());
        List<IncomingOutgoing> incomingTypes = incomingOutgoingService.queryByPager(pager);
        return new Pager4EasyUI<IncomingOutgoing>(pager.getTotalRecords(), incomingTypes);
    }
}
