package io.renren.modules.zd.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-09-07 16:30:50
 */
@TableName("zd_order_cart")
public class ZdOrderCartEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	private String majorId;
	/**
	 * 
	 */
	private String courseId;
	/**
	 * 
	 */
	private String resourceId;
	/**
	 * 
	 */
	private Integer catNum;
	/**
	 * 
	 */
	private String orgCode;
	@TableField(fill = FieldFill.INSERT)
	private String createBy;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	@TableField(fill = FieldFill.UPDATE)
	private String updateBy;
	/**
	 *
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;

	private String zmcrId;


	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	/**
	 * 获取：
	 */
	public String getMajorId() {
		return majorId;
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
	 * 设置：
	 */
	public void setCatNum(Integer catNum) {
		this.catNum = catNum;
	}
	/**
	 * 获取：
	 */
	public Integer getCatNum() {
		return catNum;
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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getZmcrId() {
		return zmcrId;
	}

	public void setZmcrId(String zmcrId) {
		this.zmcrId = zmcrId;
	}
}
