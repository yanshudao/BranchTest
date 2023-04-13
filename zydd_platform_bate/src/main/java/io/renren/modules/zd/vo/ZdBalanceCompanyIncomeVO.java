package io.renren.modules.zd.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ZdBalanceCompanyIncomeVO {
    private String incomeSn;
    private String incomeName;
    private String carrierno;
    private String semesterCode;
    private Date sendDate;
    private BigDecimal mayang;
    private BigDecimal shiyang;
    private String realname;
    private Date createTime;
    private Integer totalRealNum;
    private Integer kindNum;
    private List<Map<String,Object>> list;

    public Integer getKindNum() {
        return kindNum;
    }

    public void setKindNum(Integer kindNum) {
        this.kindNum = kindNum;
    }

    public String getIncomeSn() {
        return incomeSn;
    }

    public void setIncomeSn(String incomeSn) {
        this.incomeSn = incomeSn;
    }

    public BigDecimal getMayang() {
        return mayang;
    }

    public void setMayang(BigDecimal mayang) {
        this.mayang = mayang;
    }

    public BigDecimal getShiyang() {
        return shiyang;
    }

    public void setShiyang(BigDecimal shiyang) {
        this.shiyang = shiyang;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTotalRealNum() {
        return totalRealNum;
    }

    public void setTotalRealNum(Integer totalRealNum) {
        this.totalRealNum = totalRealNum;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public String getCarrierno() {
        return carrierno;
    }

    public void setCarrierno(String carrierno) {
        this.carrierno = carrierno;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
}
