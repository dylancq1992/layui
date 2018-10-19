package com.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dao.BaseDAO;

/**
 * 
 * @classname @description $classname$ 
 * @author 01052932-PC
 * @date ($Date$ )
 */
@Service
public   class BaseService {
	protected Logger logger = Logger.getLogger(getClass());
	 
	@Resource(name = "baseDAO")
	private BaseDAO baseDao ;
	
	 
	
	public String getSequence(String sequence_name, int length, String pattern, boolean date){
		return baseDao.getSequence(sequence_name, length, pattern, date);
	}
}
