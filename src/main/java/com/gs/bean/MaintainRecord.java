package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class MaintainRecord {
	private String recordId;
	private String checkinId;
	private Date startTime;
	private Date endTime;
	private Date actualEndTime;
	private Date recordCreatedTime;
	private Date pickupTime;
	private String recordDes;
	private String recordStatus;

	public String getRecordId(){
		return this.recordId;
	}
	public void setRecordId(String recordId){
		this.recordId=recordId;
	}

	public String getCheckinId(){
		return this.checkinId;
	}
	public void setCheckinId(String checkinId){
		this.checkinId=checkinId;
	}

	public Date getStartTime(){
		return this.startTime;
	}
	public void setStartTime(Date startTime){
		this.startTime=startTime;
	}

	public Date getEndTime(){
		return this.endTime;
	}
	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}

	public Date getActualEndTime(){
		return this.actualEndTime;
	}
	public void setActualEndTime(Date actualEndTime){
		this.actualEndTime=actualEndTime;
	}

	public Date getRecordCreatedTime(){
		return this.recordCreatedTime;
	}
	public void setRecordCreatedTime(Date recordCreatedTime){
		this.recordCreatedTime=recordCreatedTime;
	}

	public Date getPickupTime(){
		return this.pickupTime;
	}
	public void setPickupTime(Date pickupTime){
		this.pickupTime=pickupTime;
	}

	public String getRecordDes(){
		return this.recordDes;
	}
	public void setRecordDes(String recordDes){
		this.recordDes=recordDes;
	}

	public String getRecordStatus(){
		return this.recordStatus;
	}
	public void setRecordStatus(String recordStatus){
		this.recordStatus=recordStatus;
	}

}