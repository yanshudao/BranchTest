package io.renren.modules.zd.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ZdBalanceCompanyRefundVO {
    private BigDecimal mayang;
    private BigDecimal shiyang;
    private BigDecimal avgDiscount;
    private String logisticCompany;
    private String logisticNo;
    private String realname;
    private String refundCode;
    private Date createTime;
    private Integer totalRealNum;

    public BigDecimal getAvgDiscount() {
        return avgDiscount;
    }

    public void setAvgDiscount(BigDecimal avgDiscount) {
        this.avgDiscount = avgDiscount;
    }

    public Integer getTotalRealNum() {
        return totalRealNum;
    }

    public void setTotalRealNum(Integer totalRealNum) {
        this.totalRealNum = totalRealNum;
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

    public String getLogisticCompany() {
        return logisticCompany;
    }

    public void setLogisticCompany(String logisticCompany) {
        this.logisticCompany = logisticCompany;
    }

    public String getLogisticNo() {
        return logisticNo;
    }

    public void setLogisticNo(String logisticNo) {
        this.logisticNo = logisticNo;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }
}
