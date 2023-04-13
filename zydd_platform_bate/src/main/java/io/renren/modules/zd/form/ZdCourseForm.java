package io.renren.modules.zd.form;

import java.io.Serializable;
import java.util.List;

public class ZdCourseForm implements Serializable{

    private String orgCode;
    private String status;
    private List<ZdCourseDetailForm> courseForm;

    public List<ZdCourseDetailForm> getCourseForm() {
        return courseForm;
    }

    public void setCourseForm(List<ZdCourseDetailForm> courseForm) {
        this.courseForm = courseForm;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
