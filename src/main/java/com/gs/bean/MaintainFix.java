package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class MaintainFix {
	private String maintainId;
	private String maintainName;
	private String maintainHour;
	private String maintainMoney;
	private String maintainManHourFee;
	private String maintainOrFix;
	private String maintainDes;
	private String companyId;
	private String maintainStatus;

	public String getMaintainId(){
		return this.maintainId;
	}
	public void setMaintainId(String maintainId){
		this.maintainId=maintainId;
	}

	public String getMaintainName(){
		return this.maintainName;
	}
	public void setMaintainName(String maintainName){
		this.maintainName=maintainName;
	}

	public String getMaintainHour(){
		return this.maintainHour;
	}
	public void setMaintainHour(String maintainHour){
		this.maintainHour=maintainHour;
	}

	public String getMaintainMoney(){
		return this.maintainMoney;
	}
	public void setMaintainMoney(String maintainMoney){
		this.maintainMoney=maintainMoney;
	}

	public String getMaintainManHourFee(){
		return this.maintainManHourFee;
	}
	public void setMaintainManHourFee(String maintainManHourFee){
		this.maintainManHourFee=maintainManHourFee;
	}

	public String getMaintainOrFix(){
		return this.maintainOrFix;
	}
	public void setMaintainOrFix(String maintainOrFix){
		this.maintainOrFix=maintainOrFix;
	}

	public String getMaintainDes(){
		return this.maintainDes;
	}
	public void setMaintainDes(String maintainDes){
		this.maintainDes=maintainDes;
	}

	public String getCompanyId(){
		return this.companyId;
	}
	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getMaintainStatus(){
		return this.maintainStatus;
	}
	public void setMaintainStatus(String maintainStatus){
		this.maintainStatus=maintainStatus;
	}

}