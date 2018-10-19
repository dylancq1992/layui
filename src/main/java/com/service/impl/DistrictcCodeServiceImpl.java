package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.DistrictcCodeDao;
import com.service.DistrictcCodeService;
import com.vo.DistrictcCodeVO;

@Service
public class DistrictcCodeServiceImpl  implements DistrictcCodeService {
	
	@Resource
	private DistrictcCodeDao districtcCodeDao;

	public List<DistrictcCodeVO> getAllDistrictcCodeList() {
		String sql="select *,(case when levels ='B' then 'closed' else 'open' end) state from districtc_code ";
		return districtcCodeDao.queryForList(sql, DistrictcCodeVO.class);
	}

}
