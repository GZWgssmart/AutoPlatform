package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21 收费单据bean
*/
public class ChargeBill {
	private String chargeBillId; // 收费单据的id
	private String recordId; // 维修保养记录的ID
	private String chargeBillMoney; // 收费总金额
	private String paymentMethod; // 付款方式
	private String actualPayment; // 实付款
	private Date chargeTime; // 收款时间
	private Date chargeCreatedTime; // 收费单据创建时间
	private String chargeBillDes; // 收费单据描述
	private String chargeBillStatus; // 收费单据状态

	private MaintainRecord record;

	public String getChargeBillId(){
		return this.chargeBillId;
	}
	public void setChargeBillId(String chargeBillId){
		this.chargeBillId=chargeBillId;
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

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public MaintainRecord getRecord() {
		return record;
	}

	public void setRecord(MaintainRecord record) {
		this.record = record;
	}
}