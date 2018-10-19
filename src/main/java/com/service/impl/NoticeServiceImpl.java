package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bean.BaseNotice;
import com.bean.BaseNoticeAddress;
import com.dao.NoticeDao;
import com.service.NoticeService;
import com.utils.GridModel;
import com.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Resource
	private NoticeDao noticeDao;

	public GridModel getAllNoticeList(Map map, NoticeVO vo) {
		StringBuffer sb=new StringBuffer();
		
		sb.append("select noticeid,title,status,createdate,pubtime,modifydate from notice where validity='1' and creator='"+vo.getCreator()+"' order by createdate desc ");
		
		GridModel result = noticeDao.getPageForMySql(sb.toString(), BaseNotice.class, map);

		return result;
	}

	@Override
	public void add(NoticeVO vo) throws Exception {
		
		
		NoticeVO baseNotice=noticeDao.addNotice(vo);
		
		String address[]=vo.getAddress().split(",");
		
		List<BaseNoticeAddress> list=new ArrayList<BaseNoticeAddress>();
		
		for (String string : address) {
			BaseNoticeAddress bo=new BaseNoticeAddress();
			bo.setNoticeid(baseNotice.getNoticeid());
			bo.setAddresstype("1");
			bo.setAddressid(Long.valueOf(string));
			list.add(bo);
		}
		
		
		
		noticeDao.addNoticeAddress(list);
		
	}

}
