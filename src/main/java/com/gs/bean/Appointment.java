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

	private String carPlate;
	private Date arriveTime;
	private String maintainOrFix;
	private Date appCreatedTime;
	private String appoitmentStatus;

	private CarBrand brand;
	private CarColor color;
	private CarModel model;
	private CarPlate plate;

	private Company company;

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

	public String getAppoitmentStatus(){
		return this.appoitmentStatus;
	}
	public void setAppoitmentStatus(String appoitmentStatus){
		this.appoitmentStatus=appoitmentStatus;
	}

	public CarBrand getBrand() {
		return brand;
	}

	public void setBrand(CarBrand brand) {
		this.brand = brand;
	}

	public CarColor getColor() {
		return color;
	}

	public void setColor(CarColor color) {
		this.color = color;
	}

	public CarModel getModel() {
		return model;
	}

	public void setModel(CarModel model) {
		this.model = model;
	}

	public CarPlate getPlate() {
		return plate;
	}

	public void setPlate(CarPlate plate) {
		this.plate = plate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}