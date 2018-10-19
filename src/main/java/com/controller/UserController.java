package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.BaseMenu;
import com.bean.BaseUser;
import com.service.UserService;
import com.utils.GridModel;
import com.utils.JSONUtil;
import com.utils.PageUtil;
import com.vo.SessionConstants;
import com.vo.UserVO;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/getAllUserList")
	@ResponseBody
	public GridModel getAllUserList(HttpServletRequest request, HttpServletResponse response,UserVO vo) throws Exception{
		
		vo.setCreator((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
			
		Map<String, String> map = PageUtil.putPageParam(request);
		
		GridModel result=userService.getAllMenuList(map, vo);
		
		return result;
	}
	
	@RequestMapping("/getUserByOrgan")
	@ResponseBody
	public GridModel getUserByOrgan(HttpServletRequest request,UserVO vo) throws Exception{
			
		Map<String, String> map = PageUtil.putPageParam(request);
		
		GridModel result=userService.getUserByOrgan(map,vo);
		
		return result;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Map addUser(BaseUser vo,HttpServletRequest request){
		
		vo.setCreator((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userService.add(vo);
			map.put("success",true);
		} catch (Exception e) {
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
		
	}
	
	
	@RequestMapping("/edit")
	@ResponseBody
	public Map editUser(UserVO vo,HttpServletRequest request){
		
		vo.setModifier((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userService.edit(vo);
			map.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
	}

	
	@RequestMapping("/remove")
	@ResponseBody
	public Map remove(UserVO vo,HttpServletRequest request) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		vo.setModifier((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		try {
			userService.remove(vo);
			map.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		/*String json = JSONUtil.Encode(map);
		PrintWriter writer=response.getWriter();
		response.getWriter().print(json);
		writer.flush();
		writer.close();*/
		
		return map;
	}
	
	

	@RequestMapping("/doCancel")
	@ResponseBody
	public Map doCancel(UserVO vo,HttpServletRequest request) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		vo.setModifier((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		try {
			userService.doCancel(vo);
			map.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		/*String json = JSONUtil.Encode(map);
		PrintWriter writer=response.getWriter();
		response.getWriter().print(json);
		writer.flush();
		writer.close();*/
		
		return map;
	}
	
	
	@RequestMapping("/getMenuUserList")
	@ResponseBody
	public GridModel getMenuUserList(HttpServletRequest request, UserVO vo) throws Exception{
		
		vo.setCreator((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
			
		Map<String, String> map = PageUtil.putPageParam(request);
		
		GridModel result=userService.getMenuUserList(map, vo);
		
		return result;
	}
}
