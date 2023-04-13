package io.renren.modules.subject.entity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.math.BigDecimal;

public class Orderbill {
    private String id;
    private String bookstoreid;
    private Integer totalnum;
    private String erpcode;
    private String corpcode;
    private String ccustomerid;
    private Integer totaldecimal;
    private BigDecimal totalmayang;
    private Double totalshiyang;
    private Double avgdiscount;
    private Double ntaxrate;
    private Double postage;
    private Double postdiscount;
    private Double countamount;
    private String coperatorid;
    private String dmakedate;
    private String note;
    private String remark;
    //20180601新增
    private String staplerbasis; //订书依据
    private OrderbillChildren children;
    //承运单号
    @XStreamAlias("carrierno")
   private String carrierno;
    private Double nitemdiscountrate;
    private String status;//订单状态
    private String bid;//书店发书
//返回
    private String result;
    private String reason;
    //20200731 新增
    private String telephone;
    private String linkman;
    private String address;
    //20200824新增
    private String senddate;
    private String num;
    //20200904新增
    private String zipcode;
    //表体id
    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorpcode() {
        return corpcode;
    }

    public void setCorpcode(String corpcode) {
        this.corpcode = corpcode;
    }

    public String getCcustomerid() {
        return ccustomerid;
    }

    public void setCcustomerid(String ccustomerid) {
        this.ccustomerid = ccustomerid;
    }

    public Integer getTotaldecimal() {
        return totaldecimal;
    }

    public void setTotaldecimal(Integer totaldecimal) {
        this.totaldecimal = totaldecimal;
    }

    public BigDecimal getTotalmayang() {
        return totalmayang;
    }

    public void setTotalmayang(BigDecimal totalmayang) {
        this.totalmayang = totalmayang;
    }

    public Double getTotalshiyang() {
        return totalshiyang;
    }

    public void setTotalshiyang(Double totalshiyang) {
        this.totalshiyang = totalshiyang;
    }

    public Double getAvgdiscount() {
        return avgdiscount;
    }

    public void setAvgdiscount(Double avgdiscount) {
        this.avgdiscount = avgdiscount;
    }

    public Double getNtaxrate() {
        return ntaxrate;
    }

    public void setNtaxrate(Double ntaxrate) {
        this.ntaxrate = ntaxrate;
    }

    public Double getPostage() {
        return postage;
    }

    public void setPostage(Double postage) {
        this.postage = postage;
    }

    public Double getPostdiscount() {
        return postdiscount;
    }

    public void setPostdiscount(Double postdiscount) {
        this.postdiscount = postdiscount;
    }

    public Double getCountamount() {
        return countamount;
    }

    public void setCountamount(Double countamount) {
        this.countamount = countamount;
    }

    public String getCoperatorid() {
        return coperatorid;
    }

    public void setCoperatorid(String coperatorid) {
        this.coperatorid = coperatorid;
    }

    public String getDmakedate() {
        return dmakedate;
    }

    public void setDmakedate(String dmakedate) {
        this.dmakedate = dmakedate;
    }



    public String getCarrierno() {
        return carrierno;
    }

    public void setCarrierno(String carrierno) {
        this.carrierno = carrierno;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public OrderbillChildren getChildren() {
        return children;
    }

    public void setChildren(OrderbillChildren children) {
        this.children = children;
    }

    public String getBookstoreid() {
        return bookstoreid;
    }

    public void setBookstoreid(String bookstoreid) {
        this.bookstoreid = bookstoreid;
    }

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }


    public Double getNitemdiscountrate() {
        return nitemdiscountrate;
    }

    public void setNitemdiscountrate(Double nitemdiscountrate) {
        this.nitemdiscountrate = nitemdiscountrate;
    }

    public String getErpcode() {
        return erpcode;
    }

    public void setErpcode(String erpcode) {
        this.erpcode = erpcode;
    }

    public String getStaplerbasis() {
        return staplerbasis;
    }

    public void setStaplerbasis(String staplerbasis) {
        this.staplerbasis = staplerbasis;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
