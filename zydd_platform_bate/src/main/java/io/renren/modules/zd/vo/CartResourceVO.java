package io.renren.modules.zd.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class CartResourceVO  {
    @Excel(name = "专业代码",orderNum = "10")
    private String majorCode;
    @Excel(name = "专业名称",orderNum = "20")
    private String majorName;
    @Excel(name = "学生类型",orderNum = "25",replace = { "开放_01", "普招_02","成招_03","一村一_04","课程开放_05","助力计划_06","农民大学生_07","专本衔接_08","中高职衔接_09","乡村教师学历提升计划_10","网考_11","中专_19" })
    private String studentType;
    @Excel(name = "专业层次",orderNum = "22",replace = { "本科_2", "专科_4","本科（专科起点）_5","本科（高中起点）_6","中专_8" })
    private String majorType;

    @Excel(name = "课程名称",orderNum = "30")
    private String courseName;
    @Excel(name = "课程代码",orderNum = "40")
    private String courseCode;
    @Excel(name = "教材名称",orderNum = "50")
    private String resourceName;
    @Excel(name = "教材代码",orderNum = "60")
    private String resourceCode;
    @Excel(name = "采购数量",orderNum = "70")
    private String catNum;
    @Excel(name = "结果",orderNum = "80")
    private String result;
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

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getMajorType() {
        return majorType;
    }

    public void setMajorType(String majorType) {
        this.majorType = majorType;
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

    public String getCatNum() {
        return catNum;
    }

    public void setCatNum(String catNum) {
        this.catNum = catNum;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
