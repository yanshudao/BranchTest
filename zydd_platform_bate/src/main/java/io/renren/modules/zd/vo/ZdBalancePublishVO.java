package io.renren.modules.zd.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ZdBalancePublishVO {
    private String publishNo;
    private Date createTime;
    private String logisticNo;
    private Integer totalResourceNum;
    private BigDecimal totalPublish;
    private BigDecimal mayang;
    private BigDecimal shiyang;
    private String realname;
    private String fromOrgName;
    private String toOrgName;
    private String semesterName;

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

    public String getPublishNo() {
        return publishNo;
    }

    public void setPublishNo(String publishNo) {
        this.publishNo = publishNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLogisticNo() {
        return logisticNo;
    }

    public void setLogisticNo(String logisticNo) {
        this.logisticNo = logisticNo;
    }

    public Integer getTotalResourceNum() {
        return totalResourceNum;
    }

    public void setTotalResourceNum(Integer totalResourceNum) {
        this.totalResourceNum = totalResourceNum;
    }

    public BigDecimal getTotalPublish() {
        return totalPublish;
    }

    public void setTotalPublish(BigDecimal totalPublish) {
        this.totalPublish = totalPublish;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getFromOrgName() {
        return fromOrgName;
    }

    public void setFromOrgName(String fromOrgName) {
        this.fromOrgName = fromOrgName;
    }

    public String getToOrgName() {
        return toOrgName;
    }

    public void setToOrgName(String toOrgName) {
        this.toOrgName = toOrgName;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }
}
