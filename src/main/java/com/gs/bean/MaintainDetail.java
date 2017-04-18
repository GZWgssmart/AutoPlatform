package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21 维修保养明细表
*/
public class MaintainDetail {
	private String maintainDetailId;  //维修保养Id
	private String maintainRecordId;  //维修保养记录编号，来源于t_maintain_record表
	private String maintainItemId; //维修保养项目编号，来源于t_maintain_fix表，可为空
	private String maintainDiscount; //维修保养项目折扣
	private Date mdCreatedTime; //维修明细时间

	public String getMaintainDetailId(){
		return this.maintainDetailId;
	}
	public void setMaintainDetailId(String maintainDetailId){
		this.maintainDetailId=maintainDetailId;
	}

	public String getMaintainRecordId(){
		return this.maintainRecordId;
	}
	public void setMaintainRecordId(String maintainRecordId){
		this.maintainRecordId=maintainRecordId;
	}

	public String getMaintainItemId(){
		return this.maintainItemId;
	}
	public void setMaintainItemId(String maintainItemId){
		this.maintainItemId=maintainItemId;
	}

	public String getMaintainDiscount(){
		return this.maintainDiscount;
	}
	public void setMaintainDiscount(String maintainDiscount){
		this.maintainDiscount=maintainDiscount;
	}

	public Date getMdCreatedTime(){
		return this.mdCreatedTime;
	}
	public void setMdCreatedTime(Date mdCreatedTime){
		this.mdCreatedTime=mdCreatedTime;
	}

}