package io.renren.modules.subject.entity.response;


import io.renren.modules.subject.entity.BaseInfos;
import io.renren.modules.subject.entity.request.OrderbillList;

public class ResponseOrderbillInfos extends BaseInfos {
    private OrderbillList datainfo;

    public OrderbillList getDatainfo() {
        return datainfo;
    }

    public void setDatainfo(OrderbillList datainfo) {
        this.datainfo = datainfo;
    }
}
