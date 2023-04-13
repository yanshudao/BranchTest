package io.renren.modules.zd.form;

import java.util.List;

public class ZdResourceForm {
    private String orgCode;
    private String status;
    private List<ZdResourceDetailForm>  resourceForm;

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

    public List<ZdResourceDetailForm> getResourceForm() {
        return resourceForm;
    }

    public void setResourceForm(List<ZdResourceDetailForm> resourceForm) {
        this.resourceForm = resourceForm;
    }
}
