package com.service;

import java.util.Map;

import com.utils.GridModel;
import com.vo.NoticeVO;

public interface NoticeService {
	
	public GridModel getAllNoticeList(Map map,NoticeVO vo) ;
	
	public void add(NoticeVO vo) throws Exception;

}
