package io.renren.modules.zd.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 库存
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@TableName("zd_stock")
public class ZdStockEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	private String orgCode;
	/**
	 * 资源ID
	 */
	private String resourceId;
	/**
	 * 总数
	 */
	private Integer totalAmount;
	/**
	 * 锁定数量
	 */
	private Integer lockAmount;
	/**
	 * 在运数量
	 */
	private Integer onwayAmount;
	/**
	 * 单价
	 */
	private BigDecimal cost;
	/**
	 * 
	 */
	private Date createDate;
	/**
	 * 
	 */
	private String createBy;
	/**
	 * 
	 */
	private Date updateDate;
	/**
	 * 
	 */
	private String updateBy;
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

	@Version
	private Integer version;

	@TableField(exist = false)
	private String recordType;
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
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：
	 */
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * 设置：资源ID
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	/**
	 * 获取：资源ID
	 */
	public String getResourceId() {
		return resourceId;
	}
	/**
	 * 设置：总数
	 */
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 获取：总数
	 */
	public Integer getTotalAmount() {
		return totalAmount;
	}
	/**
	 * 设置：锁定数量
	 */
	public void setLockAmount(Integer lockAmount) {
		this.lockAmount = lockAmount;
	}
	/**
	 * 获取：锁定数量
	 */
	public Integer getLockAmount() {
		return lockAmount;
	}
	/**
	 * 设置：在运数量
	 */
	public void setOnwayAmount(Integer onwayAmount) {
		this.onwayAmount = onwayAmount;
	}
	/**
	 * 获取：在运数量
	 */
	public Integer getOnwayAmount() {
		return onwayAmount;
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
	 * 设置：
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：
	 */
	public Date getCreateDate() {
		return createDate;
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
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateDate() {
		return updateDate;
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



	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
