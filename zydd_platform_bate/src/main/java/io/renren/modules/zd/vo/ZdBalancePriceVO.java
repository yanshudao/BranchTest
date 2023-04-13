package io.renren.modules.zd.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ZdBalancePriceVO implements Serializable{
    private BigDecimal mayang;
    private BigDecimal shiyang;

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
}
