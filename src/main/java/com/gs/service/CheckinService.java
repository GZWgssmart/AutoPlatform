package com.gs.service;

import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface CheckinService extends BaseService<String, Checkin>{
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
    public List<Checkin> queryPagerByStatus(Pager pager, String status, User user);

    /**
     * 根据查询条件计数
     * @param checkin
     * @return
     */
    public int countByCondition(Checkin checkin, User user);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param checkin
     * @return
     */
    public List<Checkin> queryPagerByCondition(Pager pager, Checkin checkin, User user);

    /**
     * 根据查询userId查询回访状态
     * @param userId
     * @return
     */
    public Checkin queryByTrackStatus(String userId, User user);

    /**
     * 根据指定的{top}查询前{top}条数据
     * @param top
     * @param user
     * @return
     */
    public List<Checkin> queryByTop(int top, User user);

    public List<Checkin> queryMyName(User user);

}