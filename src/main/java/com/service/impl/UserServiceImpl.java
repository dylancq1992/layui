package com.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.bean.BaseMenu;
import com.bean.BaseNotice;
import com.bean.BaseUser;
import com.dao.UserDao;
import com.encoder.impl.SHAPasswordEncoderImpl;
import com.utils.GridModel;
import com.vo.ChartBarVO;
import com.vo.CountVO;
import com.vo.UserVO;

@Service
public class UserServiceImpl implements com.service.UserService {
	
	@Resource
	private UserDao userDao;
	
	@Resource(name = "passwordEncoderFactory")
	private SHAPasswordEncoderImpl passwordEncoder;

	public GridModel getAllMenuList(Map map, UserVO vo) {
		
		return userDao.getAllMenuList(map,vo);
	}

	public BaseUser add(BaseUser vo) throws Exception {
		
		String sql="select * from user where loginname='"+vo.getLoginname()+"'";
		
		List<BaseMenu> list=userDao.queryForList(sql, BaseMenu.class);
		if(list.size()>0){
			throw new Exception("登录名已经存在！");
		}
		
		vo.setUser_type("1");
		vo.setCreatedate(new Date());
		vo.setValidity("1");
		vo.setStatus("1");
		
		vo.setPassword(passwordEncoder.encodePassword(vo.getPassword()));
		
		return userDao.add(vo);
		
	}

	public void edit(UserVO vo) throws Exception {
		
		vo.setModifydate(new Date());
		
		if(StringUtils.isNotBlank(vo.getNewpassword())){
			vo.setNewpassword(passwordEncoder.encodePassword(vo.getNewpassword()));
		}
		
		userDao.edit(vo);
		
		
	}

	public void remove(UserVO vo) throws Exception {
		
		String sql="update user set validity='0',modifydate=sysdate(),modifier='"+vo.getModifier()+"' where userid  in ('"+vo.getIds()+"')";
		
		userDao.getJt().update(sql);
		
		
	}

	public void doCancel(UserVO vo) throws Exception {
		
		String sql="update user set status='"+vo.getStatus()+"',modifydate=sysdate(),modifier='"+vo.getModifier()+"' where userid  in ('"+vo.getIds()+"')";
		
		userDao.getJt().update(sql);
		
	}

	public GridModel getUserByOrgan(Map map,UserVO vo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT userid, loginname,user_type, name, sex, telephone, address, mobile_phone, fax, email, ");
		sql.append(" qq, status, organid, bindip, validity,modifier,createdate,creator,modifydate FROM user where validity = '1' ");
		sql.append(" and organid ='"+vo.getOrganid()+"'");
		GridModel result = null;
		try {
			result = userDao.getPageForMySql(sql.toString(), BaseUser.class, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void updatepwd(UserVO vo) throws Exception {
		String sql="select * from user where userid='"+vo.getUserid()+"' and  password='"+passwordEncoder.encodePassword(vo.getPassword())+"' ";
		
		BaseUser baseUser=userDao.queryForObject(sql, BaseUser.class);
		
		if(baseUser==null){
			throw new Exception("旧密码输入不正确！");
		}
		
		String sql2="update user set password='"+passwordEncoder.encodePassword(vo.getNewpassword())+"' where userid='"+vo.getUserid()+"' ";
		
		userDao.getJt().update(sql2);
		
	}

	public GridModel getMenuUserList(Map map, UserVO vo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT userid, loginname,user_type, name, sex, telephone, address, mobile_phone, fax, email, ");
		sql.append(" qq, status, organid, bindip, validity,modifier,createdate,creator,modifydate FROM user where validity = '1' ");
		sql.append(" and userid <> '"+vo.getCreator()+"' ");
		
		//超级管理员可查询所有
		if(!"-1".equals(vo.getCreator())){
			sql.append(" and creator='"+vo.getCreator()+"' ");
		}
		
		GridModel result = null;
		try {
			result = userDao.getPageForMySql(sql.toString(), BaseUser.class, map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ChartBarVO getLoginCount(UserVO vo) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append(" select count(1) as column1,t.column2 from(");
		sb.append("  SELECT ct.logid ,date_format(ct.login_time,'%Y-%m-%d') column2  FROM");
		sb.append("  login_logs ct where DATE_SUB(CURDATE(), INTERVAL 11 DAY) <=ct.login_time and");
		sb.append("  ct.user_status='1' ");
		sb.append("  ) t group by t. column2 ORDER BY t.column2 asc");
		
		List<CountVO> list=userDao.queryForList(sb.toString(), CountVO.class);
		
		List<String> categories =new ArrayList<String>();
		List<Integer> data=new ArrayList<Integer>();
		for (CountVO countVO : list) {
			categories.add(countVO.getColumn2());
			data.add(countVO.getColumn1());
		}
		ChartBarVO chatVO=new ChartBarVO();
		chatVO.setCategories(categories);
		chatVO.setData(data);
		
		return chatVO;
	}

	@Override
	public List<BaseNotice> getNotice(UserVO vo) {
		StringBuffer sb=new StringBuffer();
		sb.append("select * from ( select t1.noticeid,t1.title from notice t1,notice_address t2,user t3 ");
		sb.append(" where t1.noticeid=t2.noticeid and t2.addressid=t3.organid and t3.userid='"+vo.getUserid()+"' order by t1.createdate desc ) t limit 2 ");
		return userDao.queryForList(sb.toString(), BaseNotice.class);
	}

}
