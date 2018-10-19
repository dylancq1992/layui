package com.dao;

import java.util.List;
import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bean.BaseMenu;

@Repository
public class MenuDao extends BaseDAO {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public List<BaseMenu> getAllMenuList() {
		String sql = "select * from menu_resource  where removed='0' order by priority ";
		List<BaseMenu> list = this.queryForList(sql, BaseMenu.class);
		return list;
	}

	public BaseMenu add(BaseMenu vo) {
		
		sqlSessionTemplate.insert("com.dao.MenuDao.add", vo);
		
		return vo;
	}

	
	public void remove(BaseMenu vo) {
		sqlSessionTemplate.delete("com.dao.MenuDao.remove", vo);
	}
	
	public void edit(BaseMenu vo) {
		
		sqlSessionTemplate.update("com.dao.MenuDao.edit", vo);
	}
}
