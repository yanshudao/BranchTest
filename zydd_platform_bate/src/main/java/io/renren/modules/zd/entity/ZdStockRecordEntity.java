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
 * 库存变化明细
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
@TableName("zd_stock_record")
public class ZdStockRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	private String stockId;
	/**
	 * 
	 */
	private String resourceId;
	/**
	 * 数量
	 */
	private Integer amount;
	/**
	 * 操作类型
	 */
	private String recordType;
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
	 * 单价
	 */
	private BigDecimal cost;
	/**
	 * 学期代码
	 */
	private String semesterCode;
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
	@TableField("enable_")
	private Integer enable;
	/**
	 * 
	 */
	@TableField("remark_")
	private String remark;

	private Integer beforeAmount;
	private Integer afterAmount;

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
	 * 设置：
	 */
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	/**
	 * 获取：
	 */
	public String getStockId() {
		return stockId;
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
	 * 设置：数量
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	/**
	 * 获取：数量
	 */
	public Integer getAmount() {
		return amount;
	}
	/**
	 * 设置：操作类型
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	/**
	 * 获取：操作类型
	 */
	public String getRecordType() {
		return recordType;
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
	 * 设置：单价
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	/**
	 * 获取：单价
	 */
	public BigDecimal getCost() {
		return cost;
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

	public Integer getBeforeAmount() {
		return beforeAmount;
	}

	public void setBeforeAmount(Integer beforeAmount) {
		this.beforeAmount = beforeAmount;
	}

	public Integer getAfterAmount() {
		return afterAmount;
	}

	public void setAfterAmount(Integer afterAmount) {
		this.afterAmount = afterAmount;
	}
}
