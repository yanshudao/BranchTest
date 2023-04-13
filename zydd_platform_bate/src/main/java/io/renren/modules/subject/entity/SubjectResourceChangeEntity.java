package io.renren.modules.subject.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;

/**
 * 换版教材
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@TableName("subject_resource_change")
public class SubjectResourceChangeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 原教材ID
	 */
	private String fromResourceId;
	/**
	 * 原书代号
	 */
	private String fromResourceCode;
	/**
	 * 新教材ID
	 */
	private String toResourceId;
	/**
	 * 新书代号
	 */
	private String toResourceCode;
	/**
	 * 是否外版
	 */
	private String foreignId;
	/**
	 * 创建时间
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
	@TableField("enable_")
	private Integer enable;
	/**
	 *
	 */
	@TableField("remark_")
	private String remark;
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
	 * 设置：原教材ID
	 */
	public void setFromResourceId(String fromResourceId) {
		this.fromResourceId = fromResourceId;
	}
	/**
	 * 获取：原教材ID
	 */
	public String getFromResourceId() {
		return fromResourceId;
	}
	/**
	 * 设置：原书代号
	 */
	public void setFromResourceCode(String fromResourceCode) {
		this.fromResourceCode = fromResourceCode;
	}
	/**
	 * 获取：原书代号
	 */
	public String getFromResourceCode() {
		return fromResourceCode;
	}
	/**
	 * 设置：新教材ID
	 */
	public void setToResourceId(String toResourceId) {
		this.toResourceId = toResourceId;
	}
	/**
	 * 获取：新教材ID
	 */
	public String getToResourceId() {
		return toResourceId;
	}
	/**
	 * 设置：新书代号
	 */
	public void setToResourceCode(String toResourceCode) {
		this.toResourceCode = toResourceCode;
	}
	/**
	 * 获取：新书代号
	 */
	public String getToResourceCode() {
		return toResourceCode;
	}
	/**
	 * 设置：是否外版
	 */
	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}
	/**
	 * 获取：是否外版
	 */
	public String getForeignId() {
		return foreignId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
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
