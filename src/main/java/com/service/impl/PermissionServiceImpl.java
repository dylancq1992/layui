package com.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bean.BaseRole;
import com.bean.BaseRoleMenu;
import com.bean.BaseRoleUser;
import com.bean.BaseUserMenu;
import com.dao.PermissionDao;
import com.service.PermissionService;
import com.utils.GridModel;
import com.vo.MenuVO;
import com.vo.PermissionVO;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionDao permissionDao;

	public void role_menu_save(PermissionVO vo) throws Exception{
		//删除旧的权限
		permissionDao.deleteByRoleid(vo);
		
		//添加新的权限
		List<BaseRoleMenu> list=new ArrayList<BaseRoleMenu>();
		if(StringUtils.isNotBlank(vo.getIds())){
			String menuid[]=vo.getIds().split(",");
			String isLeaf[]=vo.getIsLeaf().split(",");
			for (int i=0;i<menuid.length;i++) {
				BaseRoleMenu bo=new BaseRoleMenu();
				bo.setCreatedate(new Date());
				bo.setCreator(vo.getCreator());
				bo.setRoleid(vo.getRoleid());
				bo.setMenuid(Long.valueOf(menuid[i]));
				bo.setIsleaf(isLeaf[i]);
				list.add(bo);
			}
			if(list.size()>0){
				permissionDao.addNewRoleMenu(list);
				//throw new Exception("123123");
			}
			
		}
		
		
	}
	
	
	public void user_menu_save(PermissionVO vo) throws Exception{
		//删除旧的权限
		permissionDao.deleteByUserid(vo);
		
		//添加新的权限
		List<BaseUserMenu> list=new ArrayList<BaseUserMenu>();
		if(StringUtils.isNotBlank(vo.getIds())){
			String menuid[]=vo.getIds().split(",");
			String isLeaf[]=vo.getIsLeaf().split(",");
			for (int i=0;i<menuid.length;i++) {
				BaseUserMenu bo=new BaseUserMenu();
				bo.setCreator(vo.getCreator());
				bo.setUserid(vo.getUserid());
				bo.setMenuid(Long.valueOf(menuid[i]));
				bo.setIsleaf(isLeaf[i]);
				list.add(bo);
			}
			if(list.size()>0){
				permissionDao.addNewUserMenu(list);
				//throw new Exception("123123");
			}
		}
		
		
	}

	public List<MenuVO> getMenuListByRoleid(PermissionVO vo) {
		
		StringBuffer sb=new StringBuffer();
		if(vo.getRoleid()!=null){
			sb.append(" select mr.*,(case when rm.menuid is not null then '1' else '0' end) ischeck,rm.isleaf from menu_resource mr");
			sb.append(" left join role_menu rm on mr.menuid=rm.menuid and rm.roleid='"+vo.getRoleid()+"' where mr.removed='0' order by mr.priority");
		}else{
			sb.append(" select * from menu_resource  where removed='0' order by priority ");
		}
		
		List<MenuVO> list = permissionDao.queryForList(sb.toString(), MenuVO.class);
		
		for (MenuVO menuVO : list) {
			if("1".equals(menuVO.getIsleaf())&&"1".equals(menuVO.getIscheck())){
				menuVO.setChecked(true);
			}else{
				menuVO.setChecked(false);
			}
		}
		
		return list;
	}
	
	public List<MenuVO> getMenuListByUserid(PermissionVO vo) {
		
		StringBuffer sb=new StringBuffer();
		
	
			//超级管理员
			if("-1".equals(vo.getCreator())){
				if(vo.getUserid()==null){
					
					sb.append(" select * from menu_resource  where removed='0' order by priority ");
				}else{
					sb.append(" select t.*,(case when a.menuid is not null then '1' else '0' end) ischeck,a.isleaf from(  ");
					sb.append(" select * from menu_resource  where removed='0' order by priority  ");
					sb.append(" ) t left join user_menu a ");
					sb.append(" on t.menuid=a.menuid and a.userid='"+vo.getUserid()+"' order by t.priority ");
				}
			}else{
				if(vo.getUserid()==null){
					sb.append("   select mr.* from menu_resource mr  ");
					sb.append(" , role_menu rm , role_user ru  where mr.removed='0' and mr.menuid=rm.menuid and rm.roleid=ru.roleid  ");
					sb.append(" and ru.userid='"+vo.getCreator()+"' group by mr.menuid order by mr.priority ");
				}else{
					sb.append(" select t.*,(case when a.menuid is not null then '1' else '0' end) ischeck,a.isleaf from(  select mr.* from menu_resource mr  ");
					sb.append(" , role_menu rm , role_user ru  where mr.removed='0' and mr.menuid=rm.menuid and rm.roleid=ru.roleid  ");
					sb.append(" and ru.userid='"+vo.getCreator()+"' group by mr.menuid order by mr.priority ) t left join user_menu a ");
					sb.append(" on t.menuid=a.menuid and a.userid='"+vo.getUserid()+"'  order by t.priority ");
				}
			
			
		}
		
		
		List<MenuVO> list = permissionDao.queryForList(sb.toString(), MenuVO.class);
		
		for (MenuVO menuVO : list) {
			if("1".equals(menuVO.getIsleaf())&&"1".equals(menuVO.getIscheck())){
				menuVO.setChecked(true);
			}else{
				menuVO.setChecked(false);
			}
		}
		
		return list;
	}

	public GridModel getRoleListByUserid(Map map, PermissionVO vo) {
		StringBuffer sb=new StringBuffer();
		sb.append(" select r.* from  role r,role_user ru where r.roleid=ru.roleid and ru.userid='"+vo.getUserid()+"' ");
		
		GridModel result = null;
		try {
			result = permissionDao.getPageForMySql(sb.toString(), BaseRole.class, map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<BaseRole> getRoleByUserid(PermissionVO vo) {
		
		String sql="select r.* from role  r where not EXISTS(SELECT 1 from role_user r2 where r.roleid=r2.roleid and r2.userid='"+vo.getUserid()+"')";
		
		return permissionDao.queryForList(sql, BaseRole.class);
	}

	public void role_user_save(PermissionVO vo) throws Exception {
		String roleid[]=vo.getIds().split(",");
		List<BaseRoleUser> list=new ArrayList<BaseRoleUser>();
		for (String string : roleid) {
			BaseRoleUser bo=new BaseRoleUser();
			bo.setCreatedate(new Date());
			bo.setCreator(vo.getCreator());
			bo.setUserid(vo.getUserid());
			bo.setRoleid(Long.valueOf(string));
			list.add(bo);
		}
		if(list.size()>0){
			permissionDao.role_user_save(list);
		}
	}

	public void remove_role_user(PermissionVO vo) throws Exception {
		String sql="delete from role_user where userid='"+vo.getUserid()+"' and roleid in ('"+vo.getIds()+"')  ";
		permissionDao.getJt().update(sql);
	}
}
