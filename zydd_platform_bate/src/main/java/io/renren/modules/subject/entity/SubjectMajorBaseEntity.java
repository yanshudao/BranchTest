package io.renren.modules.subject.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 专业
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@TableName("subject_major_base")
public class SubjectMajorBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 专业名称
	 */
	@Excel(name = "教材名称",orderNum="10")
	private String majorName;
	/**
	 * 专业代码
	 */
	@Excel(name = "教材代码",orderNum="20")
	private String majorCode;

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
	private Date updateTime;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.UPDATE)
	private String updateBy;
	/**
	 * 外部系统唯一主键
	 */
	private String foreignId;
	/**
	 * 
	 */
	private Integer version;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private String orgCode;
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

	@TableField(exist = false)
	private String orgName;

	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：专业名称
	 */
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	/**
	 * 获取：专业名称
	 */
	public String getMajorName() {
		return majorName;
	}
	/**
	 * 设置：专业代码
	 */
	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}
	/**
	 * 获取：专业代码
	 */
	public String getMajorCode() {
		return majorCode;
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
	 * 设置：外部系统唯一主键
	 */
	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}
	/**
	 * 获取：外部系统唯一主键
	 */
	public String getForeignId() {
		return foreignId;
	}
	/**
	 * 设置：
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/**
	 * 获取：
	 */
	public Integer getVersion() {
		return version;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
