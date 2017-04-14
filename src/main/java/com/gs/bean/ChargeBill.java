package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class ChargeBill {
	private String chargeBillId;
	private String maintainRecordId;
	private String chargeBillMoney;
	private String paymentMethod;
	private String actualPayment;
	private Date chargeTime;
	private Date chargeCreatedTime;
	private String chargeBillDes;
	private String chargeBillStatus;

	public String getChargeBillId(){
		return this.chargeBillId;
	}
	public void setChargeBillId(String chargeBillId){
		this.chargeBillId=chargeBillId;
	}

	public String getMaintainRecordId(){
		return this.maintainRecordId;
	}
	public void setMaintainRecordId(String maintainRecordId){
		this.maintainRecordId=maintainRecordId;
	}

	public String getChargeBillMoney(){
		return this.chargeBillMoney;
	}
	public void setChargeBillMoney(String chargeBillMoney){
		this.chargeBillMoney=chargeBillMoney;
	}

	public String getPaymentMethod(){
		return this.paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod){
		this.paymentMethod=paymentMethod;
	}

	public String getActualPayment(){
		return this.actualPayment;
	}
	public void setActualPayment(String actualPayment){
		this.actualPayment=actualPayment;
	}

	public Date getChargeTime(){
		return this.chargeTime;
	}
	public void setChargeTime(Date chargeTime){
		this.chargeTime=chargeTime;
	}

	public Date getChargeCreatedTime(){
		return this.chargeCreatedTime;
	}
	public void setChargeCreatedTime(Date chargeCreatedTime){
		this.chargeCreatedTime=chargeCreatedTime;
	}

	public String getChargeBillDes(){
		return this.chargeBillDes;
	}
	public void setChargeBillDes(String chargeBillDes){
		this.chargeBillDes=chargeBillDes;
	}

	public String getChargeBillStatus(){
		return this.chargeBillStatus;
	}
	public void setChargeBillStatus(String chargeBillStatus){
		this.chargeBillStatus=chargeBillStatus;
	}

}