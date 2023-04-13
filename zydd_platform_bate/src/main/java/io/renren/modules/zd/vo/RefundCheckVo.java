package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdRefundEntity;

public class RefundCheckVo extends ZdRefundEntity {
    private String totalMayang;
    private String totalNum;
    private String username;
    private String fromOrgName;
    private String toOrgName;

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    private String refundId;

    public String getTotalMayang() {
        return totalMayang;
    }

    public void setTotalMayang(String totalMayang) {
        this.totalMayang = totalMayang;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getFromOrgName() {
        return fromOrgName;
    }

    public void setFromOrgName(String fromOrgName) {
        this.fromOrgName = fromOrgName;
    }

    public String getToOrgName() {
        return toOrgName;
    }

    public void setToOrgName(String toOrgName) {
        this.toOrgName = toOrgName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
