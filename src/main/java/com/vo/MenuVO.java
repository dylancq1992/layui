package com.vo;

import com.bean.BaseMenu;

public class MenuVO extends BaseMenu{
	
	private boolean checked;
	private String ischeck;
	private String isleaf;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getIscheck() {
		return ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}

	public MenuVO() {
		
	}
	
	
	

}
