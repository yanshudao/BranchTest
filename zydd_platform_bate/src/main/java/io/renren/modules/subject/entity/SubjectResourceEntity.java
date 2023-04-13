package io.renren.modules.subject.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 教材
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@TableName("subject_resource")
public class SubjectResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 教材分类ID
	 */
	private String catalogId;
	/**
	 * 教材名称
	 */
	@Excel(name = "教材名称",orderNum = "20")
	private String resourceName;
	/**
	 * 教材代码
	 */
	@Excel(name = "教材代码",orderNum = "10")
	private String resourceCode;
	/**
	 * 版次
	 */
	@Excel(name = "版次",orderNum = "30")
	private String resourceVersion;
	/**
	 * 作者
	 */
	@Excel(name = "作者",orderNum = "40")
	private String author;
	/**
	 * 包册本书
	 */
	@Excel(name = "包册本书",orderNum = "50")
	private Integer bcBs;
	/**
	 * 包册件数
	 */
	private Integer bcJs;
	/**
	 * ISBN
	 */
	@Excel(name = "ISBN",orderNum = "50")
	private String isbn;
	/**
	 * 价格
	 */
	@Excel(name = "价格",orderNum = "60")
	private Double price;
	/**
	 * 出版日期
	 */
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@Excel(name = "出版日期",orderNum = "70")
	private String publishDate;
	/**
	 * 删除状态
	 */
	private String deleteFlag;
	/**
	 * 供应商ID
	 */
	private String supplierId;
	@TableField(exist = false)
	@Excel(name = "供应商",orderNum = "80")
	private String supplierName;
	/**
	 * 出版社ID
	 */
	private String publishingId;

	private String publishingName;
	/**
	 * 图片
	 */
	private String pic;
	/**
	 * 外部系统ID
	 */
	private String foreignId;
	/**
	 * 简介
	 */
	private String content;
	/**
	 * 媒体类型（文字主）
	 */
	@Excel(name = "教材类型",orderNum = "90",   replace = { "文字主_1", "文字辅_2","其他_3" })
	private String resourceType;
	/**
	 * 是否待定教材（0 不是 1 是）
	 */
	private String isPublish;
	/**
	 * 是否推荐
	 */
	private String isRecommend;
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
	 * 单位ID
	 */
	private String orgId;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private String orgCode;
	/**
	 * 
	 */
	private String catalogCode;
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
	private String oldResourceId;
	@TableField(exist = false)
	private String courseResourceId;
	@TableField(exist = false)
	private String orgName;
	@TableField(exist = false)
	@Excel(name = "教材分类",orderNum = "9")
	private String catalogName;

	private Integer resourceScope;
	@Excel(name = "教材状态",orderNum = "100", replace = { "上架_1", "下架_0"})
	private Integer isShow;
	@Excel(name = "教材类型2",orderNum = "110", replace = { "统必主_1", "其他_0"})
	private String resourceType2;
	@Excel(name = "结果",orderNum = "200")
	@TableField(exist = false)
	private String result;

	@TableField(exist = false)
	private List<SubjectResourceScopeEntity> scopeList;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPublishingName() {
		return publishingName;
	}

	public void setPublishingName(String publishingName) {
		this.publishingName = publishingName;
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
	 * 设置：教材分类ID
	 */
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	/**
	 * 获取：教材分类ID
	 */
	public String getCatalogId() {
		return catalogId;
	}
	/**
	 * 设置：教材名称
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	/**
	 * 获取：教材名称
	 */
	public String getResourceName() {
		return resourceName;
	}
	/**
	 * 设置：教材代码
	 */
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	/**
	 * 获取：教材代码
	 */
	public String getResourceCode() {
		return resourceCode;
	}
	/**
	 * 设置：版次
	 */
	public void setResourceVersion(String resourceVersion) {
		this.resourceVersion = resourceVersion;
	}
	/**
	 * 获取：版次
	 */
	public String getResourceVersion() {
		return resourceVersion;
	}
	/**
	 * 设置：作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：作者
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 设置：包册本书
	 */
	public void setBcBs(Integer bcBs) {
		this.bcBs = bcBs;
	}
	/**
	 * 获取：包册本书
	 */
	public Integer getBcBs() {
		return bcBs;
	}
	/**
	 * 设置：包册件数
	 */
	public void setBcJs(Integer bcJs) {
		this.bcJs = bcJs;
	}
	/**
	 * 获取：包册件数
	 */
	public Integer getBcJs() {
		return bcJs;
	}
	/**
	 * 设置：ISBN
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	/**
	 * 获取：ISBN
	 */
	public String getIsbn() {
		return isbn;
	}
	/**
	 * 设置：价格
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * 设置：出版日期
	 */
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	/**
	 * 获取：出版日期
	 */
	public String getPublishDate() {
		return publishDate;
	}
	/**
	 * 设置：删除状态
	 */
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * 获取：删除状态
	 */
	public String getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * 设置：供应商ID
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：供应商ID
	 */
	public String getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置：出版社ID
	 */
	public void setPublishingId(String publishingId) {
		this.publishingId = publishingId;
	}
	/**
	 * 获取：出版社ID
	 */
	public String getPublishingId() {
		return publishingId;
	}
	/**
	 * 设置：图片
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}
	/**
	 * 获取：图片
	 */
	public String getPic() {
		return pic;
	}
	/**
	 * 设置：外部系统ID
	 */
	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}
	/**
	 * 获取：外部系统ID
	 */
	public String getForeignId() {
		return foreignId;
	}
	/**
	 * 设置：简介
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：简介
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：媒体类型（文字主）
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	/**
	 * 获取：媒体类型（文字主）
	 */
	public String getResourceType() {
		return resourceType;
	}
	/**
	 * 设置：是否待定教材（0 不是 1 是）
	 */
	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}
	/**
	 * 获取：是否待定教材（0 不是 1 是）
	 */
	public String getIsPublish() {
		return isPublish;
	}
	/**
	 * 设置：是否推荐
	 */
	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}
	/**
	 * 获取：是否推荐
	 */
	public String getIsRecommend() {
		return isRecommend;
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
	 * 设置：单位ID
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：单位ID
	 */
	public String getOrgId() {
		return orgId;
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
	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}
	/**
	 * 获取：
	 */
	public String getCatalogCode() {
		return catalogCode;
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

	public String getCourseResourceId() {
		return courseResourceId;
	}

	public void setCourseResourceId(String courseResourceId) {
		this.courseResourceId = courseResourceId;
	}

	public String getOldResourceId() {
		return oldResourceId;
	}

	public void setOldResourceId(String oldResourceId) {
		this.oldResourceId = oldResourceId;
	}

	public Integer getResourceScope() {
		return resourceScope;
	}

	public void setResourceScope(Integer resourceScope) {
		this.resourceScope = resourceScope;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public List<SubjectResourceScopeEntity> getScopeList() {
		return scopeList;
	}

	public void setScopeList(List<SubjectResourceScopeEntity> scopeList) {
		this.scopeList = scopeList;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getResourceType2() {
		return resourceType2;
	}

	public void setResourceType2(String resourceType2) {
		this.resourceType2 = resourceType2;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
