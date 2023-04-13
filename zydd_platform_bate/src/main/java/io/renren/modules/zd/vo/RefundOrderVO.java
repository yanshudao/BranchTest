package io.renren.modules.zd.vo;

import io.renren.modules.zd.entity.ZdOrderResourceEntity;

public class RefundOrderVO extends ZdOrderResourceEntity {

    private String totatlnum;
    private String ordernum;


    public String getTotatlnum() {
        return totatlnum;
    }

    public void setTotatlnum(String totatlnum) {
        this.totatlnum = totatlnum;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    @Override
    public String toString() {
        return "RefundOrderVO{" +
                "totatlnum='" + totatlnum + '\'' +
                ", ordernum='" + ordernum + '\'' +
                '}';
    }
}
