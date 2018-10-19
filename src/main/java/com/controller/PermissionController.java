package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.BaseRole;
import com.service.PermissionService;
import com.utils.GridModel;
import com.utils.PageUtil;
import com.vo.MenuVO;
import com.vo.PermissionVO;
import com.vo.RoleVO;
import com.vo.SessionConstants;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	
	@Resource
	private PermissionService permissionService;
	
	
	@RequestMapping("/getMenuListByRoleid")
	@ResponseBody
	public List<MenuVO> getMenuListByRoleid(PermissionVO vo) throws Exception {

		List<MenuVO> list = permissionService.getMenuListByRoleid(vo);

		return list;
	}
	
	@RequestMapping("/getMenuListByUserid")
	@ResponseBody
	public List<MenuVO> getMenuListByUserid(PermissionVO vo,HttpServletRequest request) throws Exception {
		
		vo.setCreator((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		
		List<MenuVO> list = permissionService.getMenuListByUserid(vo);

		return list;
	}
	
	@RequestMapping("/role_menu_save")
	@ResponseBody
	public Map role_menu_save(PermissionVO vo,HttpServletRequest request){
		vo.setCreator((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			permissionService.role_menu_save(vo);
			map.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
		
	}
	
	@RequestMapping("/user_menu_save")
	@ResponseBody
	public Map user_menu_save(PermissionVO vo,HttpServletRequest request){
		vo.setCreator((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			permissionService.user_menu_save(vo);
			map.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
		
	}
	
	
	@RequestMapping("/getRoleListByUserid")
	@ResponseBody
	public GridModel getRoleListByUserid(HttpServletRequest request, HttpServletResponse response,PermissionVO vo) throws Exception{
			
		Map<String, String> map = PageUtil.putPageParam(request);
		
		GridModel result=permissionService.getRoleListByUserid(map, vo);
		
		return result;
	}
	
	@RequestMapping("/getRoleByUserid")
	@ResponseBody
	public List<BaseRole> getRoleByUserid(PermissionVO vo){
		
		return permissionService.getRoleByUserid(vo);
	}

	
	@RequestMapping("/role_user_save")
	@ResponseBody
	public Map role_user_save(PermissionVO vo,HttpServletRequest request){
		vo.setCreator((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			permissionService.role_user_save(vo);
			map.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
		
	}
	
	
	@RequestMapping("/remove_role_user")
	@ResponseBody
	public Map remove_role_user(PermissionVO vo){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			permissionService.remove_role_user(vo);
			map.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
		
	}
	
	
}
