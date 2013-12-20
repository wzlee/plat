package com.eaglec.plat.biz.user;

import java.util.List;

import com.eaglec.plat.domain.base.StaffMenu;


/**
 * @author cs
 *权限的crud
 */
public interface StaffMenuBiz {
	//添加权限
	public StaffMenu saveMenu(StaffMenu menu);
	//查询
	public List<StaffMenu> findAll(Integer type);
	//查询所有连接
	public List<String> findAllCode(Integer type);
	//根据主键ID列表查询
	public List<StaffMenu> findMenus(String menuids);
	//删除
	public void deleteRights(StaffMenu rights);
	

}
