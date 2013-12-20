package com.eaglec.plat.biz.impl.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eaglec.plat.biz.auth.ManagerBiz;
import com.eaglec.plat.dao.auth.ManagerDao;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.view.FlatManagerView;
import com.eaglec.plat.view.JSONData;

@Service
public class ManagerBizImpl implements ManagerBiz {
	
	@Autowired
	private ManagerDao managerDao;
	
	//添加
	public Manager saveManager(Manager manager){
		return managerDao.saveOrUpdate(manager);
	}
	//查询
	public List<Manager> findUser(){
		return managerDao.findList("from Manager order by registerTime");
	}
	//删除
	public void deleteUser(Manager user){
		managerDao.delete(user);
	}
	@Override
	public Manager findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return managerDao.get("from Manager u where u.username='"+username+"'");
	}
	@Override
	public List<Manager> findUserByRoleId(Integer roleid) {
		// TODO Auto-generated method stub
		return managerDao.findList("from Manager u where u.role.id="+roleid);
	}
	@Override
	public Manager findUserById(Integer id) {
		// TODO Auto-generated method stub
		return managerDao.get(id);
	}
	@Override
	public JSONData<Manager> queryManagerByName(String username, int start,int limit) {
		String hql = "from Manager where 1=1";
		if(username != null && !username.isEmpty()){
			hql += " and username like '%" + username + "%'";
		}
		hql += " order by registerTime";
		return managerDao.outJSONData(hql, start, limit);
	}
	
	/**
	 * 通过子窗口平台id查找管理员
	 * @author liuliping
	 * @since 2013-10-15
	 * @param flatId 平台id
	 * @param userStatus 用户状态
	 * @param roleId 用户角色id
	 * @return jd JSONData
	 * */
	@Override
	public JSONData<FlatManagerView> findByFlatId(Integer flatId, Integer userStatus, Integer roleId, int start, int limit) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT new com.eaglec.plat.view.FlatManagerView(m, (SELECT flatName FROM Flat f WHERE f.id = ");
		if ((flatId != null) && (flatId.intValue() != 0)) {
			sb.append(flatId).append(")) FROM Manager m WHERE m.flatId = ").append(flatId);
		} else {
			sb.append("m.flatId)) FROM Manager m WHERE m.flatId IS NOT NULL");
		}
		
		if ((userStatus != null) && (userStatus.intValue() != 0)) {    //用户状态
			sb.append(" AND m.userStatus = ").append(userStatus);
		}
		
		if ((roleId != null) && (roleId.intValue() != 0)) {
			sb.append(" AND m.role.id = ").append(roleId);
		}
		return managerDao.findObjects(sb.toString(), start, limit);
	}
	
	/**
	 * 通过窗口平台名称查找管理员
	 * @author liuliping
	 * @since 2013-10-15
	 * @param flatName 平台名称
	 * @return jd JSONData
	 * */
	@Override
	public JSONData<Manager> findByFlatName(String flatName, int start,
			int limit) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM Manager WHERE 1=1 ");
		if (!StringUtils.isEmpty(flatName)) {
			sb.append("AND flatName like '%").append(flatName).append("%'");
		} 
		return managerDao.outJSONData(sb.toString(), start, limit);
	}
	

}
