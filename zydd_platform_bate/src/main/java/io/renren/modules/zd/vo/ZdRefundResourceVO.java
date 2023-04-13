package io.renren.modules.zd.vo;

import java.math.BigDecimal;

public class ZdRefundResourceVO {
    private String id;
    private String resourceId;
    private String resourceName;
    private String resourceCode;
    private String resourceType;
    private String author;
    private String publishingName;
    private String resourceVersion;
    private BigDecimal price;
    private Integer totalAmount;
    private Integer incomeNum;
    private Integer refundSubmitNum;
    private Integer refundAuditNum;
    private Integer totalIncome;
    private Integer totalRefund;
    private Integer totalCat;
    private String companyName;

    public Integer getRefundSubmitNum() {
        return refundSubmitNum;
    }

    public void setRefundSubmitNum(Integer refundSubmitNum) {
        this.refundSubmitNum = refundSubmitNum;
    }

    public Integer getRefundAuditNum() {
        return refundAuditNum;
    }

    public void setRefundAuditNum(Integer refundAuditNum) {
        this.refundAuditNum = refundAuditNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingName() {
        return publishingName;
    }

    public void setPublishingName(String publishingName) {
        this.publishingName = publishingName;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getIncomeNum() {
        return incomeNum;
    }

    public void setIncomeNum(Integer incomeNum) {
        this.incomeNum = incomeNum;
    }

    public Integer getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Integer totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Integer getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(Integer totalRefund) {
        this.totalRefund = totalRefund;
    }

    public Integer getTotalCat() {
        return totalCat;
    }

    public void setTotalCat(Integer totalCat) {
        this.totalCat = totalCat;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
