package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.MaterialListInfo;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.MaterialListInfoService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.method.P;
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
@RequestMapping("materialList")
public class MaterialListController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaterialListController.class);

    @Resource
    private MaterialListInfoService materialListInfoService;
    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showMaterialListInfo() {
        logger.info("显示物料清单信息");
        return "dispatchingPicking/material_list";
    }

    @ResponseBody
    @RequestMapping(value = "query_pager", method = RequestMethod.GET)
    public Pager4EasyUI<MaterialListInfo> queryPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        logger.info("分页查询所有物料清单");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(materialListInfoService.count());
        List<MaterialListInfo> materialListInfos = materialListInfoService.queryByPager(pager);
        return new Pager4EasyUI<MaterialListInfo>(pager.getTotalRecords(), materialListInfos);
    }

    @ResponseBody
    @RequestMapping(value = "queryByStatus_materialList", method = RequestMethod.GET)
    public Pager4EasyUI<MaterialListInfo> queryByStatus(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("status") String status) {
        if (status.equals("Y")) {
            logger.info("分页查询可用的物料清单");
        } else {
            logger.info("分页查询不可用的物料清单");
        }
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(materialListInfoService.count());
        List<MaterialListInfo> materialListInfos = materialListInfoService.queryByStatus(pager, status);
        return new Pager4EasyUI<MaterialListInfo>(pager.getTotalRecords(), materialListInfos);
    }

    @ResponseBody
    @RequestMapping(value = "select_query", method = RequestMethod.GET)
    public Pager4EasyUI<MaterialListInfo> selectQueryPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("userName") String userName, @Param("startTime") String startTime, @Param("endTime") String endTime) {
        logger.info("条件分页查询物料清单");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(materialListInfoService.termCount(userName, startTime, endTime));
        List<MaterialListInfo> materialListInfos = materialListInfoService.termQueryPager(pager, userName, startTime, endTime);
        return new Pager4EasyUI<MaterialListInfo>(pager.getTotalRecords(), materialListInfos);
    }

    @ResponseBody
    @RequestMapping(value = "update_status", method = RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status") String status) {
        if (status.equals("Y")) {
            logger.info("激活物料清单状态");
            materialListInfoService.active(id);
        } else if (status.equals("N")) {
            logger.info("冻结物料清单状态");
            materialListInfoService.inactive(id);
        }
        return ControllerResult.getSuccessResult("更新成功");
    }
}
