/** 
 * @(#)SessionConstants.java 2008-10-24
 * 
 * Copyright (c) 1995-2008 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package com.vo;

/**
 * Session常量
 * @date 2018-7-9 下午5:31:42
 * @author wanghongwei
 *
 */
public interface SessionConstants {

	/**
	 * 操作员编号
	 */
	public static final String WSSIP_OPERATOR_ID = "operator.id";

	/**
	 * 操作员登录名
	 */
	public static final String WSSIP_OPERATOR_LOGINNAME = "operator.loginname";

	/**
	 * 操作员姓名
	 */
	public static final String WSSIP_OPERATOR_NAME = "operator.name";

	/**
	 * 操作员对象(SecurityUser)
	 */
	public static final String WSSIP_OPERATOR = "operator";
	
	/**
	 * 操作员所属机构类型
	 */
	public static final String WSSIP_OPERATOR_ORGANTYPE = "operator.organtype";
	
	
	/**
	 * 操作员所属机构行政区划
	 */
	public static final String WSSIP_OPERATOR_DISTRICTCODE = "operator.districtcode";
	
	/**
	 * 操作员所属机构
	 */
	public static final String WSSIP_OPERATOR_ORGANID = "operator.organid";
	
	/**
	 * 操作员所属机构code
	 */
	public static final String WSSIP_OPERATOR_ORGANCODE = "operator.organcode";
	
	/**
	 * 操作员所属机构name
	 */
	public static final String WSSIP_OPERATOR_ORGANNAME = "operator.organname";
	
	
	/**
	 * 操作员客户端MAC地址
	 */
	public static final String WSSIP_CLIENT_MACADDRESS = "operator.macaddress";

	/**
	 * 权限代理
	 */
	public static final String WSSIP_OPERATOR_SECURITYAGENT = "operator.securityagent";

	/**
	 * 业务操作上下文业务编码
	 */
	public static final String WSSIP_ENVIRONMENT_BUSINESSTYPE = "environment.businesstype";
	
	/**
	 * APPLIACTON CODE
	 */
	/*public static final String WSSIP_SYSTEM_APPLICATIONCODE = "com.wondersgroup.wssip.applicationcode";*/
	/**
	 * domain code
	 */
	/*public static final String WSSIP_SYSTEM_DOMAINCODE = "com.wondersgroup.wssip.domaincode";*/
	
	
	/*public static final String WSSUAP_MENU_MAP = "com.wondersgroup.wssuap.menu.map";*/
	
	/**
	 * 所在地行政区划
	 */
	//public static final String WSSIP_OPERATOR_LOCALDISTRICTCODE = "com.wondersgroup.wssip.operator.localdistrictcode";
	
	/**
	 * 隶属关系
	 */
	//public static final String WSSIP_OPERATOR_AFFILIATION = "com.wondersgroup.wssip.operator.affiliation";
	
	/**
	 * 组织父id
	 */
	//public static final String WSSIP_OPERATOR_PARENTORGANID = "com.wondersgroup.wssip.operator.parentorganid";
	
}
