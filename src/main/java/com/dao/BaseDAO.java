/*
 * Copyright wang
 */
package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import com.utils.DateUtil;
import com.utils.GridModel;
import com.vo.BaseRowMapper;

/**
 * Base DAO Class, 业务系统的DAO需要继承BaseDAO
 * 
 * @author wanghongwei
 * 
 */

@Repository
public class BaseDAO {

	@Resource(name = "basicJdbcDAO")
	private JdbcTemplate jt;

	public JdbcTemplate getJt() {
		return jt;
	}

	/** logger4j日志，子类可以直接使用 */
	protected Logger logger = Logger.getLogger(getClass());

	/**
	 * 查询单条记录
	 * 
	 * @param <R>
	 * @param sql
	 * @param clazz
	 * @param arguments
	 * @return
	 * @throws DAOException
	 */
	public <R> R queryForObject(String sql, Class<R> clazz, Object... arguments)
			throws DataAccessException {

		List<R> dtos = queryForList(sql, clazz, arguments);

		if (dtos.size() == 0) {
			return null;
		} else if (dtos.size() > 1) {
			return dtos.get(0);
		}

		return dtos.get(0);

	}

	/**
	 * 查询多条记录
	 * 
	 * @param <R>
	 * @param sql
	 * @param clazz
	 * @param arguments
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <R> List<R> queryForList(String sql, Class<R> clazz,
			Object... arguments) throws DataAccessException {

		List<R> result = jt.query(sql, arguments, new BaseRowMapper(clazz));

		return result == null ? new ArrayList<R>() : result;
	}

	/**
	 * 根据类型查询
	 * 
	 * @param <R>
	 * @param sql
	 * @param clazz
	 * @param arguments
	 * @return
	 * @throws DAOException
	 */
	public <R> R queryObject(String sql, Class<R> clazz, Object... arguments)
			throws DataAccessException {

		return jt.queryForObject(sql, clazz);

	}

	public String getSequence(String sql) {

		String ret = new String("");
		try {
			Object objID = this.getJt().queryForObject(sql, String.class);
			if (objID != null)
				ret = (String) objID;
		} catch (Exception e) {
			e.printStackTrace();

		}
		if (ret.equals("")) {
			System.out
					.println("SequeceManager getSequence return null Error ! sql='"
							+ sql + "");
		}
		return ret;

	}

	public String getSequence(String sequence_name, int length, String pattern,
			boolean date) {
		String sql1 = "SELECT lpad( " + sequence_name + ".nextval," + length
				+ ",'0') FROM dual";
		String sql2 = "SELECT sysdate FROM dual";

		String ret = new String("");
		try {

			Object objID1 = this.getJt().queryForObject(sql1, String.class);
			if (objID1 != null)
				ret = (String) objID1;
			if (date) {

				Object objID2 = this.getJt().queryForObject(sql2, Object.class);
				java.util.Date d = (Date) objID2;
				ret = DateUtil.getDateStrCompact(d) + ret;
			}
			if (!StringUtils.isEmpty(pattern)) {
				ret = pattern + ret;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ret.equals("")) {
			System.out
					.println("SequeceManager getSequence return null Error ! sql1='"
							+ sql1 + "sql2=" + sql2);
		}
		return ret;
	}

	public Map getPage(String sql, Class target, Map map) throws Exception {
		int pageIndex = Integer.parseInt(map.get("pageIndex").toString());
		int pageSize = Integer.parseInt(map.get("pageSize").toString());

		String sortField = (String) map.get("sortField");
		String sortOrder = (String) map.get("sortOrder");

		int start = pageIndex * pageSize, end = start + pageSize;

		String countSql = "select count(1) from (" + sql + ")";

		int count = getJt().queryForObject(countSql, Integer.class);

		StringBuffer sb = new StringBuffer();
		sb.append("select * from ( select temp_table.*, rownum as cnt from (");
		sb.append(sql);

		if (StringUtils.isEmpty(sortField) == false) {
			/*
			 * if ("desc".equals(sortOrder) ) sortOrder = "asc";
			 */

			sb.append(" order by " + sortField + " " + sortOrder);
		}

		sb.append(") temp_table) where cnt >= " + (1 + start) + " and cnt <= "
				+ end);

		logger.info(sb.toString());
		List list = (List) getJt().query(sb.toString(),
				new BaseRowMapper(target));

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", count);
		result.put("data", list);

		return result;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GridModel getPageForMySql(String sql, Class target, Map map){
		
		GridModel gridModel = new GridModel();
		
		
		int pageIndex = Integer.parseInt(map.get("pageIndex").toString());
		int pageSize = Integer.parseInt(map.get("pageSize").toString());

		String sortField = (String) map.get("sortField");
		String sortOrder = (String) map.get("sortOrder");

		int start = (pageIndex - 1) * pageSize;

		String countSql = "select count(1) from (" + sql + ") temp_table ";

		Long count = getJt().queryForObject(countSql, Long.class);
		
		gridModel.setTotal(count);

		StringBuffer sb = new StringBuffer();
		sb.append("select temp_table.* from (");
		sb.append(sql);

		if (StringUtils.isEmpty(sortField) == false) {

			sb.append(" order by " + sortField + " " + sortOrder);
		}

		sb.append(") temp_table  limit  " + start + " , " + pageSize);

		List list = (List) getJt().query(sb.toString(),new BaseRowMapper(target));

		gridModel.setRows(list);

		return gridModel;

	}

}
