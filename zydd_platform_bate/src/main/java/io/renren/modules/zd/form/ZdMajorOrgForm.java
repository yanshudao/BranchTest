package io.renren.modules.zd.form;

import io.renren.modules.zd.entity.ZdMajorOrg;

import java.util.List;

public class ZdMajorOrgForm {
  private List<ZdMajorOrg> list;
  private String orgCode;

    public List<ZdMajorOrg> getList() {
        return list;
    }

    public void setList(List<ZdMajorOrg> list) {
        this.list = list;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
