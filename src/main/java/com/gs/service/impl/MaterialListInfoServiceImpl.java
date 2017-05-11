package com.gs.service.impl;

import com.gs.bean.MaterialListInfo;
import com.gs.common.bean.Pager;
import com.gs.dao.MaterialListInfoDAO;
import com.gs.service.MaterialListInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/4/26.
 */
@Service
public class MaterialListInfoServiceImpl implements MaterialListInfoService {

    @Resource
    private MaterialListInfoDAO materialListInfoDAO;

    public int insert(MaterialListInfo materialListInfo) {
        return 0;
    }

    public int batchInsert(List<MaterialListInfo> list) {
        return 0;
    }

    public int delete(MaterialListInfo materialListInfo) {
        return 0;
    }

    public int deleteById(String id) {
        return 0;
    }

    public int batchDelete(List<MaterialListInfo> list) {
        return 0;
    }

    public int update(MaterialListInfo materialListInfo) {
        return 0;
    }

    public int batchUpdate(List<MaterialListInfo> list) {
        return 0;
    }

    public List<MaterialListInfo> queryAll() {
        return null;
    }

    public List<MaterialListInfo> queryByStatus(String status) {
        return null;
    }

    public MaterialListInfo query(MaterialListInfo materialListInfo) {
        return null;
    }

    public MaterialListInfo queryById(String id) {
        return null;
    }

    public List<MaterialListInfo> queryByPager(Pager pager) {
        return materialListInfoDAO.queryByPager(pager);
    }

    public int count() {
        return materialListInfoDAO.count();
    }

    public int inactive(String id) {
        return materialListInfoDAO.inactive(id);
    }

    public int active(String id) {
        return materialListInfoDAO.active(id);
    }

    public List<MaterialListInfo> queryByStatus(Pager pager, String status) {
        return materialListInfoDAO.queryByStatus(pager, status);
    }

    public int termCount(String userName, String startTime, String endTime) {
        return materialListInfoDAO.termCount(userName, startTime, endTime);
    }

    public List<MaterialListInfo> termQueryPager(Pager pager, String userName, String startTime, String endTime) {
        return materialListInfoDAO.termQueryPager(pager, userName, startTime, endTime);
    }

    public List<MaterialListInfo> queryBySpeedStatus(Pager pager, String recordId) {
        return materialListInfoDAO.queryBySpeedStatus(pager, recordId);
    }

    public int countBySpeedStatus(String recordId) {
        return materialListInfoDAO.countBySpeedStatus(recordId);
    }

    public List<MaterialListInfo> queryBySpeedStatusAndStatus(Pager pager, String recordId, String materialStatus) {
        return materialListInfoDAO.queryBySpeedStatusAndStatus(pager, recordId, materialStatus);
    }

    public int statusCount(String recordId, String materialStatus) {
        return materialListInfoDAO.statusCount(recordId, materialStatus);
    }
}
