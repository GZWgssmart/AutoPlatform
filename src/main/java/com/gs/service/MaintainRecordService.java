package com.gs.service;

import com.gs.bean.Checkin;
import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
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
    public int countByStatus(String status, User user);

    /**
     * 根据状态分页查询
     * @param pager
     * @param status
     * @return
     */
    public List<MaintainRecord> queryPagerByStatus(Pager pager, String status, User user);

    /**
     * 根据查询条件计数
     * @param record
     * @return
     */
    public int countByCondition(MaintainRecord record, User user);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param record
     * @return
     */
    public List<MaintainRecord> queryPagerByCondition(Pager pager, MaintainRecord record, User user);

    /**
     * 根据回访状态计数
     * @param status
     * @return
     */
    public int countByTrackStatus(String status, User user);

    /**
     * 根据回访状态分页查询
     * @return
     */
    public List<MaintainRecord> queryPagerByTrackStatus( Pager pager,String status, User user);

    /**
     * 根据状态checkId更新
     * @return
     */
    public void updateTrackStatus(String trackStatus, String checkinId);


    /*修改预计结束时间*/
    public void updateTime(MaintainRecord maintainRecord);


    /**
     * 根据id更新记录的进度
     * @param speedStatus 当前进度
     * @param id 维修保养记录的id
     * @return
     */
    public int updateSpeedStatusById(String speedStatus, String id);

    /**
     * 根据进度状态计数
     * @param speedStatus
     * @return
     */
    public int countBySpeedStatus(String[] speedStatus, User user);

    /**
     * 根据进度状态分页查询
     * @param pager
     * @param speedStatus
     * @return
     */
    public List<MaintainRecord> queryPagerBySpeedStatus(Pager pager, String[] speedStatus, User user);

    /**
     * 根据维修保养记录id把提车时间更新成当前时间
     * @param recordId
     * @return
     */
    public int updatePickupTime(String recordId);

    /*
* 默认查询本月的维修保养记录统计
* */
    public List<MaintainRecord> queryByDefault(String maintainOrFix,String companyId);

    /*
    * 根据年，月，季度，周，日查询所有维修保养统计
    * */
    public List<MaintainRecord> queryByCondition(String startTime,String endTime,String maintainOrFix,
                                                 String type, String companyId);

    /**
     * 根据员工的id查询该员工负责的维修保养记录，计算个数
     * @param user
     * @return
     */
    public int countByUserId(User user, String pickingStatus, String[] speedStatus);

    /**
     * 根据员工的id查询该员工负责的维修保养记录，分页查询
     * @param pager
     * @param user
     * @return
     */
    public List<MaintainRecord> queryPagerByUserId(Pager pager, User user, String pickingStatus, String[] speedStatus);

    /**
     * 根据id更新记录的进度
     *
     * @param pickingStatus 领料状态
     * @param id          维修保养记录的id
     * @return
     */
    public int updatePickingStatusById(String pickingStatus, String id);

    /**
     * 根据进度状态和领料状态计数
     *
     * @param speedStatus
     * @return
     */
    public int countBySpeedStatusAndPickingStatus(String[] speedStatus, String pickingStatus, User user);

    /**
     * 根据进度状态和领料状态分页查询
     *
     * @param pager
     * @param speedStatus
     * @return
     */
    public List<MaintainRecord> queryPagerBySpeedStatusAndPickingStatus(String[] speedStatus, Pager pager, String pickingStatus, User user);


    /**
     * 根据维修保养记录分页查询车主进度
     * @return
     */
    public List<MaintainRecord> queryByProgressPager(Pager pager, User user);

    /**
     * 根据维修保养记录分页查询车主进度个数
     * @return
     */
    public int countByProgressPager(User user);
}