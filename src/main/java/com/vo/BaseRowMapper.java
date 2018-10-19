/** 
 * @(#)UserRowMapper.java Jun 2, 2010
 * 
 * Copyright (c) 1995-2010 Wonders Information Co.,Ltd. 
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

 
import java.sql.ResultSet;
 
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.utils.InstanceUtils;

 
/**
 * 
 * @date 2018-7-9 下午5:31:52
 * @author wanghongwei
 *
 */
public class BaseRowMapper extends AbstractRowResolve implements RowMapper {

	public BaseRowMapper(Class clazz) {
		this.clazz = clazz;
	}

	public Object mapRow(ResultSet rs, int pos) throws SQLException {
		Object dto = null;
		dto = InstanceUtils.getInstance(clazz);
		invoke(dto, rs);

		return dto;
	}

}
