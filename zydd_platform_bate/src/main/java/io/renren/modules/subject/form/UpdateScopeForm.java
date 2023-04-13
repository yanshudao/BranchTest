package io.renren.modules.subject.form;

import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.entity.SubjectResourceScopeEntity;

import java.util.List;
public class UpdateScopeForm {
    private List<SubjectResourceEntity> resourceList;
    private List<SubjectResourceScopeEntity> scopeList;

    public List<SubjectResourceEntity> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<SubjectResourceEntity> resourceList) {
        this.resourceList = resourceList;
    }

    public List<SubjectResourceScopeEntity> getScopeList() {
        return scopeList;
    }

    public void setScopeList(List<SubjectResourceScopeEntity> scopeList) {
        this.scopeList = scopeList;
    }
}
