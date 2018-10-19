package com.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.bean.BaseOrgan;
import com.bean.BaseUser;
import com.dao.OrganDao;
import com.service.OrganService;
import com.utils.GridModel;
import com.vo.OrganVO;

@Service
public class OrganServiceImpl implements OrganService {

	@Resource
	private OrganDao organDao;

	public List<OrganVO> getAllOrganList() {

		String sql = "select *,(case when levels ='2' then 'closed' else 'open' end) state  from organ where validity='1' ";

		List<OrganVO> list = organDao.queryForList(sql, OrganVO.class);

		return list;
	}

	public List<BaseOrgan> getOrganByName(OrganVO vo) {

		String sql = "select *  from organ where validity='1' ";

		if (StringUtils.isNotBlank(vo.getQ())) {
			sql += " and name like '%" + vo.getQ() + "%'";
		}

		List<BaseOrgan> list = organDao.queryForList(sql, BaseOrgan.class);

		return list;
	}

	public BaseOrgan add(BaseOrgan vo) {

		vo.setCreatedate(new Date());
		vo.setValidity("1");
		vo.setOrgan_type("1");

		return organDao.add(vo);
	}

	public void edit(OrganVO vo) {
		vo.setModifydate(new Date());
		organDao.edit(vo);
	}

	public void remove(OrganVO vo) {
		String sql = "update organ set validity='0',modifydate=sysdate(),modifier='"
				+ vo.getModifier()
				+ "' where organid  ='"
				+ vo.getOrganid()
				+ "'";

		organDao.getJt().update(sql);

	}

	public GridModel getUserByOrganid(Map map, OrganVO vo) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT userid, loginname,user_type, name, sex, telephone, address, mobile_phone, fax, email, ");
		sb.append(" qq, status, organid, bindip, validity,modifier,createdate,creator,modifydate FROM user where validity = '1' and  organid is null ");
		// 超级管理员用户查看所有
		if (!"-1".equals(vo.getCreator())) {
			sb.append(" and creator='" + vo.getCreator() + "'");
		}
		GridModel result = null;
		try {
			result = organDao.getPageForMySql(sb.toString(), BaseUser.class,
					map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void add_user(OrganVO vo) {
		StringBuffer sb = new StringBuffer();
		sb.append("update user set organid='" + vo.getOrganid()
				+ "' ,modifydate=sysdate(),modifier='" + vo.getModifier()
				+ "' where userid in ('" + vo.getIds() + "')");
		organDao.getJt().update(sb.toString());
	}

	public void remove_user(OrganVO vo) {
		StringBuffer sb = new StringBuffer();
		sb.append("update user set organid=null ,modifydate=sysdate(),modifier='"
				+ vo.getModifier() + "' where userid in ('" + vo.getIds()
				+ "')");
		organDao.getJt().update(sb.toString());

	}

}
