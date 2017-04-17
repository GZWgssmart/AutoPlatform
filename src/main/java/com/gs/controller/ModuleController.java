package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Module;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.ModuleService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/4/17.
 */
@Controller
@RequestMapping("module")
public class ModuleController {

    private Logger logger = (Logger) LoggerFactory.getLogger(ModuleController.class);

    @Resource
    private ModuleService moduleService;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showModuleInfo() {
        logger.info("显示模块信息");
        return "system/module";
    }

    @ResponseBody
    @RequestMapping(value="query_pager",method= RequestMethod.GET)
    public Pager4EasyUI<Module> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有模块");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(moduleService.count());
        List<Module> Modules = moduleService.queryByPager(pager);
        return new Pager4EasyUI<Module>(pager.getTotalRecords(), Modules);
    }


    @ResponseBody
    @RequestMapping(value = "add_module", method = RequestMethod.POST)
    private ControllerResult addModule(Module module) {
        logger.info("添加模块");
        module.setModuleStatus("Y");
        moduleService.insert(module);
        return ControllerResult.getSuccessResult("添加成功");
    }

    @ResponseBody
    @RequestMapping(value = "update_module", method = RequestMethod.POST)
    private ControllerResult updateModule(Module module) {
        logger.info("更新模块");
        module.setModuleStatus("Y");
        moduleService.update(module);
        return ControllerResult.getSuccessResult("修改成功");
    }
}
