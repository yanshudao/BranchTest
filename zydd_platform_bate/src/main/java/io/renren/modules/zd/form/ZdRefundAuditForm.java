package io.renren.modules.zd.form;

import io.renren.modules.zd.entity.ZdRefundEntity;

import java.util.List;

public class ZdRefundAuditForm extends UpdateForm {
    private ZdRefundEntity refundForm;
    private List<ZdRefundResourceForm> resourceForm;
    public ZdRefundEntity getRefundForm() {
        return refundForm;
    }

    public void setRefundForm(ZdRefundEntity refundForm) {
        this.refundForm = refundForm;
    }

    public List<ZdRefundResourceForm> getResourceForm() {
        return resourceForm;
    }

    public void setResourceForm(List<ZdRefundResourceForm> resourceForm) {
        this.resourceForm = resourceForm;
    }
}
