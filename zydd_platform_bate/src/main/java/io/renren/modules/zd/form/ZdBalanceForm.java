package io.renren.modules.zd.form;

import java.math.BigDecimal;
import java.util.List;
//省对县级结算
public class ZdBalanceForm {

    private String lowLevelOrg;
    private Integer discountRate;
    private List<String> refundList;
    private List<String> publishList;


    public Integer getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }

    public String getLowLevelOrg() {
        return lowLevelOrg;
    }

    public void setLowLevelOrg(String lowLevelOrg) {
        this.lowLevelOrg = lowLevelOrg;
    }

    public List<String> getRefundList() {
        return refundList;
    }

    public void setRefundList(List<String> refundList) {
        this.refundList = refundList;
    }

    public List<String> getPublishList() {
        return publishList;
    }

    public void setPublishList(List<String> publishList) {
        this.publishList = publishList;
    }
}
