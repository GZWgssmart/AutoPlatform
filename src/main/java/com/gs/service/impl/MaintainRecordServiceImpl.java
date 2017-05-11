package com.gs.service.impl;

import com.gs.bean.Checkin;
import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
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

    public List<MaintainRecord> queryAll(User user) {
        return maintainRecordDAO.queryAll(user);
    }

    public List<MaintainRecord> queryByStatus(String status, User user) {
        return maintainRecordDAO.queryByStatus(status, user);
    }

    public MaintainRecord query(MaintainRecord maintainRecord, User user) {
        return maintainRecordDAO.query(maintainRecord, user);
    }

    public MaintainRecord queryById(String id) {
        return maintainRecordDAO.queryById(id);
    }

    public List<MaintainRecord> queryByPager(Pager pager, User user) {
        return maintainRecordDAO.queryByPager(pager, user);
    }

    public int count(User user) {
        return maintainRecordDAO.count(user);
    }

    public int inactive(String id) {
        return maintainRecordDAO.inactive(id);
    }

    public int active(String id) {
        return maintainRecordDAO.active(id);
    }

    public int countByStatus(String status, User user) {
        return maintainRecordDAO.countByStatus(status, user);
    }

    public List<MaintainRecord> queryPagerByStatus(Pager pager, String status, User user) {
        return maintainRecordDAO.queryPagerByStatus(pager, status, user);
    }

    public int countByCondition(MaintainRecord record, User user) {
        return maintainRecordDAO.countByCondition(record, user);
    }

    public List<MaintainRecord> queryPagerByCondition(Pager pager, MaintainRecord record, User user) {
        return maintainRecordDAO.queryPagerByCondition(pager, record, user);
    }

    public int countByTrackStatus(String status, User user) {
        return maintainRecordDAO.countByTrackStatus(status, user);
    }

    public List<MaintainRecord> queryPagerByTrackStatus(Pager pager, String status, User user) {
        return maintainRecordDAO.queryPagerByTrackStatus(pager, status, user);
    }

    public void updateTrackStatus(String trackStatus, String checkinId) {
        maintainRecordDAO.updateTrackStatus(trackStatus, checkinId);
    }

    public int updateSpeedStatusById(String speedStatus, String id) {
        return maintainRecordDAO.updateSpeedStatusById(speedStatus, id);
    }

    public int countBySpeedStatus(String[] speedStatus, User user) {
        return maintainRecordDAO.countBySpeedStatus(speedStatus, user);
    }

    public List<MaintainRecord> queryPagerBySpeedStatus(Pager pager, String[] speedStatus, User user) {
        return maintainRecordDAO.queryPagerBySpeedStatus(speedStatus, pager, user);
    }

    public int updatePickupTime(String recordId) {
        return maintainRecordDAO.updatePickupTime(recordId);
    }


}