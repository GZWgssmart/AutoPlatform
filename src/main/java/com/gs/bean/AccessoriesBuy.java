package com.gs.bean;

import java.util.Date;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:16:20
 */
public class AccessoriesBuy {
    private String accBuyId;
    private String accUnit;
    private int accBuyCount;
    private double accBuyPrice;
    private double accBuyTotal;
    private double accBuyDiscount;
    private double accBuyMoney;
    private Date accBuyTime;
    private Date accBuyCreatedTime;
    private String companyId;
    private String accBuyStatus;
    private String accId;

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    private Accessories accessories;

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }

    public String getAccBuyId() {
        return accBuyId;
    }

    public void setAccBuyId(String accBuyId) {
        this.accBuyId = accBuyId;
    }

    public String getAccUnit() {
        return accUnit;
    }

    public void setAccUnit(String accUnit) {
        this.accUnit = accUnit;
    }

    public int getAccBuyCount() {
        return accBuyCount;
    }

    public void setAccBuyCount(int accBuyCount) {
        this.accBuyCount = accBuyCount;
    }

    public double getAccBuyPrice() {
        return accBuyPrice;
    }

    public void setAccBuyPrice(double accBuyPrice) {
        this.accBuyPrice = accBuyPrice;
    }

    public double getAccBuyTotal() {
        return accBuyTotal;
    }

    public void setAccBuyTotal(double accBuyTotal) {
        this.accBuyTotal = accBuyTotal;
    }

    public double getAccBuyDiscount() {
        return accBuyDiscount;
    }

    public void setAccBuyDiscount(double accBuyDiscount) {
        this.accBuyDiscount = accBuyDiscount;
    }

    public double getAccBuyMoney() {
        return accBuyMoney;
    }

    public void setAccBuyMoney(double accBuyMoney) {
        this.accBuyMoney = accBuyMoney;
    }

    public Date getAccBuyTime() {
        return accBuyTime;
    }

    public void setAccBuyTime(Date accBuyTime) {
        this.accBuyTime = accBuyTime;
    }

    public Date getAccBuyCreatedTime() {
        return accBuyCreatedTime;
    }

    public void setAccBuyCreatedTime(Date accBuyCreatedTime) {
        this.accBuyCreatedTime = accBuyCreatedTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAccBuyStatus() {
        return accBuyStatus;
    }

    public void setAccBuyStatus(String accBuyStatus) {
        this.accBuyStatus = accBuyStatus;
    }

    @Override
    public String toString() {
        return "AccessoriesBuy{" +
                "accBuyId='" + accBuyId + '\'' +
                ", accUnit='" + accUnit + '\'' +
                ", accBuyCount=" + accBuyCount +
                ", accBuyPrice=" + accBuyPrice +
                ", accBuyTotal=" + accBuyTotal +
                ", accBuyDiscount=" + accBuyDiscount +
                ", accBuyMoney=" + accBuyMoney +
                ", accBuyTime=" + accBuyTime +
                ", accBuyCreatedTime=" + accBuyCreatedTime +
                ", companyId='" + companyId + '\'' +
                ", accBuyStatus='" + accBuyStatus + '\'' +
                '}';
    }
}