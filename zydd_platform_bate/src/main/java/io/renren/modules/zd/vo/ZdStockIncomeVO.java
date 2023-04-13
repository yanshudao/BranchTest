package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdStockIncomeEntity;

import java.math.BigDecimal;

public class ZdStockIncomeVO extends ZdStockIncomeEntity {
    private Integer resourceNum;
    private Integer totalNum;
    private String realname;
    private String companyName;
    private String orgName;
    private BigDecimal mayang;
    private BigDecimal shiyang;
    private String publishStatus;


    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public Integer getResourceNum() {
        return resourceNum;
    }

    public void setResourceNum(Integer resourceNum) {
        this.resourceNum = resourceNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
