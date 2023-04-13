package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdRefundCartEntity;

import java.math.BigDecimal;

public class ZdRefundCartVO extends ZdRefundCartEntity{
    private String resourceName;
    private String resourceType;
    private String refundSemesterName;
    private BigDecimal price;
    private String isbn;

    public String getRefundSemesterName() {
        return refundSemesterName;
    }

    public void setRefundSemesterName(String refundSemesterName) {
        this.refundSemesterName = refundSemesterName;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
