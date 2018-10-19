package com.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bean.BaseOrgan;
import com.vo.OrganVO;

@Repository
public class OrganDao  extends BaseDAO{
	
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public BaseOrgan add(BaseOrgan vo) {
		
		sqlSessionTemplate.insert("com.dao.OrganDao.add",vo);
		
		return vo;
	}
	
	public void edit(OrganVO vo) {
		
		sqlSessionTemplate.insert("com.dao.OrganDao.edit",vo);
	}

}
