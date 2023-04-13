package io.renren.modules.subject.entity.request;

public class Customer {
    private String id;
    private String corpcode;//公司编码
    private String custcode;//客商编码
    private String custname;//客商名称
    private String custshortname;//客商简称
    private String hareaclcode; //所属地区编码
    private String hareaclname; //所属地区名称
    private String custprop;//客商类型
    private String custflag;//客商属性
    private String custstate;//客商状态
    private String def12;//是否打印实样
    private String taxpayerid;//纳税人登记号
    private Double discountrate;//折扣
    private CustomerChildren children;
    private String salesmancode;
    private String salesmanname;
    private String usercode;
    private String username;

    private String result;
    private String reason;

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

    public String getCorpcode() {
        return corpcode;
    }

    public void setCorpcode(String corpcode) {
        this.corpcode = corpcode;
    }

    public String getCustcode() {
        return custcode;
    }

    public void setCustcode(String custcode) {
        this.custcode = custcode;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCustshortname() {
        return custshortname;
    }

    public void setCustshortname(String custshortname) {
        this.custshortname = custshortname;
    }

    public String getCustprop() {
        return custprop;
    }

    public void setCustprop(String custprop) {
        this.custprop = custprop;
    }

    public String getCustflag() {
        return custflag;
    }

    public void setCustflag(String custflag) {
        this.custflag = custflag;
    }

    public String getCuststate() {
        return custstate;
    }

    public void setCuststate(String custstate) {
        this.custstate = custstate;
    }

    public String getDef12() {
        return def12;
    }

    public void setDef12(String def12) {
        this.def12 = def12;
    }

    public String getTaxpayerid() {
        return taxpayerid;
    }

    public void setTaxpayerid(String taxpayerid) {
        this.taxpayerid = taxpayerid;
    }

    public CustomerChildren getChildren() {
        return children;
    }

    public void setChildren(CustomerChildren children) {
        this.children = children;
    }

    public Double getDiscountrate() {
        return discountrate;
    }

    public void setDiscountrate(Double discountrate) {
        this.discountrate = discountrate;
    }

    public String getSalesmancode() {
        return salesmancode;
    }

    public void setSalesmancode(String salesmancode) {
        this.salesmancode = salesmancode;
    }

    public String getSalesmanname() {
        return salesmanname;
    }

    public void setSalesmanname(String salesmanname) {
        this.salesmanname = salesmanname;
    }

    public String getHareaclcode() {
        return hareaclcode;
    }

    public void setHareaclcode(String hareaclcode) {
        this.hareaclcode = hareaclcode;
    }

    public String getHareaclname() {
        return hareaclname;
    }

    public void setHareaclname(String hareaclname) {
        this.hareaclname = hareaclname;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

 }
