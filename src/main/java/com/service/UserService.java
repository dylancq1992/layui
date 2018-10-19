package com.service;

import java.util.List;
import java.util.Map;

import com.bean.BaseNotice;
import com.bean.BaseUser;
import com.utils.GridModel;
import com.vo.ChartBarVO;
import com.vo.UserVO;

public interface UserService {
	
	public GridModel getAllMenuList(Map map,UserVO vo) ;
	
	public GridModel getUserByOrgan(Map map,UserVO vo) ;
	
	public BaseUser add(BaseUser vo) throws Exception;
	
	public void edit(UserVO vo) throws Exception;
	
	public void remove(UserVO vo) throws Exception;
	
	public void doCancel(UserVO vo) throws Exception;
	
	public void updatepwd(UserVO vo) throws Exception;
	
	public GridModel getMenuUserList(Map map,UserVO vo) ;
	
	public ChartBarVO getLoginCount(UserVO vo) throws Exception;
	
	public List<BaseNotice> getNotice(UserVO vo);

}
