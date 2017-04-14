package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:20
*/
public class Accessories{
	private String accId;
	private String accName;
	private String accCommodityCode;
	private String accDes;
	private String accPrice;
	private String accSalePrice;
	private String accUnit;
	private int accTotal;
	private int accIdle;
	private Date accUsedTime;
	private Date accBuyedTime;
	private String supplyId;
	private Date accCreatedTime;
	private String accTypeId;
	private String companyId;
	private String accStatus;

	public String getAccId(){
		return this.accId;
	}
	public void setAccId(String accId){
		this.accId=accId;
	}

	public String getAccName(){
		return this.accName;
	}
	public void setAccName(String accName){
		this.accName=accName;
	}

	public String getAccCommodityCode(){
		return this.accCommodityCode;
	}
	public void setAccCommodityCode(String accCommodityCode){
		this.accCommodityCode=accCommodityCode;
	}

	public String getAccDes(){
		return this.accDes;
	}
	public void setAccDes(String accDes){
		this.accDes=accDes;
	}

	public String getAccPrice(){
		return this.accPrice;
	}
	public void setAccPrice(String accPrice){
		this.accPrice=accPrice;
	}

	public String getAccSalePrice(){
		return this.accSalePrice;
	}
	public void setAccSalePrice(String accSalePrice){
		this.accSalePrice=accSalePrice;
	}

	public String getAccUnit(){
		return this.accUnit;
	}
	public void setAccUnit(String accUnit){
		this.accUnit=accUnit;
	}

	public int getAccTotal(){
		return this.accTotal;
	}
	public void setAccTotal(int accTotal){
		this.accTotal=accTotal;
	}

	public int getAccIdle(){
		return this.accIdle;
	}
	public void setAccIdle(int accIdle){
		this.accIdle=accIdle;
	}

	public Date getAccUsedTime(){
		return this.accUsedTime;
	}
	public void setAccUsedTime(Date accUsedTime){
		this.accUsedTime=accUsedTime;
	}

	public Date getAccBuyedTime(){
		return this.accBuyedTime;
	}
	public void setAccBuyedTime(Date accBuyedTime){
		this.accBuyedTime=accBuyedTime;
	}

	public String getSupplyId(){
		return this.supplyId;
	}
	public void setSupplyId(String supplyId){
		this.supplyId=supplyId;
	}

	public Date getAccCreatedTime(){
		return this.accCreatedTime;
	}
	public void setAccCreatedTime(Date accCreatedTime){
		this.accCreatedTime=accCreatedTime;
	}

	public String getAccTypeId(){
		return this.accTypeId;
	}
	public void setAccTypeId(String accTypeId){
		this.accTypeId=accTypeId;
	}

	public String getCompanyId(){
		return this.companyId;
	}
	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getAccStatus(){
		return this.accStatus;
	}
	public void setAccStatus(String accStatus){
		this.accStatus=accStatus;
	}

}