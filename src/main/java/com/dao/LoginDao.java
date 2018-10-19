package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bean.BaseUser;
import com.vo.UserVO;

@Repository
public class LoginDao  extends BaseDAO{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public BaseUser getByLoginName(UserVO vo) {
		
		List<BaseUser> list=sqlSessionTemplate.selectList("com.dao.LoginDao.getByLoginName", vo);
		
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
