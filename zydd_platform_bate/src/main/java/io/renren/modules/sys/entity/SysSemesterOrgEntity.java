package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-04-26 23:28:22
 */
@TableName("sys_semester_org")
public class SysSemesterOrgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 单位代码
	 */
	private String orgCode;
	/**
	 * 报订季节代码
	 */
	private String semesterCode;
	/**
	 * 是否当前报订季节（1是0否）
	 */
	private String status;
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
	 * 
	 */
	@TableField("enable_")
	private String enable;

	private Date semesterStartTime;
	private Date semesterEndTime;


	public Date getSemesterStartTime() {
		return semesterStartTime;
	}

	public void setSemesterStartTime(Date semesterStartTime) {
		this.semesterStartTime = semesterStartTime;
	}

	public Date getSemesterEndTime() {
		return semesterEndTime;
	}

	public void setSemesterEndTime(Date semesterEndTime) {
		this.semesterEndTime = semesterEndTime;
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
	 * 设置：单位代码
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：单位代码
	 */
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * 设置：报订季节代码
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
	/**
	 * 获取：报订季节代码
	 */
	public String getSemesterCode() {
		return semesterCode;
	}
	/**
	 * 设置：是否当前报订季节（1是0否）
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：是否当前报订季节（1是0否）
	 */
	public String getStatus() {
		return status;
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
	 * 设置：
	 */
	public void setEnable(String enable) {
		this.enable = enable;
	}
	/**
	 * 获取：
	 */
	public String getEnable() {
		return enable;
	}
}
