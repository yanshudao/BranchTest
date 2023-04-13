package io.renren.modules.subject.entity.request;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

public class BackbillList {
    @XStreamImplicit(itemFieldName="backbill")
    private List<Backbill> backbill;

    public List<Backbill> getBackbill() {
        return backbill;
    }

    public void setBackbill(List<Backbill> backbill) {
        this.backbill = backbill;
    }
}
