package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class IncomingType {
	private String inTypeId;  	// 收入类型编号
	private String inTypeName;	// 收入类型名称
	private String inTypeStatus;	// 收入类型状态

	public String getInTypeId(){
		return this.inTypeId;
	}
	public void setInTypeId(String inTypeId){
		this.inTypeId=inTypeId;
	}

	public String getInTypeName(){
		return this.inTypeName;
	}
	public void setInTypeName(String inTypeName){
		this.inTypeName=inTypeName;
	}

	public String getInTypeStatus(){
		return this.inTypeStatus;
	}
	public void setInTypeStatus(String inTypeStatus){
		this.inTypeStatus=inTypeStatus;
	}

}