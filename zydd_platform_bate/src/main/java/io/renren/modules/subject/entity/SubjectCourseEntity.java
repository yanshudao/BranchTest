package io.renren.modules.subject.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;

/**
 * 课程
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@TableName("subject_course")
public class SubjectCourseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
		@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 课程代码
	 */
	private String courseCode;
	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 课程类型（专科、本科、本专）
	 */
	private String courseType;
	/**
	 * 课程性质（选修，必修）
	 */
	private String courseNature;
	/**
	 * 课程学分
	 */
	private Integer courseCredit;
	/**
	 * 创建人ID
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createBy;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 更新人
	 */
	@TableField(fill = FieldFill.UPDATE)
	private String updateBy;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;
	/**
	 * 数据版本号
	 */
	private Integer version;
	/**
	 * 外部系统唯一主键
	 */
	private String foreignId;
	/**
	 * 单位ID
	 */
	private Integer orgId;
	/**
	 * 模块号
	 */
	private String moduleType;
	/**
	 * 状态
	 */
	private String status;
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
	@TableField("course_scope")
	private String courseScope;

	@TableField(exist=false)
	private String majorCourseId;
	@TableField(exist=false)
	private String subjectType;
	@TableField(exist=false)
	private String orgName;
	/**
	 * 设置：编号
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：课程代码
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	/**
	 * 获取：课程代码
	 */
	public String getCourseCode() {
		return courseCode;
	}
	/**
	 * 设置：课程名称
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * 获取：课程名称
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * 设置：课程类型（专科、本科、本专）
	 */
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	/**
	 * 获取：课程类型（专科、本科、本专）
	 */
	public String getCourseType() {
		return courseType;
	}
	/**
	 * 设置：课程性质（选修，必修）
	 */
	public void setCourseNature(String courseNature) {
		this.courseNature = courseNature;
	}
	/**
	 * 获取：课程性质（选修，必修）
	 */
	public String getCourseNature() {
		return courseNature;
	}
	/**
	 * 设置：课程学分
	 */
	public void setCourseCredit(Integer courseCredit) {
		this.courseCredit = courseCredit;
	}
	/**
	 * 获取：课程学分
	 */
	public Integer getCourseCredit() {
		return courseCredit;
	}
	/**
	 * 设置：创建人ID
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人ID
	 */
	public String getCreateBy() {
		return createBy;
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
	 * 设置：更新人
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：更新人
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：数据版本号
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/**
	 * 获取：数据版本号
	 */
	public Integer getVersion() {
		return version;
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
	 * 设置：单位ID
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：单位ID
	 */
	public Integer getOrgId() {
		return orgId;
	}
	/**
	 * 设置：模块号
	 */
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	/**
	 * 获取：模块号
	 */
	public String getModuleType() {
		return moduleType;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
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

	public String getMajorCourseId() {
		return majorCourseId;
	}

	public void setMajorCourseId(String majorCourseId) {
		this.majorCourseId = majorCourseId;
	}

	public String getCourseScope() {
		return courseScope;
	}

	public void setCourseScope(String courseScope) {
		this.courseScope = courseScope;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
