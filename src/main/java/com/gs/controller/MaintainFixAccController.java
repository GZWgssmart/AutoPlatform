package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.MaintainFixAcc;
import com.gs.common.bean.ControllerResult;
import com.gs.common.util.SessionGetUtil;
import com.gs.dao.MaintainFixAccDAO;
import com.gs.service.MaintainFixAccService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by root on 2017/5/2.
 */
@Controller()
@RequestMapping("/maintainFixAcc")
public class MaintainFixAccController {
    private Logger logger = (Logger) LoggerFactory.getLogger(MaintainFixAccController.class);
    @Resource
    private MaintainFixAccService maintainFixAccService;

    @RequestMapping(value = "insertList", method = RequestMethod.POST)
    public ControllerResult insertList(List<MaintainFixAcc> maintainFixAccList) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            logger.info("添加项目基础配件");
            maintainFixAccService.batchInsert(maintainFixAccList);
            for (MaintainFixAcc acc : maintainFixAccList) {
                System.out.println(acc);
            }
            return ControllerResult.getSuccessResult("添加成功");
        } catch (Exception e) {
            logger.info("添加失败，出现了一个错误");
            return ControllerResult.getFailResult("添加失败，出现了一个错误");
        }
    }
}
