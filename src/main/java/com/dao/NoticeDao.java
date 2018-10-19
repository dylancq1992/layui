package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bean.BaseNotice;
import com.bean.BaseNoticeAddress;
import com.vo.NoticeVO;

@Repository
public class NoticeDao extends BaseDAO{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public NoticeVO addNotice(NoticeVO vo){
		
		sqlSessionTemplate.insert("com.dao.NoticeDao.addNotice",vo);
		
		return vo;
		
	}
	
	public void addNoticeAddress(List<BaseNoticeAddress> list){
		
		sqlSessionTemplate.insert("com.dao.NoticeDao.addNoticeAddress",list);
		
	}
	

}
