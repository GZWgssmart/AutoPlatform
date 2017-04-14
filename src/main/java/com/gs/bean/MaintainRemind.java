package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class MaintainRemind {
	private String remindId;
	private String userId;
	private Date lastMaintainTime;
	private String lastMaintainMileage;
	private String remindMsg;
	private Date remindTime;
	private String remindType;
	private Date remindCreatedTime;

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