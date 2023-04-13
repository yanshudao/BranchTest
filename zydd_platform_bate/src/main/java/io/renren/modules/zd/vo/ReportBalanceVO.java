package io.renren.modules.zd.vo;

import io.renren.modules.sys.vo.SysOrgVO;
import io.renren.modules.zd.entity.ZdBalanceEntity;

import java.util.List;

public class ReportBalanceVO {
    private String currentDate;
    private SysOrgVO provinceOrg;
    private SysOrgVO countyOrg;
    private List<ZdBalancePublishDetailVO> publishVOS;
    private List<ZdBalanceRefundDetailVO> refundVOS;
    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }


    public List<ZdBalancePublishDetailVO> getPublishVOS() {
        return publishVOS;
    }

    public void setPublishVOS(List<ZdBalancePublishDetailVO> publishVOS) {
        this.publishVOS = publishVOS;
    }

    public List<ZdBalanceRefundDetailVO> getRefundVOS() {
        return refundVOS;
    }

    public void setRefundVOS(List<ZdBalanceRefundDetailVO> refundVOS) {
        this.refundVOS = refundVOS;
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
}
