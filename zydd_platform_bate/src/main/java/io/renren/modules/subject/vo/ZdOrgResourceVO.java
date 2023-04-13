package io.renren.modules.subject.vo;

import io.renren.modules.subject.entity.SubjectResourceEntity;

public class ZdOrgResourceVO extends SubjectResourceEntity {
    private Integer totalAmount;
    private Integer totalZd;
    private Integer totalAudit;
    private Integer totalSource;
    private Integer incomeNum;
    private String ids;

    public Integer getIncomeNum() {
        return incomeNum;
    }

    public void setIncomeNum(Integer incomeNum) {
        this.incomeNum = incomeNum;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalZd() {
        return totalZd;
    }

    public void setTotalZd(Integer totalZd) {
        this.totalZd = totalZd;
    }

    public Integer getTotalAudit() {
        return totalAudit;
    }

    public void setTotalAudit(Integer totalAudit) {
        this.totalAudit = totalAudit;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Integer getTotalSource() {
        return totalSource;
    }

    public void setTotalSource(Integer totalSource) {
        this.totalSource = totalSource;
    }
}
