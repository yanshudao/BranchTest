package io.renren.modules.zd.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ZdBalancePublishDetailVO {
    private String id;
    private String resourceCode;
    private String resourceName;
    private String isbn;
    private String catalogId;
    private BigDecimal publishPrice;
    private BigDecimal mayng;
    private BigDecimal shiyang;
    private Integer publishNum;
    private Integer totalNum;
    private String catalogName;
    private String resourceType;
    private BigDecimal nitemdiscountrate;
    private Date publishCreateTime;



    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public BigDecimal getPublishPrice() {
        return publishPrice;
    }

    public void setPublishPrice(BigDecimal publishPrice) {
        this.publishPrice = publishPrice;
    }

    public BigDecimal getMayng() {
        return mayng;
    }

    public void setMayng(BigDecimal mayng) {
        this.mayng = mayng;
    }

    public BigDecimal getShiyang() {
        return shiyang;
    }

    public void setShiyang(BigDecimal shiyang) {
        this.shiyang = shiyang;
    }

    public Integer getPublishNum() {
        return publishNum;
    }

    public void setPublishNum(Integer publishNum) {
        this.publishNum = publishNum;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public BigDecimal getNitemdiscountrate() {
        return nitemdiscountrate;
    }

    public void setNitemdiscountrate(BigDecimal nitemdiscountrate) {
        this.nitemdiscountrate = nitemdiscountrate;
    }

    public Date getPublishCreateTime() {
        return publishCreateTime;
    }

    public void setPublishCreateTime(Date publishCreateTime) {
        this.publishCreateTime = publishCreateTime;
    }
}
