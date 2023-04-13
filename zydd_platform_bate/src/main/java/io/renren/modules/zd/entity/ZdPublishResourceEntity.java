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
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@TableName("zd_publish_resource")
public class ZdPublishResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	private String publishId;
	/**
	 * 
	 */
	private String resourceId;
	/**
	 * 发行价格
	 */
	private BigDecimal publishPrice;
	/**
	 * 发行数量
	 */
	private Integer publishNum;
	/**
	 * 
	 */
	private BigDecimal mayng;
	/**
	 * 
	 */
	private BigDecimal shiyang;
	/**
	 * 
	 */
	private Integer realNum;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
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
	/**
	 * 
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;
	/**
	 * 
	 */
	private Integer enable;
	/**
	 * 
	 */
	private String remark;
	private BigDecimal nitemdiscountrate;

	public BigDecimal getNitemdiscountrate() {
		return nitemdiscountrate;
	}

	public void setNitemdiscountrate(BigDecimal nitemdiscountrate) {
		this.nitemdiscountrate = nitemdiscountrate;
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
	 * 设置：
	 */
	public void setPublishId(String publishId) {
		this.publishId = publishId;
	}
	/**
	 * 获取：
	 */
	public String getPublishId() {
		return publishId;
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
	 * 设置：发行价格
	 */
	public void setPublishPrice(BigDecimal publishPrice) {
		this.publishPrice = publishPrice;
	}
	/**
	 * 获取：发行价格
	 */
	public BigDecimal getPublishPrice() {
		return publishPrice;
	}
	/**
	 * 设置：发行数量
	 */
	public void setPublishNum(Integer publishNum) {
		this.publishNum = publishNum;
	}
	/**
	 * 获取：发行数量
	 */
	public Integer getPublishNum() {
		return publishNum;
	}
	/**
	 * 设置：
	 */
	public void setMayng(BigDecimal mayng) {
		this.mayng = mayng;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getMayng() {
		return mayng;
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
	public void setRealNum(Integer realNum) {
		this.realNum = realNum;
	}
	/**
	 * 获取：
	 */
	public Integer getRealNum() {
		return realNum;
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
}
