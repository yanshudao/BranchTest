package io.renren.modules.zd.form;

import java.util.List;

public class ZdBalanceCompanyForm {

    private String supplierId;
    private String toOrgCode;
    private Integer discountRate;
    private List<String> refundList;
    private List<String> incomeList;

    public String getToOrgCode() {
        return toOrgCode;
    }

    public void setToOrgCode(String toOrgCode) {
        this.toOrgCode = toOrgCode;
    }

    public Integer getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }


    public List<String> getRefundList() {
        return refundList;
    }

    public void setRefundList(List<String> refundList) {
        this.refundList = refundList;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public List<String> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<String> incomeList) {
        this.incomeList = incomeList;
    }
}
