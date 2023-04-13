package io.renren.modules.zd.vo;

import java.util.List;

public class ZdCatVO {
    private String majorName;
    private String zmcrId;
    private String studentType;
    private String majorType;
//    private String courseName;
    private String majorCode;
    private List<ZdCatResourceVO> resourceList;

    public String getZmcrId() {
        return zmcrId;
    }

    public void setZmcrId(String zmcrId) {
        this.zmcrId = zmcrId;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
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

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public List<ZdCatResourceVO> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<ZdCatResourceVO> resourceList) {
        this.resourceList = resourceList;
    }
}
