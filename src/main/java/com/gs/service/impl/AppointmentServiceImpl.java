package com.gs.service.impl;

import com.gs.bean.Appointment;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.AppointmentDAO;
import com.gs.service.AppointmentService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Resource
	private AppointmentDAO appointmentDAO;

	public int insert(Appointment appointment) { return appointmentDAO.insert(appointment); }
	public int batchInsert(List<Appointment> list) { return appointmentDAO.batchInsert(list); }
	public int delete(Appointment appointment) { return appointmentDAO.delete(appointment); }
	public int deleteById(String id) {
        return appointmentDAO.deleteById(id);
    }
	public int batchDelete(List<Appointment> list) { return appointmentDAO.batchDelete(list); }
	public int update(Appointment appointment) { return appointmentDAO.update(appointment); }
	public int batchUpdate(List<Appointment> list) { return appointmentDAO.batchUpdate(list); }
	public List<Appointment> queryAll() { return appointmentDAO.queryAll(); }
	public List<Appointment> queryByStatus(String status) { return appointmentDAO.queryByStatus(status); }
	public Appointment query(Appointment appointment) { return appointmentDAO.query(appointment); }
	public Appointment queryById(String id) { return appointmentDAO.queryById(id); }
	public List<Appointment> queryByPager(Pager pager) { return appointmentDAO.queryByPager(pager); }
	public int count() { return appointmentDAO.count(); }
	public int inactive(String id) { return appointmentDAO.inactive(id); }
	public int active(String id) { return appointmentDAO.active(id); }

	@Override
	public int countByStatus(String status) {
		return appointmentDAO.countByStatus(status);
	}

	@Override
	public int countByCondition(Appointment appointment) {
		return appointmentDAO.countByCondition(appointment);
	}

	@Override
	public List<Appointment> queryPagerByCondition(Pager pager, Appointment appointment) {
		return appointmentDAO.queryPagerByCondition(pager,appointment);
	}

	@Override
	public List<Appointment> queryPagerByStatus(Pager pager, String status) {
		return appointmentDAO.queryPagerByStatus(pager,status);
	}
}