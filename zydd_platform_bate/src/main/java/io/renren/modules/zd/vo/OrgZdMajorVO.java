package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdOrgMajorEntity;

import java.io.Serializable;

public class OrgZdMajorVO extends ZdOrgMajorEntity implements Serializable {
   private String majorName;
   private String majorCode;

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }
}
