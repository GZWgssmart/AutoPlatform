package com.gs.service;

import com.gs.bean.Checkin;
import com.gs.bean.MaintainRecord;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface MaintainRecordService extends BaseService<String, MaintainRecord>{
    /**
     * 根据状态计数
     * @param status
     * @return
     */
    public int countByStatus(String status);

    /**
     * 根据状态分页查询
     * @param pager
     * @param status
     * @return
     */
    public List<MaintainRecord> queryPagerByStatus(Pager pager, String status);

    /**
     * 根据查询条件计数
     * @param record
     * @return
     */
    public int countByCondition(MaintainRecord record);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param record
     * @return
     */
    public List<MaintainRecord> queryPagerByCondition(Pager pager, MaintainRecord record);

    /**
     * 根据回访状态计数
     * @param status
     * @return
     */
    public int countByTrackStatus(String status);

    /**
     * 根据回访状态分页查询
     * @return
     */
    public List<MaintainRecord> queryPagerByTrackStatus( Pager pager,String status);

    /**
     * 根据状态checkId更新
     * @return
     */
    public void updateTrackStatus(String checkinId);

    /**
     * 根据id更新记录的进度
     * @param speedStatus 当前进度
     * @param id 维修保养记录的id
     * @return
     */
    public int updateSpeedStatusById(String speedStatus, String id);


}