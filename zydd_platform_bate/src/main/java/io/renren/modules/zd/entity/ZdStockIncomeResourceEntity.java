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
 * 入库教材
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
@TableName("zd_stock_income_resource")
public class ZdStockIncomeResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	private String incomeId;
	/**
	 * 
	 */
	private String resourceId;
	/**
	 * 入库价格
	 */
	private BigDecimal incomePrice;
	/**
	 * 入库数量
	 */
	private Integer incomeNum;
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
	private String status;
	/**
	 * 
	 */
	private String orgCode;
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
	@TableField(value ="enable_")
	private Integer enable;
	/**
	 * 
	 */
	@TableField(value ="remark_")
	private String remark;

	private String carrierno;

	private String sourceResourceId;
	private String foreignId;

	private Integer realNum;
	private BigDecimal nitemdiscountrate;
	private BigDecimal mayang;
	private BigDecimal shiyang;


	public String getForeignId() {
		return foreignId;
	}

	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}

	public BigDecimal getMayang() {
		return mayang;
	}

	public void setMayang(BigDecimal mayang) {
		this.mayang = mayang;
	}

	public BigDecimal getShiyang() {
		return shiyang;
	}

	public void setShiyang(BigDecimal shiyang) {
		this.shiyang = shiyang;
	}

	public BigDecimal getNitemdiscountrate() {
		return nitemdiscountrate;
	}

	public void setNitemdiscountrate(BigDecimal nitemdiscountrate) {
		this.nitemdiscountrate = nitemdiscountrate;
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
	 * 设置：
	 */
	public void setIncomeId(String incomeId) {
		this.incomeId = incomeId;
	}
	/**
	 * 获取：
	 */
	public String getIncomeId() {
		return incomeId;
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
	 * 设置：入库价格
	 */
	public void setIncomePrice(BigDecimal incomePrice) {
		this.incomePrice = incomePrice;
	}
	/**
	 * 获取：入库价格
	 */
	public BigDecimal getIncomePrice() {
		return incomePrice;
	}
	/**
	 * 设置：入库数量
	 */
	public void setIncomeNum(Integer incomeNum) {
		this.incomeNum = incomeNum;
	}
	/**
	 * 获取：入库数量
	 */
	public Integer getIncomeNum() {
		return incomeNum;
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

	public String getCarrierno() {
		return carrierno;
	}

	public void setCarrierno(String carrierno) {
		this.carrierno = carrierno;
	}

	public String getSourceResourceId() {
		return sourceResourceId;
	}

	public void setSourceResourceId(String sourceResourceId) {
		this.sourceResourceId = sourceResourceId;
	}

	public Integer getRealNum() {
		return realNum;
	}

	public void setRealNum(Integer realNum) {
		this.realNum = realNum;
	}
}
