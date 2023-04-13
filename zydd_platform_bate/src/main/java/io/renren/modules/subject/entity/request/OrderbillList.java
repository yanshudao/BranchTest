package io.renren.modules.subject.entity.request;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

public class OrderbillList {
    @XStreamImplicit(itemFieldName="orderbill")
    private List<Orderbill> orderbill;

    public List<Orderbill> getOrderbill() {
        return orderbill;
    }

    public void setOrderbill(List<Orderbill> orderbill) {
        this.orderbill = orderbill;
    }
}
