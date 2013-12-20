package com.eaglec.plat.biz.impl.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.auth.RoleBiz;
import com.eaglec.plat.dao.auth.RoleDao;
import com.eaglec.plat.domain.auth.Role;
import com.eaglec.plat.view.JSONData;

@Service
public class RoleBizImpl implements RoleBiz {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public Role saveRole(Role roles) {
		// TODO Auto-generated method stub
		return roleDao.saveOrUpdate(roles);
	}

	@Override
	public List<Role> queryRole() {
		// TODO Auto-generated method stub
		return roleDao.findList("from Role");
	}

	@Override
	public void deleteRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.delete(role);
	}

	@Override
	public Role queryRoleByName(String rolename) {
		// TODO Auto-generated method stub
		return roleDao.get("from Role u where u.rolename='"+rolename+"'");
	}

	@Override
	public Role queryRoleById(Integer roleid) {
		// TODO Auto-generated method stub
		return roleDao.get(roleid);
	}

	@Override
	public void updateRoleRights(Integer roleid, List<Integer> deletemenutree,List<Integer> insertemenutree) {
		if(deletemenutree==null&&insertemenutree==null){
			roleDao.executeSql("delete from role_menu where Role_id="+roleid);
		}else{
			if(deletemenutree!=null&&deletemenutree.size()!=0){
				for(Integer deletemenuid:deletemenutree){
					roleDao.executeSql("DELETE FROM role_menu WHERE Role_id="+roleid+" AND menu_id="+deletemenuid);
				}
			}
			if(insertemenutree!=null&&insertemenutree.size()!=0){
				for(Integer insertmenuid:insertemenutree){
					roleDao.executeSql("insert into role_menu values("+roleid+","+insertmenuid+")");
				}
			}
		}
	}

	@Override
	public JSONData<Role> queryRoleByName(String rolename, int start, int limit) {
		String hql = "from Role where 1=1";
		if(!rolename.equals("")){
			hql += " and rolename like '%" + rolename + "%'";
		}
		return roleDao.outJSONData(hql, start, limit);
	}
	
}
	


