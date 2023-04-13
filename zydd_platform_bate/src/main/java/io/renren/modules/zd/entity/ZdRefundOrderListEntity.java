package io.renren.modules.zd.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ZdRefundOrderListEntity {
    private String id;

    private String resourceCode;

    private String refundCode;

    private String logisticNo;

    private String semeName;
    private String refundSemesterName;

    private Integer kinds;

    private Integer refundTotal;
    private Integer saveRefundTotal;

    private BigDecimal mayangTotal;
    private BigDecimal shiyang;

    private String refundUser;

    private Date updateTime;
    private Date createTime;

    private String status;
    private String isSync;

    private String logisticCompany;
    private String fromOrgName;
    private String toOrgName;
    private String supplierName;
    private String createName;
    private String logisticPerson;
    private String logisticTelphone;

    public String getRefundSemesterName() {
        return refundSemesterName;
    }

    public void setRefundSemesterName(String refundSemesterName) {
        this.refundSemesterName = refundSemesterName;
    }

    public String getLogisticPerson() {
        return logisticPerson;
    }

    public void setLogisticPerson(String logisticPerson) {
        this.logisticPerson = logisticPerson;
    }

    public String getLogisticTelphone() {
        return logisticTelphone;
    }

    public void setLogisticTelphone(String logisticTelphone) {
        this.logisticTelphone = logisticTelphone;
    }


    public Integer getSaveRefundTotal() {
        return saveRefundTotal;
    }

    public void setSaveRefundTotal(Integer saveRefundTotal) {
        this.saveRefundTotal = saveRefundTotal;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public String getLogisticNo() {
        return logisticNo;
    }

    public void setLogisticNo(String logisticNo) {
        this.logisticNo = logisticNo;
    }

    public String getSemeName() {
        return semeName;
    }

    public void setSemeName(String semeName) {
        this.semeName = semeName;
    }

    public Integer getKinds() {
        return kinds;
    }

    public void setKinds(Integer kinds) {
        this.kinds = kinds;
    }

    public Integer getRefundTotal() {
        return refundTotal;
    }

    public void setRefundTotal(Integer refundTotal) {
        this.refundTotal = refundTotal;
    }

    public BigDecimal getMayangTotal() {
        return mayangTotal;
    }

    public void setMayangTotal(BigDecimal mayangTotal) {
        this.mayangTotal = mayangTotal;
    }

    public BigDecimal getShiyang() {
        return shiyang;
    }

    public void setShiyang(BigDecimal shiyang) {
        this.shiyang = shiyang;
    }

    public String getRefundUser() {
        return refundUser;
    }

    public void setRefundUser(String refundUser) {
        this.refundUser = refundUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogisticCompany() {
        return logisticCompany;
    }

    public void setLogisticCompany(String logisticCompany) {
        this.logisticCompany = logisticCompany;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getIsSync() {
        return isSync;
    }

    public void setIsSync(String isSync) {
        this.isSync = isSync;
    }
}
