package com.bean;

public class BaseNoticeAddress {
	private Long id;
	private Long noticeid;
	private Long addressid;
	private String addresstype;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(Long noticeid) {
		this.noticeid = noticeid;
	}
	public Long getAddressid() {
		return addressid;
	}
	public void setAddressid(Long addressid) {
		this.addressid = addressid;
	}
	public String getAddresstype() {
		return addresstype;
	}
	public void setAddresstype(String addresstype) {
		this.addresstype = addresstype;
	}
	
	

}
