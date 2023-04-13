package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdStockIncomeResourceEntity;

import java.math.BigDecimal;

public class ZdStockIncomeResourceVO extends ZdStockIncomeResourceEntity {

    private String resourceName;
    private String resourceCode;
    private BigDecimal price;
    private String resourceVersion;
    private String resourceType;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
}
