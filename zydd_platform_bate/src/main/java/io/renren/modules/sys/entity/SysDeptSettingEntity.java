package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 报订设置
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-29 22:09:37
 */
@TableName("sys_dept_setting")
public class SysDeptSettingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 配置单位code
	 */
	private String orgCode;
	/**
	 * 上级单位ID
	 */
	private String toOrgCode;
	/**
	 * 
	 */
	private String createBy;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private String updateBy;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 报订方式 1 按照课程 2 按照资源
	 */
	private String orderType;
	/**
	 * 发行折扣
	 */
	private BigDecimal publishDiscount;
	/**
	 * 结算折扣
	 */
	private BigDecimal payDiscount;
	/**
	 * 结算单位
	 */
	private String payOrgCode;
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
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 邮政编码
	 */
	private String zipCode;
	/**
	 * 联系人
	 */
	private String person;
	/**
	 * 联系电话
	 */
	private String telPhone;

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
	 * 设置：配置单位code
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：配置单位code
	 */
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * 设置：上级单位ID
	 */
	public void setToOrgCode(String toOrgCode) {
		this.toOrgCode = toOrgCode;
	}
	/**
	 * 获取：上级单位ID
	 */
	public String getToOrgCode() {
		return toOrgCode;
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
	 * 设置：报订方式 1 按照课程 2 按照资源
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * 获取：报订方式 1 按照课程 2 按照资源
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * 设置：发行折扣
	 */
	public void setPublishDiscount(BigDecimal publishDiscount) {
		this.publishDiscount = publishDiscount;
	}
	/**
	 * 获取：发行折扣
	 */
	public BigDecimal getPublishDiscount() {
		return publishDiscount;
	}
	/**
	 * 设置：结算折扣
	 */
	public void setPayDiscount(BigDecimal payDiscount) {
		this.payDiscount = payDiscount;
	}
	/**
	 * 获取：结算折扣
	 */
	public BigDecimal getPayDiscount() {
		return payDiscount;
	}
	/**
	 * 设置：结算单位
	 */
	public void setPayOrgCode(String payOrgCode) {
		this.payOrgCode = payOrgCode;
	}
	/**
	 * 获取：结算单位
	 */
	public String getPayOrgCode() {
		return payOrgCode;
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
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：邮政编码
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * 获取：邮政编码
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * 设置：联系人
	 */
	public void setPerson(String person) {
		this.person = person;
	}
	/**
	 * 获取：联系人
	 */
	public String getPerson() {
		return person;
	}
	/**
	 * 设置：联系电话
	 */
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getTelPhone() {
		return telPhone;
	}
}
