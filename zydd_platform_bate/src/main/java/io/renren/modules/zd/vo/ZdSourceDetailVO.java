package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdSourceResourceEntity;

import java.math.BigDecimal;

public class ZdSourceDetailVO extends ZdSourceResourceEntity {
    private String resourceName;
    private String resourceVersion;
    private String resourceType;
    private String supplierName;
    private String publishingName;
    private String resourceCode;
    private BigDecimal price;
    private String countyOrgName;
    private String provinceOrgName;
    private String sourceNo;


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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPublishingName() {
        return publishingName;
    }

    public void setPublishingName(String publishingName) {
        this.publishingName = publishingName;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getCountyOrgName() {
        return countyOrgName;
    }

    public void setCountyOrgName(String countyOrgName) {
        this.countyOrgName = countyOrgName;
    }

    public String getProvinceOrgName() {
        return provinceOrgName;
    }

    public void setProvinceOrgName(String provinceOrgName) {
        this.provinceOrgName = provinceOrgName;
    }

    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

}
