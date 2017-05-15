package com.gs.dao;

import com.gs.bean.Checkin;
import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:35:15
 */
@Repository
public interface MaintainRecordDAO extends BaseDAO<String, MaintainRecord> {
    /**
     * 根据状态计数
     *
     * @param status
     * @return
     */
    public int countByStatus(@Param("status") String status, @Param("user") User user);

    /**
     * 根据状态分页查询
     *
     * @param pager
     * @param status
     * @return
     */
    public List<MaintainRecord> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status, @Param("user") User user);

    /**
     * 根据查询条件计数
     *
     * @param record
     * @return
     */
    public int countByCondition(@Param("record") MaintainRecord record, @Param("user") User user, @Param("speedStatus") String[] speedStatus);

    /**
     * 根据查询条件分页查询
     *
     * @param pager
     * @param record
     * @return
     */
    public List<MaintainRecord> queryPagerByCondition(@Param("pager") Pager pager, @Param("record") MaintainRecord record, @Param("user") User user, @Param("speedStatus") String[] speedStatus);

    /**
     * 根据状态计数
     *
     * @param status
     * @return
     */
    public int countByTrackStatus(@Param("status") String status, @Param("user") User user);

    /**
     * 根据状态分页查询
     *
     * @param pager
     * @param status
     * @return
     */
    public List<MaintainRecord> queryPagerByTrackStatus(@Param("pager") Pager pager, @Param("status") String status, @Param("user") User user);

    /**
     * 根据状态checkId更新
     *
     * @param checkinId
     * @return
     */
    public void updateTrackStatus(@Param("trackStatus") String trackStatus, @Param("checkinId") String checkinId);

    /**
     * 根据id更新记录的进度
     *
     * @param speedStatus 当前进度
     * @param id          维修保养记录的id
     * @return
     */
    public int updateSpeedStatusById(@Param("speedStatus") String speedStatus, @Param("id") String id);

    /**
     * 根据进度状态计数
     *
     * @param speedStatus
     * @return
     */
    public int countBySpeedStatus(@Param("speedStatus") String[] speedStatus, @Param("user") User user);

    /**
     * 根据进度状态分页查询
     *
     * @param pager
     * @param speedStatus
     * @return
     */
    public List<MaintainRecord> queryPagerBySpeedStatus(@Param("speedStatus") String[] speedStatus, @Param("pager") Pager pager, @Param("user") User user);

    /**
     * 根据维修保养记录id把提车时间更新成当前时间
     *
     * @param recordId
     * @return
     */
    public int updatePickupTime(String recordId);

    /*
    * 默认查询本月的维修保养记录统计
    * */
    public List<MaintainRecord> queryByDefault(@Param("maintainOrFix") String maintainOrFix, @Param("companyId") String companyId);

    /*
    * 根据年，月，季度，周，日查询所有维修保养统计
    * */
    public List<MaintainRecord> queryByCondition(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("maintainOrFix") String maintainOrFix,
                                                 @Param("type") String type, @Param("companyId") String companyId);

    /**
     * 根据员工的id查询该员工负责的维修保养记录，计算个数
     * @param user
     * @return
     */
    public int countByUserId(@Param("user") User user);

    /**
     * 根据员工的id查询该员工负责的维修保养记录，分页查询
     * @param pager
     * @param user
     * @return
     */
    public List<MaintainRecord> queryPagerByUserId(@Param("pager") Pager pager, @Param("user") User user);

    /**
     * 根据id更新记录的进度
     *
     * @param pickingStatus 领料状态
     * @param id          维修保养记录的id
     * @return
     */
    public int updatePickingStatusById(@Param("pickingStatus") String pickingStatus, @Param("id") String id);

}