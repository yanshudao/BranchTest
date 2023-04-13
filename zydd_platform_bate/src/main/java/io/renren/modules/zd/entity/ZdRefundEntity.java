package io.renren.modules.zd.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 退货主单据
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@TableName("zd_refund")
public class ZdRefundEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */

	@TableId(value = "id_", type = IdType.UUID)
	private String id;


	/**
	 * 退货单编号
	 */
	private String refundCode;
	/**
	 * 单位CODE
	 */
	@TableField(fill = FieldFill.INSERT)
	private String orgCode;
	/**
	 * 采购商ID
	 */
	private String supplierId;
	/**
	 * 0 采购退货 1 发行退货
	 */
	private String refundType;
	/**
	 * 退货单据名称
	 */
	private String refundName;
	/**
	 * 1 新建 2 提交 3审核通过
	 */
	private String status;
	/**
	 * 学期代码
	 */
	private String semesterCode;
	/**
	 * 创建人
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
	 * 
	 */
	private String isSync;
	/**
	 * 物流方式
	 */
	private String logisticType;
	/**
	 * 物流单号
	 */
	private String logisticNo;
	/**
	 * 物流公司
	 */
	private String logisticCompany;
	/**
	 * 物流包个数
	 */
	private String logisticBag;
	private String logisticAddress;
	private String logisticPerson;
	private String logisticTelphone;
	/**
	 * 删除状态
	 */
	private String delFlag;
	/**
	 * 
	 */
	@TableField(value = "enable_")
	private Integer enable;
	/**
	 * 
	 */
	@TableField(value = "remark_")
	private String remark;

	private String fromOrgCode;

	private String toOrgCode;

	private String result;
	private String reason;
	private String erpcode;
	private BigDecimal shiyang;
	private Double avgdiscount;
	private String ncStatus;

	private String completeBy;
	private Date completeTime;
	private String confirmBy;
	private Date confirmTime;

	private String shippingAddress;
	private String shippingPerson;
	private String shippingPhone;
	private String auditBy;
	private String supplierRefundId;
	private String refundSemesterCode;
	private Date auditTime;

	public String getSupplierRefundId() {
		return supplierRefundId;
	}

	public void setSupplierRefundId(String supplierRefundId) {
		this.supplierRefundId = supplierRefundId;
	}

	public String getLogisticPerson() {
		return logisticPerson;
	}

	public void setLogisticPerson(String logisticPerson) {
		this.logisticPerson = logisticPerson;
	}

	public String getLogisticTelphone() {
		return logisticTelphone;
	}

	public void setLogisticTelphone(String logisticTelphone) {
		this.logisticTelphone = logisticTelphone;
	}

	public String getNcStatus() {
		return ncStatus;
	}

	public void setNcStatus(String ncStatus) {
		this.ncStatus = ncStatus;
	}

	public Double getAvgdiscount() {
		return avgdiscount;
	}

	public void setAvgdiscount(Double avgdiscount) {
		this.avgdiscount = avgdiscount;
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
	 * 设置：退货单编号
	 */
	public void setRefundCode(String refundCode) {
		this.refundCode = refundCode;
	}
	/**
	 * 获取：退货单编号
	 */
	public String getRefundCode() {
		return refundCode;
	}
	/**
	 * 设置：单位CODE
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：单位CODE
	 */
	public String getOrgCode() {
		return orgCode;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * 设置：0 采购退货 1 发行退货
	 */
	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}
	/**
	 * 获取：0 采购退货 1 发行退货
	 */
	public String getRefundType() {
		return refundType;
	}

	/**
	 * 设置：1 新建 2 提交 3审核通过
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：1 新建 2 提交 3审核通过
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：学期代码
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
	/**
	 * 获取：学期代码
	 */
	public String getSemesterCode() {
		return semesterCode;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
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

	public String getIsSync() {
		return isSync;
	}

	public void setIsSync(String isSync) {
		this.isSync = isSync;
	}

	/**
	 * 设置：物流方式
	 */
	public void setLogisticType(String logisticType) {
		this.logisticType = logisticType;
	}
	/**
	 * 获取：物流方式
	 */
	public String getLogisticType() {
		return logisticType;
	}
	/**
	 * 设置：物流单号
	 */
	public void setLogisticNo(String logisticNo) {
		this.logisticNo = logisticNo;
	}
	/**
	 * 获取：物流单号
	 */
	public String getLogisticNo() {
		return logisticNo;
	}
	/**
	 * 设置：物流公司
	 */
	public void setLogisticCompany(String logisticCompany) {
		this.logisticCompany = logisticCompany;
	}
	/**
	 * 获取：物流公司
	 */
	public String getLogisticCompany() {
		return logisticCompany;
	}
	/**
	 * 设置：物流包个数
	 */
	public void setLogisticBag(String logisticBag) {
		this.logisticBag = logisticBag;
	}
	/**
	 * 获取：物流包个数
	 */
	public String getLogisticBag() {
		return logisticBag;
	}
	/**
	 * 设置：删除状态
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：删除状态
	 */
	public String getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置：
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	/**
	 * 获取：
	 */
	public Integer getEnable() {
		return enable;
	}
	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}

	public String getFromOrgCode() {
		return fromOrgCode;
	}

	public void setFromOrgCode(String fromOrgCode) {
		this.fromOrgCode = fromOrgCode;
	}

	public String getToOrgCode() {
		return toOrgCode;
	}

	public void setToOrgCode(String toOrgCode) {
		this.toOrgCode = toOrgCode;
	}

	public String getLogisticAddress() {
		return logisticAddress;
	}

	public void setLogisticAddress(String logisticAddress) {
		this.logisticAddress = logisticAddress;
	}

	public String getRefundName() {
		return refundName;
	}

	public void setRefundName(String refundName) {
		this.refundName = refundName;
	}

	public String getErpcode() {
		return erpcode;
	}

	public void setErpcode(String erpcode) {
		this.erpcode = erpcode;
	}

	public BigDecimal getShiyang() {
		return shiyang;
	}

	public void setShiyang(BigDecimal shiyang) {
		this.shiyang = shiyang;
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

	public String getConfirmBy() {
		return confirmBy;
	}

	public void setConfirmBy(String confirmBy) {
		this.confirmBy = confirmBy;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingPerson() {
		return shippingPerson;
	}

	public void setShippingPerson(String shippingPerson) {
		this.shippingPerson = shippingPerson;
	}

	public String getShippingPhone() {
		return shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public String getAuditBy() {
		return auditBy;
	}

	public void setAuditBy(String auditBy) {
		this.auditBy = auditBy;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getRefundSemesterCode() {
		return refundSemesterCode;
	}

	public void setRefundSemesterCode(String refundSemesterCode) {
		this.refundSemesterCode = refundSemesterCode;
	}
}
