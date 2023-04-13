package io.renren.modules.search.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;

public class StatisticsSourceResourceDetailVO implements Serializable {
    @Excel(name = "教材代码",orderNum = "10")
     private String resourceCode;
    @Excel(name = "教材名称",orderNum = "20")
    private String resourceName;
    @Excel(name = "ISBN",orderNum = "25")
    private String isbn;
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
//    @Excel(name = "id",orderNum = "120")
    private String id;
    @Excel(name = "采购单号",orderNum = "5")
    private String sourceNo;
    @Excel(name = "县级单位名称",orderNum = "7")
    private String orgName;
    @Excel(name = "县级单位代码",orderNum = "6")
    private String orgCode;
    @Excel(name = "状态",orderNum = "120",replace = { "新建_0","已报订_1","配货中_2","已发货_3", "已删除_4","无_null" })
    private String status;
    @Excel(name = "备注",orderNum = "140")
    private String remarks;
    @Excel(name = "订书依据",orderNum = "150")
    private String note;


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
