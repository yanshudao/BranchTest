package io.renren.modules.zd.form;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;
import java.util.List;

public class ZdRefundForm {
    private String supplierId;
    private String id;
    private String semesterCode;
    private String refundSemesterCode;
    private String orgCode;
    private String logisticType;
    private String logisticNo;
    private String logisticCompany;
    private String logisticAddress;
    private String logisticBag;
    private String remark;
    private String logisticPerson;
    private String logisticTelphone;
    private List<ZdRefundResourceForm> resourceForm;

    public String getRefundSemesterCode() {
        return refundSemesterCode;
    }

    public void setRefundSemesterCode(String refundSemesterCode) {
        this.refundSemesterCode = refundSemesterCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public String getLogisticType() {
        return logisticType;
    }

    public void setLogisticType(String logisticType) {
        this.logisticType = logisticType;
    }

    public String getLogisticNo() {
        return logisticNo;
    }

    public void setLogisticNo(String logisticNo) {
        this.logisticNo = logisticNo;
    }

    public String getLogisticCompany() {
        return logisticCompany;
    }

    public void setLogisticCompany(String logisticCompany) {
        this.logisticCompany = logisticCompany;
    }

    public String getLogisticAddress() {
        return logisticAddress;
    }

    public void setLogisticAddress(String logisticAddress) {
        this.logisticAddress = logisticAddress;
    }

    public String getLogisticBag() {
        return logisticBag;
    }

    public void setLogisticBag(String logisticBag) {
        this.logisticBag = logisticBag;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public List<ZdRefundResourceForm> getResourceForm() {
        return resourceForm;
    }

    public void setResourceForm(List<ZdRefundResourceForm> resourceForm) {
        this.resourceForm = resourceForm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
