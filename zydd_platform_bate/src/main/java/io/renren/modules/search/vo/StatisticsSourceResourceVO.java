package io.renren.modules.search.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;

public class StatisticsSourceResourceVO implements Serializable {
    @Excel(name = "教材代码",orderNum = "10")
     private String resourceCode;
    @Excel(name = "教材名称",orderNum = "20")
    private String resourceName;
    @Excel(name = "采购数量",orderNum = "105")
    private Integer resourceCount;
    @Excel(name = "教材版本",orderNum = "40")
    private String resourceVersion;
    @Excel(name = "教材类型",orderNum = "50",   replace = { "文字主_1", "文字辅_2","其他_3" })
    private String resourceType;
    private String semesterCode;
    @Excel(name = "报订季节",orderNum = "1")
    private String semesterName;
    @Excel(name = "总价",orderNum = "110")
    private String totalPrice;
    @Excel(name = "单价",orderNum = "100")
    private BigDecimal price;

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
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

    public Integer getResourceCount() {
        return resourceCount;
    }

    public void setResourceCount(Integer resourceCount) {
        this.resourceCount = resourceCount;
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

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }
}
