package io.renren.modules.zd.form;

import io.renren.modules.zd.entity.ZdOrderResourceEntity;

import java.util.List;

public class ZdOrgResourceForm {
    private List<ZdOrderResourceEntity> list;
    private String status;
    private String orderId;
    private String remark;
    private String note;

    public List<ZdOrderResourceEntity> getList() {
        return list;
    }

    public void setList(List<ZdOrderResourceEntity> list) {
        this.list = list;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
