package io.renren.modules.sys.vo;

import io.renren.modules.sys.entity.SysOrgEntity;

public class SysOrgVO1 extends SysOrgEntity {
	private String person;
	private String mobile;
	private String address;
	private String zipCode;
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}
