package com.eaglec.plat.biz.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.user.StaffRoleBiz;
import com.eaglec.plat.dao.user.StaffRoleDao;
import com.eaglec.plat.domain.base.StaffRole;
import com.eaglec.plat.view.JSONData;

@Service
public class StaffRoleBizImpl implements StaffRoleBiz {
	
	@Autowired
	private StaffRoleDao roleDao;

	@Override
	public StaffRole saveRole(StaffRole roles) {
		// TODO Auto-generated method stub
		return roleDao.saveOrUpdate(roles);
	}

	@Override
	public List<StaffRole> queryRole(Integer type ) {
		// TODO Auto-generated method stub
		return roleDao.findList("from StaffRole where roleType="+type);
	}

	@Override
	public void deleteRole(StaffRole role) {
		// TODO Auto-generated method stub
		roleDao.delete(role);
	}

	@Override
	public StaffRole queryRoleByName(String rolename,Integer type) {
		// TODO Auto-generated method stub
		return roleDao.get("from StaffRole u where u.roleType="+type+" and u.rolename='"+rolename+"'");
	}

	@Override
	public StaffRole queryRoleById(Integer roleid) {
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
	public JSONData<StaffRole> queryRoleByName(String rolename, Integer type,int start, int limit) {
		String hql = "from StaffRole s where 1=1 and s.roleType="+type;
		if(!rolename.equals("")){
			hql += " and s.rolename like '%" + rolename + "%'";
		}
		return roleDao.outJSONData(hql, start, limit);
	}
	
}
	


