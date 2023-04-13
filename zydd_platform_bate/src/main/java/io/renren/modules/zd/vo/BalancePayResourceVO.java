package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdBalancePayEntity;

public class BalancePayResourceVO extends ZdBalancePayEntity {


    private String  realname;

    private String orgname;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    @Override
    public String toString() {
        return "BalancePayResourceVO{" +
                "realname='" + realname + '\'' +
                ", orgname='" + orgname + '\'' +
                '}';
    }
}