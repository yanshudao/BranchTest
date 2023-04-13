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
 * 退货主单据
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-11-26 18:46:17
 */
@TableName("zd_refund_supplier")
public class ZdRefundSupplierEntity implements Serializable {
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
	 * 1 新建 2 上报 3确认 4 已结算 6审核通过 5退回
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
	 * NC单据状态
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
	/**
	 * 删除状态
	 */
	private String delFlag;

	@TableField("enable_")
	private Integer enable;
	/**
	 *
	 */
	@TableField("remark_")
	private String remark;
	/**
	 * 
	 */
	private String fromOrgCode;
	/**
	 * 
	 */
	private String toOrgCode;
	/**
	 * 物流地址
	 */
	private String logisticAddress;
	/**
	 * 
	 */
	private String result;
	/**
	 * 
	 */
	private String reason;
	/**
	 * 
	 */
	private String erpcode;
	/**
	 * 
	 */
	private BigDecimal shiyang;
	/**
	 * 平均折扣
	 */
	private BigDecimal avgdiscount;
	/**
	 * 0新建 3 NC 退货回告
	 */
	private String ncStatus;
	/**
	 * 
	 */
	private String logisticPerson;
	/**
	 * 
	 */
	private String logisticTelphone;
	/**
	 * 
	 */
	private String completeBy;
	/**
	 * 
	 */
	private Date completeTime;
	/**
	 * 
	 */
	private String confirmBy;
	/**
	 * 
	 */
	private Date confirmTime;
	/**
	 * 收货地址
	 */
	private String shippingAddress;
	/**
	 * 
	 */
	private String shippingPhone;
	/**
	 * 
	 */
	private String shippingPerson;
	private String foreignRefundId;
	private String countyOrgCode;
	private String refundSemesterCode;

	public String getCountyOrgCode() {
		return countyOrgCode;
	}

	public void setCountyOrgCode(String countyOrgCode) {
		this.countyOrgCode = countyOrgCode;
	}

	public String getForeignRefundId() {
		return foreignRefundId;
	}

	public void setForeignRefundId(String foreignRefundId) {
		this.foreignRefundId = foreignRefundId;
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
	/**
	 * 设置：采购商ID
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：采购商ID
	 */
	public String getSupplierId() {
		return supplierId;
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
	 * 设置：退货单据名称
	 */
	public void setRefundName(String refundName) {
		this.refundName = refundName;
	}
	/**
	 * 获取：退货单据名称
	 */
	public String getRefundName() {
		return refundName;
	}
	/**
	 * 设置：1 新建 2 上报 3确认 4 已结算 6审核通过 5退回
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：1 新建 2 上报 3确认 4 已结算 6审核通过 5退回
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
	/**
	 * 设置：NC单据状态
	 */
	public void setIsSync(String isSync) {
		this.isSync = isSync;
	}
	/**
	 * 获取：NC单据状态
	 */
	public String getIsSync() {
		return isSync;
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
	 * 设置：控制省级是否可见的字段。0：不可见；1：可见
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	/**
	 * 获取：控制省级是否可见的字段。0：不可见；1：可见
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
	/**
	 * 设置：
	 */
	public void setFromOrgCode(String fromOrgCode) {
		this.fromOrgCode = fromOrgCode;
	}
	/**
	 * 获取：
	 */
	public String getFromOrgCode() {
		return fromOrgCode;
	}
	/**
	 * 设置：
	 */
	public void setToOrgCode(String toOrgCode) {
		this.toOrgCode = toOrgCode;
	}
	/**
	 * 获取：
	 */
	public String getToOrgCode() {
		return toOrgCode;
	}
	/**
	 * 设置：物流地址
	 */
	public void setLogisticAddress(String logisticAddress) {
		this.logisticAddress = logisticAddress;
	}
	/**
	 * 获取：物流地址
	 */
	public String getLogisticAddress() {
		return logisticAddress;
	}
	/**
	 * 设置：
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * 获取：
	 */
	public String getResult() {
		return result;
	}
	/**
	 * 设置：
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 获取：
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * 设置：
	 */
	public void setErpcode(String erpcode) {
		this.erpcode = erpcode;
	}
	/**
	 * 获取：
	 */
	public String getErpcode() {
		return erpcode;
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
	 * 设置：平均折扣
	 */
	public void setAvgdiscount(BigDecimal avgdiscount) {
		this.avgdiscount = avgdiscount;
	}
	/**
	 * 获取：平均折扣
	 */
	public BigDecimal getAvgdiscount() {
		return avgdiscount;
	}
	/**
	 * 设置：0新建 3 NC 退货回告
	 */
	public void setNcStatus(String ncStatus) {
		this.ncStatus = ncStatus;
	}
	/**
	 * 获取：0新建 3 NC 退货回告
	 */
	public String getNcStatus() {
		return ncStatus;
	}
	/**
	 * 设置：
	 */
	public void setLogisticPerson(String logisticPerson) {
		this.logisticPerson = logisticPerson;
	}
	/**
	 * 获取：
	 */
	public String getLogisticPerson() {
		return logisticPerson;
	}
	/**
	 * 设置：
	 */
	public void setLogisticTelphone(String logisticTelphone) {
		this.logisticTelphone = logisticTelphone;
	}
	/**
	 * 获取：
	 */
	public String getLogisticTelphone() {
		return logisticTelphone;
	}
	/**
	 * 设置：
	 */
	public void setCompleteBy(String completeBy) {
		this.completeBy = completeBy;
	}
	/**
	 * 获取：
	 */
	public String getCompleteBy() {
		return completeBy;
	}
	/**
	 * 设置：
	 */
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	/**
	 * 获取：
	 */
	public Date getCompleteTime() {
		return completeTime;
	}
	/**
	 * 设置：
	 */
	public void setConfirmBy(String confirmBy) {
		this.confirmBy = confirmBy;
	}
	/**
	 * 获取：
	 */
	public String getConfirmBy() {
		return confirmBy;
	}
	/**
	 * 设置：
	 */
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	/**
	 * 获取：
	 */
	public Date getConfirmTime() {
		return confirmTime;
	}
	/**
	 * 设置：收货地址
	 */
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	/**
	 * 获取：收货地址
	 */
	public String getShippingAddress() {
		return shippingAddress;
	}
	/**
	 * 设置：
	 */
	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}
	/**
	 * 获取：
	 */
	public String getShippingPhone() {
		return shippingPhone;
	}
	/**
	 * 设置：
	 */
	public void setShippingPerson(String shippingPerson) {
		this.shippingPerson = shippingPerson;
	}
	/**
	 * 获取：
	 */
	public String getShippingPerson() {
		return shippingPerson;
	}

	public String getRefundSemesterCode() {
		return refundSemesterCode;
	}

	public void setRefundSemesterCode(String refundSemesterCode) {
		this.refundSemesterCode = refundSemesterCode;
	}
}
