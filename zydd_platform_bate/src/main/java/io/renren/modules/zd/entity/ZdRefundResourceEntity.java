package io.renren.modules.zd.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 退货详情
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@TableName("zd_refund_resource")
public class ZdRefundResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	private String refundId;
	/**
	 * 
	 */
	private String resourceId;
	/**
	 * 退货数
	 */
	@Excel(name = "退货数",orderNum = "6")
	private Integer refundNum;
	/**
	 * 实到数
	 */
	@Excel(name = "实退数",orderNum = "7")
	private Integer realNum;
	private Integer csNum;
	/**
	 * 
	 */
	@Excel(name = "定价",orderNum = "8")
	private BigDecimal refundPrice;
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
	 * nc系统状态
	 */
	private String status;
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
	@TableField(value = "enable_")
	private Integer enable;
	/**
	 * 
	 */
	@TableField(value = "remark_")
	private String remark;

	private BigDecimal nitemdiscountrate;
	private BigDecimal shiyang;
	@Excel(name = "码洋",orderNum = "9")
	private BigDecimal mayang;
	@TableField(exist = false)
	private String supplierId;

	public BigDecimal getShiyang() {
		return shiyang;
	}

	public void setShiyang(BigDecimal shiyang) {
		this.shiyang = shiyang;
	}

	public BigDecimal getMayang() {
		return mayang;
	}

	public void setMayang(BigDecimal mayang) {
		this.mayang = mayang;
	}

	public BigDecimal getNitemdiscountrate() {
		return nitemdiscountrate;
	}

	public void setNitemdiscountrate(BigDecimal nitemdiscountrate) {
		this.nitemdiscountrate = nitemdiscountrate;
	}

	/**
	 * 设置：
	 * @param id
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
	 * 设置：
	 */
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	/**
	 * 获取：
	 */
	public String getRefundId() {
		return refundId;
	}
	/**
	 * 设置：
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	/**
	 * 获取：
	 */
	public String getResourceId() {
		return resourceId;
	}
	/**
	 * 设置：退货数
	 */
	public void setRefundNum(Integer refundNum) {
		this.refundNum = refundNum;
	}
	/**
	 * 获取：退货数
	 */
	public Integer getRefundNum() {
		return refundNum;
	}
	/**
	 * 设置：实到数
	 */
	public void setRealNum(Integer realNum) {
		this.realNum = realNum;
	}
	/**
	 * 获取：实到数
	 */
	public Integer getRealNum() {
		return realNum;
	}
	/**
	 * 设置：
	 */
	public void setRefundPrice(BigDecimal refundPrice) {
		this.refundPrice = refundPrice;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getRefundPrice() {
		return refundPrice;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}


	public Integer getCsNum() {
		return csNum;
	}

	public void setCsNum(Integer csNum) {
		this.csNum = csNum;
	}
}
