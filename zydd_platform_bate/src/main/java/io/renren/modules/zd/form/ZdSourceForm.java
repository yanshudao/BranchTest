package io.renren.modules.zd.form;

import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

public class ZdSourceForm {

    private String status;
    @NotBlank(message="供应商不能为空")
    private String supplierId;
    private String semesterCode;
    private List<ZdSourceResourceForm> resourceForm;
    private String sourceType;
    private String note;
    private String remark;
    //20200805
    private String orgCode;
    private String toOrgCode;
    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public List<ZdSourceResourceForm> getResourceForm() {
        return resourceForm;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public void setResourceForm(List<ZdSourceResourceForm> resourceForm) {
        this.resourceForm = resourceForm;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getToOrgCode() {
        return toOrgCode;
    }

    public void setToOrgCode(String toOrgCode) {
        this.toOrgCode = toOrgCode;
    }


}
