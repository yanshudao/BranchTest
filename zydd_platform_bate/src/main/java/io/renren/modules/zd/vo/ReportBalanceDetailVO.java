package io.renren.modules.zd.vo;

import io.renren.modules.sys.vo.SysOrgVO;
import io.renren.modules.zd.entity.ZdBalanceCompanyEntity;
import io.renren.modules.zd.entity.ZdBalanceEntity;

import java.math.BigDecimal;
import java.util.List;

public class ReportBalanceDetailVO {
    private String currentDate;
    private ZdBalanceEntity zdBalanceCompanyVO;
    private SysOrgVO provinceOrg;
    private SysOrgVO countyOrg;
    List<ZdBalanceRefundDetailVO> refundVOS;
    List<ZdBalancePublishDetailVO>  publishVOS;
    private BigDecimal refundMayang;
    private BigDecimal publishMayang;
    private BigDecimal refundShiyang;
    private BigDecimal publishShiyang;
    private BigDecimal shiyang;
    private BigDecimal mayang;
    private Integer refundSum;
    private Integer publishSum;
    private BigDecimal avgPrice;
    private String chineseTotal;

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public ZdBalanceEntity getZdBalanceCompanyVO() {
        return zdBalanceCompanyVO;
    }

    public void setZdBalanceCompanyVO(ZdBalanceEntity zdBalanceCompanyVO) {
        this.zdBalanceCompanyVO = zdBalanceCompanyVO;
    }

    public List<ZdBalanceRefundDetailVO> getRefundVOS() {
        return refundVOS;
    }

    public void setRefundVOS(List<ZdBalanceRefundDetailVO> refundVOS) {
        this.refundVOS = refundVOS;
    }

    public List<ZdBalancePublishDetailVO> getPublishVOS() {
        return publishVOS;
    }

    public void setPublishVOS(List<ZdBalancePublishDetailVO> publishVOS) {
        this.publishVOS = publishVOS;
    }

    public SysOrgVO getProvinceOrg() {
        return provinceOrg;
    }

    public void setProvinceOrg(SysOrgVO provinceOrg) {
        this.provinceOrg = provinceOrg;
    }

    public SysOrgVO getCountyOrg() {
        return countyOrg;
    }

    public void setCountyOrg(SysOrgVO countyOrg) {
        this.countyOrg = countyOrg;
    }

    public BigDecimal getRefundMayang() {
        return refundMayang;
    }

    public void setRefundMayang(BigDecimal refundMayang) {
        this.refundMayang = refundMayang;
    }

    public BigDecimal getPublishMayang() {
        return publishMayang;
    }

    public void setPublishMayang(BigDecimal publishMayang) {
        this.publishMayang = publishMayang;
    }

    public BigDecimal getRefundShiyang() {
        return refundShiyang;
    }

    public void setRefundShiyang(BigDecimal refundShiyang) {
        this.refundShiyang = refundShiyang;
    }

    public BigDecimal getPublishShiyang() {
        return publishShiyang;
    }

    public void setPublishShiyang(BigDecimal publishShiyang) {
        this.publishShiyang = publishShiyang;
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

    public Integer getPublishSum() {
        return publishSum;
    }

    public void setPublishSum(Integer publishSum) {
        this.publishSum = publishSum;
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
