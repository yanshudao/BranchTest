package io.renren.modules.search.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;

public class StatisticsRefundResourceVO {
    @Excel(name = "教材代码",orderNum = "1")
    private String resourceCode;
    @Excel(name = "教材名称",orderNum = "2")
    private String resourceName;
    @Excel(name = "发行数量",orderNum = "3")
    private Integer publishCount;
    @Excel(name = "教材版本",orderNum = "4")
    private String resourceVersion;
    @Excel(name = "教材类型",orderNum = "9",   replace = { "文字主_1", "文字辅_2","其他_3" })
    private String resourceType;
    private String semesterCode;
    private String semesterName;
    @Excel(name = "码洋",orderNum = "6")
    private BigDecimal totalRefundPrice;
    @Excel(name = "退货数量",orderNum = "5")
    private Integer totalRefundNum;
    private String refundCode;
    private String logisticNo;
    private String fromOrgName;
    private BigDecimal refundPrice;
    private String toOrgName;

    public BigDecimal getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(BigDecimal refundPrice) {
        this.refundPrice = refundPrice;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer getPublishCount() {
        return publishCount;
    }

    public void setPublishCount(Integer publishCount) {
        this.publishCount = publishCount;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

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

    public BigDecimal getTotalRefundPrice() {
        return totalRefundPrice;
    }

    public void setTotalRefundPrice(BigDecimal totalRefundPrice) {
        this.totalRefundPrice = totalRefundPrice;
    }

    public Integer getTotalRefundNum() {
        return totalRefundNum;
    }

    public void setTotalRefundNum(Integer totalRefundNum) {
        this.totalRefundNum = totalRefundNum;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public String getLogisticNo() {
        return logisticNo;
    }

    public void setLogisticNo(String logisticNo) {
        this.logisticNo = logisticNo;
    }

    public String getFromOrgName() {
        return fromOrgName;
    }

    public void setFromOrgName(String fromOrgName) {
        this.fromOrgName = fromOrgName;
    }

    public String getToOrgName() {
        return toOrgName;
    }

    public void setToOrgName(String toOrgName) {
        this.toOrgName = toOrgName;
    }
}
