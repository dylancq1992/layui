package com.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bean.BaseRole;

@Repository
public class RoleDao extends BaseDAO {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public void add(BaseRole vo) {

		sqlSessionTemplate.insert("com.dao.RoleDao.add", vo);

	}

	
	public void edit(BaseRole vo) throws Exception {
		
		sqlSessionTemplate.update("com.dao.RoleDao.edit", vo);
	}
}
