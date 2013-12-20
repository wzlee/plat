package com.eaglec.plat.biz.auth;

import java.util.List;

import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.view.FlatManagerView;
import com.eaglec.plat.view.JSONData;


/**
 * @author cs
 *用户的CRUD
 */
public interface ManagerBiz {
	//添加
	public Manager saveManager(Manager user);
	//查询
	public List<Manager> findUser();
	//根据用户名查询
	public Manager findUserByUsername(String username);
	//根据用户Id
	public Manager findUserById(Integer id);
	//根据roleid 查询用户
	public List<Manager> findUserByRoleId(Integer roleid);
	//删除
	public void deleteUser(Manager user);
	//模糊查询
	public JSONData<Manager> queryManagerByName(String username,int start,int limit);
	//根据平台id来查询用户
	public JSONData<FlatManagerView> findByFlatId(Integer flatId, Integer userStatus, Integer roleId, 
			int start, int limit);
	//
	public JSONData<Manager> findByFlatName(String flatName, int start, int limit);
	
}
