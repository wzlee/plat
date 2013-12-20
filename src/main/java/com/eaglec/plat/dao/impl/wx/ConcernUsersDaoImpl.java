package com.eaglec.plat.dao.impl.wx;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.wx.ConcernUsersDao;
import com.eaglec.plat.domain.wx.ConcernUsers;

@Repository
public class ConcernUsersDaoImpl extends BaseDaoImpl<ConcernUsers> implements ConcernUsersDao{

	@Override
	public List<ConcernUsers> getConcernUsers(Integer page, Integer limit) {
		return null;
	}
}
