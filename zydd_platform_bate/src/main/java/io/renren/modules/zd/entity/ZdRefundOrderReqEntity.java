package io.renren.modules.zd.entity;

public class ZdRefundOrderReqEntity {
    private String semesterCode;

    private String status;

    private String refundCode;

    private String startTime;

    private String endTime;

    private String page;

    private String resourceCode;

    private String fromOrgCode;

    private String toOrgCode;

    public String getFromOrgCode() {
        return fromOrgCode;
    }

    public void setFromOrgCode(String fromOrgCode) {
        this.fromOrgCode = fromOrgCode;
    }

    public String getToOrgCode() {
        return toOrgCode;
    }

    public void setToOrgCode(String toOrgCode) {
        this.toOrgCode = toOrgCode;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }
}
