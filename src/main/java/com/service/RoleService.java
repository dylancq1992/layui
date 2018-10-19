package com.service;

import java.util.Map;

import com.bean.BaseRole;
import com.utils.GridModel;
import com.vo.RoleVO;

public interface RoleService {
	
	public GridModel getAllRoleList(Map map,RoleVO vo) ;
	
	public void add(BaseRole vo) throws Exception;
	
	public void edit(BaseRole vo) throws Exception;
	
	public void remove(RoleVO vo) throws Exception;

}
