package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdOrderCartEntity;

import java.math.BigDecimal;

public class ZdOrderCartVO extends ZdOrderCartEntity{
    private String courseCode;
    private String majorCode;
    private String courseName;
    private String majorName;
    private String majorType;
    private String studentType;
    private String subjectType;
    private BigDecimal mayang;
    private BigDecimal price;
    private String countyOrgName;
    private String resourceName;
    private String resourceCode;
    private String resourceType;
    private String zmcrId;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getZmcrId() {
        return zmcrId;
    }

    public void setZmcrId(String zmcrId) {
        this.zmcrId = zmcrId;
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

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public BigDecimal getMayang() {
        return mayang;
    }

    public void setMayang(BigDecimal mayang) {
        this.mayang = mayang;
    }

    public String getCountyOrgName() {
        return countyOrgName;
    }

    public void setCountyOrgName(String countyOrgName) {
        this.countyOrgName = countyOrgName;
    }

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
}
