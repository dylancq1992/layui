package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.BaseMenu;
import com.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Resource
	private MenuService menuService;

	
	@RequestMapping("/getAllMenuList")
	@ResponseBody
	public List<BaseMenu> getAllMenuList1() throws Exception {

		List<BaseMenu> list = menuService.getAllMenuList();

		return list;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> addMenu(BaseMenu vo){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BaseMenu bo=menuService.add(vo);
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
	public Map<String, Object> editMenu(BaseMenu vo){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			menuService.edit(vo);
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
	public String remove(BaseMenu vo){
		menuService.remove(vo);
		return "{\"success\":true}";
	}
	
	
	
}
