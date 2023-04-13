package io.renren.modules.sys.vo;

import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.entity.SysUserEntity;

public class SysUserLoginVO extends SysUserEntity {
    private SysOrgEntity org;
    private SysOrgSettingEntity orgConf;

    public SysOrgEntity getOrg() {
        return org;
    }

    public void setOrg(SysOrgEntity org) {
        this.org = org;
    }

    public SysOrgSettingEntity getOrgConf() {
        return orgConf;
    }

    public void setOrgConf(SysOrgSettingEntity orgConf) {
        this.orgConf = orgConf;
    }
}
