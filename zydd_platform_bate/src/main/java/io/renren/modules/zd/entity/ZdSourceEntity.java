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
 * 采购主表
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@TableName("zd_source")
public class ZdSourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 采购单号
	 */
	private String sourceNo;
	/**
	 * 单据名称
	 */
	private String sourceName;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createBy;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.UPDATE)
	private String updateBy;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;
	/**
	 * 单位ID
	 */
	private String orgCode;
	/**
	 * 当前季节
	 */
	private String semesterCode;
	/**
	 * 备注
	 */
	private String remarks;
	private String note;
	/**
	 * (1日常 2 报订)
	 */
	private String sourceType;
	/**
	 * 采购码洋
	 */
	private Double sourcePrice;
	/**
	 * 
	 */
	private BigDecimal mayang;
	/**
	 * 
	 */
	private BigDecimal shiyang;
	/**
	 * NC系统ID
	 */
	private String foreignId;

	private String supplierId;

	private String status;

	private String completeBy;
	private String isSync;
	private Date completeTime;

	private String toOrgCode;
	private String result;
	private String reason;
	private String erpcode;
	private String telephone;
	private String linkman;
	private String address;
	private String submitUser;

	private Date submitDate;
	private String zipCode;
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
	/**
	 * 设置：采购单号
	 */
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	/**
	 * 获取：采购单号
	 */
	public String getSourceNo() {
		return sourceNo;
	}
	/**
	 * 设置：单据名称
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	/**
	 * 获取：单据名称
	 */
	public String getSourceName() {
		return sourceName;
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
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：
	 */
	public String getUpdateBy() {
		return updateBy;
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
	 * 设置：单位ID
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：单位ID
	 */
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * 设置：当前季节
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
	/**
	 * 获取：当前季节
	 */
	public String getSemesterCode() {
		return semesterCode;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：(1日常 2 报订)
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	/**
	 * 获取：(1日常 2 报订)
	 */
	public String getSourceType() {
		return sourceType;
	}
	/**
	 * 设置：采购码洋
	 */
	public void setSourcePrice(Double sourcePrice) {
		this.sourcePrice = sourcePrice;
	}
	/**
	 * 获取：采购码洋
	 */
	public Double getSourcePrice() {
		return sourcePrice;
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
	 * 设置：NC系统ID
	 */
	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}
	/**
	 * 获取：NC系统ID
	 */
	public String getForeignId() {
		return foreignId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCompleteBy() {
		return completeBy;
	}

	public void setCompleteBy(String completeBy) {
		this.completeBy = completeBy;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getIsSync() {
		return isSync;
	}

	public void setIsSync(String isSync) {
		this.isSync = isSync;
	}

	public String getToOrgCode() {
		return toOrgCode;
	}

	public void setToOrgCode(String toOrgCode) {
		this.toOrgCode = toOrgCode;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public String getSubmitUser() {
		return submitUser;
	}

	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
