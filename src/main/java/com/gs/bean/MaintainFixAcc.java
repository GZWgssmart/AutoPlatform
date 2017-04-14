package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class MaintainFixAcc {
	private String mainAccId;
	private String maintainId;
	private String accId;
	private int accCount;

	public String getMainAccId(){
		return this.mainAccId;
	}
	public void setMainAccId(String mainAccId){
		this.mainAccId=mainAccId;
	}

	public String getMaintainId(){
		return this.maintainId;
	}
	public void setMaintainId(String maintainId){
		this.maintainId=maintainId;
	}

	public String getAccId(){
		return this.accId;
	}
	public void setAccId(String accId){
		this.accId=accId;
	}

	public int getAccCount(){
		return this.accCount;
	}
	public void setAccCount(int accCount){
		this.accCount=accCount;
	}

}