package io.renren.modules.zd.vo;


import io.renren.modules.zd.entity.ZdMajorOrg;

public class ZdMajorOrgVO extends ZdMajorOrg {
    private String majorName;
    private String orgName;

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
