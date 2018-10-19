package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bean.BaseLoginLogs;
import com.dao.LoginLogsDao;
import com.service.LoginLogsService;

@Service
public class LoginLogsServiceImpl implements LoginLogsService {
	
	@Resource
	private LoginLogsDao loginLogsDao;

	public void saveLogs(BaseLoginLogs vo) {
		
		loginLogsDao.saveLogs(vo);
		
	}

}
