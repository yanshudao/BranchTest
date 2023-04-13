package io.renren.modules.zd.entity;

import java.util.List;

public class ZdRefundReportReqEntity {
    private String refundCode;

    private List<ZdRefundResourceEntity> resourceList;

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public List<ZdRefundResourceEntity> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<ZdRefundResourceEntity> resourceList) {
        this.resourceList = resourceList;
    }
}
