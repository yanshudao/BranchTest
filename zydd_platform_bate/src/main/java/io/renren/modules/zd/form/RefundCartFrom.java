package io.renren.modules.zd.form;

import io.renren.modules.sys.entity.SysUserEntity;

import java.util.List;

public class RefundCartFrom {
    private String refundSemesterCode;
    private List<ZdRefundCartResourceFrom> catForms;
    private SysUserEntity sysUserEntity;
    private String status;
    private String supplierId;

    public String getRefundSemesterCode() {
        return refundSemesterCode;
    }

    public void setRefundSemesterCode(String refundSemesterCode) {
        this.refundSemesterCode = refundSemesterCode;
    }

    public List<ZdRefundCartResourceFrom> getCatForms() {
        return catForms;
    }

    public void setCatForms(List<ZdRefundCartResourceFrom> catForms) {
        this.catForms = catForms;
    }

    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
}
