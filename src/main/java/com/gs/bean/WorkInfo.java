package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class WorkInfo {
	private String workId;
	private String recordId;
	private String userId;
	private Date workAssignTime;
	private Date workCreatedTime;
	private String workStatus;

	public String getWorkId(){
		return this.workId;
	}
	public void setWorkId(String workId){
		this.workId=workId;
	}

	public String getRecordId(){
		return this.recordId;
	}
	public void setRecordId(String recordId){
		this.recordId=recordId;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public Date getWorkAssignTime(){
		return this.workAssignTime;
	}
	public void setWorkAssignTime(Date workAssignTime){
		this.workAssignTime=workAssignTime;
	}

	public Date getWorkCreatedTime(){
		return this.workCreatedTime;
	}
	public void setWorkCreatedTime(Date workCreatedTime){
		this.workCreatedTime=workCreatedTime;
	}

	public String getWorkStatus(){
		return this.workStatus;
	}
	public void setWorkStatus(String workStatus){
		this.workStatus=workStatus;
	}

}