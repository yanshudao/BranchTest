package io.renren.modules.zd.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;

/**
 * 入库
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@TableName("zd_stock_income")
public class ZdStockIncomeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 入库单号
	 */
	private String incomeSn;
	/**
	 * 单据名称
	 */
	private String incomeName;
	/**
	 * 供应商ID
	 */
	private String companyId;
	/**
	 * 单位CODE
	 */
	private String orgCode;
	/**
	 * 
	 */
	private String semesterCode;
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
	 * 
	 */
	private String status;
	/**
	 * 
	 */

	private String delFlag;
	/**
	 * 
	 */
	@TableField(value ="enable_")
	private Integer enable;
	/**
	 * 
	 */
	@TableField(value ="remark_")
	private String remark;

	private Date incomeTime;

	private Date lastIncomeTime;

	private String erpcode;
	private String foreignId;
	//20180709
	private String toOrgCode;

	private String num;
	private String carrierno;

	private Date sendDate;

	public String getToOrgCode() {
		return toOrgCode;
	}

	public void setToOrgCode(String toOrgCode) {
		this.toOrgCode = toOrgCode;
	}

	public String getErpcode() {
		return erpcode;
	}

	public void setErpcode(String erpcode) {
		this.erpcode = erpcode;
	}

	public String getForeignId() {
		return foreignId;
	}

	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
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
	 * 设置：入库单号
	 */
	public void setIncomeSn(String incomeSn) {
		this.incomeSn = incomeSn;
	}
	/**
	 * 获取：入库单号
	 */
	public String getIncomeSn() {
		return incomeSn;
	}
	/**
	 * 设置：单据名称
	 */
	public void setIncomeName(String incomeName) {
		this.incomeName = incomeName;
	}
	/**
	 * 获取：单据名称
	 */
	public String getIncomeName() {
		return incomeName;
	}
	/**
	 * 设置：供应商ID
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取：供应商ID
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置：单位CODE
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：单位CODE
	 */
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * 设置：
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
	/**
	 * 获取：
	 */
	public String getSemesterCode() {
		return semesterCode;
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
	/**
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：
	 */
	public String getDelFlag() {
		return delFlag;
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

	public Date getIncomeTime() {
		return incomeTime;
	}

	public void setIncomeTime(Date incomeTime) {
		this.incomeTime = incomeTime;
	}

	public Date getLastIncomeTime() {
		return lastIncomeTime;
	}

	public void setLastIncomeTime(Date lastIncomeTime) {
		this.lastIncomeTime = lastIncomeTime;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCarrierno() {
		return carrierno;
	}

	public void setCarrierno(String carrierno) {
		this.carrierno = carrierno;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
}
