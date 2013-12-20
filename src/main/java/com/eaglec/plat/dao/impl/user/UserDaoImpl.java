package com.eaglec.plat.dao.impl.user;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.dao.user.UserDao;
import com.eaglec.plat.domain.base.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	@Override
	public int add(User o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.add(o);
	}
	
	@Override
	public User save(User o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o);
	}
	
	@Override
	public Map<String, Object> save(User o, Object obj) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o, obj);
	}
	
	@Override
	public User saveOrUpdate(User o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.saveOrUpdate(o);
	}
	
	@Override
	public User update(User o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.update(o);
	}
	
}
