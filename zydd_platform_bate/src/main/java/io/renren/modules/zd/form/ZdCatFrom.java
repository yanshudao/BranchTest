package io.renren.modules.zd.form;

import io.renren.modules.sys.entity.SysUserEntity;

import java.util.List;

public class ZdCatFrom {
    private List<ZdCatResourceFrom> catForms;
    private SysUserEntity sysUserEntity;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    public List<ZdCatResourceFrom> getCatForms() {
        return catForms;
    }

    public void setCatForms(List<ZdCatResourceFrom> catForms) {
        this.catForms = catForms;
    }
}
