package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:15:51
 */
@TableName("sys_org")
public class SysOrgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 单位编号
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 单位名称
	 */
	private String orgName;
	/**
	 * 上级部门编号
	 */
	private String parentId;
	/**
	 * 排序号
	 */
	@JsonIgnore
	private Integer sortNo;
	/**
	 * 叶子节点(0:树枝节点;1:叶子节点)
	 */
	@TableField("leaf_")
	@JsonIgnore
	private Integer leaf;
	/**
	 * 启用状态
	 */
	@TableField("enable_")
	@JsonIgnore
	private Integer enable;
	/**
	 *
	 */
	@TableField("remark_")
	@JsonIgnore
	private String remark;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	@JsonIgnore
	private String createBy;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	@JsonIgnore
	private Date createTime;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.UPDATE)
	@JsonIgnore
	private String updateBy;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.UPDATE)
	@JsonIgnore
	private Date updateTime;
	/**
	 * 单位代码
	 */
	private String orgCode;

	private String orgType;

	private String parentIds;

	private String parentCodes;
	@JsonIgnore
	private String fullCodes;
	private String areaCode;
	@TableField(exist = false)
	private Integer isParent;

    @TableField(exist = false)
	private SysOrgEntity parentOrg;

	/**
	 * 设置：单位编号
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：单位编号
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：单位名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 获取：单位名称
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * 设置：上级部门编号
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：上级部门编号
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置：排序号
	 */
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	/**
	 * 获取：排序号
	 */
	public Integer getSortNo() {
		return sortNo;
	}
	/**
	 * 设置：叶子节点(0:树枝节点;1:叶子节点)
	 */
	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}
	/**
	 * 获取：叶子节点(0:树枝节点;1:叶子节点)
	 */
	public Integer getLeaf() {
		return leaf;
	}
	/**
	 * 设置：启用状态
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	/**
	 * 获取：启用状态
	 */
	public Integer getEnable() {
		return enable;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
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
	 * 设置：单位代码
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：单位代码
	 */
	public String getOrgCode() {
		return orgCode;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getParentCodes() {
		return parentCodes;
	}

	public void setParentCodes(String parentCodes) {
		this.parentCodes = parentCodes;
	}

	public SysOrgEntity getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(SysOrgEntity parentOrg) {
		this.parentOrg = parentOrg;
	}

	public String getFullCodes() {
		return fullCodes;
	}

	public void setFullCodes(String fullCodes) {
		this.fullCodes = fullCodes;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}
}
