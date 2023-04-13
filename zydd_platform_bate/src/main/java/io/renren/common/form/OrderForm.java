package io.renren.common.form;

import java.util.List;

public class OrderForm<T> {
    private List<T> ids;

    public List<T> getIds() {
        return ids;
    }

    public void setIds(List<T> ids) {
        this.ids = ids;
    }
}
