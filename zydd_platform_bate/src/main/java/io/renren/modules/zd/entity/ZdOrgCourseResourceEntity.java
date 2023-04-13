package io.renren.modules.zd.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * 单位开设课程教材
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
@TableName("zd_org_course_resource")
public class ZdOrgCourseResourceEntity implements Serializable {
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
	 * 
	 */
	@ApiModelProperty(value = "专业ID")
	@NotBlank(message="专业ID不能为空")
	private String majorId;
	/**
	 * 
	 */
	@ApiModelProperty(value = "课程ID")
	@NotBlank(message="课程ID不能为空")
	private String courseId;
	/**
	 * 
	 */
	@ApiModelProperty(value = "教材ID")
	@NotBlank(message="教材ID不能为空")
	private String resourceId;
	/**
	 * 学期代码
	 */
	private String semesterCode;
	/**
	 * 创建人
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
	@TableField(fill = FieldFill.UPDATE)
	private String updateBy;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;
	/**
	 * 1可用 0禁用
	 */
	private String status;
	/**
	 * 
	 */
	private Integer enable;
	/**
	 * 
	 */
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
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	/**
	 * 获取：
	 */
	public String getMajorId() {
		return majorId;
	}
	/**
	 * 设置：
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	/**
	 * 获取：
	 */
	public String getCourseId() {
		return courseId;
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
	 * 设置：创建人
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
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
	 * 设置：1可用 0禁用
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：1可用 0禁用
	 */
	public String getStatus() {
		return status;
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
