package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-22 15:57:47
 */
@TableName("sys_notice")
public class SysNoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	private String noticeTitle;
	/**
	 * 
	 */
	private String noticeContent;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private String orgCode;
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
	 * 
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.UPDATE)
	private String updateBy;
	/**
	 * 
	 */
	private String filePath;

	private Integer showScope;
	private Integer isShow;

	@TableField(exist = false)
	private List<String> orgList=new ArrayList<>();
	@TableField(exist = false)
	private String realname;

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
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	/**
	 * 获取：
	 */
	public String getNoticeTitle() {
		return noticeTitle;
	}
	/**
	 * 设置：
	 */
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	/**
	 * 获取：
	 */
	public String getNoticeContent() {
		return noticeContent;
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
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * 获取：
	 */
	public String getFilePath() {
		return filePath;
	}

	public Integer getShowScope() {
		return showScope;
	}

	public void setShowScope(Integer showScope) {
		this.showScope = showScope;
	}

	public List<String> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<String> orgList) {
		this.orgList = orgList;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
}
