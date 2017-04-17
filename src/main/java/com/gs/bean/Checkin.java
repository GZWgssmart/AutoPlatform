package com.gs.bean;

import java.util.Date;

/**
 * 由Wjhsmart技术支持
 *
 * 登记表对应的Bean
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:16:21
 */
public class Checkin {
    private String checkinId; // 登记ID
    private String userId; // 车主
    private String appointmentId; // 预约ID
    private String userName; // 车主姓名
    private String userPhone; // 车主电话
    private String brandId; // 汽车品牌
    private String colorId; // 汽车颜色
    private String modelId; // 汽车车型
    private String plateId; // 汽车车牌
    private String carPlate; // 车牌号码
    private Date arriveTime; // 到店时间
    private String carMileage; // 汽车行驶里程
    private String carThings; // 车上物品描述信息
    private String intactDegrees; // 汽车完好度描述
    private String userRequests; // 车主要求描述
    private String maintainOrFix; // 标识，标识是维修该是保养
    private Date checkinCreatedTime; // 记录创建时间
    private String companyId; // 汽修公司
    private String checkinStatus; // 记录的状态

    public String getCheckinId() {
        return this.checkinId;
    }

    public void setCheckinId(String checkinId) {
        this.checkinId = checkinId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppointmentId() {
        return this.appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return this.userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getBrandId() {
        return this.brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getColorId() {
        return this.colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getModelId() {
        return this.modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getPlateId() {
        return this.plateId;
    }

    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }

    public String getCarPlate() {
        return this.carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Date getArriveTime() {
        return this.arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getCarMileage() {
        return this.carMileage;
    }

    public void setCarMileage(String carMileage) {
        this.carMileage = carMileage;
    }

    public String getCarThings() {
        return this.carThings;
    }

    public void setCarThings(String carThings) {
        this.carThings = carThings;
    }

    public String getIntactDegrees() {
        return this.intactDegrees;
    }

    public void setIntactDegrees(String intactDegrees) {
        this.intactDegrees = intactDegrees;
    }

    public String getUserRequests() {
        return this.userRequests;
    }

    public void setUserRequests(String userRequests) {
        this.userRequests = userRequests;
    }

    public String getMaintainOrFix() {
        return this.maintainOrFix;
    }

    public void setMaintainOrFix(String maintainOrFix) {
        this.maintainOrFix = maintainOrFix;
    }

    public Date getCheckinCreatedTime() {
        return this.checkinCreatedTime;
    }

    public void setCheckinCreatedTime(Date checkinCreatedTime) {
        this.checkinCreatedTime = checkinCreatedTime;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCheckinStatus() {
        return this.checkinStatus;
    }

    public void setCheckinStatus(String checkinStatus) {
        this.checkinStatus = checkinStatus;
    }

}