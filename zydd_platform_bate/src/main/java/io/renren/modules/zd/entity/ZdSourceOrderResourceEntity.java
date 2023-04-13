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
 * @date 2019-02-25 16:21:35
 */
@TableName("zd_source_order_resource")
public class ZdSourceOrderResourceEntity implements Serializable {
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
	private String orderResourceId;
	private String sourceResourceId;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createBy;

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
	public void setOrderResourceId(String orderResourceId) {
		this.orderResourceId = orderResourceId;
	}
	/**
	 * 获取：
	 */
	public String getOrderResourceId() {
		return orderResourceId;
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
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：
	 */
	public String getCreateBy() {
		return createBy;
	}

	public String getSourceResourceId() {
		return sourceResourceId;
	}

	public void setSourceResourceId(String sourceResourceId) {
		this.sourceResourceId = sourceResourceId;
	}
}
