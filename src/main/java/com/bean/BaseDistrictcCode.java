package com.bean;

/**
 * 行政区划代码表
 * @date 2018-7-12 上午10:58:25
 * @author wanghongwei
 *
 */
public class BaseDistrictcCode {

	private Long id;//
	private String name;// 名称
	private Long parentid;// 父级字典项ID
	private String orderno;// 显示顺序
	private String status;// 状态
	private String levels;// 级别
	private String listnum;// 列表号
	private String appraise_organ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getListnum() {
		return listnum;
	}

	public void setListnum(String listnum) {
		this.listnum = listnum;
	}

	public String getAppraise_organ() {
		return appraise_organ;
	}

	public void setAppraise_organ(String appraise_organ) {
		this.appraise_organ = appraise_organ;
	}

}
