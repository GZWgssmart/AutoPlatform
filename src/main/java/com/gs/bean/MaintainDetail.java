package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class MaintainDetail {
	private String maintainDetailId;
	private String maintainRecordId;
	private String maintainItemId;
	private String maintainDiscount;
	private Date mdCreatedTime;

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