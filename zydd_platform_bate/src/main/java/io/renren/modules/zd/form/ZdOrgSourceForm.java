package io.renren.modules.zd.form;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class ZdOrgSourceForm implements Serializable {
    @NotNull(message="代采购单位不能为空")
    private String orgCode;
    private String supplierId;
    private String status;
    private String semesterCode;
    private List<ZdSourceResourceForm> resourceForm;
    private List<String> orderResourceIds;

    private String remark;
    private String note;


    public List<String> getOrderResourceIds() {
        return orderResourceIds;
    }

    public void setOrderResourceIds(List<String> orderResourceIds) {
        this.orderResourceIds = orderResourceIds;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public List<ZdSourceResourceForm> getResourceForm() {
        return resourceForm;
    }

    public void setResourceForm(List<ZdSourceResourceForm> resourceForm) {
        this.resourceForm = resourceForm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
