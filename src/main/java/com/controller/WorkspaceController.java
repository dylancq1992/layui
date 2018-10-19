package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bean.BaseMenu;
import com.bean.BaseNotice;
import com.bean.BaseUser;
import com.service.MenuService;
import com.service.UserService;
import com.vo.ChartBarVO;
import com.vo.MenuVO;
import com.vo.SessionConstants;
import com.vo.UserVO;

@Controller
@RequestMapping("/workspace")
public class WorkspaceController {
	
	@Resource
	private MenuService menuService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/getToLeft")
	@ResponseBody
	public Map getToLeft(HttpServletRequest request,MenuVO vo){
		
		BaseUser baseUser=(BaseUser) request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR);
		
		List<BaseMenu> listAll=menuService.getMenuListByPid(vo.getMenuid(), baseUser.getUserid());
		
		List<BaseMenu> secondList=new ArrayList<BaseMenu>();
		List<BaseMenu> thirdList=new ArrayList<BaseMenu>();
		for (BaseMenu baseMenu : listAll) {
			//二级菜单
			if("3".equals(baseMenu.getLevels())){
				secondList.add(baseMenu);
			//三级菜单
			}else if("4".equals(baseMenu.getLevels())){
				thirdList.add(baseMenu);
			}else{
				
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("success",true);
		map.put("secondList",secondList);
		map.put("thirdList",thirdList);
		
		return map;
	}
	
	
	@RequestMapping("/doCenter")
	public String doCenter(MenuVO vo){
		
		
		return vo.getUrl();
	}
	
	@RequestMapping("/updatepwd")
	@ResponseBody
	public Map updatepwd(UserVO vo,HttpServletRequest request){
		vo.setUserid(Long.valueOf((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID)));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userService.updatepwd(vo);
			map.put("success",true);
		} catch (Exception e) {
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
		
	}
	
	
	@RequestMapping("/getLoginCount")
	@ResponseBody
	public Map getLoginCount(UserVO vo){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ChartBarVO chatVO=userService.getLoginCount(vo);
			map.put("success",true);
			map.put("data", chatVO);
		} catch (Exception e) {
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
		
	}
	
	@RequestMapping("/getNotice")
	@ResponseBody
	public Map getNotice(UserVO vo,HttpServletRequest request){
		vo.setUserid(Long.valueOf((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID)));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<BaseNotice> list=userService.getNotice(vo);
			map.put("success",true);
			map.put("data", list);
		} catch (Exception e) {
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
		
	}
	

}
