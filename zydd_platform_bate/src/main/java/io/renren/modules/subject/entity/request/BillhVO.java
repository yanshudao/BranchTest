package io.renren.modules.subject.entity.request;

import java.math.BigDecimal;

public class BillhVO {
    private String id;
    private Double avgdiscount;
    private Double totalshiyang;
    private BillhVOChildren children;
    private String result;
    private String reason;

    //20180607新增
    private String custid;
    private String custcode;
    private String custname;
    private String erpcode;
    private BigDecimal totalmayang;
    //20180709
    private String staplerbasis;//订书依据
    private String sourceno;//订书依据




    //20200720
    private String telephone;
    private String linkman;
    private String address;
    private String remark;


    public String getStaplerbasis() {
        return staplerbasis;
    }

    public void setStaplerbasis(String staplerbasis) {
        this.staplerbasis = staplerbasis;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAvgdiscount() {
        return avgdiscount;
    }

    public void setAvgdiscount(Double avgdiscount) {
        this.avgdiscount = avgdiscount;
    }

    public Double getTotalshiyang() {
        return totalshiyang;
    }

    public void setTotalshiyang(Double totalshiyang) {
        this.totalshiyang = totalshiyang;
    }

    public BillhVOChildren getChildren() {
        return children;
    }

    public void setChildren(BillhVOChildren children) {
        this.children = children;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getCustcode() {
        return custcode;
    }

    public void setCustcode(String custcode) {
        this.custcode = custcode;
    }

    public BigDecimal getTotalmayang() {
        return totalmayang;
    }

    public void setTotalmayang(BigDecimal totalmayang) {
        this.totalmayang = totalmayang;
    }

    public String getErpcode() {
        return erpcode;
    }

    public void setErpcode(String erpcode) {
        this.erpcode = erpcode;
    }


    public String getSourceno() {
        return sourceno;
    }

    public void setSourceno(String sourceno) {
        this.sourceno = sourceno;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BillhVO{" +
                "id='" + id + '\'' +
                ", avgdiscount=" + avgdiscount +
                ", totalshiyang=" + totalshiyang +
                ", children=" + children +
                ", result='" + result + '\'' +
                ", reason='" + reason + '\'' +
                ", custid='" + custid + '\'' +
                ", sourceno='" + sourceno + '\'' +
                ", custcode='" + custcode + '\'' +
                ", custname='" + custname + '\'' +
                ", erpcode='" + erpcode + '\'' +
                ", totalmayang=" + totalmayang +
                '}';
    }
}
