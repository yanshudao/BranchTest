package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdRefundResourceEntity;

import java.util.List;

public class ZdRefundResourceGroup {
    private String supplierId;
    private List<ZdRefundResourceEntity> list;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public List<ZdRefundResourceEntity> getList() {
        return list;
    }

    public void setList(List<ZdRefundResourceEntity> list) {
        this.list = list;
    }
}
