package io.renren.modules.search.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StatisticsStockIncomeResourceDetailVO implements Serializable {
    @Excel(name = "教材代码",orderNum = "10")
     private String resourceCode;
    @Excel(name = "教材名称",orderNum = "20")
    private String resourceName;
    @Excel(name = "ISBN",orderNum = "25")
    private String isbn;
    @Excel(name = "数量",orderNum = "105")
    private Integer incomeNum;
    @Excel(name = "教材版本",orderNum = "40")
    private String resourceVersion;
    @Excel(name = "教材类型",orderNum = "50",   replace = { "文字主_1", "文字辅_2","其他_3" })
    private String resourceType;
    private String semesterCode;
    @Excel(name = "报订季节",orderNum = "1")
    private String semesterName;
    @Excel(name = "码洋",orderNum = "110")
    private String mayang;
    @Excel(name = "单价",orderNum = "100")
    private BigDecimal incomePrice;
//    @Excel(name = "id",orderNum = "120")
    private String id;
    @Excel(name = "采购单号",orderNum = "5")
    private String incomeSn;
    @Excel(name = "教学点单位名称",orderNum = "7")
    private String orgName;
    @Excel(name = "教学点单位代码",orderNum = "6")
    private String orgCode;
    @Excel(name = "状态",orderNum = "190",replace = { "新建_0","待入库_1","已入库_2","已结算_4","无_null" })
    private String status;
    @Excel(name = "运单号",orderNum = "120")
    private String carrierno;
    @Excel(name = "件数",orderNum = "130")
    private String num;
    @Excel(name = "发货时间",orderNum = "140",format="yyyy-MM-dd HH:mm:ss")
    private Date sendDate;


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

    public String getIncomeSn() {
        return incomeSn;
    }

    public void setIncomeSn(String incomeSn) {
        this.incomeSn = incomeSn;
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

    public String getCarrierno() {
        return carrierno;
    }

    public void setCarrierno(String carrierno) {
        this.carrierno = carrierno;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Integer getIncomeNum() {
        return incomeNum;
    }

    public void setIncomeNum(Integer incomeNum) {
        this.incomeNum = incomeNum;
    }

    public String getMayang() {
        return mayang;
    }

    public void setMayang(String mayang) {
        this.mayang = mayang;
    }

    public BigDecimal getIncomePrice() {
        return incomePrice;
    }

    public void setIncomePrice(BigDecimal incomePrice) {
        this.incomePrice = incomePrice;
    }
}
