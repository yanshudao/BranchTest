package io.renren.modules.sys.form;

import io.renren.modules.sys.entity.SysOrgSettingEntity;

public class OrgForm extends SysOrgSettingEntity {

    private String orgType;
    private String areaCode;

     /**
     * 单位名称
     */
    private String orgName;
    /**
     * 上级部门编号
     */
    private String parentId;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }



    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


}
