package io.renren.modules.zd.vo;

import io.renren.modules.sys.entity.SysOrgEntity;

public class ZdOrderOrgVO extends SysOrgEntity {
    private Integer resourceCount;
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Integer getResourceCount() {
        return resourceCount;
    }

    public void setResourceCount(Integer resourceCount) {
        this.resourceCount = resourceCount;
    }
}
