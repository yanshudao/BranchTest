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
@TableName("subject_major")
public class SubjectMajorEntity implements Serializable {
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
	 * 专业层次（2本科 4专科）
	 */
	private String majorLevel;
	/**
	 * 学生类型（01开放 02普招 03成招 04一村一 05 课程开放 06助力计划 07农民大学生）
	 */
	@Excel(name = "学生类型",orderNum="30")
	private String studentType;
	/**
	 * 单位ID
	 */
	private Integer orgId;
	/**
	 * 专业性质（01统设 02省设）
	 */
	@Excel(name = "专业类型",orderNum="40")
	private String majorType;
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
	 * 设置：专业层次（2本科 4专科）
	 */
	public void setMajorLevel(String majorLevel) {
		this.majorLevel = majorLevel;
	}
	/**
	 * 获取：专业层次（2本科 4专科）
	 */
	public String getMajorLevel() {
		return majorLevel;
	}
	/**
	 * 设置：学生类型（01开放 02普招 03成招 04一村一 05 课程开放 06助力计划 07农民大学生）
	 */
	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}
	/**
	 * 获取：学生类型（01开放 02普招 03成招 04一村一 05 课程开放 06助力计划 07农民大学生）
	 */
	public String getStudentType() {
		return studentType;
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
	 * 设置：专业性质（01统设 02省设）
	 */
	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}
	/**
	 * 获取：专业性质（01统设 02省设）
	 */
	public String getMajorType() {
		return majorType;
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
