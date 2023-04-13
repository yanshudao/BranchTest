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
 * 征订主表
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@TableName("zd_order")
public class ZdOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 报订单号
	 */
	private String orderNo;
	/**
	 * 单据名称
	 */
	private String orderName;
	/**
	 * 征订单位
	 */
	private String fromOrgCode;
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
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String updateBy;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	/**
	 * 删除状态
	 */
	private String delFlag;
	/**
	 * 订单状态
	 */
	private String status;
	/**
	 * 报订季节
	 */
	private String semesterCode;
	/**
	 * 报订码洋
	 */
	private BigDecimal orderPrice;
	/**
	 * 单位代码
	 */
	@TableField(fill = FieldFill.INSERT)
	private String orgCode;
	@TableField(exist = false)
	private String resourceCount;
	/**
	 * 
	 */
	private BigDecimal mayang;
	/**
	 * 
	 */
	private BigDecimal shiyang;
	/**
	 * 上报单位
	 */
	private String toOrgCode;
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
	private String note;
	private String confirmBy;
	private String completeBy;
	private Date confirmTime;
	private Date completeTime;

	public String getConfirmBy() {
		return confirmBy;
	}

	public void setConfirmBy(String confirmBy) {
		this.confirmBy = confirmBy;
	}

	public String getCompleteBy() {
		return completeBy;
	}

	public void setCompleteBy(String completeBy) {
		this.completeBy = completeBy;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
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
	 * 设置：报订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：报订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：单据名称
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	/**
	 * 获取：单据名称
	 */
	public String getOrderName() {
		return orderName;
	}
	/**
	 * 设置：征订单位
	 */
	public void setFromOrgCode(String fromOrgCode) {
		this.fromOrgCode = fromOrgCode;
	}
	/**
	 * 获取：征订单位
	 */
	public String getFromOrgCode() {
		return fromOrgCode;
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
	 * 设置：订单状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：订单状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：报订季节
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
	/**
	 * 获取：报订季节
	 */
	public String getSemesterCode() {
		return semesterCode;
	}
	/**
	 * 设置：报订码洋
	 */
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	/**
	 * 获取：报订码洋
	 */
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	/**
	 * 设置：单位代码
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：单位代码
	 */
	public String getOrgCode() {
		return orgCode;
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
	 * 设置：上报单位
	 */
	public void setToOrgCode(String toOrgCode) {
		this.toOrgCode = toOrgCode;
	}
	/**
	 * 获取：上报单位
	 */
	public String getToOrgCode() {
		return toOrgCode;
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

	public String getResourceCount() {
		return resourceCount;
	}

	public void setResourceCount(String resourceCount) {
		this.resourceCount = resourceCount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
