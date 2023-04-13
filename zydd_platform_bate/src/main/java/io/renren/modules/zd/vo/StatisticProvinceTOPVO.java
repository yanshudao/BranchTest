package io.renren.modules.zd.vo;


import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;

public class StatisticProvinceTOPVO {
    @Excel(name = "单位代码",orderNum = "20")
    private String orgCode;
    @Excel(name = "单位名称",orderNum = "10")
    private String orgName;
    @Excel(name = "总报订数",orderNum = "30")
    private Integer totalOrderNum;
    @Excel(name = "总发行数",orderNum = "50")
    private Integer totalPublishNum;
    @Excel(name = "总采购数",orderNum = "40")
    private Integer totalSourceNum;
    @Excel(name = "总退货数",orderNum = "60")
    private Integer totalRefundNum;
    @Excel(name = "发行实洋",orderNum = "80")
    private BigDecimal shiyang;
    @Excel(name = "发行码洋",orderNum = "70")
    private BigDecimal mayang;
    @Excel(name = "结算码洋",orderNum = "90")
    private BigDecimal balanceMayang;
    @Excel(name = "结算实洋",orderNum = "100")
    private BigDecimal balanceShiyang;
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getTotalOrderNum() {
        return totalOrderNum;
    }

    public void setTotalOrderNum(Integer totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    public Integer getTotalPublishNum() {
        return totalPublishNum;
    }

    public void setTotalPublishNum(Integer totalPublishNum) {
        this.totalPublishNum = totalPublishNum;
    }

    public Integer getTotalSourceNum() {
        return totalSourceNum;
    }

    public void setTotalSourceNum(Integer totalSourceNum) {
        this.totalSourceNum = totalSourceNum;
    }

    public Integer getTotalRefundNum() {
        return totalRefundNum;
    }

    public void setTotalRefundNum(Integer totalRefundNum) {
        this.totalRefundNum = totalRefundNum;
    }

    public BigDecimal getShiyang() {
        return shiyang;
    }

    public void setShiyang(BigDecimal shiyang) {
        this.shiyang = shiyang;
    }

    public BigDecimal getMayang() {
        return mayang;
    }

    public void setMayang(BigDecimal mayang) {
        this.mayang = mayang;
    }

    public BigDecimal getBalanceMayang() {
        return balanceMayang;
    }

    public void setBalanceMayang(BigDecimal balanceMayang) {
        this.balanceMayang = balanceMayang;
    }

    public BigDecimal getBalanceShiyang() {
        return balanceShiyang;
    }

    public void setBalanceShiyang(BigDecimal balanceShiyang) {
        this.balanceShiyang = balanceShiyang;
    }
}
