package io.renren.modules.zd.vo;

/**
 * 报订发行返回教材
 * @author lile
 *
 */
public class PublishCourseOrderVO {
	
	private String orderId; //征订id
	
	private String ordeNo;  //征订单号
	
	private String orgCode;// 发行单位
	
	//教材字段
	private String resourceId; //教材Id
	
	private String resourceCode ; // 教材代码
	
	private String  resourceName;// 教材名称
	
	private String catalogName ; //分类
	
	private String author;
	
	private String companyName; //出版社
	
	private String resourceVersion ;// 
	
	
	private double price;// 定价
	
	//库存
	private String  stockId ;//库存Id
	
	private long  stockNum;//库存数量
	
	private int orderNum; //征订数量
	
	private  long publishedNum;//已发行数量

		

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrdeNo() {
		return ordeNo;
	}

	public void setOrdeNo(String ordeNo) {
		this.ordeNo = ordeNo;
	}

	

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
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
	
	
	
	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public long getStockNum() {
		return stockNum;
	}

	public void setStockNum(long stockNum) {
		this.stockNum = stockNum;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getResourceVersion() {
		return resourceVersion;
	}

	public void setResourceVersion(String resourceVersion) {
		this.resourceVersion = resourceVersion;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public long getPublishedNum() {
		return publishedNum;
	}

	public void setPublishedNum(long publishedNum) {
		this.publishedNum = publishedNum;
	}
	
	
	
}
