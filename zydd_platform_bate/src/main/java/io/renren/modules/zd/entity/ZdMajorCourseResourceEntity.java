package io.renren.modules.zd.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 征订关系
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-10-22 11:01:03
 */
@TableName("zd_major_course_resource")
public class ZdMajorCourseResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 季节代码
	 */
	private String semesterCode;
	/**
	 * 专业代码
	 */
	@NotBlank(message = "专业代码不能为空")
	@Excel(name = "专业代码", orderNum = "10")
	private String majorCode;
	/**
	 * 专业类型
	 */
	@NotBlank(message = "专业层次不能为空")
	@Excel(name = "专业层次", orderNum = "20",replace = {"本科_2", "专科_4","本科（专科起点）_5","本科（高中起点）_6","中专_8"})
//	@Excel(name = "专业层次", orderNum = "20",dict = "majorType")
	private String majorType;
	/**
	 * 学生类型
	 */
	@NotBlank(message = "学生性质不能为空")
	@Excel(name = "学生性质", orderNum = "30",replace = {"开放_01", "普招_02","成招_03","一村一_04","课程开放_05","助力计划_06","农民大学生_07","专本衔接_08","中高职衔接_09","乡村教师学历提升计划_10","网考_11","中专_19"})
//	@Excel(name = "学生性质", orderNum = "30",dict = "studentType")
	private String studentType;
	/**
	 * 课程代码
	 */
	@NotBlank(message = "课程代码不能为空")
	@Excel(name = "课程代码", orderNum = "40")
	private String courseCode;
	/**
	 * 教材代码
	 */
	@NotBlank(message = "教材代码不能为空")
	@Excel(name = "教材代码", orderNum = "50")
	private String resourceCode;
	/**
	 * 模块号
	 */
	@Excel(name = "模块号", orderNum = "80")
	private String moduleType;
	/**
	 * 报订规则号
	 */
	@Excel(name = "规则号", orderNum = "70")
	private String ruleNumber;
	/**
	 * 考试单位
	 */
	@Excel(name = "考试单位", orderNum = "60",replace = {"中央_1", "省_2"})
	@NotNull
	private String subjectType;
	/**
	 * 创建人
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createBy;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 创建单位
	 */
	private String orgCode;
	/**
	 * 更新人
	 */
	private String updateBy;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	private String oldResourceCode;
	private Integer isDel;
	private Integer isArchives;
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
	 * 设置：季节代码
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
	/**
	 * 获取：季节代码
	 */
	public String getSemesterCode() {
		return semesterCode;
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
	 * 设置：专业类型
	 */
	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}
	/**
	 * 获取：专业类型
	 */
	public String getMajorType() {
		return majorType;
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
	 * 设置：教材代码
	 */
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	/**
	 * 获取：教材代码
	 */
	public String getResourceCode() {
		return resourceCode;
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
	 * 设置：报订规则号
	 */
	public void setRuleNumber(String ruleNumber) {
		this.ruleNumber = ruleNumber;
	}
	/**
	 * 获取：报订规则号
	 */
	public String getRuleNumber() {
		return ruleNumber;
	}
	/**
	 * 设置：考试单位
	 */
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	/**
	 * 获取：考试单位
	 */
	public String getSubjectType() {
		return subjectType;
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
	 * 设置：创建单位
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：创建单位
	 */
	public String getOrgCode() {
		return orgCode;
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

	public String getOldResourceCode() {
		return oldResourceCode;
	}

	public void setOldResourceCode(String oldResourceCode) {
		this.oldResourceCode = oldResourceCode;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getIsArchives() {
		return isArchives;
	}

	public void setIsArchives(Integer isArchives) {
		this.isArchives = isArchives;
	}
}
