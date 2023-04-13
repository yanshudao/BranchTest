package io.renren.modules.zd.form;

import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

public class ZdPublishForm {
    private String status;
    @NotBlank(message="发行单位不能为空")
    private String orgCode;
    private String semesterCode;
    private String logisticType;
    @NotBlank(message="物流单号不能为空")
    private String logisticNo;
    @NotBlank(message="物流公司不能为空")
    private String logisticCompany;
    @NotBlank(message="邮寄地址不能为空")
    private String logisticAddress;
    @NotBlank(message="物流件数不能为空")
    private String logisticBag;
    private String logisticPerson;
    private String logisticTelphone;
    private String publishId;
    private String sourceType;
    private String foreignId;
    private List<ZdPublishResourceForm> resourceForm;

    public List<ZdPublishResourceForm> getResourceForm() {
        return resourceForm;
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

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    public void setResourceForm(List<ZdPublishResourceForm> resourceForm) {
        this.resourceForm = resourceForm;
    }

    public String getLogisticAddress() {
        return logisticAddress;
    }

    public void setLogisticAddress(String logisticAddress) {
        this.logisticAddress = logisticAddress;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
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

    public String getLogisticBag() {
        return logisticBag;
    }

    public void setLogisticBag(String logisticBag) {
        this.logisticBag = logisticBag;
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

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getForeignId() {
        return foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }
}
