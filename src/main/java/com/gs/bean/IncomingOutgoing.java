package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class IncomingOutgoing {
	private String inOutId;
	private String inTypeId;
	private String outTypeId;
	private String inOutMoney;
	private String inOutCreatedUser;
	private Date inOutCreatedTime;
	private String inOutStatus;

	public String getInOutId(){
		return this.inOutId;
	}
	public void setInOutId(String inOutId){
		this.inOutId=inOutId;
	}

	public String getInTypeId(){
		return this.inTypeId;
	}
	public void setInTypeId(String inTypeId){
		this.inTypeId=inTypeId;
	}

	public String getOutTypeId(){
		return this.outTypeId;
	}
	public void setOutTypeId(String outTypeId){
		this.outTypeId=outTypeId;
	}

	public String getInOutMoney(){
		return this.inOutMoney;
	}
	public void setInOutMoney(String inOutMoney){
		this.inOutMoney=inOutMoney;
	}

	public String getInOutCreatedUser(){
		return this.inOutCreatedUser;
	}
	public void setInOutCreatedUser(String inOutCreatedUser){
		this.inOutCreatedUser=inOutCreatedUser;
	}

	public Date getInOutCreatedTime(){
		return this.inOutCreatedTime;
	}
	public void setInOutCreatedTime(Date inOutCreatedTime){
		this.inOutCreatedTime=inOutCreatedTime;
	}

	public String getInOutStatus(){
		return this.inOutStatus;
	}
	public void setInOutStatus(String inOutStatus){
		this.inOutStatus=inOutStatus;
	}

}