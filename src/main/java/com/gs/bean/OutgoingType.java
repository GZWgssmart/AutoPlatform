package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class OutgoingType {
	private String outTypeId; 	// 支出类型编号
	private String outTypeName;  // 支出类型名称
	private String outTypeStatus; // 支出类型状态

	public String getOutTypeId(){
		return this.outTypeId;
	}
	public void setOutTypeId(String outTypeId){
		this.outTypeId=outTypeId;
	}

	public String getOutTypeName(){
		return this.outTypeName;
	}
	public void setOutTypeName(String outTypeName){
		this.outTypeName=outTypeName;
	}

	public String getOutTypeStatus(){
		return this.outTypeStatus;
	}
	public void setOutTypeStatus(String outTypeStatus){
		this.outTypeStatus=outTypeStatus;
	}

}