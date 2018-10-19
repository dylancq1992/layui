package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bean.BaseMenu;
import com.dao.MenuDao;
import com.service.BaseService;
import com.service.MenuService;

@Service
public class MenuServiceImpl   implements MenuService {
	
	@Resource
	private MenuDao menuDao;

	public List<BaseMenu> getAllMenuList() {
		return menuDao.getAllMenuList();
	}

	public BaseMenu add(BaseMenu vo) throws Exception {
		
		String sql="select * from menu_resource where code='"+vo.getCode()+"' and removed='0' ";
		
		List<BaseMenu> list=menuDao.queryForList(sql, BaseMenu.class);
		if(list.size()>0){
			throw new Exception("菜单代码已经存在！");
		}
		vo.setStatus("1");
		vo.setRemoved("0");
		return menuDao.add(vo);
	}

	public void remove(BaseMenu vo) {
		
		menuDao.remove(vo);
		
	}

	public void edit(BaseMenu vo) throws Exception {
		
		menuDao.edit(vo);
		
	}

	public List<BaseMenu> getMenuListByPid(Long pid,Long userid) {
		StringBuffer sb=new StringBuffer();
		//超级管理员
		if(-1==userid){
			sb.append(" select * from menu_resource where FIND_IN_SET(menuid,getChildLst('"+pid+"'))  and levels in ('3','4') order by priority ");
		}else{
			sb.append(" select a.* from (");
			sb.append(" select  t1.* from menu_resource t1,role_menu t2,role_user t3  ");
			sb.append("  where FIND_IN_SET(t1.menuid,getChildLst('"+pid+"')) and t1.levels in ('3','4')");
			sb.append(" and t1.menuid=t2.menuid and t2.roleid=t3.roleid and t3.userid='"+userid+"'");
			
			sb.append(" UNION");
			
			sb.append("  select  t1.* from menu_resource t1,user_menu t2 ");
			sb.append(" where FIND_IN_SET(t1.menuid,getChildLst('"+pid+"')) and t1.levels in ('3','4') ");
			sb.append("  and t1.menuid=t2.menuid  and t2.userid='"+userid+"' and  t1.removed='0' ");
			sb.append(" ) a order by a.priority ");
		}
		
		List<BaseMenu> list=menuDao.queryForList(sb.toString(), BaseMenu.class);
		return list;
	}

	
	
}
