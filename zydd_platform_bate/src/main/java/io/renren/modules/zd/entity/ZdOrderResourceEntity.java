package io.renren.modules.zd.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 征订明细
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
@TableName("zd_order_resource")
public class ZdOrderResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	private String orderId;
	/**
	 * 
	 */
	private String resourceId;
	/**
	 * 报订数量
	 */
	@Excel(name = "报订数量",orderNum = "7")
	private Integer orderNum;
	/**
	 * 专业ID
	 */
	private String majorId;
	/**
	 * 课程ID
	 */
	private String courseId;
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
	private String orderResourceNo;
	/**
	 * 
	 */
	private String orgId;
	/**
	 * 
	 */
	private BigDecimal itemPrice;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private String orgCode;
	/**
	 * 
	 */
	private BigDecimal mayang;
	/**
	 * 
	 */
	private BigDecimal shiyang;

	private String status;

	private BigDecimal nitemdiscountrate;
	private String carrierno;
	private String delFlag;
	private String zmcrId;
	/**
	 *
	 */
	@TableField(value = "enable_")
	private Integer enable;
	/**
	 *
	 */
	@TableField(value = "remark_")
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
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：
	 */
	public String getOrderId() {
		return orderId;
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
	 * 设置：报订数量
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：报订数量
	 */
	public Integer getOrderNum() {
		return orderNum;
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
	public void setOrderResourceNo(String orderResourceNo) {
		this.orderResourceNo = orderResourceNo;
	}
	/**
	 * 获取：
	 */
	public String getOrderResourceNo() {
		return orderResourceNo;
	}
	/**
	 * 设置：
	 */
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 设置：
	 */
	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getItemPrice() {
		return itemPrice;
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
	public void setMayang(BigDecimal mayang) {
		this.mayang = mayang;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getMayang() {
		return mayang;
	}
	/**
	 * 设置：
	 */
	public void setShiyang(BigDecimal shiyang) {
		this.shiyang = shiyang;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getShiyang() {
		return shiyang;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getNitemdiscountrate() {
		return nitemdiscountrate;
	}

	public void setNitemdiscountrate(BigDecimal nitemdiscountrate) {
		this.nitemdiscountrate = nitemdiscountrate;
	}

	public String getCarrierno() {
		return carrierno;
	}

	public void setCarrierno(String carrierno) {
		this.carrierno = carrierno;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getZmcrId() {
		return zmcrId;
	}

	public void setZmcrId(String zmcrId) {
		this.zmcrId = zmcrId;
	}
}
