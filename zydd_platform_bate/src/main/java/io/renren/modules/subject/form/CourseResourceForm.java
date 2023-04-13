package io.renren.modules.subject.form;

import java.util.List;

public class CourseResourceForm {
    private String courseId;
    private List<String> resourceIds;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }
}
