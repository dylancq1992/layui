package com.bean;

import java.util.Date;

/**
 * 角色和菜单关系表
 * 
 * @date 2018-7-12 上午10:56:13
 * @author wanghongwei
 * 
 */
public class BaseRoleMenu {

	private Long id;
	private Long roleid;
	private Long menuid;
	private Date createdate;
	private String creator;
	private String isleaf;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public Long getMenuid() {
		return menuid;
	}

	public void setMenuid(Long menuid) {
		this.menuid = menuid;
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

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}

	

	
}
