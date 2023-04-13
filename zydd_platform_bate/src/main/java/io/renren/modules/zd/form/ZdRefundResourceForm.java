package io.renren.modules.zd.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class ZdRefundResourceForm {
    private String id;
    @NotBlank(message = "教材不能为空")
    private String resourceId;
    @Min(value = 1)
    private Integer refundNum;
    private Integer realNum;
    private BigDecimal nitemdiscountrate;
    private BigDecimal price;

    public BigDecimal getNitemdiscountrate() {
        return nitemdiscountrate;
    }

    public void setNitemdiscountrate(BigDecimal nitemdiscountrate) {
        this.nitemdiscountrate = nitemdiscountrate;
    }

    public Integer getRealNum() {
        return realNum;
    }

    public void setRealNum(Integer realNum) {
        this.realNum = realNum;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
