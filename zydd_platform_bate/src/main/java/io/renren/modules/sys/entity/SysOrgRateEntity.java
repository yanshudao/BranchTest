package io.renren.modules.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@TableName("sys_org_rate")
public class SysOrgRateEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	private String orgCode;
	private String semesterCode;
	private BigDecimal rate1;
	private BigDecimal rate2;
	private BigDecimal rate3;
	private String createBy;
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getSemesterCode() {
		return semesterCode;
	}

	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}

	public BigDecimal getRate1() {
		return rate1;
	}

	public void setRate1(BigDecimal rate1) {
		this.rate1 = rate1;
	}

	public BigDecimal getRate2() {
		return rate2;
	}

	public void setRate2(BigDecimal rate2) {
		this.rate2 = rate2;
	}

	public BigDecimal getRate3() {
		return rate3;
	}

	public void setRate3(BigDecimal rate3) {
		this.rate3 = rate3;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
