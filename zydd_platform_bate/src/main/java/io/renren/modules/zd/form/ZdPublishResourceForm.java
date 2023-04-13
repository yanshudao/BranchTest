package io.renren.modules.zd.form;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class ZdPublishResourceForm {
    private String id;
    private String resourceId;
    @Min(1)
    private Integer num;
    private BigDecimal nitemdiscountrate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getNitemdiscountrate() {
        return nitemdiscountrate;
    }

    public void setNitemdiscountrate(BigDecimal nitemdiscountrate) {
        this.nitemdiscountrate = nitemdiscountrate;
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
