package io.renren.modules.zd.vo;

import java.math.BigDecimal;

public class PublishResourceVO {
	private String id;
	private String resourceCode;
	private String resourceName;
	private String isbn;
	private String catalogId;
	private BigDecimal publishPrice;
	private BigDecimal mayng;
	private BigDecimal shiyang;
	private Integer publishNum;
	private Integer realNum;
	private String catalogName;
	private String resourceType;
	private BigDecimal nitemdiscountrate;

	public BigDecimal getShiyang() {
		return shiyang;
	}

	public void setShiyang(BigDecimal shiyang) {
		this.shiyang = shiyang;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BigDecimal getNitemdiscountrate() {
		return nitemdiscountrate;
	}

	public void setNitemdiscountrate(BigDecimal nitemdiscountrate) {
		this.nitemdiscountrate = nitemdiscountrate;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public Integer getPublishNum() {
		return publishNum;
	}

	public void setPublishNum(Integer publishNum) {
		this.publishNum = publishNum;
	}

	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public BigDecimal getPublishPrice() {
		return publishPrice;
	}

	public void setPublishPrice(BigDecimal publishPrice) {
		this.publishPrice = publishPrice;
	}

	public BigDecimal getMayng() {
		return mayng;
	}

	public void setMayng(BigDecimal mayng) {
		this.mayng = mayng;
	}

	public Integer getRealNum() {
		return realNum;
	}

	public void setRealNum(Integer realNum) {
		this.realNum = realNum;
	}
}
