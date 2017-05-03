package com.gs.dao;

import com.gs.bean.Appointment;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface AppointmentDAO extends BaseDAO<String, Appointment>{

    public int countByStatus(String status);

    public List<Appointment> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status);

    public int countByCondition(Appointment appointment);

    public List<Appointment> queryPagerByCondition(@Param("pager") Pager pager, @Param("appointment") Appointment appointment);

    public int updateSpeedStatusById(@Param("speedStatus") String speedStatus, @Param("id") String id);

}