package io.renren.common.form;

import io.renren.common.validator.annotation.ListNotHasNull;

import java.util.List;

public class DeleteForm<T> {
    @ListNotHasNull
    private List<T> ids;

    private String remark;
    private String note;
    public List<T> getIds() {
        return ids;
    }

    public void setIds(List<T> ids) {
        this.ids = ids;
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
