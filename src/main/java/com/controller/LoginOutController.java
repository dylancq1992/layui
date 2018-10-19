package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loginout")
public class LoginOutController {
	
	@RequestMapping("/doLoginOut")
	public String doLoginOut(HttpServletRequest request,HttpServletResponse response){
		
		request.getSession().invalidate();
		
		return "login";
	}

}
