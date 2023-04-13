package io.renren.modules.zd.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.renren.modules.zd.entity.ZdMajorCourseResourceEntity;

import java.math.BigDecimal;

public class ZdMajorCourseResourceVO  extends ZdMajorCourseResourceEntity {
    @Excel(name = "专业名称", orderNum = "15")
    private String majorName;
    @Excel(name = "课程名称", orderNum = "45")
    private String courseName;
    @Excel(name = "教材名称", orderNum = "55")
    private String resourceName;
    private String orgName;
    @Excel(name = "结果", orderNum = "100")
    private String result;
    private String resourceType;
    @Excel(name = "定价", orderNum = "65")
    private BigDecimal price;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
