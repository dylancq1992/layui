package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bean.BaseRoleMenu;
import com.bean.BaseRoleUser;
import com.bean.BaseUserMenu;
import com.vo.PermissionVO;

@Repository
public class PermissionDao extends BaseDAO{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void deleteByRoleid(PermissionVO vo){
		sqlSessionTemplate.delete("com.dao.PermissionDao.deleteByRoleid",vo);
	}
	
	public void deleteByUserid(PermissionVO vo){
		sqlSessionTemplate.delete("com.dao.PermissionDao.deleteByUserid",vo);
	}
	
	
	public void addNewRoleMenu(List<BaseRoleMenu> list){
		sqlSessionTemplate.insert("com.dao.PermissionDao.addNewRoleMenu",list);
	}
	
	public void addNewUserMenu(List<BaseUserMenu> list){
		sqlSessionTemplate.insert("com.dao.PermissionDao.addNewUserMenu",list);
	}
	
	public void role_user_save(List<BaseRoleUser> list) throws Exception {
		sqlSessionTemplate.delete("com.dao.PermissionDao.role_user_save",list);
	}
}
