package io.renren.modules.subject.form;

import io.renren.modules.subject.entity.SubjectResourceEntity;

public class SubjectResourceVersionForm {
    private SubjectResourceEntity oldResource;
    private SubjectResourceEntity newResource;

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

    public SubjectResourceEntity getOldResource() {
        return oldResource;
    }

    public void setOldResource(SubjectResourceEntity oldResource) {
        this.oldResource = oldResource;
    }

    public SubjectResourceEntity getNewResource() {
        return newResource;
    }

    public void setNewResource(SubjectResourceEntity newResource) {
        this.newResource = newResource;
    }
}
