package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Accessories;
import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.bean.info.MaterialUseInfo;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.AccessoriesService;
import com.gs.service.MaintainRecordService;
import com.gs.service.MaterialUseInfoService;
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
 * Created by Xiao-Qiang on 2017/4/17.
 */
@Controller
@RequestMapping("materialUse")
public class MaterialUseController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaterialUseController.class);

    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY + "," +
            Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.COMPANY_ARTIFICER;

    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY + "," + Constants.COMPANY_ARTIFICER;


    @Resource
    private MaintainRecordService mrs;

    @Resource
    private MaterialUseInfoService muis;

    @Resource
    private AccessoriesService as;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String showMaterialUseInfo() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
        if (!CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("无权访问，想要访问请联系管理员!");
            return "error/notPermission";
        }
        logger.info("显示领料信息");
        return "dispatchingPicking/material_use";
    }

    @ResponseBody
    @RequestMapping(value = "query_pager", method = RequestMethod.GET)
    public Pager4EasyUI<MaterialUseInfo> queryBySpeedStatus(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("recordId") String recordId) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        User user = SessionGetUtil.getUser();
        logger.info("分页查询当前维修记录领料明细");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(muis.countBySpeedStatus(recordId, user));
        List<MaterialUseInfo> materialUseInfos = muis.queryBySpeedStatus(pager, recordId, user);
        return new Pager4EasyUI<MaterialUseInfo>(pager.getTotalRecords(), materialUseInfos);
    }

    @ResponseBody
    @RequestMapping(value = "updatePickingStatusById", method = RequestMethod.GET)
    public ControllerResult updatePickingStatus(@Param("id") String id, @Param("status") String status) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("更新失败");
                return ControllerResult.getFailResult("更新失败，没有该权限操作");
            }
            logger.info("更新领料审核状态");
            User user = SessionGetUtil.getUser();
            mrs.updatePickingStatusById(status, id);
            List<MaterialUseInfo> materialUseInfoList = muis.queryAll(id, user);
            List<Accessories> accessories = new ArrayList<Accessories>();
            for (MaterialUseInfo mui : materialUseInfoList) {
                Accessories a = new Accessories();
                a.setAccId(mui.getAccId());
                Accessories a2 = as.queryByIdTotalAndIdle(a.getAccId());
                a.setAccTotal(a2.getAccTotal() - mui.getAccCount());
                a.setAccIdle(a2.getAccIdle() - mui.getAccCount());
                accessories.add(a);
            }
            as.updateTotalAndIdle(accessories);
            return ControllerResult.getSuccessResult("更新成功!");
        } catch (Exception e) {
            logger.info("更新失败，出现了一个错误");
            return ControllerResult.getFailResult("更新失败，出现了一个错误");
        }
    }

}
