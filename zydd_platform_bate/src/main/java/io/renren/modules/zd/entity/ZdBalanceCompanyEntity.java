package io.renren.modules.zd.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-10 09:18:28
 */
@TableName("zd_balance_company")
public class ZdBalanceCompanyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	private String balanceCompanyNo;
	/**
	 * 
	 */
	private String semesterCode;
	/**
	 * 
	 */
	private Integer discountRate;
	/**
	 * 
	 */
	private BigDecimal incomePriceTotal;
	private BigDecimal incomePriceShiyang;
	/**
	 * 
	 */
	private BigDecimal refundPriceTotal;
	private BigDecimal refundPriceShiyang;
	/**
	 * 
	 */
	private BigDecimal mayang;
	/**
	 * 
	 */
	private BigDecimal shiyang;
	/**
	 * 
	 */
	private String balanceStatus;
	/**
	 * 
	 */
	private String auditStatus;

	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createBy;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.UPDATE)
	private String updateBy;
	private String toOrgCode;
	private String orgCode;
	private String companyId;
	@TableField(exist = false)
	private String orgName;
	@TableField(exist = false)
	private String companyName;

	public BigDecimal getIncomePriceShiyang() {
		return incomePriceShiyang;
	}

	public void setIncomePriceShiyang(BigDecimal incomePriceShiyang) {
		this.incomePriceShiyang = incomePriceShiyang;
	}

	public BigDecimal getRefundPriceShiyang() {
		return refundPriceShiyang;
	}

	public void setRefundPriceShiyang(BigDecimal refundPriceShiyang) {
		this.refundPriceShiyang = refundPriceShiyang;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 设置：
	 */

	public void setBalanceCompanyNo(String balanceCompanyNo) {
		this.balanceCompanyNo = balanceCompanyNo;
	}
	/**
	 * 获取：
	 */
	public String getBalanceCompanyNo() {
		return balanceCompanyNo;
	}
	/**
	 * 设置：
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
	/**
	 * 获取：
	 */
	public String getSemesterCode() {
		return semesterCode;
	}
	/**
	 * 设置：
	 */
	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}
	/**
	 * 获取：
	 */
	public Integer getDiscountRate() {
		return discountRate;
	}
	/**
	 * 设置：
	 */
	public void setIncomePriceTotal(BigDecimal incomePriceTotal) {
		this.incomePriceTotal = incomePriceTotal;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getIncomePriceTotal() {
		return incomePriceTotal;
	}
	/**
	 * 设置：
	 */
	public void setRefundPriceTotal(BigDecimal refundPriceTotal) {
		this.refundPriceTotal = refundPriceTotal;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getRefundPriceTotal() {
		return refundPriceTotal;
	}
	/**
	 * 设置：
	 */
	public void setMayang(BigDecimal mayang) {
		this.mayang = mayang;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getMayang() {
		return mayang;
	}
	/**
	 * 设置：
	 */
	public void setShiyang(BigDecimal shiyang) {
		this.shiyang = shiyang;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getShiyang() {
		return shiyang;
	}
	/**
	 * 设置：
	 */
	public void setBalanceStatus(String balanceStatus) {
		this.balanceStatus = balanceStatus;
	}
	/**
	 * 获取：
	 */
	public String getBalanceStatus() {
		return balanceStatus;
	}
	/**
	 * 设置：
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * 获取：
	 */
	public String getAuditStatus() {
		return auditStatus;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	public String getToOrgCode() {
		return toOrgCode;
	}

	public void setToOrgCode(String toOrgCode) {
		this.toOrgCode = toOrgCode;
	}
}
