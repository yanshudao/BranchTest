package io.renren.modules.zd.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
 * @date 2018-10-23 09:42:20
 */
@TableName("zd_source_cart")
public class ZdSourceCartEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	private String resourceId;
	private String orgCode;
	private Integer status;
	@Excel(name = "采购数量",orderNum = "20")
	private Integer num;
	@TableField(fill = FieldFill.INSERT)
	private String createBy;
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	private String newResourceId;
	private String semesterCode;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getNewResourceId() {
		return newResourceId;
	}

	public void setNewResourceId(String newResourceId) {
		this.newResourceId = newResourceId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getSemesterCode() {
		return semesterCode;
	}

	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
}
