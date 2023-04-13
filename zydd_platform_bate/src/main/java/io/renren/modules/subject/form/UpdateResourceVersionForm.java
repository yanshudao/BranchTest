package io.renren.modules.subject.form;

import io.renren.modules.subject.entity.SubjectResourceEntity;

public class UpdateResourceVersionForm {
    private String oldResource;
    private String newResource;
    private String majorId;
    private String courseId;

    private String orderStatus;
    private String sourceStatus;


    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(String sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    public String getOldResource() {
        return oldResource;
    }

    public void setOldResource(String oldResource) {
        this.oldResource = oldResource;
    }

    public String getNewResource() {
        return newResource;
    }

    public void setNewResource(String newResource) {
        this.newResource = newResource;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
