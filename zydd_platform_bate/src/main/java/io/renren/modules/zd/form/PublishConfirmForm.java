package io.renren.modules.zd.form;

import io.renren.modules.zd.entity.ZdPublishResourceEntity;

import java.util.List;

public class PublishConfirmForm {
    private String publishId;
    private List<ZdPublishResourceEntity> resourceList;

    public List<ZdPublishResourceEntity> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<ZdPublishResourceEntity> resourceList) {
        this.resourceList = resourceList;
    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }
}
