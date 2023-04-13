package io.renren.modules.zd.form;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ZdSourceIncomeResourceForm {
    @NotNull
    private String id;
    private String resourceId;
    @NotNull
    private Integer num;
    private BigDecimal nitemdiscountrate;

    public BigDecimal getNitemdiscountrate() {
        return nitemdiscountrate;
    }

    public void setNitemdiscountrate(BigDecimal nitemdiscountrate) {
        this.nitemdiscountrate = nitemdiscountrate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceId() {
        return resourceId;
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
}
