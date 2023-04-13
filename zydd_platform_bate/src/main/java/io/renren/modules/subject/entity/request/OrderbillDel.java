package io.renren.modules.subject.entity.request;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import io.renren.modules.subject.entity.BaseInfos;

@XStreamAlias("datainfos")
public class OrderbillDel {
    @XStreamAsAttribute
    private String syscode;
    @XStreamAsAttribute
    private String datasource;
    @XStreamAsAttribute
    private String company;
    @XStreamAsAttribute
    private String operate;
    @XStreamAsAttribute
    private String sender;
    @XStreamAsAttribute
    private String sendtime;
    private Orderbill orderbill;

    public Orderbill getOrderbill() {
        return orderbill;
    }

    public void setOrderbill(Orderbill orderbill) {
        this.orderbill = orderbill;
    }

    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }
}