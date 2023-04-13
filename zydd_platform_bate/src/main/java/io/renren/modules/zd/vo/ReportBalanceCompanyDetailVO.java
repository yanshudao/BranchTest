package io.renren.modules.zd.vo;

import io.renren.modules.sys.entity.SysCompanyEntity;
import io.renren.modules.sys.vo.SysOrgVO;
import io.renren.modules.sys.vo.SysOrgVO1;
import io.renren.modules.zd.entity.ZdBalanceCompanyEntity;
import io.renren.modules.zd.entity.ZdRefundOrderListEntity;

import java.math.BigDecimal;
import java.util.List;

public class ReportBalanceCompanyDetailVO {
    private String currentDate;
    private SysCompanyEntity companyEntity;
    private SysOrgVO orgEntity;
    List<ZdBalanceRefundDetailVO>  refundVOS;
    List<ZdBalanceIncomeDetailVO>  incomeVOS;
    private BigDecimal refundMayang;
    private BigDecimal incomeMayang;
    private BigDecimal refundShiyang;
    private BigDecimal incomeShiyang;
    private BigDecimal shiyang;
    private BigDecimal mayang;
    private Integer refundSum;
    private Integer incomeSum;
    private BigDecimal avgPrice;
    private String chineseTotal;

    
    
    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public SysCompanyEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(SysCompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

    public SysOrgVO getOrgEntity() {
        return orgEntity;
    }

    public void setOrgEntity(SysOrgVO orgEntity) {
        this.orgEntity = orgEntity;
    }

    public List<ZdBalanceRefundDetailVO> getRefundVOS() {
        return refundVOS;
    }

    public void setRefundVOS(List<ZdBalanceRefundDetailVO> refundVOS) {
        this.refundVOS = refundVOS;
    }

    public List<ZdBalanceIncomeDetailVO> getIncomeVOS() {
        return incomeVOS;
    }

    public void setIncomeVOS(List<ZdBalanceIncomeDetailVO> incomeVOS) {
        this.incomeVOS = incomeVOS;
    }

    public BigDecimal getRefundMayang() {
        return refundMayang;
    }

    public void setRefundMayang(BigDecimal refundMayang) {
        this.refundMayang = refundMayang;
    }

    public BigDecimal getIncomeMayang() {
        return incomeMayang;
    }

    public void setIncomeMayang(BigDecimal incomeMayang) {
        this.incomeMayang = incomeMayang;
    }

    public BigDecimal getRefundShiyang() {
        return refundShiyang;
    }

    public void setRefundShiyang(BigDecimal refundShiyang) {
        this.refundShiyang = refundShiyang;
    }

    public BigDecimal getIncomeShiyang() {
        return incomeShiyang;
    }

    public void setIncomeShiyang(BigDecimal incomeShiyang) {
        this.incomeShiyang = incomeShiyang;
    }

    public BigDecimal getShiyang() {
        return shiyang;
    }

    public void setShiyang(BigDecimal shiyang) {
        this.shiyang = shiyang;
    }

    public Integer getRefundSum() {
        return refundSum;
    }

    public void setRefundSum(Integer refundSum) {
        this.refundSum = refundSum;
    }

    public Integer getIncomeSum() {
        return incomeSum;
    }

    public void setIncomeSum(Integer incomeSum) {
        this.incomeSum = incomeSum;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getChineseTotal() {
        return chineseTotal;
    }

    public void setChineseTotal(String chineseTotal) {
        this.chineseTotal = chineseTotal;
    }

    public BigDecimal getMayang() {
        return mayang;
    }

    public void setMayang(BigDecimal mayang) {
        this.mayang = mayang;
    }
}
