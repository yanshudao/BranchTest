package io.renren.modules.zd.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.renren.modules.zd.entity.ZdSourceCartEntity;

import java.math.BigDecimal;

public class ZdSourceCartVO extends ZdSourceCartEntity {
    private String resourceName;
    @Excel(name = "教材代码",orderNum = "10")
    private String resourceCode;
    private BigDecimal price;
    private String resourceType;
    @Excel(name = "结果",orderNum = "30")
    private String result;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
