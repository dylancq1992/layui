package com.bean;

import java.util.Date;

/**
 * 组织表
 * @date 2018-7-12 上午10:56:39
 * @author wanghongwei
 *
 */
public class BaseOrgan {

	private Long organid;
	private Long parentorganid;
	private String organ_type;
	private String code;
	private String name;
	private String description;
	private String grade;
	private String validity;
	private String districtc_code;
	private Date createdate;
	private String creator;
	private Date modifydate;
	private String modifier;
	private String levels;

	public Long getOrganid() {
		return organid;
	}

	public void setOrganid(Long organid) {
		this.organid = organid;
	}

	public Long getParentorganid() {
		return parentorganid;
	}

	public void setParentorganid(Long parentorganid) {
		this.parentorganid = parentorganid;
	}

	public String getOrgan_type() {
		return organ_type;
	}

	public void setOrgan_type(String organ_type) {
		this.organ_type = organ_type;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}


	public String getDistrictc_code() {
		return districtc_code;
	}

	public void setDistrictc_code(String districtc_code) {
		this.districtc_code = districtc_code;
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

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}
	

}
