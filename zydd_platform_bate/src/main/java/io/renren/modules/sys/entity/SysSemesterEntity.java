package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 征订季节设定
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-29 22:09:37
 */
@TableName("sys_semester")
public class SysSemesterEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 学期名称
	 */
	private String name;
	/**
	 * 年度
	 */
	private BigDecimal nd;
	/**
	 * 学期code
	 */
	private String xqdm;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 是否可退货
	 */
	private String isRefund;
	/**
	 * 是否开放报订
	 */
	private String isOrder;
	private BigDecimal allowRefundPercent;
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
	 * 学期代码
	 */
	private String semesterCode;
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
	private String isCurrent;
	@TableField(exist = false)
	private String semesterStartTimeStr;
	@TableField(exist = false)
	private String semesterEndTimeStr;
	@TableField(exist = false)
	private Date semesterStartTime;
	@TableField(exist = false)
	private Date semesterEndTime;

	public String getSemesterStartTimeStr() {
		return semesterStartTimeStr;
	}

	public void setSemesterStartTimeStr(String semesterStartTimeStr) {
		this.semesterStartTimeStr = semesterStartTimeStr;
	}

	public String getSemesterEndTimeStr() {
		return semesterEndTimeStr;
	}

	public void setSemesterEndTimeStr(String semesterEndTimeStr) {
		this.semesterEndTimeStr = semesterEndTimeStr;
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
	 * 设置：学期名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：学期名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：年度
	 */
	public void setNd(BigDecimal nd) {
		this.nd = nd;
	}
	/**
	 * 获取：年度
	 */
	public BigDecimal getNd() {
		return nd;
	}
	/**
	 * 设置：学期code
	 */
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	/**
	 * 获取：学期code
	 */
	public String getXqdm() {
		return xqdm;
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
	 * 设置：是否可退货
	 */
	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}
	/**
	 * 获取：是否可退货
	 */
	public String getIsRefund() {
		return isRefund;
	}
	/**
	 * 设置：是否开放报订
	 */
	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}
	/**
	 * 获取：是否开放报订
	 */
	public String getIsOrder() {
		return isOrder;
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

	public String getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(String isCurrent) {
		this.isCurrent = isCurrent;
	}

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

	public BigDecimal getAllowRefundPercent() {
		return allowRefundPercent;
	}

	public void setAllowRefundPercent(BigDecimal allowRefundPercent) {
		this.allowRefundPercent = allowRefundPercent;
	}
}
