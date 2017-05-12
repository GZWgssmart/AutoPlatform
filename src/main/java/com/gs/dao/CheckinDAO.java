package com.gs.dao;

import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface CheckinDAO extends BaseDAO<String, Checkin>{

    /**
     * 根据状态计数
     * @param status
     * @return
     */
    public int countByStatus(@Param("status") String status, @Param("user") User user);

    /**
     * 根据状态分页查询
     * @param pager
     * @param status
     * @return
     */
    public List<Checkin> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status, @Param("user") User user);

    /**
     * 根据查询条件计数
     * @param checkin
     * @return
     */
    public int countByCondition(@Param("checkin") Checkin checkin, @Param("user") User user);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param checkin
     * @return
     */
    public List<Checkin> queryPagerByCondition(@Param("pager") Pager pager, @Param("checkin") Checkin checkin, @Param("user") User user);

    /**
     * 根据查询userId查询回访状态
     * @param userId
     * @return
     */
    public Checkin queryByTrackStatus(@Param("userId") String userId, @Param("user") User user);

}