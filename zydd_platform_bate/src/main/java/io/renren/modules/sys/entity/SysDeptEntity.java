package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-29 22:09:37
 */
@TableName("sys_dept")
public class SysDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 单位编号
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 隶属单位（废弃）
	 */
	private String unitId;
	/**
	 * 单位名称
	 */
	private String deptName;
	/**
	 * 上级部门编号
	 */
	private String parentId;
	/**
	 * 排序号
	 */
	private Integer sortNo;
	/**
	 * 叶子节点(0:树枝节点;1:叶子节点)
	 */
	private Integer leaf;
	/**
	 * 启用状态
	 */
	@TableField("enable_")
	private Integer enable;
	/**
	 *
	 */
	@TableField("remark_")
	private String remark;
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
	 * 单位代码
	 */
	@TableField(fill = FieldFill.INSERT)
	private String orgCode;

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
	 * 设置：隶属单位（废弃）
	 */
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	/**
	 * 获取：隶属单位（废弃）
	 */
	public String getUnitId() {
		return unitId;
	}
	/**
	 * 设置：单位名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取：单位名称
	 */
	public String getDeptName() {
		return deptName;
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
}
