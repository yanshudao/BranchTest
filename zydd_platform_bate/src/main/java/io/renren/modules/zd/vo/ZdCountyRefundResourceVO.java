package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdRefundResourceEntity;

import java.math.BigDecimal;

public class ZdCountyRefundResourceVO  extends ZdRefundResourceEntity{
    private String resourceName;
    private String resourceCode;
    private String resourceType;
    private String author;
    private String publishingName;
    private String resourceVersion;
    private BigDecimal mayang;
    private BigDecimal shiyang;
    private Integer realNum;


    @Override
    public BigDecimal getMayang() {
        return mayang;
    }

    @Override
    public void setMayang(BigDecimal mayang) {
        this.mayang = mayang;
    }

    @Override
    public BigDecimal getShiyang() {
        return shiyang;
    }

    @Override
    public void setShiyang(BigDecimal shiyang) {
        this.shiyang = shiyang;
    }

    @Override
    public Integer getRealNum() {
        return realNum;
    }

    @Override
    public void setRealNum(Integer realNum) {
        this.realNum = realNum;
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

}
