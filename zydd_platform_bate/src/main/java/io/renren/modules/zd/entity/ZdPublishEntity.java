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
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
@TableName("zd_publish")
public class ZdPublishEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private String orgCode;
	/**
	 * 来源单位
	 */
	private String fromOrgCode;
	/**
	 * 目标单位
	 */
	private String toOrgCode;
	/**
	 * 创建单位
	 */
	private String publishNo;
	/**
	 * 单据名称
	 */
	private String publishName;
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
	private BigDecimal mayang;
	/**
	 * 
	 */
	private BigDecimal shiyang;
	/**
	 * 
	 */
	private String status;
	/**
	 * 
	 */
	private String delFlag;
	/**
	 * 
	 */
	private String semesterCode;
	/**
	 * 0报订发行 1日常发行
	 */
	private String publishType;
	/**
	 * 物流方式
	 */
	private String logisticType;
	/**
	 * 物流单号
	 */
	private String logisticNo;
	/**
	 * 物流单位
	 */
	private String logisticCompany;
	/**
	 * 物流包数量
	 */
	private String logisticBag;
	private String logisticAddress;
	private String logisticPerson;
	private String logisticTelphone;
	private String foreignId;
	//income 入库单
	private String sourceType;
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
	 * 设置：来源单位
	 */
	public void setFromOrgCode(String fromOrgCode) {
		this.fromOrgCode = fromOrgCode;
	}
	/**
	 * 获取：来源单位
	 */
	public String getFromOrgCode() {
		return fromOrgCode;
	}
	/**
	 * 设置：目标单位
	 */
	public void setToOrgCode(String toOrgCode) {
		this.toOrgCode = toOrgCode;
	}
	/**
	 * 获取：目标单位
	 */
	public String getToOrgCode() {
		return toOrgCode;
	}
	/**
	 * 设置：创建单位
	 */

	public String getPublishNo() {
		return publishNo;
	}

	public void setPublishNo(String publishNo) {
		this.publishNo = publishNo;
	}

	public String getPublishName() {
		return publishName;
	}

	public void setPublishName(String publishName) {
		this.publishName = publishName;
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
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：
	 */
	public String getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置：
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
	/**
	 * 获取：
	 */
	public String getSemesterCode() {
		return semesterCode;
	}
	/**
	 * 设置：0报订发行 1日常发行
	 */
	public void setPublishType(String publishType) {
		this.publishType = publishType;
	}
	/**
	 * 获取：0报订发行 1日常发行
	 */
	public String getPublishType() {
		return publishType;
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
	 * 设置：物流单位
	 */
	public void setLogisticCompany(String logisticCompany) {
		this.logisticCompany = logisticCompany;
	}
	/**
	 * 获取：物流单位
	 */
	public String getLogisticCompany() {
		return logisticCompany;
	}
	/**
	 * 设置：物流包数量
	 */
	public void setLogisticBag(String logisticBag) {
		this.logisticBag = logisticBag;
	}
	/**
	 * 获取：物流包数量
	 */
	public String getLogisticBag() {
		return logisticBag;
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

	public String getLogisticAddress() {
		return logisticAddress;
	}

	public void setLogisticAddress(String logisticAddress) {
		this.logisticAddress = logisticAddress;
	}

	public String getForeignId() {
		return foreignId;
	}

	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
}
