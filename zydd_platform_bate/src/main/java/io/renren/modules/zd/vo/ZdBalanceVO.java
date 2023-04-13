package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdBalanceEntity;

import java.math.BigDecimal;

public class ZdBalanceVO extends ZdBalanceEntity {
    private BigDecimal totalPublish;
    private BigDecimal totalRefund;
    private BigDecimal totalPay;
    private BigDecimal mayang;
    private BigDecimal shiyang;
    private BigDecimal needPay;

    private String highLevelOrgName;

    public BigDecimal getTotalPublish() {
        return totalPublish;
    }

    public void setTotalPublish(BigDecimal totalPublish) {
        this.totalPublish = totalPublish;
    }

    public BigDecimal getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(BigDecimal totalRefund) {
        this.totalRefund = totalRefund;
    }

    public BigDecimal getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(BigDecimal totalPay) {
        this.totalPay = totalPay;
    }


    public String getHighLevelOrgName() {
        return highLevelOrgName;
    }

    public void setHighLevelOrgName(String highLevelOrgName) {
        this.highLevelOrgName = highLevelOrgName;
    }

    public BigDecimal getMayang() {
        return mayang;
    }

    public void setMayang(BigDecimal mayang) {
        this.mayang = mayang;
    }

    public BigDecimal getShiyang() {
        return shiyang;
    }

    public void setShiyang(BigDecimal shiyang) {
        this.shiyang = shiyang;
    }

    public BigDecimal getNeedPay() {
        return needPay;
    }

    public void setNeedPay(BigDecimal needPay) {
        this.needPay = needPay;
    }

}
