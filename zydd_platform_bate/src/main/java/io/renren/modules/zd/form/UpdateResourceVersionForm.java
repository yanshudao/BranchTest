package io.renren.modules.zd.form;

import java.util.List;

public class UpdateResourceVersionForm {
    private List<String> zmcrList;
    private String resourceCode;

    private List<String> orgList;
    public List<String> getZmcrList() {
        return zmcrList;
    }

    public void setZmcrList(List<String> zmcrList) {
        this.zmcrList = zmcrList;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public List<String> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<String> orgList) {
        this.orgList = orgList;
    }
}
