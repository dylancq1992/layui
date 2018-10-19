package com.service;

import java.util.List;
import java.util.Map;

import com.bean.BaseOrgan;
import com.utils.GridModel;
import com.vo.OrganVO;

public interface OrganService {
	
	public List<OrganVO> getAllOrganList();
	
	public List<BaseOrgan> getOrganByName(OrganVO vo);
	
	public BaseOrgan add(BaseOrgan vo);
	
	public void edit(OrganVO vo);
	
	public void remove(OrganVO vo);
	
	
	public GridModel getUserByOrganid(Map map,OrganVO vo);
	
	public void add_user(OrganVO vo);
	
	public void remove_user(OrganVO vo);

}
