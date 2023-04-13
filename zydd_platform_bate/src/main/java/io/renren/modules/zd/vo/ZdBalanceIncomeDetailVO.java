package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdStockIncomeResourceEntity;
import org.omg.CORBA.INTERNAL;

import java.math.BigDecimal;
import java.util.Date;

public class ZdBalanceIncomeDetailVO extends ZdStockIncomeResourceEntity {

    private String resourceName;
    private String resourceCode;
    private BigDecimal price;
    private String resourceVersion;
    private String resourceType;
    private String erpcode;
    private Integer totalNum;
    private BigDecimal totalMayang;
    private BigDecimal totalShiyang;
    private BigDecimal nitemdiscountrate;
    private Date incomeCreateTime;


    public String getErpcode() {
        return erpcode;
    }

    public void setErpcode(String erpcode) {
        this.erpcode = erpcode;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public BigDecimal getTotalMayang() {
        return totalMayang;
    }

    public void setTotalMayang(BigDecimal totalMayang) {
        this.totalMayang = totalMayang;
    }

    public BigDecimal getTotalShiyang() {
        return totalShiyang;
    }

    public void setTotalShiyang(BigDecimal totalShiyang) {
        this.totalShiyang = totalShiyang;
    }

    @Override
    public BigDecimal getNitemdiscountrate() {
        return nitemdiscountrate;
    }

    @Override
    public void setNitemdiscountrate(BigDecimal nitemdiscountrate) {
        this.nitemdiscountrate = nitemdiscountrate;
    }

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

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Date getIncomeCreateTime() {
        return incomeCreateTime;
    }

    public void setIncomeCreateTime(Date incomeCreateTime) {
        this.incomeCreateTime = incomeCreateTime;
    }
}
