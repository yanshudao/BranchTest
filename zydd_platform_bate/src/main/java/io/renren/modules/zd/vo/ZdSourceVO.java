package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdSourceEntity;

public class ZdSourceVO extends ZdSourceEntity {
    private String supplierName;
    private String orgName;
    private Integer resourceNum;
    private String realname;
    private String semesterName;

    private Integer totalCount;
    private Integer sendCount;
    private Integer deleteCount;

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }


    public Integer getResourceNum() {
        return resourceNum;
    }

    public void setResourceNum(Integer resourceNum) {
        this.resourceNum = resourceNum;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public Integer getDeleteCount() {
        return deleteCount;
    }

    public void setDeleteCount(Integer deleteCount) {
        this.deleteCount = deleteCount;
    }
}
