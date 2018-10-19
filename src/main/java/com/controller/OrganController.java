package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.BaseMenu;
import com.bean.BaseOrgan;
import com.service.OrganService;
import com.utils.GridModel;
import com.utils.PageUtil;
import com.vo.OrganVO;
import com.vo.SessionConstants;
import com.vo.UserVO;

@Controller
@RequestMapping("/organ")
public class OrganController {
	
	@Resource
	private OrganService organService;
	
	@RequestMapping("/getAllOrganList")
	@ResponseBody
	public List<OrganVO> getAllOrganList() throws Exception {

		List<OrganVO> list = organService.getAllOrganList();

		return list;
	}
	
	
	@RequestMapping("/getOrganByName")
	@ResponseBody
	public List<BaseOrgan> getOrganByName(OrganVO vo) throws Exception {

		List<BaseOrgan> list = organService.getOrganByName(vo);

		return list;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request,BaseOrgan vo){
		Map<String, Object> map = new HashMap<String, Object>();
		vo.setCreator((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		try {
			BaseOrgan bo=organService.add(vo);
			map.put("success",true);
			map.put("data", bo);
		} catch (Exception e) {
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
	}

	
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String, Object> edit(HttpServletRequest request,OrganVO vo){
		Map<String, Object> map = new HashMap<String, Object>();
		vo.setModifier((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		try {
			organService.edit(vo);
			map.put("success",true);
			map.put("data", vo);
		} catch (Exception e) {
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public Map<String, Object> remove(HttpServletRequest request,OrganVO vo){
		Map<String, Object> map = new HashMap<String, Object>();
		vo.setModifier((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		try {
			organService.remove(vo);
			map.put("success",true);
		} catch (Exception e) {
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("/getUserByOrganid")
	@ResponseBody
	public GridModel getUserByOrganid(HttpServletRequest request,OrganVO vo) throws Exception{
		
		vo.setCreator((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
			
		Map<String, String> map = PageUtil.putPageParam(request);
		
		GridModel result=organService.getUserByOrganid(map,vo);
		
		return result;
	}
	
	@RequestMapping("/add_user")
	@ResponseBody
	public Map<String, Object> add_user(HttpServletRequest request,OrganVO vo){
		Map<String, Object> map = new HashMap<String, Object>();
		vo.setModifier((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		try {
			organService.add_user(vo);
			map.put("success",true);
		} catch (Exception e) {
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	
	@RequestMapping("/remove_user")
	@ResponseBody
	public Map<String, Object> remove_user(HttpServletRequest request,OrganVO vo){
		Map<String, Object> map = new HashMap<String, Object>();
		vo.setModifier((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		try {
			organService.remove_user(vo);
			map.put("success",true);
		} catch (Exception e) {
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
	}
}
