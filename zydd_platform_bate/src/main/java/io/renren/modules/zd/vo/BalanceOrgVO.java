package io.renren.modules.zd.vo;

import io.renren.modules.sys.entity.SysOrgEntity;

public class BalanceOrgVO extends SysOrgEntity{
    private Integer preparePublishCount;
    private Integer prepareBalanceCount;
    private Integer prepareRefundCount;

    public Integer getPreparePublishCount() {
        return preparePublishCount;
    }

    public void setPreparePublishCount(Integer preparePublishCount) {
        this.preparePublishCount = preparePublishCount;
    }

    public Integer getPrepareBalanceCount() {
        return prepareBalanceCount;
    }

    public void setPrepareBalanceCount(Integer prepareBalanceCount) {
        this.prepareBalanceCount = prepareBalanceCount;
    }

    public Integer getPrepareRefundCount() {
        return prepareRefundCount;
    }

    public void setPrepareRefundCount(Integer prepareRefundCount) {
        this.prepareRefundCount = prepareRefundCount;
    }
}
