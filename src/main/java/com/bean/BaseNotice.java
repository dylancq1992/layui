package com.bean;

import java.util.Date;

public class BaseNotice {

	private Long noticeid;
	private String title;
	private String content;
	private String validity;// 默认值1
	private String status;// 状态
	private Date pubtime;// 发布时间
	private String pubname;
	private Date createdate;
	private String creator;
	private Date modifydate;
	private String modifier;

	public Long getNoticeid() {
		return noticeid;
	}

	public void setNoticeid(Long noticeid) {
		this.noticeid = noticeid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPubtime() {
		return pubtime;
	}

	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}

	public String getPubname() {
		return pubname;
	}

	public void setPubname(String pubname) {
		this.pubname = pubname;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

}
