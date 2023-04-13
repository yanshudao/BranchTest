package io.renren.modules.zd.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

public class ZdRefundResourceDetailEntity {
    private String resourceId;

    @Excel(name = "教材代码",orderNum = "1")
    private String resourceCode;
    @Excel(name = "教材名称",orderNum = "2")
    private String resourceName;
    @Excel(name = "教材类型",orderNum = "3",   replace = { "文字主_1", "文字辅_2","其他_3" })
    private String resourceType;
    private String resourceVersion;
    @Excel(name = "定价",orderNum = "4")
    private BigDecimal price;
    private String publishingName;
    @Excel(name = "报订季节",orderNum = "5")
    private String semeName;
    @Excel(name = "季节报订数",orderNum = "6")
    private Integer orderNum;
    @Excel(name = "季节退货数",orderNum = "7")
    private Integer refundNum;
    @Excel(name = "季节实货数",orderNum = "8")
    private Integer realNum;

    private Integer totalPublishNum;

    private Date updateTime;
    private String refundUser;

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

    public String getPublishingName() {
        return publishingName;
    }

    public void setPublishingName(String publishingName) {
        this.publishingName = publishingName;
    }

    public String getSemeName() {
        return semeName;
    }

    public void setSemeName(String semeName) {
        this.semeName = semeName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Integer getTotalPublishNum() {
        return totalPublishNum;
    }

    public void setTotalPublishNum(Integer totalPublishNum) {
        this.totalPublishNum = totalPublishNum;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRefundUser() {
        return refundUser;
    }

    public void setRefundUser(String refundUser) {
        this.refundUser = refundUser;
    }

    public Integer getRealNum() {
        return realNum;
    }

    public void setRealNum(Integer realNum) {
        this.realNum = realNum;
    }
}
