package io.renren.modules.zd.vo;

import io.renren.modules.subject.entity.SubjectMajorEntity;

public class OrgMajorVO extends SubjectMajorEntity {

    private String majorId;

    private String orgMajorId;
    private String semesterCode;

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getOrgMajorId() {
        return orgMajorId;
    }

    public void setOrgMajorId(String orgMajorId) {
        this.orgMajorId = orgMajorId;
    }
}
