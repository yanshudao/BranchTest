package io.renren.modules.zd.form;

import io.renren.common.form.OrderForm;

public class SubmitZdRefundCartFrom extends OrderForm {
    private String supplierId ;
    private String refundSemesterCode ;
    private String status;

    public String getRefundSemesterCode() {
        return refundSemesterCode;
    }

    public void setRefundSemesterCode(String refundSemesterCode) {
        this.refundSemesterCode = refundSemesterCode;
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
}
