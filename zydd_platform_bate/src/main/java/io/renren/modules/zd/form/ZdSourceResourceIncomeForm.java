package io.renren.modules.zd.form;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ZdSourceResourceIncomeForm {

    @NotNull
    private String resourceId;

    @NotNull
    private Integer num;
    private String resourceCode;
    private String fxwbids;
    private String foreignId;
    private BigDecimal mayang;
    private BigDecimal shiyang;
    private BigDecimal nitemdiscountrate;
    private BigDecimal incomePrice;

    public String getForeignId() {
        return foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }

    public String getFxwbids() {
        return fxwbids;
    }

    public void setFxwbids(String fxwbids) {
        this.fxwbids = fxwbids;
    }

    public BigDecimal getIncomePrice() {
        return incomePrice;
    }

    public void setIncomePrice(BigDecimal incomePrice) {
        this.incomePrice = incomePrice;
    }

    public String getResourceId() {
        return resourceId;
    }

    public BigDecimal getMayang() {
        return mayang;
    }

    public void setMayang(BigDecimal mayang) {
        this.mayang = mayang;
    }

    public BigDecimal getShiyang() {
        return shiyang;
    }

    public void setShiyang(BigDecimal shiyang) {
        this.shiyang = shiyang;
    }

    public BigDecimal getNitemdiscountrate() {
        return nitemdiscountrate;
    }

    public void setNitemdiscountrate(BigDecimal nitemdiscountrate) {
        this.nitemdiscountrate = nitemdiscountrate;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }
}
