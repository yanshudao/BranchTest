package io.renren.modules.zd.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.renren.modules.zd.entity.ZdOrderResourceEntity;

import java.math.BigDecimal;

public class OrderResourceVO extends ZdOrderResourceEntity{
    @Excel(name = "教材名称",orderNum = "70")
    private String resourceName;
    @Excel(name = "教材类型",orderNum = "90",    replace = { "文字主_1", "文字辅_2","其他_3" })
    private String resourceType;
    @Excel(name = "课程名称",orderNum = "45")
    private String courseName;
    @Excel(name = "课程代码",orderNum = "30")
    private String courseCode;
    @Excel(name = "教材代码",orderNum = "80")
    private String resourceCode;
    @Excel(name = "课程代码",orderNum = "50")
    private String resourceVersion;
    @Excel(name = "ISBN",orderNum = "70")
    private String isbn;
    @Excel(name = "专业层次",orderNum = "30",   replace = { "本科_2", "专科_4","本科（专科起点）_5","本科（高中起点）_6","中专_8"})
    private String majorType;
    @Excel(name = "学生类型",orderNum = "40",replace = { "开放_01", "普招_02","成招_03","一村一_04","课程开放_05","助力计划_06","农民大学生_07","专本衔接_08","中高职衔接_09","乡村教师学历提升计划_10","网考_11","中专_19"})
    private String studentType;
    @Excel(name = "专业代码",orderNum = "20")
    private String majorCode;
    @Excel(name = "专业名称",orderNum = "10")
    private String majorName;
    private String countyName;
    private String countyCode;
    private String provinceName;
    private String provinceCode;
    private BigDecimal price;
    private BigDecimal mayng;
    private String orderNo;
    private String countyOrgName;
    private String provinceOrgName;
    private String subjectType;

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public BigDecimal getMayng() {
        return mayng;
    }

    public void setMayng(BigDecimal mayng) {
        this.mayng = mayng;
    }
}
