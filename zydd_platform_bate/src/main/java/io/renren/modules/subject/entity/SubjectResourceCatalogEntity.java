package io.renren.modules.subject.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 教材目录
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@TableName("subject_resource_catalog")
public class SubjectResourceCatalogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
		@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 分类名称
	 */
	private String catalogName;
	/**
	 * 分类code
	 */
	private String catalogCode;
	/**
	 * 父级code
	 */
	private String parentCode;
	/**
	 * 所有父级
	 */
	private String parentCodes;
	/**
	 * 排序
	 */
	private BigDecimal treeSort;
	/**
	 * 是否最末级
	 */
	private String treeLeaf;
	/**
	 * 层次级别
	 */
	private BigDecimal treeLevel;
	/**
	 * 机构级别
	 */
	@TableField(fill = FieldFill.INSERT)
	private String orgCode;
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
	 * 设置：分类名称
	 */
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	/**
	 * 获取：分类名称
	 */
	public String getCatalogName() {
		return catalogName;
	}
	/**
	 * 设置：分类code
	 */
	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}
	/**
	 * 获取：分类code
	 */
	public String getCatalogCode() {
		return catalogCode;
	}
	/**
	 * 设置：父级code
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	/**
	 * 获取：父级code
	 */
	public String getParentCode() {
		return parentCode;
	}
	/**
	 * 设置：所有父级
	 */
	public void setParentCodes(String parentCodes) {
		this.parentCodes = parentCodes;
	}
	/**
	 * 获取：所有父级
	 */
	public String getParentCodes() {
		return parentCodes;
	}
	/**
	 * 设置：排序
	 */
	public void setTreeSort(BigDecimal treeSort) {
		this.treeSort = treeSort;
	}
	/**
	 * 获取：排序
	 */
	public BigDecimal getTreeSort() {
		return treeSort;
	}
	/**
	 * 设置：是否最末级
	 */
	public void setTreeLeaf(String treeLeaf) {
		this.treeLeaf = treeLeaf;
	}
	/**
	 * 获取：是否最末级
	 */
	public String getTreeLeaf() {
		return treeLeaf;
	}
	/**
	 * 设置：层次级别
	 */
	public void setTreeLevel(BigDecimal treeLevel) {
		this.treeLevel = treeLevel;
	}
	/**
	 * 获取：层次级别
	 */
	public BigDecimal getTreeLevel() {
		return treeLevel;
	}
	/**
	 * 设置：机构级别
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：机构级别
	 */
	public String getOrgCode() {
		return orgCode;
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
}
