package io.renren.modules.zd.form;

import java.util.List;

public class CompleteOrgRefundForm {
    private String refundId;
    private List<CompleteOrgRefundResourceForm> resourceForms;

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public List<CompleteOrgRefundResourceForm> getResourceForms() {
        return resourceForms;
    }

    public void setResourceForms(List<CompleteOrgRefundResourceForm> resourceForms) {
        this.resourceForms = resourceForms;
    }
}
