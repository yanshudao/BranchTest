package io.renren.modules.zd.entity;

public class ZdRefundableResourceEntity {
    private String resourceId;

    private String resourceCode;

    private String resourceName;

    private String resourceType;

    private Double price;

    private Integer approvingNum;

    private Integer refundedNum;

    private Integer refundableNum;

    private Integer semePublishedNum;

    private Integer totalNum;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
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

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getApprovingNum() {
        return approvingNum;
    }

    public void setApprovingNum(Integer approvingNum) {
        this.approvingNum = approvingNum;
    }

    public Integer getRefundedNum() {
        return refundedNum;
    }

    public void setRefundedNum(Integer refundedNum) {
        this.refundedNum = refundedNum;
    }

    public Integer getRefundableNum() {
        return refundableNum;
    }

    public void setRefundableNum(Integer refundableNum) {
        this.refundableNum = refundableNum;
    }

    public Integer getSemePublishedNum() {
        return semePublishedNum;
    }

    public void setSemePublishedNum(Integer semePublishedNum) {
        this.semePublishedNum = semePublishedNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return "ZdRefundableResourceEntity{" +
                "resourceId='" + resourceId + '\'' +
                ", resourceCode='" + resourceCode + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", price=" + price +
                ", approvingNum=" + approvingNum +
                ", refundedNum=" + refundedNum +
                ", refundableNum=" + refundableNum +
                ", semePublishedNum=" + semePublishedNum +
                ", totalNum=" + totalNum +
                '}';
    }
}
