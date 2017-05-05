package com.gs.service;

import com.gs.bean.Appointment;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppointmentService extends BaseService<String, Appointment>{


    public int countByStatus(String status);

    public List<Appointment> queryPagerByStatus(Pager pager, String status);

    public List<Appointment> querySpeedStatus(Pager pager);

    public int countByCondition(Appointment appointment);

    public List<Appointment> queryPagerByCondition(Pager pager, Appointment appointment);

    public int updateSpeedStatusById(String speedStatus, String id);

}