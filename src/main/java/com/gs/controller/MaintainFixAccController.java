package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.MaintainFixAcc;
import com.gs.common.bean.ControllerResult;
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
    @RequestMapping(value="insertList",method = RequestMethod.POST)
    public ControllerResult insertList(List<MaintainFixAcc> maintainFixAccList){
        maintainFixAccService.batchInsert(maintainFixAccList);
        for(MaintainFixAcc acc : maintainFixAccList){
            System.out.println(acc);
        }
        return ControllerResult.getSuccessResult("添加配件成功");
    }
}
