package io.renren.modules.zd.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ZdSemesterRegVO implements Serializable {
    private String custCode;
    private String orgType;
    private String orgCode;
    private Integer orderCartTotal;
    private Integer sourceCartTotal;
    private Integer orderTotal;
    private Integer sourceTotal;
    private Integer refundTotal;
    private Integer refundSupplierTotal;
    private Integer stockIncomeTotal;
    private Integer publishTotal;
    private BigDecimal rate1;
    private BigDecimal rate2;
    private BigDecimal rate3;
    private Integer stuTotal;

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public Integer getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Integer orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Integer getSourceTotal() {
        return sourceTotal;
    }

    public void setSourceTotal(Integer sourceTotal) {
        this.sourceTotal = sourceTotal;
    }

    public Integer getRefundTotal() {
        return refundTotal;
    }

    public void setRefundTotal(Integer refundTotal) {
        this.refundTotal = refundTotal;
    }

    public Integer getRefundSupplierTotal() {
        return refundSupplierTotal;
    }

    public void setRefundSupplierTotal(Integer refundSupplierTotal) {
        this.refundSupplierTotal = refundSupplierTotal;
    }

    public BigDecimal getRate1() {
        return rate1;
    }

    public void setRate1(BigDecimal rate1) {
        this.rate1 = rate1;
    }

    public BigDecimal getRate2() {
        return rate2;
    }

    public void setRate2(BigDecimal rate2) {
        this.rate2 = rate2;
    }

    public BigDecimal getRate3() {
        return rate3;
    }

    public void setRate3(BigDecimal rate3) {
        this.rate3 = rate3;
    }

    public Integer getStuTotal() {
        return stuTotal;
    }

    public void setStuTotal(Integer stuTotal) {
        this.stuTotal = stuTotal;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getStockIncomeTotal() {
        return stockIncomeTotal;
    }

    public void setStockIncomeTotal(Integer stockIncomeTotal) {
        this.stockIncomeTotal = stockIncomeTotal;
    }

    public Integer getPublishTotal() {
        return publishTotal;
    }

    public void setPublishTotal(Integer publishTotal) {
        this.publishTotal = publishTotal;
    }

    public Integer getOrderCartTotal() {
        return orderCartTotal;
    }

    public void setOrderCartTotal(Integer orderCartTotal) {
        this.orderCartTotal = orderCartTotal;
    }

    public Integer getSourceCartTotal() {
        return sourceCartTotal;
    }

    public void setSourceCartTotal(Integer sourceCartTotal) {
        this.sourceCartTotal = sourceCartTotal;
    }
}
