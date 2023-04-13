package io.renren.modules.subject.entity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.math.BigDecimal;

@XStreamAlias("book")
public class Book {
    @XStreamAlias("id")
    private String id;
    @XStreamAlias("corpcode")
    private String corpcode;
    @XStreamAlias("bookcode")
    private String bookcode;
    @XStreamAlias("bookname")
    private String bookname;
    @XStreamAlias("fixedprice")
    private BigDecimal fixedprice;
    @XStreamAlias("bookcategory")
    private String bookcategory;
    private String bid;
    private Double unitprice;//退书发送 单价
    private Integer ndecimal;
    private Integer nwastnum;
    private Integer nhaoshunum;
    private Double nitemdiscountrate;//单品折扣
    private Double noriginalcursummny;
    private String cwarehouseid;
    private String cspaceid;
    private String ccansunkuid;
    private String ccansuspaceid;
    private Integer scrappedvol;//册数

    private String invclassname;//分类名称
    private String authorname;//作者
    private String wordno;//字数
    private Integer perpackbooks;//包册
    private String vandpno;//版本
    private String isbncode;//isbn
    private String pubdate;//出版日期
    private String kelvin;//开本
    private String vintroduction;//介绍
    private String versionno;//版次
    private String printno;//印次
    private String foreeditflag;//介绍

    private String textbooktypecode;//教材分类编码
    private String textbooktypename;//教材分类名称
    //退书相关
    private Double mayang;
    private Double shiyang;

    private String iszxx;
    private String ytsid;
    private String ytscode;
    private String ytsname;
    //发行网id
    private String fxwbids;
    private String bookid;
    private Double nprice;//退书单接受 单价

    private String visibleflag;

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public Double getNprice() {
        return nprice;
    }

    public void setNprice(Double nprice) {
        this.nprice = nprice;
    }

    public String getFxwbids() {
        return fxwbids;
    }

    public void setFxwbids(String fxwbids) {
        this.fxwbids = fxwbids;
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

    public String getBookcode() {
        return bookcode;
    }

    public void setBookcode(String bookcode) {
        this.bookcode = bookcode;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public BigDecimal getFixedprice() {
        return fixedprice;
    }

    public void setFixedprice(BigDecimal fixedprice) {
        this.fixedprice = fixedprice;
    }

    public String getBookcategory() {
        return bookcategory;
    }

    public void setBookcategory(String bookcategory) {
        this.bookcategory = bookcategory;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getNdecimal() {
        return ndecimal;
    }

    public void setNdecimal(Integer ndecimal) {
        this.ndecimal = ndecimal;
    }

    public Integer getNwastnum() {
        return nwastnum;
    }

    public void setNwastnum(Integer nwastnum) {
        this.nwastnum = nwastnum;
    }

    public Integer getNhaoshunum() {
        return nhaoshunum;
    }

    public void setNhaoshunum(Integer nhaoshunum) {
        this.nhaoshunum = nhaoshunum;
    }

    public Double getNitemdiscountrate() {
        return nitemdiscountrate;
    }

    public void setNitemdiscountrate(Double nitemdiscountrate) {
        this.nitemdiscountrate = nitemdiscountrate;
    }

    public Double getNoriginalcursummny() {
        return noriginalcursummny;
    }

    public void setNoriginalcursummny(Double noriginalcursummny) {
        this.noriginalcursummny = noriginalcursummny;
    }

    public String getCwarehouseid() {
        return cwarehouseid;
    }

    public void setCwarehouseid(String cwarehouseid) {
        this.cwarehouseid = cwarehouseid;
    }

    public String getCspaceid() {
        return cspaceid;
    }

    public void setCspaceid(String cspaceid) {
        this.cspaceid = cspaceid;
    }

    public String getCcansunkuid() {
        return ccansunkuid;
    }

    public void setCcansunkuid(String ccansunkuid) {
        this.ccansunkuid = ccansunkuid;
    }

    public String getCcansuspaceid() {
        return ccansuspaceid;
    }

    public void setCcansuspaceid(String ccansuspaceid) {
        this.ccansuspaceid = ccansuspaceid;
    }

    public Integer getScrappedvol() {
        return scrappedvol;
    }

    public void setScrappedvol(Integer scrappedvol) {
        this.scrappedvol = scrappedvol;
    }

    public String getInvclassname() {
        return invclassname;
    }

    public void setInvclassname(String invclassname) {
        this.invclassname = invclassname;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getWordno() {
        return wordno;
    }

    public void setWordno(String wordno) {
        this.wordno = wordno;
    }

    public Integer getPerpackbooks() {
        return perpackbooks;
    }

    public void setPerpackbooks(Integer perpackbooks) {
        this.perpackbooks = perpackbooks;
    }

    public String getVandpno() {
        return vandpno;
    }

    public void setVandpno(String vandpno) {
        this.vandpno = vandpno;
    }

    public String getIsbncode() {
        return isbncode;
    }

    public void setIsbncode(String isbncode) {
        this.isbncode = isbncode;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getKelvin() {
        return kelvin;
    }

    public void setKelvin(String kelvin) {
        this.kelvin = kelvin;
    }

    public String getVintroduction() {
        return vintroduction;
    }

    public void setVintroduction(String vintroduction) {
        this.vintroduction = vintroduction;
    }

    public Double getMayang() {
        return mayang;
    }

    public void setMayang(Double mayang) {
        this.mayang = mayang;
    }

    public Double getShiyang() {
        return shiyang;
    }

    public void setShiyang(Double shiyang) {
        this.shiyang = shiyang;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", corpcode='" + corpcode + '\'' +
                ", bookcode='" + bookcode + '\'' +
                ", bookname='" + bookname + '\'' +
                ", fixedprice=" + fixedprice +
                ", bookcategory='" + bookcategory + '\'' +
                '}';
    }

    public String getIszxx() {
        return iszxx;
    }

    public void setIszxx(String iszxx) {
        this.iszxx = iszxx;
    }

    public String getYtsid() {
        return ytsid;
    }

    public void setYtsid(String ytsid) {
        this.ytsid = ytsid;
    }

    public String getYtscode() {
        return ytscode;
    }

    public void setYtscode(String ytscode) {
        this.ytscode = ytscode;
    }

    public String getYtsname() {
        return ytsname;
    }

    public void setYtsname(String ytsname) {
        this.ytsname = ytsname;
    }

    public String getTextbooktypecode() {
        return textbooktypecode;
    }

    public void setTextbooktypecode(String textbooktypecode) {
        this.textbooktypecode = textbooktypecode;
    }

    public String getTextbooktypename() {
        return textbooktypename;
    }

    public void setTextbooktypename(String textbooktypename) {
        this.textbooktypename = textbooktypename;
    }

    public String getVersionno() {
        return versionno;
    }

    public void setVersionno(String versionno) {
        this.versionno = versionno;
    }

    public String getPrintno() {
        return printno;
    }

    public void setPrintno(String printno) {
        this.printno = printno;
    }

    public String getForeeditflag() {
        return foreeditflag;
    }

    public void setForeeditflag(String foreeditflag) {
        this.foreeditflag = foreeditflag;
    }

    public String getVisibleflag() {
        return visibleflag;
    }

    public void setVisibleflag(String visibleflag) {
        this.visibleflag = visibleflag;
    }
}
