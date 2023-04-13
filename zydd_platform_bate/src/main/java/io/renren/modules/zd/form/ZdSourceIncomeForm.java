package io.renren.modules.zd.form;

import java.util.List;

public class ZdSourceIncomeForm {
    private String supplierId;
    private String semesterCode;
    private String erpcode;
    private String staplerbasis;
    private String foreignId;
    private List<ZdSourceResourceIncomeForm> incomeForms;

    public List<ZdSourceResourceIncomeForm> getIncomeForms() {
        return incomeForms;
    }

    public void setIncomeForms(List<ZdSourceResourceIncomeForm> incomeForms) {
        this.incomeForms = incomeForms;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getErpcode() {
        return erpcode;
    }

    public void setErpcode(String erpcode) {
        this.erpcode = erpcode;
    }

    public String getForeignId() {
        return foreignId;
    }

    public String getStaplerbasis() {
        return staplerbasis;
    }

    public void setStaplerbasis(String staplerbasis) {
        this.staplerbasis = staplerbasis;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }

    /* public String getSourceId() {
                return sourceId;
            }

            public void setSourceId(String sourceId) {
                this.sourceId = sourceId;
            }
        */
    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }
}
