package com.service;

import java.util.List;

import com.bean.BaseMenu;

public interface MenuService {

	public List<BaseMenu> getAllMenuList();
	
	public BaseMenu add (BaseMenu vo) throws Exception;
	
	
	public void edit (BaseMenu vo) throws Exception;
	
	public void remove(BaseMenu vo);
	
	
	public List<BaseMenu> getMenuListByPid(Long pid,Long userid);
	
}
