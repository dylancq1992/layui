package com.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bean.BaseLoginLogs;

@Repository
public class LoginLogsDao {
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void saveLogs(BaseLoginLogs vo) {
		
		sqlSessionTemplate.insert("com.dao.LoginLogsDao.saveLogs", vo);
		
		
	}

}
