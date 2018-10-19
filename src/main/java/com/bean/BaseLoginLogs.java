package com.bean;

import java.util.Date;

/**
 * 登录日志
 * 
 * @date 2018-7-9 上午9:33:57
 * @author wanghongwei
 * 
 */
public class BaseLoginLogs {

	private Long logid;
	private Long userid;
	private String loginname;
	private String sessionid;
	private Date login_time;
	private String ip_address;
	private String mac_address;
	private String area;
	private String operating_system;
	private String browser;
	private String user_status;
	private String error_info;

	public Long getLogid() {
		return logid;
	}

	public void setLogid(Long logid) {
		this.logid = logid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOperating_system() {
		return operating_system;
	}

	public void setOperating_system(String operating_system) {
		this.operating_system = operating_system;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getError_info() {
		return error_info;
	}

	public void setError_info(String error_info) {
		this.error_info = error_info;
	}

}
