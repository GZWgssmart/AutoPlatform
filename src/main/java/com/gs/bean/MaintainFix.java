package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21 维修保养项目表
*/
public class MaintainFix {
	private String maintainId; //保养项目Id
	private String maintainName; //保养项目名称
	private String maintainHour; //保养工时
	private String maintainMoney; //保养项目基础费用
	private String maintainManHourFee; //保养项目工时费
	private String maintainOrFix; //标识保养还是维修
	private String maintainDes; //维修保养描述
	private String companyId; //维修保养所属Id t_company
	private String maintainStatus; //维修保养状态 Y N

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