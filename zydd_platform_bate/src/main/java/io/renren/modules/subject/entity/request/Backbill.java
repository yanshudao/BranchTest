package io.renren.modules.subject.entity.request;

public class Backbill {
    private String id;
    private String corpcode;
    private String ccustomerid;
    private Integer totaldecimal;
    private Double totalmayang;
    private String coperatorid;
    private String dmakedate;
    private String result;
    private String reason;
    //20180601
    private String erpcode;

    //20200521
    private String fxwcode;

    private BackbillChildren children;

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

    public Double getTotalmayang() {
        return totalmayang;
    }

    public void setTotalmayang(Double totalmayang) {
        this.totalmayang = totalmayang;
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

    public BackbillChildren getChildren() {
        return children;
    }

    public void setChildren(BackbillChildren children) {
        this.children = children;
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

    public String getErpcode() {
        return erpcode;
    }

    public void setErpcode(String erpcode) {
        this.erpcode = erpcode;
    }

    public String getFxwcode() {
        return fxwcode;
    }

    public void setFxwcode(String fxwcode) {
        this.fxwcode = fxwcode;
    }
}
