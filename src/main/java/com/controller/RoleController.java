package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bean.BaseRole;
import com.service.RoleService;
import com.utils.GridModel;
import com.utils.PageUtil;
import com.vo.RoleVO;
import com.vo.SessionConstants;
import com.vo.UserVO;

@Controller
@RequestMapping("/role")
public class RoleController {
	

	@Resource
	private RoleService roleService;
	
	@RequestMapping("/getAllRoleList")
	@ResponseBody
	public GridModel getAllRoleList(HttpServletRequest request, HttpServletResponse response,RoleVO vo) throws Exception{
			
		Map<String, String> map = PageUtil.putPageParam(request);
		
		GridModel result=roleService.getAllRoleList(map, vo);
		
		return result;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Map add(BaseRole vo,HttpServletRequest request){
		
		vo.setCreator((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			roleService.add(vo);
			map.put("success",true);
		} catch (Exception e) {
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
	}

	
	@RequestMapping("/edit")
	@ResponseBody
	public Map edit(BaseRole vo,HttpServletRequest request){
		
		vo.setModifier((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			roleService.edit(vo);
			map.put("success",true);
		} catch (Exception e) {
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	
	@RequestMapping("/remove")
	@ResponseBody
	public Map remove(RoleVO vo) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			roleService.remove(vo);
			map.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		
		return map;
	}
}
