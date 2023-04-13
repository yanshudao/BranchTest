package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 报订设置
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:15:51
 */
@TableName("sys_org_setting")
public class SysOrgSettingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
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
	@TableField(fill = FieldFill.UPDATE)
	private String updateBy;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.UPDATE)
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
	//客商代码
	private String custCode;
	//制单员
	private String usercode;
	//业务员
	private String salesmancode;
	private BigDecimal allowRefundPercent;
	@TableField(exist = false)
	private String orgName;
	@TableField(exist = false)
	private String toOrgId;

	private Integer isConfigRate1;
	private Integer isConfigRate2;
	private Integer isConfigRate3;
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

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getSalesmancode() {
		return salesmancode;
	}

	public void setSalesmancode(String salesmancode) {
		this.salesmancode = salesmancode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public BigDecimal getAllowRefundPercent() {
		return allowRefundPercent;
	}

	public void setAllowRefundPercent(BigDecimal allowRefundPercent) {
		this.allowRefundPercent = allowRefundPercent;
	}

	public String getToOrgId() {
		return toOrgId;
	}

	public void setToOrgId(String toOrgId) {
		this.toOrgId = toOrgId;
	}

	public Integer getIsConfigRate1() {
		return isConfigRate1;
	}

	public void setIsConfigRate1(Integer isConfigRate1) {
		this.isConfigRate1 = isConfigRate1;
	}

	public Integer getIsConfigRate2() {
		return isConfigRate2;
	}

	public void setIsConfigRate2(Integer isConfigRate2) {
		this.isConfigRate2 = isConfigRate2;
	}

	public Integer getIsConfigRate3() {
		return isConfigRate3;
	}

	public void setIsConfigRate3(Integer isConfigRate3) {
		this.isConfigRate3 = isConfigRate3;
	}
}
