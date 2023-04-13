package io.renren.modules.zd.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 采购详情
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@TableName("zd_source_resource")
public class ZdSourceResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	private String sourceId;
	/**
	 * 
	 */
	private String courseId;
	/**
	 * 
	 */
	private String resourceId;
	/**
	 * 采购数量
	 */
	private Integer sourceNum;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private String orgCode;
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
	private BigDecimal resourcePrice;
	/**
	 * NC系统状态
	 */
	private String itemStatus;
	/**
	 * 码洋
	 */
	private BigDecimal mayang;
	/**
	 * 实洋
	 */
	private BigDecimal shiyang;
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
	private String status;
	private String carrierno;
	private String delFlag;
	@TableField(exist = false)
	private String ids;

	private BigDecimal nitemdiscountrate;
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
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	/**
	 * 获取：
	 */
	public String getSourceId() {
		return sourceId;
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
	 * 设置：采购数量
	 */
	public void setSourceNum(Integer sourceNum) {
		this.sourceNum = sourceNum;
	}
	/**
	 * 获取：采购数量
	 */
	public Integer getSourceNum() {
		return sourceNum;
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
	public void setResourcePrice(BigDecimal resourcePrice) {
		this.resourcePrice = resourcePrice;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getResourcePrice() {
		return resourcePrice;
	}
	/**
	 * 设置：NC系统状态
	 */
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	/**
	 * 获取：NC系统状态
	 */
	public String getItemStatus() {
		return itemStatus;
	}
	/**
	 * 设置：码洋
	 */
	public void setMayang(BigDecimal mayang) {
		this.mayang = mayang;
	}
	/**
	 * 获取：码洋
	 */
	public BigDecimal getMayang() {
		return mayang;
	}
	/**
	 * 设置：实洋
	 */
	public void setShiyang(BigDecimal shiyang) {
		this.shiyang = shiyang;
	}
	/**
	 * 获取：实洋
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
