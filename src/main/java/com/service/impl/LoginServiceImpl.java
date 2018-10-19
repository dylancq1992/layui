package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bean.BaseMenu;
import com.bean.BaseUser;
import com.dao.LoginDao;
import com.encoder.impl.SHAPasswordEncoderImpl;
import com.service.LoginService;
import com.vo.UserVO;
import com.vo.WebResult;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Resource
	private LoginDao loginDao;
	
	@Resource(name = "passwordEncoderFactory")
	private SHAPasswordEncoderImpl passwordEncoder;
	

	public BaseUser getByLoginName(UserVO vo) throws Exception {
		
		BaseUser user=loginDao.getByLoginName(vo);
		
		if(user==null){
			throw new Exception(WebResult.INCORRECT_NULL_USER);
		}
		
		if(!user.getPassword().equals(passwordEncoder.encodePassword(vo.getPassword()))){
			
			throw new Exception(WebResult.AUTHENTICATION_EXCEPTION);
		}
		
		return user;
	}


	public List<BaseMenu> getUserMenu(BaseUser vo) {
		
		StringBuffer sb=new StringBuffer();
		
		if(-1==vo.getUserid()){
			sb.append("select *  from menu_resource t where t.removed='0' and t.levels='2' order by priority asc ");
		}else{
			sb.append(" select a.* from (");
			sb.append(" select t1.* from menu_resource t1,role_menu t2,role_user t3  ");
			sb.append(" where t1.menuid=t2.menuid and t2.roleid=t3.roleid and t3.userid='"+vo.getUserid()+"' ");
			sb.append(" and t1.levels='2' and t1.removed='0'  UNION  ");
			sb.append(" select t1.* from menu_resource t1,user_menu t2 where t1.menuid=t2.menuid and");
			sb.append(" t2.userid='"+vo.getUserid()+"'  and t1.levels='2' and t1.removed='0' ");
			sb.append(" ) a order by a.priority");
		}
		
		List<BaseMenu> list=loginDao.queryForList(sb.toString(), BaseMenu.class);
		return list;
	}

}
