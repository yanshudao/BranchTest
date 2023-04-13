package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdRefundResourceEntity;

public class RefundResourceInfoVo extends ZdRefundResourceEntity {
    private String resourceCode;
    private String resourceName;
    private String resourceVersion;
    private String resourceType;
    private String userName;
    private String publishPrice;
    private String publishName;
    private String semesterName;
    private String orderNum;
    private String totalNum;
    private String orgName;
    private String semesterCode;

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPublishPrice() {
        return publishPrice;
    }

    public void setPublishPrice(String publishPrice) {
        this.publishPrice = publishPrice;
    }

    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return "RefundResourceInfoVo{" +
                "resourceName='" + resourceName + '\'' +
                ", resourceVersion='" + resourceVersion + '\'' +
                ", userName='" + userName + '\'' +
                ", publishPrice='" + publishPrice + '\'' +
                ", publishName='" + publishName + '\'' +
                ", semesterName='" + semesterName + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", totalNum='" + totalNum + '\'' +
                ", orgName='" + orgName + '\'' +
                ", semesterCode='" + semesterCode + '\'' +
                '}';
    }
}
