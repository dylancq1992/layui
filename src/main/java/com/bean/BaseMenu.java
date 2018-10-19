package com.bean;

import java.io.Serializable;

/**
 * 菜单实体类
 * 
 * @date 2018-7-5 下午2:34:23
 * @author wanghongwei
 * 
 */
public class BaseMenu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long menuid;// 菜单标识
	private Long parentmenuid;// 父菜单
	private String code;// 代码
	private String name;// 名称
	private String description;// 描述
	private String url;// 链接
	private Integer priority;// 优先级
	private String status;// 状态
	private String removed;// 删除标志
	private String menutype;//
	private String allowcustom;// 是否允许自定义菜单(0 不支持， 1支持 )
	private String icon;// 图标名称
	private String levels;//树的层级关系

	public Long getMenuid() {
		return menuid;
	}

	public void setMenuid(Long menuid) {
		this.menuid = menuid;
	}

	public Long getParentmenuid() {
		return parentmenuid;
	}

	public void setParentmenuid(Long parentmenuid) {
		this.parentmenuid = parentmenuid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemoved() {
		return removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public String getAllowcustom() {
		return allowcustom;
	}

	public void setAllowcustom(String allowcustom) {
		this.allowcustom = allowcustom;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	
}
