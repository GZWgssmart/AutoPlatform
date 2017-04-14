package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:20
*/
public class AccessoriesSale {
	private String accSaleId;
	private String accId;
	private Date accSaledTime;
	private int accSaleCount;
	private String accSalePrice;
	private String accSaleTotal;
	private String accSaleDiscount;
	private String accSaleMoney;
	private Date accSaleCreatedTime;
	private String companyId;
	private String accSaleStatus;

	public String getAccSaleId(){
		return this.accSaleId;
	}
	public void setAccSaleId(String accSaleId){
		this.accSaleId=accSaleId;
	}

	public String getAccId(){
		return this.accId;
	}
	public void setAccId(String accId){
		this.accId=accId;
	}

	public Date getAccSaledTime(){
		return this.accSaledTime;
	}
	public void setAccSaledTime(Date accSaledTime){
		this.accSaledTime=accSaledTime;
	}

	public int getAccSaleCount(){
		return this.accSaleCount;
	}
	public void setAccSaleCount(int accSaleCount){
		this.accSaleCount=accSaleCount;
	}

	public String getAccSalePrice(){
		return this.accSalePrice;
	}
	public void setAccSalePrice(String accSalePrice){
		this.accSalePrice=accSalePrice;
	}

	public String getAccSaleTotal(){
		return this.accSaleTotal;
	}
	public void setAccSaleTotal(String accSaleTotal){
		this.accSaleTotal=accSaleTotal;
	}

	public String getAccSaleDiscount(){
		return this.accSaleDiscount;
	}
	public void setAccSaleDiscount(String accSaleDiscount){
		this.accSaleDiscount=accSaleDiscount;
	}

	public String getAccSaleMoney(){
		return this.accSaleMoney;
	}
	public void setAccSaleMoney(String accSaleMoney){
		this.accSaleMoney=accSaleMoney;
	}

	public Date getAccSaleCreatedTime(){
		return this.accSaleCreatedTime;
	}
	public void setAccSaleCreatedTime(Date accSaleCreatedTime){
		this.accSaleCreatedTime=accSaleCreatedTime;
	}

	public String getCompanyId(){
		return this.companyId;
	}
	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getAccSaleStatus(){
		return this.accSaleStatus;
	}
	public void setAccSaleStatus(String accSaleStatus){
		this.accSaleStatus=accSaleStatus;
	}

}