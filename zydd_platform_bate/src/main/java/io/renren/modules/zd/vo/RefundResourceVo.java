package io.renren.modules.zd.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.renren.modules.zd.entity.ZdRefundResourceEntity;

public class RefundResourceVo extends ZdRefundResourceEntity {
    @Excel(name = "教材名称",orderNum = "2")
    private String resourceName;
    @Excel(name = "教材代码",orderNum = "1")
    private String resourceCode;
    private String resourceVersion;
    @Excel(name = "ISBN",orderNum = "3")
    private String isbn;
    private String catalogId;
    private String catalogName;
    @Excel(name = "教材类型",orderNum = "5",   replace = { "文字主_1", "文字辅_2","其他_3" })
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
