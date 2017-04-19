package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class Salary{
	private String salaryId; // 工资发放编号
	private String prizeSalary;
	private String minusSalary;
	private String totalSalary;
	private String salaryDes;
	private Date salaryTime;
	private Date salaryCreatedTime;

	private String userId;		//



	public String getSalaryId(){
		return this.salaryId;
	}
	public void setSalaryId(String salaryId){
		this.salaryId=salaryId;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getPrizeSalary(){
		return this.prizeSalary;
	}
	public void setPrizeSalary(String prizeSalary){
		this.prizeSalary=prizeSalary;
	}

	public String getMinusSalary(){
		return this.minusSalary;
	}
	public void setMinusSalary(String minusSalary){
		this.minusSalary=minusSalary;
	}

	public String getTotalSalary(){
		return this.totalSalary;
	}
	public void setTotalSalary(String totalSalary){
		this.totalSalary=totalSalary;
	}

	public String getSalaryDes(){
		return this.salaryDes;
	}
	public void setSalaryDes(String salaryDes){
		this.salaryDes=salaryDes;
	}

	public Date getSalaryTime(){
		return this.salaryTime;
	}
	public void setSalaryTime(Date salaryTime){
		this.salaryTime=salaryTime;
	}

	public Date getSalaryCreatedTime(){
		return this.salaryCreatedTime;
	}
	public void setSalaryCreatedTime(Date salaryCreatedTime){
		this.salaryCreatedTime=salaryCreatedTime;
	}

}