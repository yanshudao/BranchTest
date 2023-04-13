package io.renren.modules.subject.form;

import java.util.List;

public class MajorCourseForm {
    private String majorId;
    private List<String> courseIds;

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public List<String> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<String> courseIds) {
        this.courseIds = courseIds;
    }
}
