package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:20
*/
public class AccessoriesBuy {
	private String accBuyId;
	private String accId;
	private String accUnit;
	private int accBuyCount;
	private String accBuyPrice;
	private String accBuyTotal;
	private String accBuyDiscount;
	private String accBuyMoney;
	private Date accBuyTime;
	private Date accBuyCreatedTime;
	private String companyId;
	private String accBuyStatus;

	public String getAccBuyId(){
		return this.accBuyId;
	}
	public void setAccBuyId(String accBuyId){
		this.accBuyId=accBuyId;
	}

	public String getAccId(){
		return this.accId;
	}
	public void setAccId(String accId){
		this.accId=accId;
	}

	public String getAccUnit(){
		return this.accUnit;
	}
	public void setAccUnit(String accUnit){
		this.accUnit=accUnit;
	}

	public int getAccBuyCount(){
		return this.accBuyCount;
	}
	public void setAccBuyCount(int accBuyCount){
		this.accBuyCount=accBuyCount;
	}

	public String getAccBuyPrice(){
		return this.accBuyPrice;
	}
	public void setAccBuyPrice(String accBuyPrice){
		this.accBuyPrice=accBuyPrice;
	}

	public String getAccBuyTotal(){
		return this.accBuyTotal;
	}
	public void setAccBuyTotal(String accBuyTotal){
		this.accBuyTotal=accBuyTotal;
	}

	public String getAccBuyDiscount(){
		return this.accBuyDiscount;
	}
	public void setAccBuyDiscount(String accBuyDiscount){
		this.accBuyDiscount=accBuyDiscount;
	}

	public String getAccBuyMoney(){
		return this.accBuyMoney;
	}
	public void setAccBuyMoney(String accBuyMoney){
		this.accBuyMoney=accBuyMoney;
	}

	public Date getAccBuyTime(){
		return this.accBuyTime;
	}
	public void setAccBuyTime(Date accBuyTime){
		this.accBuyTime=accBuyTime;
	}

	public Date getAccBuyCreatedTime(){
		return this.accBuyCreatedTime;
	}
	public void setAccBuyCreatedTime(Date accBuyCreatedTime){
		this.accBuyCreatedTime=accBuyCreatedTime;
	}

	public String getCompanyId(){
		return this.companyId;
	}
	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getAccBuyStatus(){
		return this.accBuyStatus;
	}
	public void setAccBuyStatus(String accBuyStatus){
		this.accBuyStatus=accBuyStatus;
	}

}