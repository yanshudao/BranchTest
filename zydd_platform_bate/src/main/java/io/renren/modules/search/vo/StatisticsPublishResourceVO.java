package io.renren.modules.search.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;

public class StatisticsPublishResourceVO {
    @Excel(name = "教材代码",orderNum = "10")
    private String resourceCode;
    @Excel(name = "教材名称",orderNum = "20")
    private String resourceName;
    @Excel(name = "ISBN",orderNum = "25")
    private String isbn;
    @Excel(name = "发行数量",orderNum = "30")
    private Integer publishCount;
    @Excel(name = "发行数量",orderNum = "35")
    private Integer realCount;
    @Excel(name = "教材版本",orderNum = "40")
    private String resourceVersion;
    @Excel(name = "教材类型",orderNum = "50",   replace = { "文字主_1", "文字辅_2","其他_3" })
    private String resourceType;
    private String semesterCode;
    @Excel(name = "报订季节",orderNum = "60")
    private String semesterName;
    @Excel(name = "发行码洋",orderNum = "70")
    private BigDecimal totalPublish;
    @Excel(name = "发行单号",orderNum = "80")
    private String publishNo;
    @Excel(name = "物流单号",orderNum = "90")
    private String logisticNo;
    @Excel(name = "省级单位",orderNum = "100")
    private String fromOrgName;
    @Excel(name = "县级单位",orderNum = "110")
    private String toOrgName;

    private BigDecimal publishPrice;

    public BigDecimal getPublishPrice() {
        return publishPrice;
    }

    public void setPublishPrice(BigDecimal publishPrice) {
        this.publishPrice = publishPrice;
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

    public String getLogisticNo() {
        return logisticNo;
    }

    public void setLogisticNo(String logisticNo) {
        this.logisticNo = logisticNo;
    }

    public String getPublishNo() {
        return publishNo;
    }

    public void setPublishNo(String publishNo) {
        this.publishNo = publishNo;
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

    public BigDecimal getTotalPublish() {
        return totalPublish;
    }

    public void setTotalPublish(BigDecimal totalPublish) {
        this.totalPublish = totalPublish;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getRealCount() {
        return realCount;
    }

    public void setRealCount(Integer realCount) {
        this.realCount = realCount;
    }
}
