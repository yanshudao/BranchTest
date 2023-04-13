package io.renren.modules.zd.form;

import java.util.List;

public class SearchForm {
    private List<String> zmcrList;

    private List<String> orgList;

    private String supplierId;
    private String isSync;

    public List<String> getZmcrList() {
        return zmcrList;
    }

    public void setZmcrList(List<String> zmcrList) {
        this.zmcrList = zmcrList;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public List<String> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<String> orgList) {
        this.orgList = orgList;
    }

    public String getIsSync() {
        return isSync;
    }

    public void setIsSync(String isSync) {
        this.isSync = isSync;
    }
}
