package io.renren.modules.search.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StatisticsResourceDetailVO implements Serializable {
    @Excel(name = "课程代码",orderNum = "30")
    private String courseCode;
    @Excel(name = "课程名称",orderNum = "40")
    private String courseName;
    @Excel(name = "专业代码",orderNum = "10")
    private String majorCode;
    @Excel(name = "专业名称",orderNum = "20")
    private String majorName;
    @Excel(name = "学生类型",orderNum = "25",replace = { "开放_01", "普招_02","成招_03","一村一_04","课程开放_05","助力计划_06","农民大学生_07","专本衔接_08","中高职衔接_09","乡村教师学历提升计划_10","网考_11","中专_19" })
    private String studentType;
    @Excel(name = "专业层次",orderNum = "22",replace = { "本科_2", "专科_4","本科（专科起点）_5","本科（高中起点）_6","中专_8" })
    private String majorType;
    @Excel(name = "教材代码",orderNum = "50")
    private String resourceCode;
    @Excel(name = "教材名称",orderNum = "60")
    private String resourceName;
    @Excel(name = "报订数量",orderNum = "70")
    private Integer resourceCount;
    @Excel(name = "教材版本",orderNum = "80")
    private String resourceVersion;
    @Excel(name = "教材类型",orderNum = "90",   replace = { "文字主_1", "文字辅_2","其他_3" })
    private String resourceType;
    @Excel(name = "教材价格",orderNum = "100")
    private BigDecimal price;
    private String semesterCode;
    @Excel(name = "报订季节",orderNum = "5")
    private String semesterName;
    private String supplierName;
    private String publishingName;
    @Excel(name = "区域",orderNum = "6")
    private String areaName;
    @Excel(name = "报订单位",orderNum = "7")
    private String fromOrgName;
    @Excel(name = "状态",orderNum = "110",   replace = { "新建_1", "待审核_2","已审核_3" })
    private String status;
    @Excel(name = "id",orderNum = "120")
    private String id;
    @Excel(name = "报订单号",orderNum = "130")
    private String orderNo;
    @Excel(name = "上报时间",orderNum = "140",format="yyyy-MM-dd HH:mm:ss")
    private Date confirmTime;

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMajorType() {
        return majorType;
    }

    public void setMajorType(String majorType) {
        this.majorType = majorType;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getFromOrgName() {
        return fromOrgName;
    }

    public void setFromOrgName(String fromOrgName) {
        this.fromOrgName = fromOrgName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
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

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
