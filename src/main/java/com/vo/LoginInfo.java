package com.vo;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class LoginInfo implements ValueObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7981502639504377101L;
	
	private String errmsg;

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	

}
