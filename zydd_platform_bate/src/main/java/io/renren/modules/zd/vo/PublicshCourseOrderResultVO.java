package io.renren.modules.zd.vo;

import io.renren.common.utils.PageUtils;

/**
 * 订单发行时候返回教材信息的 总结果 包含单位的信息
 * @author lile
 *
 */
public class PublicshCourseOrderResultVO {
	
	private String orgId;//单位ID
	
	private String orgName; //单位名称
	
	private String person; 

	private String telPhone;
	
	private String zipCode; //邮编
	
	private String address ;//
	
	private PageUtils pageUtil;
	

	
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PageUtils getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(PageUtils pageUtil) {
		this.pageUtil = pageUtil;
	}

	
}
