package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.*;
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
@RequestMapping("/trackVisit")
public class TrackVisitController {

    private Logger logger = (Logger) LoggerFactory.getLogger(TrackVisitController.class);


    @Resource
    private TrackListService trackListService;

    @Resource
    private CheckinService checkinService;

    @Resource
    private MaintainRecordService maintainRecordService;

    @Resource
    private UserService userService;


    @RequestMapping(value = "show_trackVisit", method = RequestMethod.GET)
    public String messageSend() {
        logger.info("显示追踪访问页面");
        return "customer/track_visit";
    }

    @ResponseBody
    @RequestMapping(value="query_pager",method= RequestMethod.GET)
    public Pager4EasyUI<TrackList> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有追踪访问");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(trackListService.count());
        List<TrackList> trackListList = trackListService.queryByPager(pager);
        return new Pager4EasyUI<TrackList>(pager.getTotalRecords(), trackListList);
    }

    @ResponseBody
    @RequestMapping(value="add_track", method=RequestMethod.POST)
    public ControllerResult trackAdd(TrackList trackList){
        logger.info("添加回访");
        trackList.setTrackUser("c9179fd3-84d2-42ba-b24d-6ed444c838b5");
        trackListService.insert(trackList);
        Checkin checkin = checkinService.queryByTrackStatus(trackList.getUserId());
        maintainRecordService.updateTrackStatus(checkin.getCheckinId());
        return ControllerResult.getSuccessResult("添加成功");

    }


}

































