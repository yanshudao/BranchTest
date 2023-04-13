package io.renren.modules.subject.entity.request;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

public class CustomerChildren {
    @XStreamImplicit(itemFieldName="addr")
    private List<CustomerAddress> addr;

    public List<CustomerAddress> getAddr() {
        return addr;
    }

    public void setAddr(List<CustomerAddress> addr) {
        this.addr = addr;
    }
}
