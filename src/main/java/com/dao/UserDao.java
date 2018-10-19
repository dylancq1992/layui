package com.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bean.BaseMenu;
import com.bean.BaseUser;
import com.utils.GridModel;
import com.vo.UserVO;

@Repository
public class UserDao extends BaseDAO {
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public GridModel getAllMenuList(Map map, UserVO vo) {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT userid, loginname,user_type, name, sex, telephone, address, mobile_phone, fax, email, ");
		sql.append(" qq, status, organid, bindip, validity,modifier,createdate,creator,modifydate FROM user where validity = '1' ");
		
		//超级管理员可查询所有
		if(!"-1".equals(vo.getCreator())){
			sql.append(" and creator='"+vo.getCreator()+"' ");
		}
		
		if(StringUtils.isNotBlank(vo.getQuery())){
			sql.append(" and name like '%"+vo.getQuery()+"%'  or  loginname like '%"+vo.getQuery()+"%' ");
		}
		
		GridModel result = null;
		try {
			result = this.getPageForMySql(sql.toString(), BaseUser.class, map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public BaseUser add(BaseUser vo) {
		
		sqlSessionTemplate.insert("com.dao.UserDao.add", vo);

		return vo;
	}
	
	
	public void edit(UserVO vo) {
		
		sqlSessionTemplate.insert("com.dao.UserDao.edit", vo);

	}
}
