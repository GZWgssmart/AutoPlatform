package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Company;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import com.gs.service.AppointmentService;
import com.gs.bean.Appointment;
import com.gs.common.bean.Pager4EasyUI;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("appointment")
public class AppointmentController {


    private Logger logger = (Logger) LoggerFactory.getLogger(AppointmentController.class);

    @Resource
    private AppointmentService appointmentService;


    @RequestMapping(value = "appointment", method = RequestMethod.GET)
    public String appointment() {
        logger.info("预约管理");
        return "maintenanceAppointment/appointment";
    }

    @ResponseBody
    @RequestMapping(value = "query_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Appointment> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询预约");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(appointmentService.count());
        List<Appointment> appointments = appointmentService.queryByPager(pager);
        return new Pager4EasyUI<Appointment>(pager.getTotalRecords(),appointments);
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult appointmentAdd(Appointment appointment){
        logger.info("添加预约");
        appointment.setCompanyId("65dc09ac-23e2-11e7-ba3e-juyhgt91a73a");
        appointmentService.insert(appointment);
        System.out.print(appointment);
        return ControllerResult.getSuccessResult("添加成功");
    }

    @ResponseBody
    @RequestMapping(value="update", method=RequestMethod.POST)
    public ControllerResult appointmentUpdate(Appointment appointment){
        logger.info("更新预约");
        appointment.setCompanyId("65dc09ac-23e2-11e7-ba3e-juyhgt91a73a");
        appointmentService.update(appointment);
        return ControllerResult.getSuccessResult("更新成功");
    }

    @ResponseBody
    @RequestMapping(value="update_status", method=RequestMethod.GET)
    public ControllerResult updateAppointmentStatus(String appointmentId,String status){
            logger.info("更新预约状态");
            if (status.equals("Y")) {
                appointmentService.inactive(appointmentId);
            } else {
                appointmentService.active(appointmentId);
            }
            return ControllerResult.getSuccessResult("更新成功");
        }

    @ResponseBody
    @RequestMapping(value = "appointment_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryMaintenanceAppointmentAll() {
        logger.info("查询所有预约信息");
        List<Appointment> appointments = appointmentService.queryAll();
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (Appointment appointment :appointments) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(appointment.getAppointmentId());
            comboBox4EasyUI.setText(appointment.getUserName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }

    @ResponseBody
    @RequestMapping(value = "id", method = RequestMethod.GET)
    public Appointment queryById(String id) {
        logger.info("根据id查询预约信息");
        return appointmentService.queryById(id);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}