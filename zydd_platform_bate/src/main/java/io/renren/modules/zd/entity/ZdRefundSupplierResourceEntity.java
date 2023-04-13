package io.renren.modules.zd.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 退货详情
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-11-26 18:46:17
 */
@TableName("zd_refund_supplier_resource")
public class ZdRefundSupplierResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 退货主单据ID
	 */
	private String refundSupplierId;
	/**
	 * 教材ID
	 */
	private String resourceId;
	/**
	 * 退货数
	 */
	@Excel(name = "退货数",orderNum = "60")
	private Integer refundNum;
	/**
	 * 实到数
	 */
	@Excel(name = "实退数",orderNum = "70")
	private Integer realNum;
	/**
	 * 退货单价
	 */
	@Excel(name = "定价",orderNum = "80")
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
	private String foreignRefundResourceId;
	/**
	 * 残损数量
	 */
	private Integer csNum;
	/**
	 * 折扣
	 */
	private BigDecimal nitemdiscountrate;
	/**
	 * 
	 */
	@Excel(name = "码洋",orderNum = "90")
	private BigDecimal mayang;
	/**
	 * 
	 */
	private BigDecimal shiyang;

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
	 * 设置：退货主单据ID
	 */
	public void setRefundSupplierId(String refundSupplierId) {
		this.refundSupplierId = refundSupplierId;
	}
	/**
	 * 获取：退货主单据ID
	 */
	public String getRefundSupplierId() {
		return refundSupplierId;
	}
	/**
	 * 设置：教材ID
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	/**
	 * 获取：教材ID
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
	 * 设置：退货单价
	 */
	public void setRefundPrice(BigDecimal refundPrice) {
		this.refundPrice = refundPrice;
	}
	/**
	 * 获取：退货单价
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
	/**
	 * 设置：nc系统状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：nc系统状态
	 */
	public String getStatus() {
		return status;
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
	/**
	 * 设置：残损数量
	 */
	public void setCsNum(Integer csNum) {
		this.csNum = csNum;
	}
	/**
	 * 获取：残损数量
	 */
	public Integer getCsNum() {
		return csNum;
	}
	/**
	 * 设置：折扣
	 */
	public void setNitemdiscountrate(BigDecimal nitemdiscountrate) {
		this.nitemdiscountrate = nitemdiscountrate;
	}
	/**
	 * 获取：折扣
	 */
	public BigDecimal getNitemdiscountrate() {
		return nitemdiscountrate;
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

	public String getForeignRefundResourceId() {
		return foreignRefundResourceId;
	}

	public void setForeignRefundResourceId(String foreignRefundResourceId) {
		this.foreignRefundResourceId = foreignRefundResourceId;
	}
}
