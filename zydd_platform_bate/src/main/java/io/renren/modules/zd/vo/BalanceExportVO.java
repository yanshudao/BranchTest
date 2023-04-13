package io.renren.modules.zd.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class BalanceExportVO {
    @Excel(name = "学期代码", orderNum = "10")
    private String semesterCode;
    @Excel(name = "学期名称", orderNum = "15")
    private String semesterName;

    private String lowLevelOrg;

    private String highLevelOrg;
    @Excel(name = "总退货", orderNum = "40")
    private String totalRefund;
    @Excel(name = "总发行", orderNum = "30")
    private String totalPublish;
    @Excel(name = "总支付", orderNum = "50")
    private String totalPay;
    @Excel(name = "总码洋", orderNum = "60")
    private String mayang;
    @Excel(name = "总实洋", orderNum = "70")
    private String shiyang;
    @Excel(name = "欠款", orderNum = "80")
    private String needPay;
    private String highLevelOrgName;
    @Excel(name = "单位名称", orderNum = "20")
    private String lowLevelOrgName;

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getLowLevelOrg() {
        return lowLevelOrg;
    }

    public void setLowLevelOrg(String lowLevelOrg) {
        this.lowLevelOrg = lowLevelOrg;
    }

    public String getHighLevelOrg() {
        return highLevelOrg;
    }

    public void setHighLevelOrg(String highLevelOrg) {
        this.highLevelOrg = highLevelOrg;
    }

    public String getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(String totalRefund) {
        this.totalRefund = totalRefund;
    }

    public String getTotalPublish() {
        return totalPublish;
    }

    public void setTotalPublish(String totalPublish) {
        this.totalPublish = totalPublish;
    }

    public String getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(String totalPay) {
        this.totalPay = totalPay;
    }

    public String getMayang() {
        return mayang;
    }

    public void setMayang(String mayang) {
        this.mayang = mayang;
    }

    public String getShiyang() {
        return shiyang;
    }

    public void setShiyang(String shiyang) {
        this.shiyang = shiyang;
    }

    public String getNeedPay() {
        return needPay;
    }

    public void setNeedPay(String needPay) {
        this.needPay = needPay;
    }

    public String getHighLevelOrgName() {
        return highLevelOrgName;
    }

    public void setHighLevelOrgName(String highLevelOrgName) {
        this.highLevelOrgName = highLevelOrgName;
    }

    public String getLowLevelOrgName() {
        return lowLevelOrgName;
    }

    public void setLowLevelOrgName(String lowLevelOrgName) {
        this.lowLevelOrgName = lowLevelOrgName;
    }
}
