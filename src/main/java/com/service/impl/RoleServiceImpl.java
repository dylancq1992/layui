package com.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bean.BaseMenu;
import com.bean.BaseRole;
import com.bean.BaseUser;
import com.dao.RoleDao;
import com.service.RoleService;
import com.utils.GridModel;
import com.vo.RoleVO;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleDao roleDao;

	public GridModel getAllRoleList(Map map, RoleVO vo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * ");
		sql.append("  FROM role where validity = '1' ");
		GridModel result = null;
		try {
			result = roleDao.getPageForMySql(sql.toString(), BaseRole.class, map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void add(BaseRole vo) throws Exception {
		
		String sql="select * from role where code='"+vo.getCode()+"' and validity='1' ";
		
		List<BaseRole> list=roleDao.queryForList(sql, BaseRole.class);
		
		if(list.size()>0){
			throw new Exception("角色代码已经存在！");
		}
		vo.setValidity("1");
		vo.setCreatedate(new Date());
		roleDao.add(vo);
		
	}

	public void edit(BaseRole vo) throws Exception {
		
		vo.setModifydate(new Date());
		roleDao.edit(vo);
	}

	public void remove(RoleVO vo) throws Exception {
		String sql="update role set validity='0' where roleid in ('"+vo.getIds()+"')";
		roleDao.getJt().update(sql);
	}

}
