package com.service;

import java.util.List;
import java.util.Map;

import com.bean.BaseRole;
import com.utils.GridModel;
import com.vo.MenuVO;
import com.vo.PermissionVO;

public interface PermissionService {
	
	public void role_menu_save(PermissionVO vo) throws Exception;
	
	public void user_menu_save(PermissionVO vo) throws Exception;
	
	public List<MenuVO> getMenuListByRoleid(PermissionVO vo);
	
	public List<MenuVO> getMenuListByUserid(PermissionVO vo);
	
	public GridModel getRoleListByUserid(Map map,PermissionVO vo);
	
	public List<BaseRole> getRoleByUserid(PermissionVO vo);
	
	public void role_user_save(PermissionVO vo) throws Exception;
	
	public void remove_role_user(PermissionVO vo) throws Exception;

}
