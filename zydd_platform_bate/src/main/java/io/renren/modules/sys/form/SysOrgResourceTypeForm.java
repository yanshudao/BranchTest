package io.renren.modules.sys.form;

import java.util.List;

public class SysOrgResourceTypeForm {
    private String orgCode;
    private List<String> idList;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }
}
