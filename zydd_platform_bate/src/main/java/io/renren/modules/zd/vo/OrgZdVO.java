package io.renren.modules.zd.vo;

import java.util.List;

public class OrgZdVO {
    private String majorType;
    private String studentType;
    private List<OrgZdMajorVO> list;

    public String getMajorType() {
        return majorType;
    }

    public void setMajorType(String majorType) {
        this.majorType = majorType;
    }

    public List<OrgZdMajorVO> getList() {
        return list;
    }

    public void setList(List<OrgZdMajorVO> list) {
        this.list = list;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }
}
