package com.gs.service.impl;

import com.gs.bean.Checkin;
import com.gs.bean.MaintainRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.MaintainRecordDAO;
import com.gs.service.MaintainRecordService;
import com.gs.common.bean.Pager;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:58:54
 */
@Service
public class MaintainRecordServiceImpl implements MaintainRecordService {

    @Resource
    private MaintainRecordDAO maintainRecordDAO;

    public int insert(MaintainRecord maintainRecord) {
        return maintainRecordDAO.insert(maintainRecord);
    }

    public int batchInsert(List<MaintainRecord> list) {
        return maintainRecordDAO.batchInsert(list);
    }

    public int delete(MaintainRecord maintainRecord) {
        return maintainRecordDAO.delete(maintainRecord);
    }

    public int deleteById(String id) {
        return maintainRecordDAO.deleteById(id);
    }

    public int batchDelete(List<MaintainRecord> list) {
        return maintainRecordDAO.batchDelete(list);
    }

    public int update(MaintainRecord maintainRecord) {
        return maintainRecordDAO.update(maintainRecord);
    }

    public int batchUpdate(List<MaintainRecord> list) {
        return maintainRecordDAO.batchUpdate(list);
    }

    public List<MaintainRecord> queryAll() {
        return maintainRecordDAO.queryAll();
    }

    public List<MaintainRecord> queryByStatus(String status) {
        return maintainRecordDAO.queryByStatus(status);
    }

    public MaintainRecord query(MaintainRecord maintainRecord) {
        return maintainRecordDAO.query(maintainRecord);
    }

    public MaintainRecord queryById(String id) {
        return maintainRecordDAO.queryById(id);
    }

    public List<MaintainRecord> queryByPager(Pager pager) {
        return maintainRecordDAO.queryByPager(pager);
    }

    public int count() {
        return maintainRecordDAO.count();
    }

    public int inactive(String id) {
        return maintainRecordDAO.inactive(id);
    }

    public int active(String id) {
        return maintainRecordDAO.active(id);
    }

    public int countByStatus(String status) {
        return maintainRecordDAO.countByStatus(status);
    }

    public List<MaintainRecord> queryPagerByStatus(Pager pager, String status) {
        return maintainRecordDAO.queryPagerByStatus(pager, status);
    }

    public int countByCondition(MaintainRecord record) {
        return maintainRecordDAO.countByCondition(record);
    }

    public List<MaintainRecord> queryPagerByCondition(Pager pager, MaintainRecord record) {
        return maintainRecordDAO.queryPagerByCondition(pager, record);
    }

    public int countByTrackStatus(String status) {
        return maintainRecordDAO.countByTrackStatus(status);
    }

    public List<MaintainRecord> queryPagerByTrackStatus( Pager pager, String status) {
        return maintainRecordDAO.queryPagerByTrackStatus(pager, status);
    }

    public void updateTrackStatus(String checkinId) {
        maintainRecordDAO.updateTrackStatus(checkinId);
    }

    public int updateSpeedStatusById(String speedStatus, String id) {
        return maintainRecordDAO.updateSpeedStatusById(speedStatus, id);
    }

    public int countBySpeedStatus(String[] speedStatus) {
        return maintainRecordDAO.countBySpeedStatus(speedStatus);
    }

    public List<MaintainRecord> queryPagerBySpeedStatus(Pager pager, String[] speedStatus) {
        return maintainRecordDAO.queryPagerBySpeedStatus(speedStatus, pager);
    }

    public int countByRecordStatus() {
        return maintainRecordDAO.countByRecordStatus();
    }
    public List<MaintainRecord> queryPagerByMessage(Pager pager) {
        return maintainRecordDAO.queryPagerByMessage(pager);
    }


}