package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21  保养提醒记录表
*/
public class MaintainRemind {
	private String remindId; //保养提醒记录Id
	private String userId;  //用户编号 来源于t_user表
	private Date lastMaintainTime;  //上次保养时间
	private String lastMaintainMileage;  //上次保养行驶里程
	private String remindMsg;  //保养提醒消息
	private Date remindTime;  //保养提醒时间
	private String remindType;  //保养提醒方式
	private Date remindCreatedTime;  //保养提醒记录创建时间
	private User user;//传递user对象

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getRemindId(){
		return this.remindId;
	}
	public void setRemindId(String remindId){
		this.remindId=remindId;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public Date getLastMaintainTime(){
		return this.lastMaintainTime;
	}
	public void setLastMaintainTime(Date lastMaintainTime){
		this.lastMaintainTime=lastMaintainTime;
	}

	public String getLastMaintainMileage(){
		return this.lastMaintainMileage;
	}
	public void setLastMaintainMileage(String lastMaintainMileage){
		this.lastMaintainMileage=lastMaintainMileage;
	}

	public String getRemindMsg(){
		return this.remindMsg;
	}
	public void setRemindMsg(String remindMsg){
		this.remindMsg=remindMsg;
	}

	public Date getRemindTime(){
		return this.remindTime;
	}
	public void setRemindTime(Date remindTime){
		this.remindTime=remindTime;
	}

	public String getRemindType(){
		return this.remindType;
	}
	public void setRemindType(String remindType){
		this.remindType=remindType;
	}

	public Date getRemindCreatedTime(){
		return this.remindCreatedTime;
	}
	public void setRemindCreatedTime(Date remindCreatedTime){
		this.remindCreatedTime=remindCreatedTime;
	}

}