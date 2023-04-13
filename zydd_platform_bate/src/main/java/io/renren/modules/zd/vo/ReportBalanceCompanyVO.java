package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdBalanceCompanyEntity;

import java.util.List;

public class ReportBalanceCompanyVO {
    private String currentDate;
    private ZdBalanceCompanyEntity zdBalanceCompanyVO;
    List<ZdBalanceCompanyRefundVO>  refundVOS;
    List<ZdBalanceCompanyIncomeVO>  incomeVOS;

    public ZdBalanceCompanyEntity getZdBalanceCompanyVO() {
        return zdBalanceCompanyVO;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public void setZdBalanceCompanyVO(ZdBalanceCompanyEntity zdBalanceCompanyVO) {
        this.zdBalanceCompanyVO = zdBalanceCompanyVO;
    }

    public List<ZdBalanceCompanyRefundVO> getRefundVOS() {
        return refundVOS;
    }

    public void setRefundVOS(List<ZdBalanceCompanyRefundVO> refundVOS) {
        this.refundVOS = refundVOS;
    }

    public List<ZdBalanceCompanyIncomeVO> getIncomeVOS() {
        return incomeVOS;
    }

    public void setIncomeVOS(List<ZdBalanceCompanyIncomeVO> incomeVOS) {
        this.incomeVOS = incomeVOS;
    }
}
