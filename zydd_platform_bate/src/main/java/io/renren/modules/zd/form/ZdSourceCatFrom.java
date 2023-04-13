package io.renren.modules.zd.form;

import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.entity.ZdSourceCartEntity;

import java.util.List;

public class ZdSourceCatFrom {
    private SysUserEntity sysUserEntity;
    private List<ZdSourceCartEntity> cartList;
    private Integer status;
    private String semesterCode;

    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    public List<ZdSourceCartEntity> getCartList() {
        return cartList;
    }

    public void setCartList(List<ZdSourceCartEntity> cartList) {
        this.cartList = cartList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }
}
