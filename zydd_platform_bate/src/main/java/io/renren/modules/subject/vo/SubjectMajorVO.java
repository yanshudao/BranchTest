package io.renren.modules.subject.vo;

import io.renren.modules.subject.entity.SubjectMajorEntity;

public class SubjectMajorVO extends SubjectMajorEntity {
    private String majorCourseId;

    public String getMajorCourseId() {
        return majorCourseId;
    }

    public void setMajorCourseId(String majorCourseId) {
        this.majorCourseId = majorCourseId;
    }
}
