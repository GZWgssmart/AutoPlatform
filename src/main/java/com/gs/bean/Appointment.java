package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:20
*/
public class Appointment{
	private String appointmentId;
	private String userId;
	private String userName;
	private String userPhone;
	private String brandId;
	private String colorId;
	private String modelId;
	private String plateId;
	private String carPlate;
	private Date arriveTime;
	private String maintainOrFix;
	private Date appCreatedTime;
	private String companyId;
	private String appoitmentStatus;

	public String getAppointmentId(){
		return this.appointmentId;
	}
	public void setAppointmentId(String appointmentId){
		this.appointmentId=appointmentId;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserName(){
		return this.userName;
	}
	public void setUserName(String userName){
		this.userName=userName;
	}

	public String getUserPhone(){
		return this.userPhone;
	}
	public void setUserPhone(String userPhone){
		this.userPhone=userPhone;
	}

	public String getBrandId(){
		return this.brandId;
	}
	public void setBrandId(String brandId){
		this.brandId=brandId;
	}

	public String getColorId(){
		return this.colorId;
	}
	public void setColorId(String colorId){
		this.colorId=colorId;
	}

	public String getModelId(){
		return this.modelId;
	}
	public void setModelId(String modelId){
		this.modelId=modelId;
	}

	public String getPlateId(){
		return this.plateId;
	}
	public void setPlateId(String plateId){
		this.plateId=plateId;
	}

	public String getCarPlate(){
		return this.carPlate;
	}
	public void setCarPlate(String carPlate){
		this.carPlate=carPlate;
	}

	public Date getArriveTime(){
		return this.arriveTime;
	}
	public void setArriveTime(Date arriveTime){
		this.arriveTime=arriveTime;
	}

	public String getMaintainOrFix(){
		return this.maintainOrFix;
	}
	public void setMaintainOrFix(String maintainOrFix){
		this.maintainOrFix=maintainOrFix;
	}

	public Date getAppCreatedTime(){
		return this.appCreatedTime;
	}
	public void setAppCreatedTime(Date appCreatedTime){
		this.appCreatedTime=appCreatedTime;
	}

	public String getCompanyId(){
		return this.companyId;
	}
	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getAppoitmentStatus(){
		return this.appoitmentStatus;
	}
	public void setAppoitmentStatus(String appoitmentStatus){
		this.appoitmentStatus=appoitmentStatus;
	}

}