package com.eaglec.plat.biz.auth;

import java.util.List;

import com.eaglec.plat.domain.auth.Role;
import com.eaglec.plat.view.JSONData;

/**
 * @author cs
 *角色crud
 */
public interface RoleBiz {
	//添加
	public Role saveRole(Role roles);
	//查询所有
	public List<Role> queryRole();
	//根据角色名
	public Role queryRoleByName(String rolename);
	//根据角色ID
	public Role queryRoleById(Integer roleid);
	//删除
	public void deleteRole(Role role);
	//修改权限角色关联表
	public void updateRoleRights(Integer roleid,List<Integer> deletemenutree,List<Integer> insertemenutree);
	
	public JSONData<Role> queryRoleByName(String rolename,int start,int limit);
}
