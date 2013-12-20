package com.eaglec.plat;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.utils.Dao;
import com.eaglec.plat.utils.RandomUtils;

public class CreateOpenId {
	
	@Autowired
	private Dao dao;
	
	@Test
	public void createOpenId(){
		String sql = "select * from user where openID is null";
		try {
			List<User> users = dao.find(User.class, sql);
			for(User u : users){
				String updateSql = "update user set openID = '"+RandomUtils.generateMixString(10)
						+"' where id = "+u.getId();
				dao.update(updateSql);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
