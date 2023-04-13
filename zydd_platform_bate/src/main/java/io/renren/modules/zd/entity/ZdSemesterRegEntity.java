package io.renren.modules.zd.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;


@TableName("zd_semester_reg")
public class ZdSemesterRegEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	@Excel(name = "分部代码",orderNum = "10")
	private String groupCode;
	@Excel(name = "分部名称",orderNum = "20")
	private String groupName;
	@Excel(name = "分校代码",orderNum = "30")
	private String schoolCode;
	@Excel(name = "分校名称",orderNum = "40")
	private String schoolName;
	@Excel(name = "教学点代码",orderNum = "50")
	private String teachCode;
	@Excel(name = "教学点名称",orderNum = "60")
	private String teachName;
	@Excel(name = "学期",orderNum = "9")
	private String semesterCode;
	@Excel(name = "客商代码",orderNum = "80")
	private String custCode;
	private String custName;
    @Excel(name = "学生类型",orderNum = "85",dict = "studentType")
    private String studentType;
    @Excel(name = "专业层次",orderNum = "89",dict = "majorType")
    private String majorType;
	@Excel(name = "专业代码",orderNum = "90")
	private String majorCode;
	@Excel(name = "专业名称",orderNum = "120")
	private String majorName;
	@Excel(name = "课程代码",orderNum = "130")
	private String courseCode;
	@Excel(name = "课程名称",orderNum = "140")
	private String courseName;
	@Excel(name = "注册人数",orderNum = "150")
	private Integer stuNum;

	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
    @TableField(fill = FieldFill.INSERT)
	private String createBy;
    @TableField(fill = FieldFill.UPDATE)
	private Date updateTime;
    @TableField(fill = FieldFill.UPDATE)
	private String updateBy;
	@TableField(exist = false)
    @Excel(name="结果",orderNum = "160")
	private String result;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSemesterCode() {
		return semesterCode;
	}

	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = StringUtils.trim(studentType);
	}

	public String getMajorType() {
		return majorType;
	}

	public void setMajorType(String majorType) {
		this.majorType =  StringUtils.trim(majorType);
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getStuNum() {
		return stuNum;
	}

	public void setStuNum(Integer stuNum) {
		this.stuNum = stuNum;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getTeachCode() {
		return teachCode;
	}

	public void setTeachCode(String teachCode) {
		this.teachCode = teachCode;
	}

	public String getTeachName() {
		return teachName;
	}

	public void setTeachName(String teachName) {
		this.teachName = teachName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
