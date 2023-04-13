package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdMajorCourseOrg;
public class ZdMajorCourseOrgVO extends ZdMajorCourseOrg {
    private String majorName;
    private String courseName;
    private String orgName;
    private String toOrgName;

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getToOrgName() {
        return toOrgName;
    }

    public void setToOrgName(String toOrgName) {
        this.toOrgName = toOrgName;
    }
}
