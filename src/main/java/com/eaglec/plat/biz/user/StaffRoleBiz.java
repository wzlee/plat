package com.eaglec.plat.biz.user;

import java.util.List;

import com.eaglec.plat.domain.base.StaffRole;
import com.eaglec.plat.view.JSONData;

/**
 * @author cs
 *角色crud
 */
public interface StaffRoleBiz {
	//添加
	public StaffRole saveRole(StaffRole roles);
	//查询所有
	public List<StaffRole> queryRole(Integer type);
	//根据角色名
	public StaffRole queryRoleByName(String rolename,Integer type);
	//根据角色ID
	public StaffRole queryRoleById(Integer roleid);
	//删除
	public void deleteRole(StaffRole role);
	//修改权限角色关联表
	public void updateRoleRights(Integer roleid,List<Integer> deletemenutree,List<Integer> insertemenutree);
	
	public JSONData<StaffRole> queryRoleByName(String rolename,Integer type,int start,int limit);
}
