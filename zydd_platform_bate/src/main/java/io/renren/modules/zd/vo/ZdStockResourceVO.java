package io.renren.modules.zd.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ZdStockResourceVO implements Serializable {
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
    private Integer orderCount;
    private Integer publishCount;
    private Integer preparePublishCount;
    private Integer refundSubmitNum;


    public Integer getRefundSubmitNum() {
        return refundSubmitNum;
    }

    public void setRefundSubmitNum(Integer refundSubmitNum) {
        this.refundSubmitNum = refundSubmitNum;
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

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getPublishCount() {
        return publishCount;
    }

    public void setPublishCount(Integer publishCount) {
        this.publishCount = publishCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPreparePublishCount() {
        return preparePublishCount;
    }

    public void setPreparePublishCount(Integer preparePublishCount) {
        this.preparePublishCount = preparePublishCount;
    }
}
