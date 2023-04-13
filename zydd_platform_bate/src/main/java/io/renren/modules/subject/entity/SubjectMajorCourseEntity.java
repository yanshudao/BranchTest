package io.renren.modules.subject.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;

/**
 * 专业课程
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@TableName("subject_major_course")
public class SubjectMajorCourseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 课程ID
	 */
	private String courseId;
	/**
	 * 专业ID
	 */
	private String majorId;
	/**
	 * 年度
	 */
	private String nd;
	/**
	 * 学期代码
	 */
	private String xqdm;
	/**
	 * 单位ID
	 */
	private Integer orgId;
	/**
	 * 外部系统唯一ID
	 */
	private String foreignId;
	/**
	 * 规则号
	 */
	private String ruleNumber;
	/**
	 * 学生类型
	 */
	private String studentType;
	/**
	 * 专业性质
	 */
	private String majorType;
	/**
	 * 模块号
	 */
	private String moduleType;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private String orgCode;
	/**
	 * 学期代码
	 */
	private String semesterCode;
	/**
	 * 
	 */
	private String courseCode;
	/**
	 * 
	 */
	private String majorCode;
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
	@TableField(fill = FieldFill.UPDATE)
	private String updateBy;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;

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
	 * 设置：课程ID
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	/**
	 * 获取：课程ID
	 */
	public String getCourseId() {
		return courseId;
	}
	/**
	 * 设置：专业ID
	 */
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	/**
	 * 获取：专业ID
	 */
	public String getMajorId() {
		return majorId;
	}
	/**
	 * 设置：年度
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}
	/**
	 * 获取：年度
	 */
	public String getNd() {
		return nd;
	}
	/**
	 * 设置：学期代码
	 */
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	/**
	 * 获取：学期代码
	 */
	public String getXqdm() {
		return xqdm;
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
	 * 设置：外部系统唯一ID
	 */
	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}
	/**
	 * 获取：外部系统唯一ID
	 */
	public String getForeignId() {
		return foreignId;
	}
	/**
	 * 设置：规则号
	 */
	public void setRuleNumber(String ruleNumber) {
		this.ruleNumber = ruleNumber;
	}
	/**
	 * 获取：规则号
	 */
	public String getRuleNumber() {
		return ruleNumber;
	}
	/**
	 * 设置：学生类型
	 */
	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}
	/**
	 * 获取：学生类型
	 */
	public String getStudentType() {
		return studentType;
	}
	/**
	 * 设置：专业性质
	 */
	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}
	/**
	 * 获取：专业性质
	 */
	public String getMajorType() {
		return majorType;
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
	 * 设置：
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	/**
	 * 获取：
	 */
	public String getCourseCode() {
		return courseCode;
	}
	/**
	 * 设置：
	 */
	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}
	/**
	 * 获取：
	 */
	public String getMajorCode() {
		return majorCode;
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
}
