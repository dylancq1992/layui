package com.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.BaseDistrictcCode;
import com.service.DistrictcCodeService;
import com.vo.DistrictcCodeVO;

@Controller
@RequestMapping("/districtc_code")
public class DistrictcCodeController {
	
	@Resource
	private DistrictcCodeService districtcCodeService;
	
	@RequestMapping("/getAllDistrictcCodeList")
	@ResponseBody
	public List<DistrictcCodeVO> getAllDistrictcCodeList(){
		
		List<DistrictcCodeVO> list=districtcCodeService.getAllDistrictcCodeList();
		
		return list;
		
	}
	

}
