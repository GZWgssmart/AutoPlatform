package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class SupplyType {
	private String supplyTypeId;
	private String supplyTypeName;
	private String supplyTypeDes;
	private String companyId;
	private String supplyTypeStatus;

	public String getSupplyTypeId(){
		return this.supplyTypeId;
	}
	public void setSupplyTypeId(String supplyTypeId){
		this.supplyTypeId=supplyTypeId;
	}

	public String getSupplyTypeName(){
		return this.supplyTypeName;
	}
	public void setSupplyTypeName(String supplyTypeName){
		this.supplyTypeName=supplyTypeName;
	}

	public String getSupplyTypeDes(){
		return this.supplyTypeDes;
	}
	public void setSupplyTypeDes(String supplyTypeDes){
		this.supplyTypeDes=supplyTypeDes;
	}

	public String getCompanyId(){
		return this.companyId;
	}
	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getSupplyTypeStatus(){
		return this.supplyTypeStatus;
	}
	public void setSupplyTypeStatus(String supplyTypeStatus){
		this.supplyTypeStatus=supplyTypeStatus;
	}

}