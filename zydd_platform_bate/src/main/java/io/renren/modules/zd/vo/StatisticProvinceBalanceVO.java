package io.renren.modules.zd.vo;

import java.math.BigDecimal;

public class StatisticProvinceBalanceVO {
    private String orgName;
    private String orgCode;
    private BigDecimal totalShiyang;
    private BigDecimal totalPay;
    private BigDecimal needPay;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public BigDecimal getTotalShiyang() {
        return totalShiyang;
    }

    public void setTotalShiyang(BigDecimal totalShiyang) {
        this.totalShiyang = totalShiyang;
    }

    public BigDecimal getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(BigDecimal totalPay) {
        this.totalPay = totalPay;
    }

    public BigDecimal getNeedPay() {
        return needPay;
    }

    public void setNeedPay(BigDecimal needPay) {
        this.needPay = needPay;
    }
}
