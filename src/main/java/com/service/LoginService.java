package com.service;

import java.util.List;

import com.bean.BaseMenu;
import com.bean.BaseUser;
import com.vo.UserVO;

public interface LoginService {

	BaseUser getByLoginName(UserVO vo) throws Exception;
	
	List<BaseMenu> getUserMenu(BaseUser vo);
}
