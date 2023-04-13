package io.renren.modules.zd.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.renren.modules.zd.entity.ZdRefundSupplierResourceEntity;

public class RefundSupplierResourceVO extends ZdRefundSupplierResourceEntity {
    @Excel(name = "单据号",orderNum = "1")
    private String refundCode;
    @Excel(name = "教材名称",orderNum = "20")
    private String resourceName;

    @Excel(name = "教材代码",orderNum = "10")
    private String resourceCode;
    private String resourceVersion;
    @Excel(name = "ISBN",orderNum = "30")
    private String isbn;
    private String catalogId;
    private String catalogName;
    @Excel(name = "教材类型",orderNum = "50",   replace = { "文字主_1", "文字辅_2","其他_3" })
    private String resourceType;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

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

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    @Override
    public String toString() {
        return "RefundResourceVo{" +
                "resourceName='" + resourceName + '\'' +
                ", resourceVersion='" + resourceVersion + '\'' +
                ", catalogId='" + catalogId + '\'' +
                ", catalogName='" + catalogName + '\'' +
                '}';
    }
}
